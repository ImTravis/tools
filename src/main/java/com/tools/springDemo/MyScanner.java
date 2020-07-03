package com.tools.springDemo;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(value = {ElementType.TYPE})
public @interface MyScanner {
    String[] basePackages() default {};

    String[] value() default {};
}
