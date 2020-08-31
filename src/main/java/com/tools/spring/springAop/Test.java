package com.tools.spring.springAop;

import com.tools.springDemo.AAA;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.scan("com.tools.spring.springAop");
        ac.refresh();


        ServiceA serviceA = (ServiceA)ac.getBean("serviceA");
        System.out.println("\n[ServiceA] 获取 ServiceA 对象:"+ac.getBean("serviceA"));
        serviceA.getA();
        ac.removeBeanDefinition("serviceA");
    }
}
