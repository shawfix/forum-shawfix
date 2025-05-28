package pub.shawfix.forum.common.support;

import org.springframework.util.ObjectUtils;
import pub.shawfix.forum.common.enums.ErrorCodeEn;
import pub.shawfix.forum.common.exception.BizException;

import java.text.MessageFormat;

/**
 * @author shawfix
 * @create 2025/5/28 13:13
 * @desc
 **/
public class CheckUtil {

    private CheckUtil() {
    }

    /**
     * 检查请求路径参数是否为空
     * @param o
     * @param message
     */
    public static void checkParamToast(Object o, String message) {
        if (ObjectUtils.isEmpty(o)) {
            throw new BizException(ErrorCodeEn.PARAM_CHECK_ERROR.getCode(), MessageFormat.format(ErrorCodeEn.PARAM_CHECK_ERROR.getMessage(), message));
        }
    }

    public static void checkEmptyToast(Object o, String message) {
        if (ObjectUtils.isEmpty(o)) {
            throw new BizException(ErrorCodeEn.CHECK_ERROR_TOAST.getCode(), MessageFormat.format(ErrorCodeEn.CHECK_ERROR_TOAST.getMessage(), message));
        }
    }

    public static void isEmpty(Object o, ErrorCodeEn errorCode) {
        if (ObjectUtils.isEmpty(o)) {
            throw new BizException(errorCode);
        }
    }

    public static void isNotEmpty(Object o, ErrorCodeEn errorCode) {
        if (!ObjectUtils.isEmpty(o)) {
            throw new BizException(errorCode);
        }
    }

    public static void isFalse(Boolean b, ErrorCodeEn errorCode) {
        if (!b) {
            throw new BizException(errorCode);
        }
    }

    public static void isTrue(Boolean b, ErrorCodeEn errorCode) {
        if (b) {
            throw new BizException(errorCode);
        }
    }

}
