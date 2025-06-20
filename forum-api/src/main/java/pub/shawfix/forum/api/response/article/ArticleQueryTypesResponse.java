package pub.shawfix.forum.api.response.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author shawfix
 * @create 2025/5/30 11:06
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleQueryTypesResponse implements Serializable {

 private Long id;

 private String name;

 private Long refCount;

 /**
  * 描述
  */
 private String description;

 /**
  * 作用域
  */
 private String scope;

 /**
  * 创建人
  */
 private Long creatorId;

 /**
  * 审核状态
  */
 private String auditState;

 /**
  * 创建时间
  */
 private String createAt;

 /**
  * 更新时间
  */
 private String updateAt;
}
