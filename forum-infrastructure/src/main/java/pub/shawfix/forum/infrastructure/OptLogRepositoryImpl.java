package pub.shawfix.forum.infrastructure;

import org.springframework.stereotype.Repository;
import pub.shawfix.forum.common.model.PageRequest;
import pub.shawfix.forum.domain.entity.OptLog;
import pub.shawfix.forum.domain.repository.OptLogRepository;
import pub.shawfix.forum.infrastructure.dal.dao.OptLogDAO;
import pub.shawfix.forum.infrastructure.dal.dataobject.OptLogDO;

import javax.annotation.Resource;

/**
 * @author shawfix
 * @create 2025/5/28 17:19
 * @desc
 **/
@Repository
public class OptLogRepositoryImpl implements OptLogRepository {

    @Resource
    private OptLogDAO optLogDAO;

    @Override
    public void save(OptLog optLog) {
        OptLogDO optLogDO = OptLogDO.builder()
                .content(optLog.getContent())
                .operatorId(optLog.getOperatorId())
                .type(optLog.getType().getValue())
                .build();
        optLogDO.initBase();

        optLogDAO.insert(optLogDO);
    }

    @Override
    public PageRequest<OptLog> page(PageRequest<OptLog> pageRequest) {
        return null;
    }
}
