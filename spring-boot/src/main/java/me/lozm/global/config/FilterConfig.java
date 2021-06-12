package me.lozm.global.config;

import me.lozm.global.filter.CustomFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean(name = "commonFilterConfig")
    public FilterRegistrationBean commonFilterConfig() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new CustomFilter());
//        filterRegistrationBean.addUrlPatterns("/api");
        return filterRegistrationBean;
    }

}
