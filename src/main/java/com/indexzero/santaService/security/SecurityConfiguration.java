package com.indexzero.santaService.security;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Profile("sec")
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
            .antMatchers(HttpMethod.GET, "/santa-profile").fullyAuthenticated()
            .antMatchers(HttpMethod.GET, "/").permitAll()
            .antMatchers(HttpMethod.GET, "/index").permitAll()
            .antMatchers(HttpMethod.GET, "/santa").permitAll()
            .antMatchers(HttpMethod.GET, "/customer").permitAll()
            .antMatchers(HttpMethod.GET, "/santa-register").permitAll()
            .antMatchers(HttpMethod.GET, "/customer-register").permitAll()
            .anyRequest().fullyAuthenticated();
        http.formLogin()
            .permitAll();
    }
    
}
