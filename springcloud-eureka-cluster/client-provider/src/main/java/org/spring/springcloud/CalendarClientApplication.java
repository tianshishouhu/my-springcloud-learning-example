package org.spring.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Spring Boot Eureka Server 应用启动类
 * 
 * Created by wangjian on 26/11/17.
 */
@EnableEurekaClient
// Eureka Client 标识
@SpringBootApplication
// Spring Boot 应用标识
@EnableFeignClients
public class CalendarClientApplication {

	public static void main(String[] args) {
		// 程序启动入口
		// 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
		SpringApplication.run(CalendarClientApplication.class, args);
	}
}
