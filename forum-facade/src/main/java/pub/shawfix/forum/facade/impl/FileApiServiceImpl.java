package pub.shawfix.forum.facade.impl;

import org.springframework.stereotype.Service;
import pub.shawfix.forum.api.model.ResultModel;
import pub.shawfix.forum.api.request.file.FileUploadImgRequest;
import pub.shawfix.forum.api.service.FileApiService;
import pub.shawfix.forum.app.manager.FileManager;
import pub.shawfix.forum.facade.support.ResultModelUtil;
import pub.shawfix.forum.facade.validator.FileValidator;

import javax.annotation.Resource;

/**
 * @author shawfix
 * @create 2025/5/29 17:03
 * @desc
 **/
@Service
public class FileApiServiceImpl implements FileApiService {

    @Resource
    private FileManager fileManager;

    @Override
    public ResultModel<String> uploadImg(FileUploadImgRequest request) {
        FileValidator.uploadImg(request);

        return ResultModelUtil.success(fileManager.uploadImg(request));
    }
}
