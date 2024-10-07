package org.example.jaeger_testing.config;

import io.opentracing.Tracer;
import io.opentracing.contrib.web.servlet.filter.TracingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TracingFilterConfig {

    private final Tracer tracer;

    public TracingFilterConfig(Tracer tracer) {
        this.tracer = tracer;
    }

//    @Bean
//    public FilterRegistrationBean<TracingFilter> tracingFilterRegistration() {
//        FilterRegistrationBean<TracingFilter> registration = new FilterRegistrationBean<TracingFilter>();
//        registration.setFilter(new TracingFilter(tracer));
//        registration.addUrlPatterns("/*");  // Применение фильтра ко всем URL
//        registration.setOrder(1);  // Можно указать приоритет выполнения
//        return registration;
//    }
}
