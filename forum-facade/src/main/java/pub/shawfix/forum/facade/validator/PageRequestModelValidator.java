package pub.shawfix.forum.facade.validator;

import pub.shawfix.forum.api.model.PageRequestModel;
import pub.shawfix.forum.common.support.CheckUtil;

/**
 * @author shawfix
 * @create 2025/5/30 17:17
 * @desc
 **/
public class PageRequestModelValidator {

    public static void validator(PageRequestModel<?> pageRequestModel) {
        CheckUtil.checkParamToast(pageRequestModel, "pageRequestModel");
        CheckUtil.checkParamToast(pageRequestModel.getPageNo(), "pageNo");
        CheckUtil.checkParamToast(pageRequestModel.getPageSize(), "pageSize");
        CheckUtil.checkParamToast(pageRequestModel.getFilter(), "filter");
    }
}
