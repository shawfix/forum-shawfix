package pub.shawfix.forum.portal.controller.support;

import org.springframework.util.ObjectUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author shawfix
 * @create 2025/5/28 16:21
 * @desc
 **/
public class WebUtil {

    public static String requestIp(HttpServletRequest request) {
        String ret = request.getHeader("X-forwarded-for");
        if (ObjectUtils.isEmpty(ret)) {
            ret = request.getHeader("X-Real-IP");
        }

        return ObjectUtils.isEmpty(ret) ? request.getRemoteAddr() : ret.split(",")[0];
    }

    public static String requestUa(HttpServletRequest request) {
        String value = request.getHeader("User-Agent");
        if (ObjectUtils.isEmpty(value)) {
            return "";
        }

        return value;
    }

    public static void cookieAddSid(HttpServletResponse response, String sid) {
        Cookie cookie = new Cookie(WebConst.COOKIE_SID_KEY, sid);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
