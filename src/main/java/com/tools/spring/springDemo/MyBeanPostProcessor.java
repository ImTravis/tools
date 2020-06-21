package com.tools.spring.springDemo;

import com.tools.redis.scene.distributedLock.ThreadRedis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author xcc
 * @2020/6/12 16:19
 * 文件说明
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    private final static Logger logger = LoggerFactory.getLogger(MyBeanPostProcessor.class);
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        logger.info("\n自定义后置处理：Before:"+beanName+":"+bean.getClass());
        if(bean instanceof AAA){
            AAA aaa = (AAA)bean;
            logger.info("\n自定义后置处理：Before"+aaa.getName());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        logger.info("\n自定义后置处理：After:"+beanName+":"+bean.getClass());
        if(bean instanceof AAA){
            AAA aaa = (AAA)bean;
            logger.info("\n自定义后置处理：After"+aaa.getName());
        }
        return bean;
    }
}
