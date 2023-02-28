package com.example.bankapp.config;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/authorithation").setViewName("sign_in");
        registry.addViewController("/registration").setViewName("sign_up");
    }
}
