package pub.shawfix.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author shawfix
 * @create 2025/5/28 13:43
 * @desc
 **/
@Getter
@AllArgsConstructor
public enum CacheBizTypeEn {

    USER_LOGIN_TOKEN("USER_LOGIN_TOKEN", "用户登录凭证 token"),
    TAG_USED("TAG_USED", "已使用标签");

    private final String value;
    private final String desc;

}
