package com.tools.springDemo;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class BBB implements ApplicationContextAware {
    public BBB(){
        System.out.println("[BBB] Constructor");


    }
    @PostConstruct
    public void initPostConstruct(){
        System.out.println("\n[BBB] @PostConstruct-init [BBB] init");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("\n[BBB] implements ApplicationContextAware ，applicationContext = " + applicationContext);
    }
}
