package com.alibaba.csp.sentinel.dashboard.rule.redis;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@EnableCaching
public class RedisUtil extends CachingConfigurerSupport {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public StringRedisTemplate getStringRedisTemplate() {
        return stringRedisTemplate;
    }

    /**
     * 存放string类型
     *
     * @param key
     * @param data
     * @param timeout
     */
    public void setString(String key, String data, Long timeout, String channel) {
        try {
            stringRedisTemplate.execute(new SessionCallback<Object>() {
                @Override
                @SuppressWarnings("unchecked")
                public Object execute(RedisOperations operations) throws DataAccessException {
                    List<Object> result = null;
                    operations.multi(); //开始事务
                    operations.opsForValue().set(key, data);
                    operations.convertAndSend(channel, data);
                    if (timeout != null) {
                        stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
                    }
                    try {
                        result = operations.exec(); //提交事务
                    } catch (Exception e) { //如果key被改变,提交事务时这里会报异常
                        operations.discard();
                    }
                    return result;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 存放string类型
     *
     * @param key
     * @param data
     */
    public void setString(String key, String data, String channel) {
        setString(key, data, null, channel);
    }

    /**
     * 根据key查询string类型
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 根据对应的key删除key
     *
     * @param key
     */
    public Boolean delKey(String key) {
        return stringRedisTemplate.delete(key);
    }
}
