package pub.shawfix.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author shawfix
 * @create 2025/5/28 11:43
 * @desc
 **/
@Getter
@AllArgsConstructor
public enum FollowedTypeEn {

    USER("USER", "用户"),
    POSTS("POSTS", "帖子");

    private final String value;
    private final String desc;

    public static FollowedTypeEn getEntity(String value) {
        for (FollowedTypeEn followedTypeEn : values()) {
            if (followedTypeEn.getValue().equalsIgnoreCase(value)) {
                return followedTypeEn;
            }
        }

        return null;
    }

}
