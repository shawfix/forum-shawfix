package pub.shawfix.forum.domain.repository;

import pub.shawfix.forum.common.enums.ArticleTypeScopeEn;
import pub.shawfix.forum.common.enums.AuditStateEn;
import pub.shawfix.forum.common.model.PageRequest;
import pub.shawfix.forum.common.model.PageResult;
import pub.shawfix.forum.domain.entity.ArticleType;

import java.util.List;

/**
 * @author shawfix
 * @create 2025/5/30 16:27
 * @desc
 **/
public interface ArticleTypeRepository {
    void save(ArticleType articleType);

    List<ArticleType> query(ArticleType articleType);

    List<ArticleType> queryByState(AuditStateEn auditState);

    List<ArticleType> queryByScopesAndState(List<ArticleTypeScopeEn> scopes, AuditStateEn auditState);

    void update(ArticleType articleType);

    ArticleType get(Long id);

    ArticleType getByNameAndState(String typeName, AuditStateEn pass);

    void increaseRefCount(Long id);

    void decreaseRefCount(Long id);

    PageResult<ArticleType> page(PageRequest<ArticleType> articleTypePageRequest);
}
