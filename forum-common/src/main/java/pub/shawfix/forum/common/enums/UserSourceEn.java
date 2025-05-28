package pub.shawfix.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author shawfix
 * @create 2025/5/28 11:26
 * @desc
 **/
@Getter
@AllArgsConstructor
public enum UserSourceEn {
    REGISTER("REGISTER", "注册"),
    GITHUB("GITHUB", "github");

    private final String value;
    private final String desc;

    public static UserSourceEn getEntity(String value) {
        for (UserSourceEn userSourceEn: values()) {
            if (userSourceEn.getValue().equalsIgnoreCase(value)) {
                return userSourceEn;
            }
        }

        return null;
    }
}
