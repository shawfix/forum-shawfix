package pub.shawfix.forum.domain.repository;

import pub.shawfix.forum.domain.entity.BasePosts;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author shawfix
 * @create 2025/5/30 11:47
 * @desc
 **/
public interface PostsRepository {
    BasePosts get(Long id);

    List<BasePosts> queryInIds(Set<Long> postsIds);

    List<Long> getAllIdByAuthorId(Long authorId);

    void increaseComments(Long id, Date updateAt);

    void decreaseComments(Long id, Date updateAt);

    void increaseViews(Long id, Date updateAt);

    void increaseApproval(Long id, Date updateAt);

    void decreaseApproval(Long id, Date updateAt);

    void delete(Long id);

    void update(BasePosts basePosts);
}
