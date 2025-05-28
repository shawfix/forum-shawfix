package pub.shawfix.forum.common.exception;

import lombok.Data;
import pub.shawfix.forum.common.enums.ErrorCodeEn;

/**
 * @author shawfix
 * @create 2025/5/28 13:16
 * @desc
 **/
@Data
public class BizException extends RuntimeException {

    private String message;
    private Integer code;

    /**
     * @desc 默认系统异常
     */
    public BizException() {
        this(ErrorCodeEn.SYSTEM_ERROR);
    }

    /**
     * @desc 指定参数的业务异常
     * @param errorCode
     */
    public BizException(ErrorCodeEn errorCode) {
        this(errorCode.getCode(), errorCode.getMessage());
    }

    /**
     * @desc 指定参数的业务异常
     * @param code
     * @param message
     */
    public BizException(Integer code, String message) {
        super(message);
        this.message = message;
        this.code = code;
    }
}
