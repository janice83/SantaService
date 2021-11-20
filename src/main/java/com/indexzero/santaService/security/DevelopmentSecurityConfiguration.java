package com.indexzero.santaService.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Profile("dev")
@EnableWebSecurity
public class DevelopmentSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /* @Configuration
    @Order(80)
    public static class SantaAccountSecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Autowired
        private SantaAccountUserDetailsService userDetailsService;

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
                .antMatchers(HttpMethod.GET, whitelistGet).permitAll()
                .antMatchers(HttpMethod.POST, "/santa-register", "/customer-register").permitAll()
                .antMatchers(HttpMethod.POST, "/santa-profile", "/customer-profile").permitAll()
                .antMatchers(HttpMethod.GET, "/santa-profile").hasRole("SANTA")
                .antMatchers(HttpMethod.GET, "/customer-profile").hasRole("CUSTOMER")
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin()
                    .loginPage("/santa/login")
                    .defaultSuccessUrl("/santa-profile", true).permitAll()
                    //.failureUrl("/santa-login?error")
                .and()
                .logout()
                    .logoutSuccessUrl("/santa")
                    .clearAuthentication(true)
                    .logoutUrl("/logout").permitAll();
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(devSantaPasswordEncoder());
        }

        @Bean
        public PasswordEncoder devSantaPasswordEncoder() {
            return new BCryptPasswordEncoder();
        }

    }

    @Configuration
    @Order(81)
    public static class CustomerAccountSecurityConfiguration extends WebSecurityConfigurerAdapter {
        
        @Autowired
        private CustomerAccountUserDetailsService userDetailsService;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                .authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers("/customer","/customer/**").permitAll()
                .antMatchers(HttpMethod.GET, "/customer-profile").authenticated()
                .antMatchers(HttpMethod.POST, "/customer-login").permitAll()
                .anyRequest().hasRole("CUSTOMER")
                .and()
                .formLogin()
                    .loginPage("/customer/login")
                    .defaultSuccessUrl("/customer-profile", true).permitAll();
                    //.failureUrl("/customer/login?error");
                
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(devCustomerPasswordEncoder());
        }

        @Bean
        public PasswordEncoder devCustomerPasswordEncoder() {
            return new BCryptPasswordEncoder();
        }
    } */

}
