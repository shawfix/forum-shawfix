package pub.shawfix.forum.infrastructure.dal.dao;

import org.apache.ibatis.annotations.Param;
import pub.shawfix.forum.infrastructure.dal.dataobject.CacheDO;

import java.util.List;
import java.util.Set;

/**
 * @author shawfix
 * @create 2025/5/28 15:17
 * @desc
 **/
public interface CacheDAO {

    void insertBatch(List<CacheDO> cacheDOs);

    void insert(CacheDO cacheDO);

    List<CacheDO> getAll();

    void batchDeleteByKeys(@Param("keys") Set<String> keys);

    void updateByKey(@Param("key") String key, @Param("value") String value);
}
