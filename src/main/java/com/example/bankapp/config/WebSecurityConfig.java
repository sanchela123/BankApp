package com.example.bankapp.config;

import com.example.bankapp.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;


@Configuration
@EnableWebSecurity

public class WebSecurityConfig {

    @Autowired
    RegistrationService registrationService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        //Редирект со страницы логина при статусе авторизации=true
        httpSecurity.addFilterBefore(new LoginPageFilter(), DefaultLoginPageGeneratingFilter.class);

        httpSecurity.csrf().disable().authorizeHttpRequests()
                .requestMatchers("/css/**","/images/**").permitAll()
                .requestMatchers("/registration", "/login", "/registration_status").permitAll()
                .requestMatchers("/lk","/extrans","/lksettings","/ittrans","/manage").hasRole("USER")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/lk")
                .permitAll()
                .and()
                .logout().permitAll().logoutSuccessUrl("/login");
        return httpSecurity.build();
    }
    //При авторизации (иногда) первый раз вылетает редирект ?continue -> error page

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
        auth.userDetailsService(registrationService).passwordEncoder(bCryptPasswordEncoder);
    }

}
