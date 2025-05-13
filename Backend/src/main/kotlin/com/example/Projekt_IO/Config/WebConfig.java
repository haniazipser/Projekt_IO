package com.example.Projekt_IO.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.logging.Filter;

public class WebConfig implements WebMvcConfigurer {
    @Bean
    public ShallowEtagHeaderFilter shallowEtagFilter() {
        return new ShallowEtagHeaderFilter();
    }
}
