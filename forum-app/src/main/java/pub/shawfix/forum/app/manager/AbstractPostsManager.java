package pub.shawfix.forum.app.manager;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import pub.shawfix.forum.api.model.PageResponseModel;
import pub.shawfix.forum.api.request.AdminBooleanRequest;
import pub.shawfix.forum.api.vo.PostsVO;
import pub.shawfix.forum.app.support.IsLogin;
import pub.shawfix.forum.app.support.LoginUserContext;
import pub.shawfix.forum.app.transfer.PostsTransfer;
import pub.shawfix.forum.common.enums.AuditStateEn;
import pub.shawfix.forum.common.enums.ErrorCodeEn;
import pub.shawfix.forum.common.enums.UserRoleEn;
import pub.shawfix.forum.common.model.PageResult;
import pub.shawfix.forum.common.support.CheckUtil;
import pub.shawfix.forum.common.support.EventBus;
import pub.shawfix.forum.common.support.SafesUtil;
import pub.shawfix.forum.domain.entity.BasePosts;
import pub.shawfix.forum.domain.entity.Comment;
import pub.shawfix.forum.domain.entity.Posts;
import pub.shawfix.forum.domain.entity.Tag;
import pub.shawfix.forum.domain.repository.CommentRepository;
import pub.shawfix.forum.domain.repository.PostsRepository;
import pub.shawfix.forum.domain.repository.TagRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author shawfix
 * @create 2025/5/30 11:28
 * @desc
 **/
public abstract class AbstractPostsManager {

    @Resource
    CommentRepository commentRepository;

    @Resource
    PostsRepository postsRepository;

    @Resource
    TagRepository tagRepository;

    Set<Tag> checkTags(Set<Long> tagIds) {
        List<Tag> tags = tagRepository.queryByIds(tagIds);
        Set<Tag> selectTags = SafesUtil.ofList(tags).stream()
                .filter(tag -> AuditStateEn.PASS.equals(tag.getAuditState()))
                .collect(Collectors.toSet());
        CheckUtil.isEmpty(selectTags, ErrorCodeEn.TAG_NOT_EMPTY);

        return selectTags;
    }

    PageResponseModel<PostsVO> pagePostsVO(PageResult<Posts> pageResult) {
        Set<Long> solutionIds = SafesUtil.ofList(pageResult.getList()).stream()
                .filter(posts -> !ObjectUtils.isEmpty(posts.getSolutionId()) && posts.getSolutionId() != 0L)
                .map(Posts::getSolutionId).collect(Collectors.toSet());

        if (ObjectUtils.isEmpty(solutionIds)) {
            return PageResponseModel.build(pageResult.getTotal(), pageResult.getSize(), PostsTransfer.toPostsVOs(pageResult.getList(), new ArrayList<>()));
        }

        List<Comment> comments = commentRepository.queryInIds(solutionIds);
        if (ObjectUtils.isEmpty(comments)) {
            return PageResponseModel.build(pageResult.getTotal(), pageResult.getSize(), PostsTransfer.toPostsVOs(pageResult.getList(), new ArrayList<>()));
        }

        return PageResponseModel.build(pageResult.getTotal(), pageResult.getSize(), PostsTransfer.toPostsVOs(pageResult.getList(), comments));
    }

    @IsLogin
    @Transactional
    public void delete(Long id) {
        BasePosts basePosts = postsRepository.get(id);
        CheckUtil.isEmpty(basePosts, ErrorCodeEn.POSTS_NOT_EXIST);
        CheckUtil.isFalse(LoginUserContext.getUser().getId().equals(basePosts.getAuthorId()), ErrorCodeEn.POSTS_NOT_EXIST);

        tagRepository.deletePostsMapping(id);
        commentRepository.deleteByPostsId(id);
        postsRepository.delete(id);
        EventBus.emit(EventBus.Topic.POSTS_DELETE, basePosts);
    }

    @IsLogin(role = UserRoleEn.ADMIN)
    public void auditState(AdminBooleanRequest booleanRequest) {
        BasePosts basePosts = postsRepository.get(booleanRequest.getId());
        CheckUtil.isEmpty(basePosts, ErrorCodeEn.ARTICLE_NOT_EXIST);

        basePosts.setAuditState(booleanRequest.getIs() ? AuditStateEn.PASS : AuditStateEn.REJECT);
        postsRepository.update(basePosts);
    }
}
