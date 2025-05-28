package pub.shawfix.forum.api.service;

import pub.shawfix.forum.api.model.PageRequestModel;
import pub.shawfix.forum.api.model.PageResponseModel;
import pub.shawfix.forum.api.model.ResultModel;
import pub.shawfix.forum.api.request.AdminBooleanRequest;
import pub.shawfix.forum.api.request.user.*;
import pub.shawfix.forum.api.response.user.UserInfoResponse;
import pub.shawfix.forum.api.response.user.UserOptLogPageResponse;
import pub.shawfix.forum.api.response.user.UserPageResponse;

/**
 * @author shawfix
 * @create 2025/5/27 11:05
 * @desc
 **/
public interface UserApiService {
    ResultModel<?> enable(Long uid);

    ResultModel<?> disable(Long uid);

    ResultModel<?> follow(Long followed);

    ResultModel<?> cancelFollow(Long followed);

    ResultModel<PageResponseModel<UserPageResponse>> pageFollower(PageRequestModel<Long> pageRequestModel);

    ResultModel<PageResponseModel<UserPageResponse>> pageFans(PageRequestModel<Long> pageRequestModel);

    ResultModel<Boolean> hasFollow(Long followed);

    ResultModel<UserInfoResponse> info(String token);

    ResultModel<UserInfoResponse> info(Long uid);

    ResultModel<String> register(UserRegisterRequest request);

    ResultModel<String> emailLogin(UserEmailLoginRequest request);

    ResultModel<?> logout(UserTokenLogoutRequest request);

    ResultModel<?> updateInfo(UserUpdateInfoRequest request);

    ResultModel<?> updateHeadImg(String linkFilenameData);

    ResultModel<?> updatePwd(UserUpdatePwdRequest request);

    ResultModel<PageResponseModel<UserPageResponse>> adminPage(PageRequestModel<UserAdminPageRequest> pageRequestModel);

    ResultModel<PageResponseModel<UserPageResponse>> pageActive(PageRequestModel<?> pageRequestModel);

    ResultModel<PageResponseModel<UserOptLogPageResponse>> pageOptLog(PageRequestModel<UserOptLogPageRequest> pageRequestModel);

    ResultModel<?> updateRole(AdminBooleanRequest booleanRequest);
}
