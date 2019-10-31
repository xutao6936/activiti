package com.example.activiti.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        // 为了防止跨域攻击， 默认的PUT POST DELETE请求无法通过，需要携带token  现在将此功能关闭
        httpSecurity.csrf().disable().authorizeRequests().antMatchers("/*.html").authenticated().anyRequest().permitAll();
        httpSecurity.formLogin().and().httpBasic();
    }
}
