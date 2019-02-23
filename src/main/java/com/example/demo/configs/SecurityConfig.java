package com.example.demo.configs;

import com.example.demo.services.CustomUserServiceImpl;
import com.example.demo.utils.MyMd5PasswordEncorder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * SpringSecurity配置 权限控制 过滤器
 * @author ggg1235
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new MyMd5PasswordEncorder();
    }

    @Bean
    UserDetailsService customUserSerice(){
        return new CustomUserServiceImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserSerice());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/register","/login","/index","/static/css/**","/static/js/**","/static/images/**","/static/ueditor/**","/config/**","/config","/static/bootstrap4.1.0/**").permitAll()
//                .antMatchers("/admin/**","/static/admin/**","/newsAPI/**").hasAnyRole("ROLE_ADMIN")
                .and()
                .formLogin().loginPage("/login").failureUrl("/login-error").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .headers().frameOptions().disable()
                .and()
                .csrf().disable();
    }

}
