package pub.shawfix.forum.api.request.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * @author shawfix
 * @create 2025/5/30 11:20
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleSaveArticleRequest implements Serializable {

    private Long id;

    private Long typeId;

    private String title;

    private String contentType;

    private String markdownContent;

    private String htmlContent;

    private String headImg;

    private Set<Long> tagIds;

}
