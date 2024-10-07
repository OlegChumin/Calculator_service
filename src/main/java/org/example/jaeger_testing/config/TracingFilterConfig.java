package org.example.jaeger_testing.config;

import io.opentracing.Tracer;
import io.opentracing.contrib.web.servlet.filter.TracingFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;


@Configuration
public class TracingFilterConfig {

    private final Tracer tracer;

    public TracingFilterConfig(Tracer tracer) {
        this.tracer = tracer;
    }

    @Bean
    public TracingFilter tracingFilter(Tracer tracer) {
        return new TracingFilter(tracer);
    }

//    @Bean
//    public FilterRegistrationBean<Filter> tracingFilterRegistration() {
//        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
//        registration.setFilter(new JakartaFilterAdapter(new TracingFilter(tracer)));
//        registration.addUrlPatterns("/*");  // Применение фильтра ко всем URL
//        registration.setOrder(1);  // Можно указать приоритет выполнения
//        return registration;
//    }
}
