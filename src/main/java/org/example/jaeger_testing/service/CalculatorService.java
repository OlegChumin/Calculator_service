package org.example.jaeger_testing.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CalculatorService {

    public double sum(double a, double b) {
        log.info("Executing sum: {} + {}", a, b);
        double result = a + b;
        log.info("Result of sum: {}", result);
        return result;
    }

    public double subtract(double a, double b) {
        log.info("Executing subtract: {} - {}", a, b);
        double result = a - b;
        log.info("Result of subtract: {}", result);
        return result;
    }

    public double multiply(double a, double b) {
        log.info("Executing multiply: {} * {}", a, b);
        double result = a * b;
        log.info("Result of multiply: {}", result);
        return result;
    }

    public double divide(double a, double b) {
        log.info("Executing divide: {} / {}", a, b);
        if (b == 0) {
            log.error("Division by zero attempted");
            throw new IllegalArgumentException("Division by zero is not allowed");
        }
        double result = a / b;
        log.info("Result of divide: {}", result);
        return result;
    }
}
