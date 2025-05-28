package pub.shawfix.forum.api.response.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author shawfix
 * @create 2025/5/27 17:49
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse implements Serializable {
    private Long id;

    private String email;

    private String role;

    private String nickname;

    private String sex;

    private String avatar;

    private String signature;

    private Date createAt;

    private Date lastLoginTime;
}
