package com.tools.spring.springAop;

import org.springframework.stereotype.Service;

@Service
public class ServiceA implements ServiceInterface{
    public ServiceA(){
        System.out.println("\n ServiceA Construct");
    }

    @Override
    public String getA(){
        System.out.println("\n ServiceA Get A");
        return "";
    }
}
