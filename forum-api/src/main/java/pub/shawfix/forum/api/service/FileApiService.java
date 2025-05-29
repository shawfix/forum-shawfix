package pub.shawfix.forum.api.service;

import org.springframework.web.multipart.MultipartFile;
import pub.shawfix.forum.api.model.ResultModel;
import pub.shawfix.forum.api.request.file.FileUploadImgRequest;

/**
 * @author shawfix
 * @create 2025/5/29 16:53
 * @desc
 **/
public interface FileApiService {

    ResultModel<String> uploadImg(FileUploadImgRequest request);

}
