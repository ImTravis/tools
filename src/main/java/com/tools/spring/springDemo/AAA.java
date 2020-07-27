package com.tools.spring.springDemo;

import org.springframework.stereotype.Component;

@Component
public class AAA {
    private String name;
    private String age;

    public AAA() {
        this.name="xc";
        setAge("22");
        System.out.println("AAA Constructor");
    }

    public void init(){
        System.out.println("\ninit loading");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
