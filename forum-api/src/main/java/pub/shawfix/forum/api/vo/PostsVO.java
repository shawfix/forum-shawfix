package pub.shawfix.forum.api.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author shawfix
 * @create 2025/5/30 16:55
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostsVO implements Serializable {

    private Boolean official;

    private Boolean top;

    private Boolean marrow;

    private String headImg;

    private Long id;

    private String category;

    private String categoryDesc;

    private String title;

    private String introduction;

    private Long authorId;

    private String authorNickname;

    private String authorAvatar;

    private Date createAt;

    private Long views;

    private Long approvals;

    private Long comments;

    private List<TagVO> tags;

    private SolutionVO solution;

}
