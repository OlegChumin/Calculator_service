package org.example.jaeger_testing.config;

import io.opentracing.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JaegerTracerConfig {

    @Bean
    public Tracer jaegerTracer() {
        return new io.jaegertracing.Configuration("calculator-service")
                .withSampler(io.jaegertracing.Configuration.SamplerConfiguration.fromEnv().withType("const").withParam(1))
                .withReporter(io.jaegertracing.Configuration.ReporterConfiguration.fromEnv().withLogSpans(true))
                .getTracer();
    }
}
