package org.example.snsproject.config;

import org.example.snsproject.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //登录与注册不拦截
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/login", "/register", "/logout", "/articles",
                "/articles/hot", "/articles/new", "/tags/hot", "/articles/listArchives", "/articles/view/{id}", "/tags/detail",
                "/tags/detail/{id}", "/categorys/detail", "/categorys/detail/{id}", "/comments/article/{id}"
                );
    }
}
