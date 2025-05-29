package pub.shawfix.forum.facade.validator;

import pub.shawfix.forum.api.request.file.FileUploadImgRequest;
import pub.shawfix.forum.common.support.CheckUtil;

/**
 * @author shawfix
 * @create 2025/5/29 17:16
 * @desc
 **/
public class FileValidator {
    public static void uploadImg(FileUploadImgRequest request) {
        CheckUtil.checkParamToast(request, "request");
        CheckUtil.checkParamToast(request.getBase64(), "base64");
        CheckUtil.checkParamToast(request.getFileName(), "fileName");
    }
}
