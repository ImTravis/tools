package com.tools.spring.postProcessor;

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
public class MyInfoBeanPostProcessor implements BeanPostProcessor {
    private final static Logger logger = LoggerFactory.getLogger(MyInfoBeanPostProcessor.class);
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        logger.info("\n自定义后置处理：Before:"+beanName+":"+bean.getClass());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        logger.info("\n自定义后置处理：After:"+beanName+":"+bean.getClass());
        return bean;
    }
}
