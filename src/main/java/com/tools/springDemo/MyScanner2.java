package com.tools.springDemo;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(value = {ElementType.TYPE})
public @interface MyScanner2 {
    String[] basePackages() default {};

    String[] value() default {};
}
