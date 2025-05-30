package pub.shawfix.forum.infrastructure.dal.dao;

import org.apache.ibatis.annotations.Param;
import pub.shawfix.forum.infrastructure.dal.dataobject.TagDO;

import java.util.List;
import java.util.Set;

/**
 * @author shawfix
 * @create 2025/5/30 14:39
 * @desc
 **/
public interface TagDAO {
    void insert(TagDO tagDO);

    List<TagDO> query(TagDO tagDO);

    void update(TagDO tagDO);

    List<TagDO> queryInIds(@Param("ids") Set<Long> ids);

    void increaseRefCount(@Param("ids") Set<Long> ids);

    void decreaseRefCount(@Param("ids") Set<Long> ids);

    TagDO get(Long id);
}
