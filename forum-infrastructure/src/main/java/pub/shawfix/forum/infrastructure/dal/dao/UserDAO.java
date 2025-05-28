package pub.shawfix.forum.infrastructure.dal.dao;

import org.apache.ibatis.annotations.Param;
import pub.shawfix.forum.infrastructure.dal.dataobject.UserDO;

import java.util.List;
import java.util.Set;

/**
 * @author shawfix
 * @create 2025/5/28 16:56
 * @desc
 **/
public interface UserDAO {

    void insert(UserDO userDO);

    UserDO get(@Param("id") Long id);

    UserDO getByEmail(@Param("email") String email);

    void update(UserDO userDO);

    List<UserDO> queryInIds(@Param("ids") Set<Long> ids);

    List<UserDO> queryOrderLastLoginTIme();

    List<UserDO> query(UserDO userDO);
}
