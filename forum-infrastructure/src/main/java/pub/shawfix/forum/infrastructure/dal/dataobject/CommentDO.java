package pub.shawfix.forum.infrastructure.dal.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shawfix
 * @create 2025/5/30 17:04
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDO extends BaseDO {
    /**
     * 评论人ID
     */
    private Long userId;

    /**
     * 二次评论人ID
     */
    private Long replyId;

    /**
     * 二次评论人ID
     */
    private Long replyReplyId;

    /**
     * 帖子ID
     */
    private Long postsId;

    /**
     * 内容
     */
    private String content;
}
