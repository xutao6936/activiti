package com.example.activiti.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    SpringContextUtils springContextUtils(){
        return new SpringContextUtils();
    }
}
