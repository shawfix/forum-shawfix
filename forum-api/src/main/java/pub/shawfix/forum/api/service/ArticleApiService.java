package pub.shawfix.forum.api.service;

import pub.shawfix.forum.api.model.PageRequestModel;
import pub.shawfix.forum.api.model.PageResponseModel;
import pub.shawfix.forum.api.model.ResultModel;
import pub.shawfix.forum.api.request.AdminBooleanRequest;
import pub.shawfix.forum.api.request.article.*;
import pub.shawfix.forum.api.response.article.ArticleInfoResponse;
import pub.shawfix.forum.api.response.article.ArticleQueryTypesResponse;
import pub.shawfix.forum.api.response.article.ArticleUserPageResponse;

import java.util.List;

/**
 * @author shawfix
 * @create 2025/5/30 11:05
 * @desc
 **/
public interface ArticleApiService {

    ResultModel<List<ArticleQueryTypesResponse>> queryAllTypes();

    ResultModel<List<ArticleQueryTypesResponse>> queryAdminTypes();

    ResultModel<PageResponseModel<ArticleUserPageResponse>> adminPage(PageRequestModel<ArticleAdminPageRequest> pageRequestModel);

    ResultModel<List<ArticleQueryTypesResponse>> queryEditArticleTypes();

    ResultModel<?> addType(ArticleAddTypeRequest request);

    ResultModel<Long> saveArticle(ArticleSaveArticleRequest request);

    ResultModel<PageResponseModel<ArticleUserPageResponse>> userPage(PageRequestModel<ArticleUserPageRequest> pageRequestModel);

    ResultModel<PageResponseModel<ArticleUserPageResponse>> authorPage(PageRequestModel<ArticleAuthorPageRequest> pageRequestModel);

    ResultModel<ArticleInfoResponse> info(Long id);

    ResultModel<?> adminTop(AdminBooleanRequest booleanRequest);

    ResultModel<?> adminOfficial(AdminBooleanRequest booleanRequest);

    ResultModel<?> adminMarrow(AdminBooleanRequest booleanRequest);

    ResultModel<PageResponseModel<ArticleQueryTypesResponse>> typePage(PageRequestModel<ArticleAdminTypePageRequest> pageRequestModel);

    ResultModel<?> typeAuditState(AdminBooleanRequest booleanRequest);
}
