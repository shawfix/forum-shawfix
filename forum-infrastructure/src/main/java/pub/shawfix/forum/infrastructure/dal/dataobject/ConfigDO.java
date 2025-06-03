package pub.shawfix.forum.infrastructure.dal.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author shawfix
 * @create 2025/6/3 09:59
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfigDO extends BaseDO {
    private String state;

    private String type;

    private String name;

    private String content;

    private Date startAt;

    private Date endAt;

    private Long creator;
}
