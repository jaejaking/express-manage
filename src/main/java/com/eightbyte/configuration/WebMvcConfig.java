package com.eightbyte.configuration;

import com.eightbyte.interceptor.CrossDomainInterceptor;
import com.eightbyte.interceptor.TimeCostInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private TimeCostInterceptor timeCostInterceptor;

    @Autowired
    private CrossDomainInterceptor crossDomainInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeCostInterceptor).addPathPatterns("/**");
        registry.addInterceptor(crossDomainInterceptor).addPathPatterns("/**");
    }
}
