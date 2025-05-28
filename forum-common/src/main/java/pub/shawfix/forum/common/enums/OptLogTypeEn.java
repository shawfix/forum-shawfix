package pub.shawfix.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author shawfix
 * @create 2025/5/28 11:51
 * @desc
 **/
@Getter
@AllArgsConstructor
public enum OptLogTypeEn {

    USER_LOGIN("USER_LOGIN", "用户登录记录"),
    USER_LOGOUT("USER_LOGOUT", "用户登出记录"),
    USER_REGISTER("USER_REGISTER", "用户注册记录");

    private final String value;
    private final String desc;

    public static OptLogTypeEn getEntity(String value) {
        for (OptLogTypeEn optLogTypeEn : values()) {
            if (optLogTypeEn.getValue().equalsIgnoreCase(value)) {
                return optLogTypeEn;
            }
        }

        return null;
    }

}
