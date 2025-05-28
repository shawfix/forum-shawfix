package pub.shawfix.forum.domain.service;

import pub.shawfix.forum.common.enums.CacheBizTypeEn;

/**
 * @author shawfix
 * @create 2025/5/28 13:40
 * @desc
 **/
public interface CacheService {

    /**
     * 存储
     * @param bizType
     * @param key
     * @param value
     * @return
     */
    Boolean set(CacheBizTypeEn bizType, String key, String value);

    /**
     * 存储并设置超时时常（单位：秒）
     * @param bizType
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    Boolean setAndExpire(CacheBizTypeEn bizType, String key, String value, Long seconds);

    /**
     * 获取值
     * @param bizType
     * @param key
     * @return
     */
    String get(CacheBizTypeEn bizType, String key);

    /**
     * 判断是否存在
     * @param bizType
     * @param key
     * @return
     */
    Boolean exists(CacheBizTypeEn bizType, String key);

    /**
     * 删除
     * @param bizType
     * @param key
     * @return
     */
    Boolean del(CacheBizTypeEn bizType, String key);
}
