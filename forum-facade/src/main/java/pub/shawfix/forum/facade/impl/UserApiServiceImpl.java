package pub.shawfix.forum.facade.impl;

import org.springframework.stereotype.Service;
import pub.shawfix.forum.api.model.PageRequestModel;
import pub.shawfix.forum.api.model.PageResponseModel;
import pub.shawfix.forum.api.model.ResultModel;
import pub.shawfix.forum.api.request.AdminBooleanRequest;
import pub.shawfix.forum.api.request.user.*;
import pub.shawfix.forum.api.response.user.UserInfoResponse;
import pub.shawfix.forum.api.response.user.UserOptLogPageResponse;
import pub.shawfix.forum.api.response.user.UserPageResponse;
import pub.shawfix.forum.api.service.UserApiService;
import pub.shawfix.forum.app.manager.UserManager;
import pub.shawfix.forum.facade.support.ResultModelUtil;
import pub.shawfix.forum.facade.validator.UserValidator;

import javax.annotation.Resource;

/**
 * @author shawfix
 * @create 2025/5/28 10:28
 * @desc
 **/
@Service
public class UserApiServiceImpl implements UserApiService {

    @Resource
    private UserManager userManager;

    @Override
    public ResultModel<String> register(UserRegisterRequest request) {
        UserValidator.register(request);

        return ResultModelUtil.success(userManager.register(request));
    }

    @Override
    public ResultModel<String> emailLogin(UserEmailLoginRequest request) {
        UserValidator.emailLogin(request);

        return ResultModelUtil.success(userManager.emailRequestLogin(request));
    }

    @Override
    public ResultModel<?> enable(Long uid) {
        return null;
    }

    @Override
    public ResultModel<?> disable(Long uid) {
        return null;
    }

    @Override
    public ResultModel<?> follow(Long followed) {
        return null;
    }

    @Override
    public ResultModel<?> cancelFollow(Long followed) {
        return null;
    }

    @Override
    public ResultModel<PageResponseModel<UserPageResponse>> pageFollower(PageRequestModel<Long> pageRequestModel) {
        return null;
    }

    @Override
    public ResultModel<PageResponseModel<UserPageResponse>> pageFans(PageRequestModel<Long> pageRequestModel) {
        return null;
    }

    @Override
    public ResultModel<Boolean> hasFollow(Long followed) {
        return null;
    }

    @Override
    public ResultModel<UserInfoResponse> info(String token) {
        return null;
    }

    @Override
    public ResultModel<UserInfoResponse> info(Long uid) {
        return null;
    }

    @Override
    public ResultModel<?> logout(UserTokenLogoutRequest request) {
        return null;
    }

    @Override
    public ResultModel<?> updateInfo(UserUpdateInfoRequest request) {
        UserValidator.updateInfo(request);
        userManager.updateInfo(request);
        return ResultModelUtil.success();
    }

    @Override
    public ResultModel<?> updateHeadImg(String linkFilenameData) {
        userManager.updateHeadImg(linkFilenameData);
        return ResultModelUtil.success();
    }

    @Override
    public ResultModel<?> updatePwd(UserUpdatePwdRequest request) {
        return null;
    }

    @Override
    public ResultModel<PageResponseModel<UserPageResponse>> adminPage(PageRequestModel<UserAdminPageRequest> pageRequestModel) {
        return null;
    }

    @Override
    public ResultModel<PageResponseModel<UserPageResponse>> pageActive(PageRequestModel<?> pageRequestModel) {
        return null;
    }

    @Override
    public ResultModel<PageResponseModel<UserOptLogPageResponse>> pageOptLog(PageRequestModel<UserOptLogPageRequest> pageRequestModel) {
        return null;
    }

    @Override
    public ResultModel<?> updateRole(AdminBooleanRequest booleanRequest) {
        return null;
    }
}
