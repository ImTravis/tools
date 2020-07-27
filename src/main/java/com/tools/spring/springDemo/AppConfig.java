package com.tools.spring.springDemo;

import org.apache.ibatis.type.Alias;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("com.tools.spring.springDemo")
@Configuration
public class AppConfig {

    @Bean(initMethod = "init")
    public AAA AAA(){
        return new AAA();
    }

}


