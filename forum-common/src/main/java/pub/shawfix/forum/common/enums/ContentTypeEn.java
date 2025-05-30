package pub.shawfix.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author shawfix
 * @create 2025/5/30 11:38
 * @desc
 **/
@Getter
@AllArgsConstructor
public enum ContentTypeEn {

    HTML("HTML", "html富文本"), MARKDOWN("MARKDOWN", "markdown内容");

    private final String value;
    private final String desc;

    public static ContentTypeEn getEntity(String value) {
        for (ContentTypeEn contentTypeEn : ContentTypeEn.values()) {
            if (contentTypeEn.getValue().equalsIgnoreCase(value)) {
                return contentTypeEn;
            }
        }

        return null;
    }

}
