package org.spring.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Spring Boot Eureka Server 应用启动类
 *
 * Created by wangjian on 26/11/17.
 */
@EnableDiscoveryClient // Eureka Discovery Client 标识
@SpringBootApplication // Spring Boot 应用标识
@MapperScan("org.spring.springcloud.mapper")
@EnableCaching
@EnableScheduling
public class CalendarApplication {

    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(CalendarApplication.class,args);
    }
}
