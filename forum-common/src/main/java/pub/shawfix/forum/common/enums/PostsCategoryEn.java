package pub.shawfix.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author shawfix
 * @create 2025/5/30 11:37
 * @desc
 **/
@Getter
@AllArgsConstructor
public enum PostsCategoryEn {

    ARTICLE("ARTICLE", "文章"),
    FAQ("FAQ", "问答");

    private final String value;
    private final String desc;

}
