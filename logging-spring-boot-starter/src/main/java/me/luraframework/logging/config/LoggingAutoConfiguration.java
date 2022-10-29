package me.luraframework.logging.config;

import me.luraframework.logging.filter.RequestLogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LoggingAutoConfiguration {

    @Bean
    public FilterRegistrationBean<RequestLogFilter> requestLogFilterFilter() {
        FilterRegistrationBean<RequestLogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestLogFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
