package com.tools.springDemo;

import org.springframework.context.annotation.Configuration;

@Configuration
@MyScanner(basePackages = "com.tools.taskQueue,com.tools.redis")
public class MyConfig {
}
