package pub.shawfix.forum.common.support;

import org.springframework.util.ObjectUtils;

/**
 * @author shawfix
 * @create 2025/5/28 18:06
 * @desc
 **/
public class RequestContext {
    /**
     * trace id
     */
    private static final ThreadLocal<String> REQUEST_TRACE_ID = new ThreadLocal<>();

    /**
     * 为当前请求生成 trade id
     *
     * @param
     */
    public static void init() {
        if (ObjectUtils.isEmpty(REQUEST_TRACE_ID.get())) {
            REQUEST_TRACE_ID.set(StringUtil.generateUUID());
        }
    }

    /**
     * 获取当前请求 trade id
     *
     * @return
     */
    public static String getTraceId() {
        return ObjectUtils.isEmpty(REQUEST_TRACE_ID.get()) ? "" : REQUEST_TRACE_ID.get();
    }

    public static void removeAll() {
        REQUEST_TRACE_ID.remove();
    }
}
