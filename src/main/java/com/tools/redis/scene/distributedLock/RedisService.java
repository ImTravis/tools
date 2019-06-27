package com.tools.redis.scene.distributedLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author xcc.
 * @data 2019/2/25.
 * @time 9:42.
 */
@Component
public class RedisService {

    @Autowired
    JedisPool jedisPool;

    private final static Logger logger = LoggerFactory.getLogger(RedisService.class);

    /**
     * 获取锁
     * @param lockKey
     * @param requestId
     * @return true 获取到了，false,获取不到
     */
    public boolean getLock( String lockKey,String requestId){
        boolean res = false;
        try (Jedis jedis = jedisPool.getResource()) {
            int expireTime = 10000;
            res = RedisLockHander.tryGetDistributedLock(jedis, lockKey, requestId, expireTime);
        } catch (Exception e) {
            logger.error("REDIS 启动异常："+e.getMessage());
            return res;
        }
        return res;
    }

    /**
     *
     * @param lockKey
     * @param requestId
     * @return true 释放锁成功，false 释放锁失败
     */
    public boolean releaseLock( String lockKey,String requestId){
        boolean res = false;
        try (Jedis jedis = jedisPool.getResource()) {
            res = RedisLockHander.releaseDistributedLock(jedis,lockKey,requestId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return res;
        }
        return res;
    }

}
