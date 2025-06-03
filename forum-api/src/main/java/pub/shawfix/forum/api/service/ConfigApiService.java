package pub.shawfix.forum.api.service;

import pub.shawfix.forum.api.model.PageRequestModel;
import pub.shawfix.forum.api.model.PageResponseModel;
import pub.shawfix.forum.api.model.ResultModel;
import pub.shawfix.forum.api.request.AdminBooleanRequest;
import pub.shawfix.forum.api.request.config.ConfigAddRequest;
import pub.shawfix.forum.api.request.config.ConfigPageRequest;
import pub.shawfix.forum.api.request.config.ConfigUpdateRequest;
import pub.shawfix.forum.api.response.config.ConfigResponse;

import java.util.List;
import java.util.Set;

/**
 * @author shawfix
 * @create 2025/6/3 08:53
 * @desc
 **/
public interface ConfigApiService {
    /**
     * 添加
     * @param request
     * @return
     */
    ResultModel<?> add(ConfigAddRequest request);

    /**
     * 更新
     * @param request
     * @return
     */
    ResultModel<?> update(ConfigUpdateRequest request);

    /**
     * 更新状态
     * @param request
     * @return
     */
    ResultModel<?> state(AdminBooleanRequest request);

    /**
     * 分页查询
     * @param pageRequestModel
     * @return
     */
    ResultModel<PageResponseModel<ConfigResponse>> page(PageRequestModel<ConfigPageRequest> pageRequestModel);

    /**
     * 类别查询可用
     * @param types
     * @return
     */
    ResultModel<List<ConfigResponse>> queryAvailable(Set<String> types);
}
