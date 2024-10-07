package org.example.jaeger_testing.aspect;

import io.jaegertracing.Configuration;
import io.opentracing.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class JaegerConfig {

    @Bean
    public Tracer jaegerTracer() {
        return new Configuration("calculator-service")
                .withSampler(Configuration.SamplerConfiguration.fromEnv().withType("const").withParam(1))
                .withReporter(Configuration.ReporterConfiguration.fromEnv().withLogSpans(true))
                .getTracer();
    }
}
