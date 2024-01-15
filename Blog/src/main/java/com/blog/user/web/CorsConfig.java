package com.blog.user.web;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//报错
//Access to XMLHttpRequest at 'http://127.0.0.1:8088/user/list' from origin
// 'http://localhost:8080' has been blocked by CORS policy:
// No 'Access-Control-Allow-Origin' header is present on the requested resource.
// xhr.js?b50d:178 GET http://127.0.0.1:8088/user/list net::ERR_FAILED
// 在学习前后端分离开发的过程中，遇到这个报错是非常正常的，当然也是比较好解决的问题，
// 莫非就是前端项目使用了一个端口，后端项目使用了一个端口，但是其实同一个ip，
// 但是不同端口之间也不能直接获取信息的，那么就要解决它。
//在Spring Boot项目中，解决非常简单，新建一个配置类，然后用注解注入即可：
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders(CorsConfiguration.ALL)
                .allowedMethods(CorsConfiguration.ALL)
                .allowCredentials(true)
                .maxAge(3600); // 1小时内不需要再预检（发OPTIONS请求）
    }
}