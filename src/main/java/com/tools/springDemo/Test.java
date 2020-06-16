package com.tools.springDemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(AppConfig.class);
        ac.refresh();
        AAA aaa = (AAA)ac.getBean("AAA");


        System.out.println("\n[AAA] 获取 AAA 对象:"+ac.getBean("AAA"));

        System.out.println("\n[AAA] remove AAA:"+ac.getBean("AAA"));
        ac.removeBeanDefinition("AAA");
//        System.out.println(ac.getBean("BBB"));//因为BBB没注入报：No bean named 'BBB' available

    }
}
