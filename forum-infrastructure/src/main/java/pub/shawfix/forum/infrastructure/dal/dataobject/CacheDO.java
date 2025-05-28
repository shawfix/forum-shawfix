package pub.shawfix.forum.infrastructure.dal.dataobject;

import lombok.*;

/**
 * @author shawfix
 * @create 2025/5/28 15:19
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CacheDO extends BaseDO {

    private String type;

    private String key;

    private String value;

}
