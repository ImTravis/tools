package com.tools.config.dataSource;

import java.lang.annotation.*;

/**
 * @author xcc.
 * @data 2019/3/6.
 * @time 15:18.
 * @des 作用于类、接口或者方法上
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String name();
}
