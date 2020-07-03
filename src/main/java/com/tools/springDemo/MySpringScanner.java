package com.tools.springDemo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class MySpringScanner implements BeanDefinitionRegistryPostProcessor {

    private List<String> basePackages = new ArrayList<>();

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        //追加自定义路径扫描，只扫描自定义注解
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry,false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(MyScanner.class));
//        scanner.addIncludeFilter(new AnnotationTypeFilter(Component.class));
//        scanner.setIncludeAnnotationConfig(false);
        int count = scanner.scan("com.tools.taskQueue");

        System.out.println("\n ClassPathBeanDefinitionScanner 通过自定义注解 装配了:" + count+"个Bean");
        parsePackagePath(registry);
    }

    public void parsePackagePath(BeanDefinitionRegistry registry){

        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) registry;

        String[] configurations = beanFactory.getBeanNamesForAnnotation(Configuration.class);
        for (String configuration : configurations) {

            //获取MyScanner注解的basePackages
            BeanDefinition definition = beanFactory.getBeanDefinition(configuration);
            if(AnnotatedBeanDefinition.class.isAssignableFrom(definition.getClass())){
                AnnotatedBeanDefinition bdf = (AnnotatedBeanDefinition)definition;
                Class beanClass ;
                try {
                    beanClass = Class.forName(bdf.getBeanClassName());
                    beanClass.getAnnotations();
                    System.out.println("\ndsds");

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            MyScanner myScanner = beanFactory.getBean(configuration).getClass().getAnnotation(MyScanner.class);
            if (myScanner != null) {
                String[] basePackage = myScanner.basePackages();
                if (basePackage.length == 0) {
                    System.out.println("\n MyScanner 未配置");
                    Collections.addAll(basePackages, basePackage);
                }

            }

        }

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        configurableListableBeanFactory.getBean("appConfig").getClass().getAnnotations();
        System.out.println("\n postProcessBeanFactory:");
    }
}
