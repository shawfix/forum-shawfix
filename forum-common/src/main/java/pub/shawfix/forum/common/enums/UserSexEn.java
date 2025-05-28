package pub.shawfix.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author shawfix
 * @create 2025/5/28 11:22
 * @desc
 **/
@Getter
@AllArgsConstructor
public enum UserSexEn {

    UNKNOWN("UNKNOWN", "未知"),
    MAN("MAN", "男"),
    WOMAN("WOMAN", "女");

    private final String value;
    private final String desc;

    public static UserSexEn getEntity(String value) {
        for (UserSexEn userSexEn: values()) {
            if (userSexEn.getValue().equalsIgnoreCase(value)) {
                return userSexEn;
            }
        }

        return null;
    }

}
