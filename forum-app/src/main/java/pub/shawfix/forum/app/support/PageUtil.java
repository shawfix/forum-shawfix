package pub.shawfix.forum.app.support;

import pub.shawfix.forum.api.model.PageRequestModel;
import pub.shawfix.forum.api.model.PageResponseModel;
import pub.shawfix.forum.common.model.PageRequest;
import pub.shawfix.forum.common.model.PageResult;

import java.util.List;

/**
 * @author shawfix
 * @create 2025/5/30 17:13
 * @desc
 **/
public class PageUtil {
    public static <T> PageRequest<T> buildPageRequest(PageRequestModel pageRequestModel, T filter) {
        return PageRequest.build(pageRequestModel.getPageNo(), pageRequestModel.getPageSize(), filter);
    }

    public static PageRequest buildPageRequest(PageRequestModel pageRequestModel) {
        return PageRequest.build(pageRequestModel.getPageNo(), pageRequestModel.getPageSize());
    }

    public static <T> PageResponseModel<T> buildPageResponseModel(PageResult pageResult, List<T> list) {
        PageResponseModel<T> pageResponseModel = new PageResponseModel<>();
        pageResponseModel.setSize(pageResult.getSize());
        pageResponseModel.setTotal(pageResult.getTotal());
        pageResponseModel.setList(list);

        return pageResponseModel;
    }
}
