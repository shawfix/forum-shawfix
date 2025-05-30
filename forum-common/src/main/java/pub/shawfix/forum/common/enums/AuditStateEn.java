package pub.shawfix.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author shawfix
 * @create 2025/5/30 11:32
 * @desc
 **/
@Getter
@AllArgsConstructor
public enum AuditStateEn {

    WAIT("WAIT", "待审核"),
    PASS("PASS", "审核通过"),
    REJECT("REJECT", "审核拒绝");

    private final String value;
    private final String desc;

    public static AuditStateEn getEntity(String value) {
        for (AuditStateEn auditStateEn : values()) {
            if (auditStateEn.getValue().equalsIgnoreCase(value)) {
                return auditStateEn;
            }
        }

        return null;
    }

}
