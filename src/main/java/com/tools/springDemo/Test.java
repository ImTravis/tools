package com.tools.springDemo;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

public class Test {
    public static void main(String[] args){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(AppConfig.class);
        ac.refresh();
        AAA aaa = (AAA)ac.getBean("AAA");

//
//        System.out.println("\n[AAA] 获取 AAA 对象:"+ac.getBean("AAA"));
//
//        System.out.println("\n[AAA] remove AAA:"+ac.getBean("AAA"));
//        ac.removeBeanDefinition("AAA");
//        System.out.println(ac.getBean("BBB"));//因为BBB没注入报：No bean named 'BBB' available

        //通过配置文件加载Bean,持有DefaultBeanDefinitionDocumentReader对xml进行扫描解析,方法参考：doRegisterBeanDefinitions
//        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
//        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
//        reader.loadBeanDefinitions(new FileSystemResource("E:\\javaDev\\workspace\\tools\\src\\main\\resources\\bean.xml"));
//        factory.getBean("ccc");


        //通过配置文件加载Bean
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
//        applicationContext.getBean("ccc");
//        System.out.println("ccc");


    }
}
