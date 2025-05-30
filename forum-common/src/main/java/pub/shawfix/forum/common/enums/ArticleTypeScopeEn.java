package pub.shawfix.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author shawfix
 * @create 2025/5/30 14:05
 * @desc
 **/
@Getter
@AllArgsConstructor
public enum ArticleTypeScopeEn {

    OFFICIAL("OFFICIAL", "官方"),
    USER("USER", "用户");

    private final String value;
    private final String desc;

    public static ArticleTypeScopeEn getEntity(String value) {
        for (ArticleTypeScopeEn articleTypeScopeEn : values()) {
            if (articleTypeScopeEn.getValue().equalsIgnoreCase(value)) {
                return articleTypeScopeEn;
            }
        }

        return null;
    }

}
