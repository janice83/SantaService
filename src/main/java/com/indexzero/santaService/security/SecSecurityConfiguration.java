package com.indexzero.santaService.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Profile("sec")
@EnableWebSecurity
public class SecSecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    private CustomUserAccountUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] whitelistGet = new String[] {
             "/", "/index", "/customer", "/customer-register", "/santa-register",
                "/santas", "/santa" };

        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();

        http.authorizeRequests().antMatchers("/h2-console", "/h2-console/**").permitAll();

        http.authorizeRequests()
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
            .antMatchers(whitelistGet).permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/login-page")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/success", true)
                .permitAll();
            

        
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
