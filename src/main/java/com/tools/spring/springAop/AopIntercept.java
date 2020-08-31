package com.tools.spring.springAop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopIntercept {
    public AopIntercept(){
        System.out.println("\n AopIntercept Construct");
    }

    @Pointcut("execution(* com.tools.spring.springAop.ServiceA.*(..))")
    public void pointCut(){};

    @Before("pointCut()")
    public void getABefore(){
        System.out.println("\n Aop Before");
    }

    @After("pointCut()")
    public void getAAfter(){
        System.out.println("\n Aop After");
    }


}
