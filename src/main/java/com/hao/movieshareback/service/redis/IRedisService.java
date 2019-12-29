package com.hao.movieshareback.service.redis;

import com.hao.movieshareback.vo.redis.RedisVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRedisService {
    /**
     * findById
     * @param key 键
     * @return /
     */
    Page findByKey(String key, Pageable pageable);

    /**
     * findById
     * @param key 键
     * @return /
     */
    List<RedisVo> findByKey(String key);

    /**
     * 查询验证码的值
     * @param key 键
     * @return /
     */
    String getCodeVal(String key);

    /**
     * 保存验证码
     * @param key 键
     * @param val 值
     */
    void saveCode(String key, Object val);

    /**
     * delete
     * @param key 键
     */
    void delete(String key);

    /**
     * 清空缓存
     */
    void deleteAll();

    void saveMailVadateToken(String key, Object val);

    String getMailVadateToken(String key);

    void validateCode(String value,String uuid);
}
