package com.tools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class ToolsApplication {

	public static void main(String[] args) {

		SpringApplication.run(ToolsApplication.class, args);
		System.out.print("*************启动成功*************");

	}

	public static void run(String type,String[] args){
		Class primarySource = (new SecurityManager() {
			public Class getPrimarySource() {
				Class[] primaryClasses = this.getClassContext();
				return primaryClasses[2];
			}
		}).getPrimarySource();
		if (type.equals("none")) {
			SpringApplication springApplication = new SpringApplication(new Class[]{primarySource});
			springApplication.setWebApplicationType(WebApplicationType.NONE);
			springApplication.run(args);
			final CountDownLatch latch = new CountDownLatch(1);
			Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run() {
					latch.countDown();
				}
			});

			try {
				latch.await();
			} catch (InterruptedException var6) {
				System.out.println("应用程序错误：{}"+"应用程序启动出错（" + var6.getMessage() + "）");
			}
		}
	}

}

