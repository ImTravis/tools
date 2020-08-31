package com.tools.spring.springAop;


import org.springframework.stereotype.Component;

@Component
public class AopBean {
    public AopBean(){
        System.out.println("\n Construct AopBean");
    }
}
