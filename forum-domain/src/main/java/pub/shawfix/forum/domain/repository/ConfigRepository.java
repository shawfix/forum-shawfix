package pub.shawfix.forum.domain.repository;

import pub.shawfix.forum.common.model.PageRequest;
import pub.shawfix.forum.common.model.PageResult;
import pub.shawfix.forum.domain.entity.Config;

import java.util.List;
import java.util.Set;

/**
 * @author shawfix
 * @create 2025/6/3 09:42
 * @desc
 **/
public interface ConfigRepository {

    /**
     * 保存
     * @param config
     */
    void save(Config config);

    /**
     * 查询
     * @param id
     * @return
     */
    Config get(Long id);

    /**
     * 更新
     * @param config
     */
    void update(Config config);

    /**
     * 根据类型查询可用
     * @param types
     * @return
     */
    List<Config> queryAvailable(Set<String> types);

    /**
     * 分页查询
     * @param pageRequest
     * @return
     */
    PageResult<Config> page(PageRequest<Config> pageRequest);
}
