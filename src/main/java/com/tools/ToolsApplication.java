package com.tools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ToolsApplication {

	public static void main(String[] args) {
		System.setProperty("es.set.netty.runtime.available.processors","false");
		SpringApplication.run(ToolsApplication.class, args);
		System.out.print("*************启动成功*************");

	}

}

