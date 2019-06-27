package com.tools.redis.scene.distributedLock;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * @author xcc.
 * @data 2019/2/25.
 * @time 9:43.
 * @des 分布式锁 简单-实现类
 */
public class RedisLockHander {
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    /**
     * ms
     */
    private static final String PX_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * 尝试获取分布式锁  利用redis set值的 NX参数
     *
     * @param jedis      reids客户端
     * @param lockKey    锁
     * @param requestId  锁拥有者标识
     * @param expireTime 超期时间 ms为单位
     * @return
     */

    /**
     *
     * @des
     * 第一个为lockKey，我们使用key来当锁，因为key是唯一的。
     *
     * 第二个为value，我们传的是requestId，很多童鞋可能不明白，有key作为锁不就够了吗，为什么还要用到value？原因就是我们在上面讲到可靠性时，
     * 分布式锁要满足第四个条件解铃还须系铃人，通过给value赋值为requestId，我们就知道这把锁是哪个请求加的了，在解锁的时候就可以有依据。
     * requestId可以使用UUID.randomUUID().toString()方法生成。
     *
     * 第三个为nxxx，这个参数我们填的是NX，意思是SET IF NOT EXIST，即当key不存在时，我们进行set操作；若key已经存在，则不做任何操作；
     *
     * 第四个为expx，这个参数我们传的是PX，意思是我们要给这个key加一个过期的设置，具体时间由第五个参数决定。
     *
     * 第五个为time，与第四个参数相呼应，代表key的过期时间。
     *
     * 总的来说，执行上面的set()方法就只会导致两种结果：
     * 1. 当前没有锁（key不存在），那么就进行加锁操作，并对锁设置个有效期，同时value表示加锁的客户端。
     * 2. 已有锁存在，不做任何操作
     */
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, PX_EXPIRE_TIME, expireTime);
        return LOCK_SUCCESS.equals(result);
    }

    /**
     * 释放分布式锁  利用 LUA脚本保证操作的原子性（Redis单进程单线程并保证执行LUA脚本时不执行其它命令）
     *
     * @param jedis     redis客户端
     * @param lockKey   锁
     * @param requestId 锁拥有者标识
     * @return
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object res = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        return RELEASE_SUCCESS.equals(res);
    }

}
