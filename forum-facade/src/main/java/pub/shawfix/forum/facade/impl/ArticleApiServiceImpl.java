package pub.shawfix.forum.facade.impl;

import org.springframework.stereotype.Service;
import pub.shawfix.forum.api.model.PageRequestModel;
import pub.shawfix.forum.api.model.PageResponseModel;
import pub.shawfix.forum.api.model.ResultModel;
import pub.shawfix.forum.api.request.AdminBooleanRequest;
import pub.shawfix.forum.api.request.article.*;
import pub.shawfix.forum.api.response.article.ArticleInfoResponse;
import pub.shawfix.forum.api.response.article.ArticleQueryTypesResponse;
import pub.shawfix.forum.api.response.article.ArticleUserPageResponse;
import pub.shawfix.forum.api.service.ArticleApiService;
import pub.shawfix.forum.app.manager.ArticleManager;
import pub.shawfix.forum.facade.support.ResultModelUtil;
import pub.shawfix.forum.facade.validator.ArticleValidator;
import pub.shawfix.forum.facade.validator.PageRequestModelValidator;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shawfix
 * @create 2025/5/30 11:26
 * @desc
 **/
@Service
public class ArticleApiServiceImpl implements ArticleApiService {

    @Resource
    private ArticleManager articleManager;

    @Override
    public ResultModel<List<ArticleQueryTypesResponse>> queryAllTypes() {
        return ResultModelUtil.success(articleManager.queryAllTypes());
    }

    @Override
    public ResultModel<List<ArticleQueryTypesResponse>> queryAdminTypes() {
        return ResultModelUtil.success(articleManager.queryAdminTypes());
    }

    @Override
    public ResultModel<List<ArticleQueryTypesResponse>> queryEditArticleTypes() {
        return ResultModelUtil.success(articleManager.queryEditArticleTypes());
    }

    @Override
    public ResultModel<?> addType(ArticleAddTypeRequest request) {
        ArticleValidator.addType(request);

        articleManager.addType(request);

        return ResultModelUtil.success();
    }

    @Override
    public ResultModel<Long> saveArticle(ArticleSaveArticleRequest request) {
        ArticleValidator.saveArticle(request);

        return ResultModelUtil.success(articleManager.saveArticle(request));
    }

    @Override
    public ResultModel<PageResponseModel<ArticleUserPageResponse>> userPage(PageRequestModel<ArticleUserPageRequest> pageRequestModel) {
        PageRequestModelValidator.validator(pageRequestModel);

        return ResultModelUtil.success(articleManager.userPage(pageRequestModel));
    }

    @Override
    public ResultModel<PageResponseModel<ArticleUserPageResponse>> authorPage(PageRequestModel<ArticleAuthorPageRequest> pageRequestModel) {
        PageRequestModelValidator.validator(pageRequestModel);

        return ResultModelUtil.success(articleManager.authorPage(pageRequestModel));
    }

    @Override
    public ResultModel<PageResponseModel<ArticleUserPageResponse>> adminPage(PageRequestModel<ArticleAdminPageRequest> pageRequestModel) {
        PageRequestModelValidator.validator(pageRequestModel);

        return ResultModelUtil.success(articleManager.adminPage(pageRequestModel));
    }

    @Override
    public ResultModel<ArticleInfoResponse> info(Long id) {

        return ResultModelUtil.success(articleManager.info(id));
    }

    @Override
    public ResultModel<?> adminTop(AdminBooleanRequest booleanRequest) {
        ArticleValidator.validatorBooleanRequest(booleanRequest);

        articleManager.adminTop(booleanRequest);

        return ResultModelUtil.success();
    }

    @Override
    public ResultModel<?> adminOfficial(AdminBooleanRequest booleanRequest) {
        ArticleValidator.validatorBooleanRequest(booleanRequest);

        articleManager.adminOfficial(booleanRequest);

        return ResultModelUtil.success();
    }

    @Override
    public ResultModel<?> adminMarrow(AdminBooleanRequest booleanRequest) {
        ArticleValidator.validatorBooleanRequest(booleanRequest);

        articleManager.adminMarrow(booleanRequest);

        return ResultModelUtil.success();
    }

    @Override
    public ResultModel<PageResponseModel<ArticleQueryTypesResponse>> typePage(PageRequestModel<ArticleAdminTypePageRequest> pageRequestModel) {
        PageRequestModelValidator.validator(pageRequestModel);

        return ResultModelUtil.success(articleManager.typePage(pageRequestModel));
    }

    @Override
    public ResultModel<?> typeAuditState(AdminBooleanRequest booleanRequest) {
        ArticleValidator.validatorBooleanRequest(booleanRequest);

        articleManager.typeAuditState(booleanRequest);

        return ResultModelUtil.success();
    }
}
