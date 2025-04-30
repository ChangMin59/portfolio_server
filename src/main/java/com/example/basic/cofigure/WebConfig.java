package com.example.basic.cofigure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/api/**")
                .allowedOrigins("https://changmin59.github.io")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*") // 🔑 헤더도 명시적으로 허용
                .exposedHeaders("Content-Type") // 🔑 필요한 경우 확장 가능
                .allowCredentials(false); //
    }
}
