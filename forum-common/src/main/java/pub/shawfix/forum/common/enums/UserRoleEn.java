package pub.shawfix.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author shawfix
 * @create 2025/5/28 11:08
 * @desc
 **/
@Getter
@AllArgsConstructor
public enum UserRoleEn {

    USER("USER", "用户"),
    ADMIN("ADMIN", "管理员"),
    SUPER_ADMIN("SUPER_ADMIN", "超级管理员");

    private final String value;
    private final String desc;

    public static UserRoleEn getEntity(String value) {
        for (UserRoleEn entity : values()) {
            if (entity.getValue().equalsIgnoreCase(value)) {
                return entity;
            }
        }

        return null;
    }

    public boolean hasPermission(String value) {
        if (USER.equals(this)) {
            return false;
        }

        UserRoleEn authRole = getEntity(value);
        if (ADMIN.equals(this) && (ADMIN.equals(authRole) || SUPER_ADMIN.equals(authRole))) {
            return false;
        }

        if (SUPER_ADMIN.equals(this) && SUPER_ADMIN.equals(authRole)) {
            return false;
        }

        return true;
    }

}
