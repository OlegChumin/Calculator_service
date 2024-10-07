package org.example.jaeger_testing.rest;

import io.opentracing.Span;
import io.opentracing.Tracer;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.jaeger_testing.dto.OperationRequest;
import org.example.jaeger_testing.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/calculator")
//@RequiredArgsConstructor
public class CalculatorController {

    private final CalculatorService calculatorService;

    private Tracer tracer;

    public CalculatorController(CalculatorService calculatorService, Tracer tracer) {
        this.calculatorService = calculatorService;
        this.tracer = tracer;
    }

    // Тестовый метод для создания спана вручную
    @GetMapping("/test-span")
    public String createTestSpan() {
        Span span = tracer.buildSpan("test-span").start();
        span.setTag("example", "manual span");
        span.finish();

        return "Test span created and sent to Jaeger";
    }

    // Тестовый метод для создания спана вручную
    @GetMapping("/test-span2")
    public String createTestSpan2() {
        Span span = tracer.buildSpan("test-span2").start();

        // Получение traceId
        String traceId = span.context().toTraceId();
        log.info("Trace ID: {}", traceId);

        span.setTag("example", "manual span");
        span.finish();

        return "Test span created and sent to Jaeger, Trace ID: " + traceId;
    }



//    @PostMapping("/sum")
//    public ResponseEntity<Double> sum(@RequestBody OperationRequest request) {
//        // Начинаем новый спан для метода "sum"
//        Span span = tracer.buildSpan("sum-method").start();
//
//        // Выполняем основную логику
//        double result = calculatorService.sum(request.getA(), request.getB());
//
//        // Добавляем тег с результатом
//        span.setTag("result", result);
//
//        // Завершаем спан
//        span.finish();
//
//        return ResponseEntity.ok(result);
//    }

    @PostMapping("/sum")
    public ResponseEntity<Double> sum(@RequestBody OperationRequest request, HttpServletRequest httpServletRequest) {
        log.info("Incoming request headers: {}", Collections.list(httpServletRequest.getHeaderNames()).stream()
                .collect(Collectors.toMap(h -> h, httpServletRequest::getHeader)));

        double result = calculatorService.sum(request.getA(), request.getB());
        return ResponseEntity.ok(result);
    }


    @PostMapping("/subtract")
    public ResponseEntity<Double> subtract(@RequestBody OperationRequest request) {
        return ResponseEntity.ok(calculatorService.subtract(request.getA(), request.getB()));
    }

    @PostMapping("/multiply")
    public ResponseEntity<Double> multiply(@RequestBody OperationRequest request) {
        return ResponseEntity.ok(calculatorService.multiply(request.getA(), request.getB()));
    }

    @PostMapping("/divide")
    public ResponseEntity<Double> divide(@RequestBody OperationRequest request) {
        return ResponseEntity.ok(calculatorService.divide(request.getA(), request.getB()));
    }
}
