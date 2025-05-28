package pub.shawfix.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author shawfix
 * @create 2025/5/28 11:18
 * @desc
 **/
@Getter
@AllArgsConstructor
public enum UserStateEn {

    UN_ACTIVATION("UN_ACTIVATION", "未激活"),
    ENABLE("ENABLE", "启用"),
    DISABLE("DISABLE", "禁用");

    private final String value;
    private final String desc;

    public static UserStateEn getEntity(String value) {
        for (UserStateEn userSexEn: values()) {
            if (userSexEn.getValue().equalsIgnoreCase(value)) {
                return userSexEn;
            }
        }

        return null;
    }

}
