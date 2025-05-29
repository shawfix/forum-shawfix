package pub.shawfix.forum.portal.controller.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.shawfix.forum.api.model.ResultModel;
import pub.shawfix.forum.api.request.user.UserEmailLoginRequest;
import pub.shawfix.forum.api.request.user.UserRegisterRequest;
import pub.shawfix.forum.api.request.user.UserUpdateInfoRequest;
import pub.shawfix.forum.api.service.UserApiService;
import pub.shawfix.forum.common.constant.Constant;
import pub.shawfix.forum.portal.controller.support.WebUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author shawfix
 * @create 2025/5/27 11:03
 * @desc
 **/
@RestController
@RequestMapping("/user-rest")
public class UserRestController {

    @Resource
    private UserApiService userApiService;

    @PostMapping("/register")
    public ResultModel<String> register(@RequestBody UserRegisterRequest request, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        request.setIp(WebUtil.requestIp(servletRequest));
        request.setUa(WebUtil.requestUa(servletRequest));

        ResultModel<String> resultModel = userApiService.register(request);

        WebUtil.cookieAddSid(servletResponse, resultModel.getData());

        return resultModel;
    }

    @PostMapping("/login")
    public ResultModel<String> login(@RequestBody UserEmailLoginRequest request, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        request.setIp(WebUtil.requestIp(servletRequest));
        request.setUa(WebUtil.requestUa(servletRequest));

        ResultModel<String> resultModel = userApiService.emailLogin(request);

        WebUtil.cookieAddSid(servletResponse, resultModel.getData());

        return resultModel;
    }

    @PostMapping("/update-info")
    public ResultModel<?> updateInfo(@RequestBody UserUpdateInfoRequest updateInfoRequest, HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        return userApiService.updateInfo(updateInfoRequest);
    }
}
