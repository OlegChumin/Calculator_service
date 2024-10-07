package org.example.jaeger_testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class JaegerTestingApplication {

    public static void main(String[] args) {
        SpringApplication.run(JaegerTestingApplication.class, args);
    }

}
