package pub.shawfix.forum.infrastructure.dal.dao;

import org.apache.ibatis.annotations.Param;
import pub.shawfix.forum.infrastructure.dal.dataobject.CommentDO;

import java.util.List;
import java.util.Set;

/**
 * @author shawfix
 * @create 2025/5/30 17:03
 * @desc
 **/
public interface CommentDAO {
    CommentDO findByPrimaryKey(@Param("id") Long id);

    List<CommentDO> findByIds(@Param("ids") Set<Long> ids);

    void insert(CommentDO commentDO);

    void update(CommentDO commentDO);

    List<CommentDO> queryByPostsId(@Param("postsId") Long postsId);

    List<CommentDO> queryInReplyIds(@Param("commentIds") List<Long> commentIds);

    void deleteByPostsId(@Param("postsId") Long id);

    List<CommentDO> queryInIds(@Param("ids") Set<Long> ids);
}
