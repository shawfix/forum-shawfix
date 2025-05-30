package pub.shawfix.forum.infrastructure;

import com.github.pagehelper.PageInfo;
import org.springframework.util.ObjectUtils;
import pub.shawfix.forum.common.enums.AuditStateEn;
import pub.shawfix.forum.common.model.PageResult;
import pub.shawfix.forum.common.support.SafesUtil;
import pub.shawfix.forum.domain.entity.Posts;
import pub.shawfix.forum.domain.entity.Tag;
import pub.shawfix.forum.domain.entity.User;
import pub.shawfix.forum.infrastructure.dal.dao.PostsDAO;
import pub.shawfix.forum.infrastructure.dal.dao.TagDAO;
import pub.shawfix.forum.infrastructure.dal.dao.TagPostsMappingDAO;
import pub.shawfix.forum.infrastructure.dal.dao.UserDAO;
import pub.shawfix.forum.infrastructure.dal.dataobject.PostsDO;
import pub.shawfix.forum.infrastructure.dal.dataobject.TagPostsMappingDO;
import pub.shawfix.forum.infrastructure.transfer.PostsTransfer;
import pub.shawfix.forum.infrastructure.transfer.TagTransfer;
import pub.shawfix.forum.infrastructure.transfer.UserTransfer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author shawfix
 * @create 2025/5/30 14:38
 * @desc
 **/
public abstract class AbstractPostsRepository {
    @Resource
    PostsDAO postsDAO;

    @Resource
    TagDAO tagDAO;

    @Resource
    TagPostsMappingDAO tagPostsMappingDAO;

    @Resource
    UserDAO userDAO;

    public PageResult<Posts> basePagePosts(List<Long> postsIds, PageInfo pageInfo, AuditStateEn auditStateEn) {
        List<PostsDO> queryPostsDOS;
        if (ObjectUtils.isEmpty(auditStateEn)) {
            queryPostsDOS = postsDAO.queryInIds(new HashSet<>(postsIds));
        } else {
            queryPostsDOS = postsDAO.queryInIdsAndState(new HashSet<>(postsIds), auditStateEn.getValue());
        }

        if (ObjectUtils.isEmpty(queryPostsDOS)) {
            return PageResult.build(pageInfo.getTotal(), pageInfo.getSize(), new ArrayList<>());
        }

        // 按 postsIds 顺序排序
        List<PostsDO> postsDOS = postsIds.stream().map(postsId -> {
            for (PostsDO postsDO : queryPostsDOS) {
                if (postsDO.getId().equals(postsId)) {
                    return postsDO;
                }
            }
            return null;
        }).filter(postsDO -> !ObjectUtils.isEmpty(postsDO)).collect(Collectors.toList());

        Set<Long> userIds = SafesUtil.ofList(postsDOS).stream().map(PostsDO::getAuthorId).collect(Collectors.toSet());
        List<User> users = UserTransfer.toUsers(userDAO.queryInIds(userIds));

        List<TagPostsMappingDO> tagPostsMappingDOList = tagPostsMappingDAO.queryInPostsIds(new HashSet<>(postsIds));
        if (ObjectUtils.isEmpty(tagPostsMappingDOList)) {
            return PageResult.build(pageInfo.getTotal(), pageInfo.getSize(), PostsTransfer.toPostsList(postsDOS, users, tagPostsMappingDOList, new ArrayList<>()));
        }

        Set<Long> tagIds = SafesUtil.ofList(tagPostsMappingDOList).stream().map(TagPostsMappingDO::getTagId).collect(Collectors.toSet());
        List<Tag> tags = TagTransfer.toTags(tagDAO.queryInIds(tagIds));

        return PageResult.build(pageInfo.getTotal(), pageInfo.getSize(), PostsTransfer.toPostsList(postsDOS, users, tagPostsMappingDOList, tags));
    }
}
