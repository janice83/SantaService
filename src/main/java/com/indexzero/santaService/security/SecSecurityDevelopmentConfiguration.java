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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Profile("sec")
@Configuration
@EnableWebSecurity
public class SecSecurityDevelopmentConfiguration {
    
    @Configuration
    @Order(1)
    public static class SantaAccountConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Autowired
        private SantaAccountUserDetailsService santaUserDetailsService;
        public SantaAccountConfigurationAdapter() {
            super();
        }
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            String[] whitelistGet = new String[] { 
                "/", "/index", "/customer", "/customer-register", "/santa-register",
                    "/santas"};
                    
            http.authorizeRequests()
                .antMatchers(HttpMethod.GET, whitelistGet).permitAll()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll();
            
            http.antMatcher("/santa*")
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, whitelistGet).permitAll()
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
            .antMatchers("/santa*").hasRole("SANTA")
            .and()
            .formLogin()
                .loginPage("/santa").permitAll()
                .loginProcessingUrl("/santa-login")
                .defaultSuccessUrl("/santa-profile", true)
            /* .and()
            .logout().permitAll() */
                /* .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/santa").deleteCookies("JSESSIONID")
.               invalidateHttpSession(true)  */
            .and()
                .exceptionHandling().accessDeniedPage("/403");
            
        }
        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(santaUserDetailsService).passwordEncoder(passwordEncoder());
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }
    @Configuration
    @Order(2)
    public static class CustomerAccountConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private CustomerAccountUserDetailsService customerUserDetailsService;

        public CustomerAccountConfigurerAdapter() {
            super();
            
        }
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            String[] whitelistGet = new String[] { 
                "/", "/index", "/customer", "/customer-register", "/santa-register",
                    "/santas"};
            http.authorizeRequests()
                .antMatchers(HttpMethod.GET, whitelistGet).permitAll()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll();
            http.antMatcher("/customer*")
            .authorizeRequests()
            
            .antMatchers("/customer*").hasRole("CUSTOMER")
            .and()
            .formLogin()
                .loginPage("/customer").permitAll()
                .loginProcessingUrl("/customer-login")
                .defaultSuccessUrl("/customer-profile", true)
            /* .and()
            .logout().permitAll() */
                /* .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/customer").deleteCookies("JSESSIONID")
.               invalidateHttpSession(true)  */
            .and()
                .exceptionHandling().accessDeniedPage("/403");
                
            
        }
        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(customerUserDetailsService).passwordEncoder(customerPasswordEncoder());
        }

        @Bean
        public PasswordEncoder customerPasswordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }
    
}
