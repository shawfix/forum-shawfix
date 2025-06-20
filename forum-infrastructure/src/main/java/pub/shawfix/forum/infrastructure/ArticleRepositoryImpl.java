package pub.shawfix.forum.infrastructure;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import pub.shawfix.forum.common.model.PageResult;
import pub.shawfix.forum.common.support.SafesUtil;
import pub.shawfix.forum.domain.entity.Article;
import pub.shawfix.forum.domain.entity.ArticleType;
import pub.shawfix.forum.domain.entity.Tag;
import pub.shawfix.forum.domain.entity.User;
import pub.shawfix.forum.domain.entity.value.PostsPageQueryValue;
import pub.shawfix.forum.domain.repository.ArticleRepository;
import pub.shawfix.forum.infrastructure.dal.dao.*;
import pub.shawfix.forum.infrastructure.dal.dataobject.PostsDO;
import pub.shawfix.forum.infrastructure.dal.dataobject.TagPostsMappingDO;
import pub.shawfix.forum.infrastructure.dal.dataobject.UserDO;
import pub.shawfix.forum.infrastructure.transfer.ArticleTypeTransfer;
import pub.shawfix.forum.infrastructure.transfer.PostsTransfer;
import pub.shawfix.forum.infrastructure.transfer.TagTransfer;
import pub.shawfix.forum.infrastructure.transfer.UserTransfer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author shawfix
 * @create 2025/5/30 15:56
 * @desc
 **/
@Repository
public class ArticleRepositoryImpl implements ArticleRepository {

    @Resource
    private ArticleTypeDAO articleTypeDAO;

    @Resource
    private PostsDAO postsDAO;

    @Resource
    private TagDAO tagDAO;

    @Resource
    private TagPostsMappingDAO tagPostsMappingDAO;

    @Resource
    private UserDAO userDAO;

    @Override
    public void save(Article article) {
        PostsDO postsDO = PostsTransfer.toPostsDO(article);
        postsDO.setCreateAt(new Date());

        postsDAO.insert(postsDO);

        tagPostsMappingDAO.batchInsert(SafesUtil.ofSet(article.getTags())
                .stream()
                .map(tag -> {
                    TagPostsMappingDO tagPostsMappingDO = TagPostsMappingDO.builder()
                            .tagId(tag.getId())
                            .postsId(postsDO.getId())
                            .build();
                    tagPostsMappingDO.initBase();
                    return tagPostsMappingDO;
                })
                .collect(Collectors.toList()));
        article.setId(postsDO.getId());
    }

    @Override
    public Article get(Long id) {
        PostsDO postsDO = postsDAO.get(id);
        if (ObjectUtils.isEmpty(postsDO)) {
            return null;
        }

        UserDO userDO = userDAO.get(postsDO.getAuthorId());
        if (ObjectUtils.isEmpty(userDO)) {
            return null;
        }

        User user = UserTransfer.toUser(userDO);
        ArticleType articleType = ArticleTypeTransfer.toArticleType(articleTypeDAO.get(postsDO.getTypeId()));
        List<TagPostsMappingDO> tagPostsMappingDOS = tagPostsMappingDAO.query(TagPostsMappingDO.builder()
                .postsId(id)
                .build());
        Set<Long> tagIds = SafesUtil.ofList(tagPostsMappingDOS)
                .stream()
                .map(TagPostsMappingDO::getTagId)
                .collect(Collectors.toSet());
        if (ObjectUtils.isEmpty(tagIds)) {
            return PostsTransfer.toArticle(postsDO, user, articleType, new ArrayList<>());
        }

        List<Tag> tags = TagTransfer.toTags(tagDAO.queryInIds(tagIds));

        return PostsTransfer.toArticle(postsDO, user, articleType, tags);
    }

    @Override
    public void update(Article article) {
        postsDAO.update(PostsTransfer.toPostsDO(article));

        List<TagPostsMappingDO> tagPostsMappingDOS = SafesUtil.ofSet(article.getTags())
                .stream()
                .map(tag -> {
                    TagPostsMappingDO tagPostsMappingDO = TagPostsMappingDO.builder()
                            .postsId(article.getId())
                            .tagId(tag.getId())
                            .build();
                    tagPostsMappingDO.initBase();
                    return tagPostsMappingDO;
                })
                .collect(Collectors.toList());
        tagPostsMappingDAO.batchInsert(tagPostsMappingDOS);
    }

    @Override
    public PageResult<Article> page(Integer pageNo, Integer pageSize, PostsPageQueryValue pageQueryValue) {
        PageHelper.startPage(pageNo, pageSize);

        List<PostsDO> postsDOS = postsDAO.queryByValue(pageQueryValue);
        PageInfo<PostsDO> pageInfo = new PageInfo<PostsDO>();

        if (ObjectUtils.isEmpty(postsDOS)) {
            return PageResult.build(pageInfo.getTotal(), pageInfo.getSize(), new ArrayList<>());
        }

        Set<Long> userIds = SafesUtil.ofList(postsDOS)
                .stream()
                .map(PostsDO::getAuthorId)
                .collect(Collectors.toSet());
        List<User> users = UserTransfer.toUsers(userDAO.queryInIds(userIds));

        Set<Long> postsIds = SafesUtil.ofList(postsDOS)
                .stream()
                .map(PostsDO::getId)
                .collect(Collectors.toSet());
        List<TagPostsMappingDO> tagPostsMappingDOS = tagPostsMappingDAO.queryInPostsIds(postsIds);

        if (ObjectUtils.isEmpty(tagPostsMappingDOS)) {
            List<Article> articles = PostsTransfer.toArticles(postsDOS, users, tagPostsMappingDOS, new ArrayList<>());
            return PageResult.build(pageInfo.getTotal(), pageInfo.getSize(), articles);
        }

        Set<Long> tagIds = SafesUtil.ofList(tagPostsMappingDOS)
                .stream()
                .map(TagPostsMappingDO::getTagId)
                .collect(Collectors.toSet());
        List<Tag> tags = TagTransfer.toTags(tagDAO.queryInIds(tagIds));
        List<Article> articles = PostsTransfer.toArticles(postsDOS, users, tagPostsMappingDOS, tags);

        return PageResult.build(pageInfo.getTotal(), pageInfo.getSize(), articles);
    }
}
