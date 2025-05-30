package pub.shawfix.forum.infrastructure;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Repository;
import pub.shawfix.forum.common.model.PageResult;
import pub.shawfix.forum.domain.entity.Comment;
import pub.shawfix.forum.domain.repository.CommentRepository;
import pub.shawfix.forum.infrastructure.dal.dao.CommentDAO;
import pub.shawfix.forum.infrastructure.dal.dataobject.CommentDO;
import pub.shawfix.forum.infrastructure.transfer.CommentTransfer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author shawfix
 * @create 2025/5/30 17:02
 * @desc
 **/
@Repository
public class CommentRepositoryImpl implements CommentRepository {
    @Resource
    private CommentDAO commentDAO;

    @Override
    public void save(Comment comment) {
        CommentDO commentDO = CommentTransfer.toCommentDO(comment);

        commentDAO.insert(commentDO);

        comment.setId(commentDO.getId());
    }

    @Override
    public Comment get(Long id) {
        return CommentTransfer.toComment(commentDAO.findByPrimaryKey(id));
    }

    @Override
    public List<Comment> queryByPostsId(Long postsId) {
        return CommentTransfer.toComments(commentDAO.queryByPostsId(postsId));
    }

    @Override
    public List<Comment> queryInReplyIds(Set<Long> replyIds) {
        return CommentTransfer.toComments(commentDAO.queryInReplyIds(new ArrayList<>(replyIds)));
    }

    @Override
    public PageResult<Comment> page(Integer pageNo, Integer pageSize, Long postsId) {
        PageHelper.startPage(pageNo, pageSize);

        List<CommentDO> commentDOS = commentDAO.queryByPostsId(postsId);
        PageInfo<CommentDO> pageInfo = new PageInfo<>(commentDOS);

        return PageResult.build(pageInfo.getTotal(), pageInfo.getSize(), CommentTransfer.toComments(commentDOS));
    }

    @Override
    public void deleteByPostsId(Long postsId) {
        commentDAO.deleteByPostsId(postsId);
    }

    @Override
    public List<Comment> queryInIds(Set<Long> ids) {
        List<CommentDO> commentDOS = commentDAO.queryInIds(ids);
        return CommentTransfer.toComments(commentDOS);
    }
}
