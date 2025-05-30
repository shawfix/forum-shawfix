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
        return null;
    }

    @Override
    public ResultModel<List<ArticleQueryTypesResponse>> queryAdminTypes() {
        return null;
    }

    @Override
    public ResultModel<PageResponseModel<ArticleQueryTypesResponse>> adminPage(PageRequestModel<ArticleAdminPageRequest> pageRequestModel) {
        return null;
    }

    @Override
    public ResultModel<List<ArticleQueryTypesResponse>> queryEditArticleTypes() {
        return null;
    }

    @Override
    public ResultModel<?> addType(ArticleAddTypeRequest request) {
        return null;
    }

    @Override
    public ResultModel<Long> saveArticle(ArticleSaveArticleRequest request) {
        return null;
    }

    @Override
    public ResultModel<PageResponseModel<ArticleUserPageResponse>> userPage(PageRequestModel<ArticleUserPageRequest> pageRequestModel) {
        return null;
    }

    @Override
    public ResultModel<PageResponseModel<ArticleUserPageResponse>> authorPage(PageRequestModel<ArticleAuthorPageRequest> pageRequestModel) {
        return null;
    }

    @Override
    public ResultModel<ArticleInfoResponse> info(Long id) {
        return null;
    }

    @Override
    public ResultModel<?> adminTop(AdminBooleanRequest booleanRequest) {
        return null;
    }

    @Override
    public ResultModel<?> adminOfficial(AdminBooleanRequest booleanRequest) {
        return null;
    }

    @Override
    public ResultModel<?> adminMarrow(AdminBooleanRequest booleanRequest) {
        return null;
    }

    @Override
    public ResultModel<PageResponseModel<ArticleQueryTypesResponse>> typePage(PageRequestModel<ArticleAdminTypePageRequest> pageRequestModel) {
        return null;
    }

    @Override
    public ResultModel<?> typeAuditState(AdminBooleanRequest booleanRequest) {
        return null;
    }
}
