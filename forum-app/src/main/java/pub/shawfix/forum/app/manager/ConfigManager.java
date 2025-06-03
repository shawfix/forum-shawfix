package pub.shawfix.forum.app.manager;

import org.springframework.stereotype.Component;
import pub.shawfix.forum.api.model.PageRequestModel;
import pub.shawfix.forum.api.model.PageResponseModel;
import pub.shawfix.forum.api.request.AdminBooleanRequest;
import pub.shawfix.forum.api.request.config.ConfigAddRequest;
import pub.shawfix.forum.api.request.config.ConfigPageRequest;
import pub.shawfix.forum.api.request.config.ConfigUpdateRequest;
import pub.shawfix.forum.api.response.config.ConfigResponse;
import pub.shawfix.forum.app.support.IsLogin;
import pub.shawfix.forum.app.support.LoginUserContext;
import pub.shawfix.forum.app.support.PageUtil;
import pub.shawfix.forum.app.transfer.ConfigTransfer;
import pub.shawfix.forum.common.enums.AuditStateEn;
import pub.shawfix.forum.common.enums.ErrorCodeEn;
import pub.shawfix.forum.common.enums.UserRoleEn;
import pub.shawfix.forum.common.model.PageResult;
import pub.shawfix.forum.common.support.CheckUtil;
import pub.shawfix.forum.domain.entity.Config;
import pub.shawfix.forum.domain.repository.ConfigRepository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @author shawfix
 * @create 2025/6/3 09:41
 * @desc
 **/
@Component
public class ConfigManager {
    @Resource
    private ConfigRepository configRepository;

    @IsLogin(role = UserRoleEn.ADMIN)
    public void add(ConfigAddRequest request) {
        Config config = ConfigTransfer.toConfig(request);
        config.setCreator(LoginUserContext.getUser()
                .getId());

        configRepository.save(config);
    }

    @IsLogin(role = UserRoleEn.ADMIN)
    public void update(ConfigUpdateRequest request) {
        Config config = configRepository.get(request.getId());
        CheckUtil.isEmpty(config, ErrorCodeEn.CONFIG_NOT_EXIST);

        ConfigTransfer.update(config, request);
        configRepository.update(config);
    }

    @IsLogin(role = UserRoleEn.ADMIN)
    public void state(AdminBooleanRequest request) {
        Config config = configRepository.get(request.getId());
        CheckUtil.isEmpty(config, ErrorCodeEn.CONFIG_NOT_EXIST);

        config.setState(request.getIs() ? AuditStateEn.PASS : AuditStateEn.REJECT);
        configRepository.update(config);
    }

    @IsLogin(role = UserRoleEn.ADMIN)
    public PageResponseModel<ConfigResponse> page(PageRequestModel<ConfigPageRequest> pageRequestModel) {
        ConfigPageRequest request = pageRequestModel.getFilter();
        PageResult<Config> pageResult = configRepository.page(PageUtil.buildPageRequest(pageRequestModel, ConfigTransfer.toConfig(request)));

        return PageResponseModel.build(pageResult.getTotal(), pageResult.getSize(), ConfigTransfer.toConfigResponses(pageResult.getList()));
    }

    public List<ConfigResponse> queryAvailable(Set<String> types) {
        return ConfigTransfer.toConfigResponses(configRepository.queryAvailable(types));
    }
}
