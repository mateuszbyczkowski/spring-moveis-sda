package com.kss.springmovies.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .authorizeRequests()

                .antMatchers(HttpMethod.POST, "/movies/**", "/movie/**", "/result/**").permitAll()
                .antMatchers(HttpMethod.GET, "/movies/**", "/movie/**", "/result/**").permitAll()

                .antMatchers("/", "/index").permitAll()

                .antMatchers("/hello", "/actor/**").hasRole("USER")
                .antMatchers("/api/**").hasRole("ADMIN")

                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login-error");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}admin").roles("ADMIN", "USER");
    }
}
