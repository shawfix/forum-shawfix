package pub.shawfix.forum.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pub.shawfix.forum.common.enums.AuditStateEn;
import pub.shawfix.forum.common.enums.ConfigTypeEn;

import java.util.Date;

/**
 * @author shawfix
 * @create 2025/6/3 09:43
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Config extends BaseEntity {

    private AuditStateEn state;

    private ConfigTypeEn type;

    private String name;

    private String content;

    private Date startAt;

    private Date endAt;

    private Long creator;

}
