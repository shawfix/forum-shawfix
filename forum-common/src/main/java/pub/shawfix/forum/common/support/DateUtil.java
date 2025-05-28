package pub.shawfix.forum.common.support;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author shawfix
 * @create 2025/5/28 15:59
 * @desc
 **/
public class DateUtil {

    public static String toyyyyMMddHHmmss(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

}
