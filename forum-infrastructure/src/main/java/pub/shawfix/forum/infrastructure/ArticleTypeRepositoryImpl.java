package pub.shawfix.forum.infrastructure;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import pub.shawfix.forum.common.enums.ArticleTypeScopeEn;
import pub.shawfix.forum.common.enums.AuditStateEn;
import pub.shawfix.forum.common.enums.ErrorCodeEn;
import pub.shawfix.forum.common.exception.BizException;
import pub.shawfix.forum.common.model.PageRequest;
import pub.shawfix.forum.common.model.PageResult;
import pub.shawfix.forum.domain.entity.ArticleType;
import pub.shawfix.forum.domain.repository.ArticleTypeRepository;
import pub.shawfix.forum.infrastructure.dal.dao.ArticleTypeDAO;
import pub.shawfix.forum.infrastructure.dal.dataobject.ArticleTypeDO;
import pub.shawfix.forum.infrastructure.transfer.ArticleTypeTransfer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shawfix
 * @create 2025/5/30 16:27
 * @desc
 **/
@Repository
public class ArticleTypeRepositoryImpl implements ArticleTypeRepository {
    @Resource
    private ArticleTypeDAO articleTypeDAO;

    @Override
    public void save(ArticleType articleType) {
        ArticleTypeDO articleTypeDO = ArticleTypeTransfer.toArticleTypeDO(articleType);
        try {
            articleTypeDAO.insert(articleTypeDO);
            articleType.setId(articleTypeDO.getId());
        } catch (DuplicateKeyException e) {
            throw new BizException(ErrorCodeEn.ARTICLE_TYPE_IS_EXIST);
        }
    }

    @Override
    public List<ArticleType> query(ArticleType articleType) {
        return ArticleTypeTransfer.toArticleTypes(articleTypeDAO.query(ArticleTypeTransfer.toArticleTypeDO(articleType)));
    }

    @Override
    public List<ArticleType> queryByState(AuditStateEn auditState) {
        List<ArticleTypeDO> articleTypeDOS = articleTypeDAO.query(ArticleTypeDO.builder()
                .auditState(ObjectUtils.isEmpty(auditState) ? null : auditState.getValue())
                .build());

        return ArticleTypeTransfer.toArticleTypes(articleTypeDOS);
    }

    @Override
    public List<ArticleType> queryByScopesAndState(List<ArticleTypeScopeEn> scopes, AuditStateEn auditState) {
        List<ArticleTypeDO> articleTypeDOS = articleTypeDAO.queryInScopesAndState(scopes.stream()
                .map(ArticleTypeScopeEn::getValue)
                .collect(Collectors.toList()), auditState.getValue());

        return ArticleTypeTransfer.toArticleTypes(articleTypeDOS);
    }

    @Override
    public void update(ArticleType articleType) {
        ArticleTypeDO articleTypeDO = ArticleTypeDO.builder()
                .build();
        articleTypeDO.setId(articleType.getId());
        if (!ObjectUtils.isEmpty(articleTypeDO.getAuditState())) {
            articleTypeDO.setAuditState(articleType.getAuditState()
                    .getValue());
        }

        articleTypeDAO.update(articleTypeDO);
    }

    @Override
    public ArticleType get(Long id) {
        return ArticleTypeTransfer.toArticleType(articleTypeDAO.get(id));
    }

    @Override
    public ArticleType getByNameAndState(String typeName, AuditStateEn auditState) {
        List<ArticleTypeDO> articleTypeDOS = articleTypeDAO.query(ArticleTypeDO.builder()
                .name(typeName)
                .auditState(auditState.getValue())
                .build());

        if (ObjectUtils.isEmpty(articleTypeDOS)) {
            return null;
        }

        return ArticleTypeTransfer.toArticleType(articleTypeDOS.get(0));
    }

    @Override
    public void increaseRefCount(Long id) {
        articleTypeDAO.increaseRefCount(id);
    }

    @Override
    public void decreaseRefCount(Long id) {
        articleTypeDAO.decreaseRefCount(id);
    }

    @Override
    public PageResult<ArticleType> page(PageRequest<ArticleType> pageRequest) {
        PageHelper.startPage(pageRequest.getPageNo(), pageRequest.getPageSize());

        ArticleType articleType = pageRequest.getFilter();
        ArticleTypeDO articleTypeDO = ArticleTypeDO.builder()
                .name(articleType.getName())
                .description(articleType.getDescription())
                .auditState(ObjectUtils.isEmpty(articleType.getAuditState()) ? null : articleType.getAuditState()
                        .getValue())
                .scope(ObjectUtils.isEmpty(articleType.getScope()) ? null : articleType.getScope()
                        .getValue())
                .build();
        List<ArticleTypeDO> articleTypeDOList = articleTypeDAO.query(articleTypeDO);
        PageInfo<ArticleTypeDO> pageInfo = new PageInfo<>(articleTypeDOList);

        if (ObjectUtils.isEmpty(articleTypeDOList)) {
            return PageResult.build(pageInfo.getTotal(), pageInfo.getSize(), new ArrayList<>());
        }

        return PageResult.build(pageInfo.getTotal(), pageInfo.getSize(), ArticleTypeTransfer.toArticleTypes(articleTypeDOList));
    }
}
