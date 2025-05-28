package pub.shawfix.forum.infrastructure.dal.dao;

import pub.shawfix.forum.infrastructure.dal.dataobject.OptLogDO;

import java.util.List;

/**
 * @author shawfix
 * @create 2025/5/28 17:21
 * @desc
 **/
public interface OptLogDAO {

    void insert(OptLogDO optLogDO);

    List<OptLogDO> query(OptLogDO optLogDO);
}
