package pub.shawfix.forum.domain.repository;

import pub.shawfix.forum.common.model.PageRequest;
import pub.shawfix.forum.domain.entity.OptLog;

/**
 * @author shawfix
 * @create 2025/5/28 11:49
 * @desc
 **/
public interface OptLogRepository {

    void save(OptLog optLog);

    PageRequest<OptLog> page(PageRequest<OptLog> pageRequest);

}
