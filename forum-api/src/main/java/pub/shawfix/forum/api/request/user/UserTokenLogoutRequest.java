package pub.shawfix.forum.api.request.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shawfix
 * @create 2025/5/27 18:23
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserTokenLogoutRequest extends UserBaseLoginRequest {

    private String token;

}
