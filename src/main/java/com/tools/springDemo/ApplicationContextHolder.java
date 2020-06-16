package com.tools.springDemo;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextHolder implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        System.out.println("\n[ApplicationContextHolder] implements ApplicationContextAware ，applicationContext = " + applicationContext);
    }

    public static <T> T getBean(Class<T> tClass) {
        if (applicationContext == null) {
            System.out.println("\n[ApplicationContextHolder] 为 空");
            return null;
        } else {
            return applicationContext.getBean(tClass);
        }

    }
}
