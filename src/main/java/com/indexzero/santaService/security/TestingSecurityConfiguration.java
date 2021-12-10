package com.indexzero.santaService.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Profile("testing")
@Configuration
public class TestingSecurityConfiguration extends WebSecurityConfigurerAdapter {

    public void configure(WebSecurity security) throws Exception {
        security.ignoring().antMatchers("/**");
    }
    
}
