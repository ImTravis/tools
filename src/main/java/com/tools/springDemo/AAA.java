package com.tools.springDemo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//@Component
public class AAA implements InitializingBean, BeanNameAware, BeanFactoryAware, ApplicationContextAware, DisposableBean {
    private String name;
    public AAA(String name) {
        this.name = name;
        System.out.println("\n[AAA] Constructor 1");
    }
    public AAA() {
        System.out.println("\n[AAA] Constructor 2");
    }

    @PostConstruct
    public void initPostConstruct() {
        System.out.println("\n[AAA] @PostConstruct- before init AAA init");
    }


    public void init() {
        System.out.println("\n[AAA] @Bean-init_method AAA init");
    }

    @PreDestroy
    public void desTory() {
        System.out.println("\n[AAA] @PreDestroy AAA desTory");
    }


    public void des() {
        System.out.println("\n[AAA] @Bean-destroyMethod AAA desTory");
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("\n[AAA] implements DisposableBean AAA desTory");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("\n[AAA] implements InitializingBean ，afterPropertiesSet()");
    }


    private String beanName;
    private BeanFactory beanFactory;

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
        System.out.println("\n[AAA] implements BeanNameAware ，beanName = " + beanName);

    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        System.out.println("\n[AAA] implements BeanFactoryAware ，beanFactory = " + beanFactory.getClass());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("\n[AAA] implements ApplicationContextAware ，applicationContext = " + applicationContext);
    }

}
