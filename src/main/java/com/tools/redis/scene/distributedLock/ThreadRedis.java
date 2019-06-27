package com.tools.redis.scene.distributedLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @author xcc.
 * @data 2019/2/25.
 * @time 9:38.
 * @des 分布式锁的测试类
 */
public class ThreadRedis extends Thread {

    private final static Logger logger = LoggerFactory.getLogger(ThreadRedis.class);

    @Autowired
    private RedisService redisService;

    public ThreadRedis(RedisService service) {
        this.redisService = service;
    }


    @Override
    public void run(){
        String localKey = "go_time";
        String requestId = UUID.randomUUID().toString();
        boolean result = redisService.getLock(localKey,requestId);
        if(result){
            logger.info(Thread.currentThread().getName() +"加锁成功+获取到锁");
            logger.info("开始业务处理\n");
            logger.info("业务处理结束\n");

            boolean resultRes = redisService.releaseLock(localKey,requestId);
            if(resultRes){
                logger.info("释放成功");
            }

        }else{
            logger.info(Thread.currentThread().getName() +"未获取到锁");
        }
    }
}