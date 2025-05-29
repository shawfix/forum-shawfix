package pub.shawfix.forum.facade.validator;

import pub.shawfix.forum.api.request.user.UserEmailLoginRequest;
import pub.shawfix.forum.api.request.user.UserRegisterRequest;
import pub.shawfix.forum.api.request.user.UserUpdateInfoRequest;
import pub.shawfix.forum.common.support.CheckUtil;

/**
 * @author shawfix
 * @create 2025/5/28 16:40
 * @desc
 **/
public class UserValidator {
    public static void register(UserRegisterRequest request) {
        CheckUtil.checkParamToast(request, "request");
        CheckUtil.checkParamToast(request.getEmail(), "email");
        CheckUtil.checkParamToast(request.getNickname(), "nickname");
        CheckUtil.checkParamToast(request.getPassword(), "password");
    }

    public static void emailLogin(UserEmailLoginRequest request) {
        CheckUtil.checkParamToast(request, "request");
        CheckUtil.checkParamToast(request.getEmail(), "email");
        CheckUtil.checkParamToast(request.getPassword(), "password");
    }

    public static void updateInfo(UserUpdateInfoRequest request) {
        CheckUtil.checkParamToast(request, "request");
        CheckUtil.checkParamToast(request.getEmail(), "email");
        CheckUtil.checkParamToast(request.getNickname(), "nickname");
        CheckUtil.checkParamToast(request.getSignature(), "signature");
    }
}
