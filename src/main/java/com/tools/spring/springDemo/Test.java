package com.tools.spring.springDemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(AppConfig.class);
        ac.refresh();

        AAA aaa = (AAA) ac.getBean("AAA");
        System.out.println("\nname="+aaa.getName());
//        System.out.println(ac.getBean("BBB"));//因为BBB没注入报：No bean named 'BBB' available

    }
}
