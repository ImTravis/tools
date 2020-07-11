package com.tools.springDemo;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;

public class Test {
    public static void main(String[] args){
//        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
//        ac.register(AppConfig.class);
//        ac.refresh();
//        AAA aaa = (AAA)ac.getBean("AAA");
//        System.out.println("\n[AAA] 获取 AAA 对象:"+ac.getBean("AAA"));
//        System.out.println("\n[AAA] remove AAA:"+ac.getBean("AAA"));
//        ac.removeBeanDefinition("AAA");
//        System.out.println(ac.getBean("BBB"));

        //通过配置文件加载Bean,持有DefaultBeanDefinitionDocumentReader对xml进行扫描解析,方法参考：doRegisterBeanDefinitions
//        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
//        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
////        reader.loadBeanDefinitions(new ClassPathResource("bean.xml"));
//        reader.loadBeanDefinitions(new FileSystemResource("E:\\javaDev\\workspace\\tools\\src\\main\\resources\\bean.xml"));
//        factory.getBean("ccc");


//        ClassPathResource classPathResource = new ClassPathResource("bean.xml");
//        XmlBeanFactory factory2 = new XmlBeanFactory(classPathResource);
//        factory2.getBean("ccc");
//        System.out.println("\n"+factory2.getBean("ccc"));

//        DefaultListableBeanFactory factory3 = new DefaultListableBeanFactory();
//        //注册指定对象
//        AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(factory3);
//        annotatedBeanDefinitionReader.register(AAA.class,BBB.class);
//        System.out.println(factory3.getBean("BBB"));

//
//        DefaultListableBeanFactory factory4 = new DefaultListableBeanFactory();
//        //扫描包注册
//        ClassPathBeanDefinitionScanner classPathBeanDefinitionScanner = new ClassPathBeanDefinitionScanner(factory4);
//        classPathBeanDefinitionScanner.scan("com.tools.springDemo");
//        System.out.println(factory4.getBean("AAA"));//AAA获取不到，AAA是通过AppConfig类@Configuration，里面的@Bean来声明实例化的



        //通过配置文件加载Bean
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        applicationContext.getBean("ccc");
        System.out.println(applicationContext.getBean("ccc"));


    }
}
