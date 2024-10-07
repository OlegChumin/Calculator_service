package org.example.jaeger_testing.config;

import io.opentracing.Tracer;
import io.opentracing.contrib.web.servlet.filter.TracingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;


@Configuration
public class JaegerConfig {

    @Bean
    public Tracer jaegerTracer() {
        return new io.jaegertracing.Configuration("calculator-service")
                .withSampler(io.jaegertracing.Configuration.SamplerConfiguration.fromEnv().withType("const").withParam(1))
                 .withReporter(io.jaegertracing.Configuration.ReporterConfiguration.fromEnv().withLogSpans(true))
                .getTracer();
    }

    @Bean
    public Filter tracingFilter(Tracer tracer) {
        return new TracingFilter(tracer);
    }
}
