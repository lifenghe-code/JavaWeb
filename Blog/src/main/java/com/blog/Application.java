package com.blog;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient   //开启Eureka客户端
// Mapper包扫描放到application.yml配置文件中了
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}