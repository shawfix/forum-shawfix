package pub.shawfix.forum.facade.impl;

import org.springframework.stereotype.Service;
import pub.shawfix.forum.api.model.PageRequestModel;
import pub.shawfix.forum.api.model.PageResponseModel;
import pub.shawfix.forum.api.model.ResultModel;
import pub.shawfix.forum.api.request.AdminBooleanRequest;
import pub.shawfix.forum.api.request.config.ConfigAddRequest;
import pub.shawfix.forum.api.request.config.ConfigPageRequest;
import pub.shawfix.forum.api.request.config.ConfigUpdateRequest;
import pub.shawfix.forum.api.response.config.ConfigResponse;
import pub.shawfix.forum.api.service.ConfigApiService;
import pub.shawfix.forum.app.manager.ConfigManager;
import pub.shawfix.forum.facade.support.ResultModelUtil;
import pub.shawfix.forum.facade.validator.PageRequestModelValidator;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @author shawfix
 * @create 2025/6/3 09:40
 * @desc
 **/
@Service
public class ConfigApiServiceImpl implements ConfigApiService {

    @Resource
    private ConfigManager configManager;

    @Override
    public ResultModel<?> add(ConfigAddRequest request) {
        configManager.add(request);
        return ResultModelUtil.success();
    }

    @Override
    public ResultModel<?> update(ConfigUpdateRequest request) {
        configManager.update(request);
        return ResultModelUtil.success();
    }

    @Override
    public ResultModel<?> state(AdminBooleanRequest request) {
        configManager.state(request);
        return ResultModelUtil.success();
    }

    @Override
    public ResultModel<PageResponseModel<ConfigResponse>> page(PageRequestModel<ConfigPageRequest> pageRequestModel) {
        PageRequestModelValidator.validator(pageRequestModel);
        return ResultModelUtil.success(configManager.page(pageRequestModel));
    }

    @Override
    public ResultModel<List<ConfigResponse>> queryAvailable(Set<String> types) {
        return ResultModelUtil.success(configManager.queryAvailable(types));
    }
}
