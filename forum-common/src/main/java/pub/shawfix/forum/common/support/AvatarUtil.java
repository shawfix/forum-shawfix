package pub.shawfix.forum.common.support;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.ObjectUtils;

/**
 * @author shawfix
 * @create 2025/5/28 11:28
 * @desc
 **/
public class AvatarUtil {

    // https://www.gravatar.com/avatar/
    public static final String GRAVATAR_URL = "https://sdn.geekzu.org/avatar/%s?d=retro";


    public static String get(String avatar, String email) {
        return ObjectUtils.isEmpty(avatar) ? String.format(GRAVATAR_URL, DigestUtils.md5Hex(ObjectUtils.isEmpty(email) ? "" : email)) : avatar;
    }

}
