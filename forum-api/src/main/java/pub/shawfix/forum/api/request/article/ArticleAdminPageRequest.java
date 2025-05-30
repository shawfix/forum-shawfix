package pub.shawfix.forum.api.request.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author shawfix
 * @create 2025/5/30 11:14
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleAdminPageRequest implements Serializable {

    private Long typeId;

    private String auditState;

    private Boolean official;

    private Boolean top;

    private Boolean marrow;

    private Long userId;

    private String title;
}
