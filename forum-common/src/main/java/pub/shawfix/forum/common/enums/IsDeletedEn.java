package pub.shawfix.forum.common.enums;

import lombok.Getter;

/**
 * @author shawfix
 * @create 2025/5/28 15:26
 * @desc
 **/
public enum IsDeletedEn {
    DELETED(1),
    NOT_DELETED(0);

    @Getter
    private final Integer value;

    IsDeletedEn(Integer value) {
        this.value = value;
    }
}
