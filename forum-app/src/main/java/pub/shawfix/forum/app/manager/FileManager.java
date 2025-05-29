package pub.shawfix.forum.app.manager;

import org.springframework.stereotype.Component;
import pub.shawfix.forum.api.request.file.FileUploadImgRequest;
import pub.shawfix.forum.app.support.IsLogin;
import pub.shawfix.forum.domain.service.FileService;

import javax.annotation.Resource;

/**
 * @author shawfix
 * @create 2025/5/29 17:05
 * @desc
 **/
@Component
public class FileManager {

    @Resource
    private FileService fileService;

    @IsLogin
    public String uploadImg(FileUploadImgRequest request) {
        return fileService.uploadImg(request.getBase64(), request.getFileName());
    }

}
