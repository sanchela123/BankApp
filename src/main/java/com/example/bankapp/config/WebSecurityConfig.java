package com.example.bankapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }

}
