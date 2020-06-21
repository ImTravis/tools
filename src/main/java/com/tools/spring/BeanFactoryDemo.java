package com.tools.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author xcc
 * @2020/5/25 16:20
 * 文件说明
 */
public class BeanFactoryDemo {
    public static void main(String[] args) {




        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("/mapping/PersonMapper.xml"));
//
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/mapping/PersonMapper.xml");
//
        try {
            Resource resource = new ClassPathResource("/static/Spring.xml");
//            InputStream inputStream = resource.getInputStream();
//            ClassUtils.forName("",ClassUtils.getDefaultClassLoader());
//            reader.loadBeanDefinitions(resource);

            BeanFactory a = new DefaultListableBeanFactory();
            BeanDefinitionReader reader = new XmlBeanDefinitionReader((BeanDefinitionRegistry) a);
//            ((XmlBeanDefinitionReader) reader).setValidationMode(1);
            reader.loadBeanDefinitions(resource);


        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print("ds");
    }
}
