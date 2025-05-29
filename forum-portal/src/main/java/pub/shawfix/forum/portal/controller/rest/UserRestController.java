package pub.shawfix.forum.portal.controller.rest;

import com.google.common.collect.Sets;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pub.shawfix.forum.api.model.ResultModel;
import pub.shawfix.forum.api.request.file.FileUploadImgRequest;
import pub.shawfix.forum.api.request.user.UserEmailLoginRequest;
import pub.shawfix.forum.api.request.user.UserRegisterRequest;
import pub.shawfix.forum.api.request.user.UserUpdateInfoRequest;
import pub.shawfix.forum.api.service.FileApiService;
import pub.shawfix.forum.api.service.UserApiService;
import pub.shawfix.forum.common.constant.Constant;
import pub.shawfix.forum.common.enums.ErrorCodeEn;
import pub.shawfix.forum.common.support.CheckUtil;
import pub.shawfix.forum.portal.controller.support.WebUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

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

    @Resource
    private FileApiService fileApiService;

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

    @PostMapping("/update-headimg")
    public ResultModel<?> resultModelUpdateHeadImg(MultipartFile file, HttpServletRequest request) throws IOException {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));
        // 获取原始文件名
        String originalFilename = file.getOriginalFilename();
        ResultModel<String> linkFileName = updateHeadImg(file, originalFilename, request);

        return userApiService.updateHeadImg(linkFileName.getData());
    }

    // .css;.js;.png;.jpeg;.jpg;.woff2;.html;.ico;.gif;.bmp;.svg;.woff;.map
    private static final Set<String> ALLOW_TYPES = Sets.newHashSet("png", "jpeg", "jpg", "ico", "gif", "bmp", "svg");

    private ResultModel<String> updateHeadImg(MultipartFile file, String originalFilename, HttpServletRequest request) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        String fileType = file.getContentType();
        boolean isAllowType = false;
        for (String allowType : ALLOW_TYPES) {
            if (fileType != null && fileType.contains(allowType)) {
                isAllowType = true;
                break;
            }
        }

        CheckUtil.isFalse(isAllowType, ErrorCodeEn.FILE_UPLOAD_NOT_SUPPORT_IMG_TYPE);

        FileUploadImgRequest uploadImgRequest = null;
        try {
            uploadImgRequest = FileUploadImgRequest.builder().base64(file.getBytes()).fileName(originalFilename).build();
        } catch (Exception e) {
            CheckUtil.isTrue(true, ErrorCodeEn.FILE_UPLOAD_FAIL);
        }

        return fileApiService.uploadImg(uploadImgRequest);
    }
}
