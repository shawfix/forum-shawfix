package pub.shawfix.forum.infrastructure.dal.dao;

import org.apache.ibatis.annotations.Param;
import pub.shawfix.forum.infrastructure.dal.dataobject.ArticleTypeDO;

import java.util.List;

/**
 * @author shawfix
 * @create 2025/5/30 15:57
 * @desc
 **/
public interface ArticleTypeDAO {
    void insert(ArticleTypeDO articleTypeDO);

    List<ArticleTypeDO> query(ArticleTypeDO articleTypeDO);

    List<ArticleTypeDO> queryInScopesAndState(@Param("scopes") List<String> scopes, @Param("auditState") String auditState);

    void update(ArticleTypeDO articleTypeDO);

    ArticleTypeDO get(@Param("id") Long id);

    void increaseRefCount(@Param("id") Long id);

    void decreaseRefCount(@Param("id") Long id);
}
