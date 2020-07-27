package com.tools.springDemo;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.util.Arrays;
import java.util.Set;

public class MyClassPathScanner extends ClassPathBeanDefinitionScanner {

    public MyClassPathScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
        if (beanDefinitions.isEmpty()) {
            logger.warn("No DgbSecurity Spring Componet was found in '" + Arrays.toString(basePackages) + "' package. Please check your configuration.");
        }

        return beanDefinitions;
    }

    //定义默认的注解过滤，只扫描指定注解
    protected void registerDefaultFilters() {
        this.addIncludeFilter(new AnnotationTypeFilter(MyScanner.class));
        this.addIncludeFilter(new AnnotationTypeFilter(MyScanner2.class));
    }
}
