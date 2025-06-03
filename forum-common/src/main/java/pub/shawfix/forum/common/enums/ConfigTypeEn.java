package pub.shawfix.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author shawfix
 * @create 2025/6/3 09:44
 * @desc
 **/
@Getter
@AllArgsConstructor
public enum ConfigTypeEn {

    HOME_CAROUSEL("HOME_CAROUSEL", "首页轮播图"),
    SIDEBAR_CAROUSEL("SIDEBAR_CAROUSEL", "侧边栏轮播图");

    private final String value;
    private final String desc;

    public static ConfigTypeEn getEntity(String value) {
        for (ConfigTypeEn configTypeEn: values()) {
            if (configTypeEn.getValue().equalsIgnoreCase(value)) {
                return configTypeEn;
            }
        }

        return null;
    }
}
