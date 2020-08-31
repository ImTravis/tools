package com.tools.springDemo;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(value = {ElementType.TYPE})
@Import(com.tools.springDemo.MyScannerRegister.class)
public @interface MyScanner {
    String[] basePackages() default {};

    String[] value() default {};
}
