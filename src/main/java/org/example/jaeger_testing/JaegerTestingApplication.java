package org.example.jaeger_testing;

import io.opentracing.Tracer;
import org.example.jaeger_testing.aspect.CustomTracingRestTemplateInterceptor;
import org.example.jaeger_testing.aspect.HttpTracingExtractorNew;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableAspectJAutoProxy
public class JaegerTestingApplication {

    public static void main(String[] args) {
        SpringApplication.run(JaegerTestingApplication.class, args);
    }

    // Инъекция значения из конфигурационного файла, если параметр не найден, будет "default-service-name"
    @Value("${opentracing.jaeger.service-name:default-service-name}")
    private String serviceName;

    @Bean(name = "customJaegerTracer")
    public Tracer jaegerTracer() {
        return new io.jaegertracing.Configuration(serviceName)
                .withSampler(io.jaegertracing.Configuration.SamplerConfiguration.fromEnv().withType("const").withParam(1))
                .withReporter(io.jaegertracing.Configuration.ReporterConfiguration.fromEnv().withLogSpans(true))
                .getTracer();
    }

    @Bean(name = "tracingRestTemplate")
    public RestTemplate restTemplate(@Qualifier("customJaegerTracer") Tracer tracer) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new CustomTracingRestTemplateInterceptor(tracer));
        return restTemplate;
    }

    @Bean(name = "httpTracingExtractor")
    public HttpTracingExtractorNew httpTracingExtractorNew(@Qualifier("customJaegerTracer") Tracer tracer) {
        return new HttpTracingExtractorNew(tracer);
    }

}
