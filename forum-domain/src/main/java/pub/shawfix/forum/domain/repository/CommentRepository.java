package pub.shawfix.forum.domain.repository;

import pub.shawfix.forum.common.model.PageResult;
import pub.shawfix.forum.domain.entity.Comment;

import java.util.List;
import java.util.Set;

/**
 * @author shawfix
 * @create 2025/5/30 17:01
 * @desc
 **/
public interface CommentRepository {
    void save(Comment comment);

    Comment get(Long id);

    List<Comment> queryByPostsId(Long postsId);

    List<Comment> queryInReplyIds(Set<Long> replyIds);

    PageResult<Comment> page(Integer pageNo, Integer pageSize, Long postsId);

    void deleteByPostsId(Long postsId);

    List<Comment> queryInIds(Set<Long> ids);
}
