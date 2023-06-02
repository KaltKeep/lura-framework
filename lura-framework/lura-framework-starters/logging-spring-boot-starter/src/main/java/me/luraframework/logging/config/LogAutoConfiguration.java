package me.luraframework.logging.config;

import com.cait.samps.logging.filter.AccessLogFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @description: TODO
 * @author: Liu Ran
 * @data: 10/25/22 16:45
 */
@Configuration
@EnableConfigurationProperties(LogProperties.class)
@ComponentScan(basePackages = "com.cait.samps.logging")
public class LogAutoConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "samps.logging.access", name = "enable", havingValue = "true")
    public FilterRegistrationBean<AccessLogFilter> logFilterFilterRegistrationBean(LogProperties logProperties) {
        FilterRegistrationBean<AccessLogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AccessLogFilter(logProperties));
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

}
