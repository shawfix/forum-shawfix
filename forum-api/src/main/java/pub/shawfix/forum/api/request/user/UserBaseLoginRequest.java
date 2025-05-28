package pub.shawfix.forum.api.request.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author shawfix
 * @create 2025/5/27 18:21
 * @desc
 **/
@Data
public class UserBaseLoginRequest implements Serializable {

    private String ua;

    private String ip;

}
