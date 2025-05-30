package pub.shawfix.forum.infrastructure.dal.dao;

import org.apache.ibatis.annotations.Param;
import pub.shawfix.forum.infrastructure.dal.dataobject.TagPostsMappingDO;

import java.util.List;
import java.util.Set;

/**
 * @author shawfix
 * @create 2025/5/30 14:40
 * @desc
 **/
public interface TagPostsMappingDAO {
    void insert(TagPostsMappingDO tagPostsMappingDO);

    List<TagPostsMappingDO> query(TagPostsMappingDO tagPostsMappingDO);

    void deleteByPostsId(@Param("postsId") Long postsId);

    void batchInsert(List<TagPostsMappingDO> tagPostsMappingDOS);

    List<TagPostsMappingDO> queryInPostsIds(@Param("postsIds") Set<Long> postsIds);

    List<TagPostsMappingDO> queryInTagIds(@Param("tagIds") Set<Long> tagIds);
}
