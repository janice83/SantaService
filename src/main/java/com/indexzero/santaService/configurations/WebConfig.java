package com.indexzero.santaService.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.PriorityOrdered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        /* registry.addViewController("/login").setViewName("santa-claus");
        registry.setOrder(PriorityOrdered.HIGHEST_PRECEDENCE); */
    }
    
}
