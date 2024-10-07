package org.example.jaeger_testing.rest;

import io.opentracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.jaeger_testing.dto.OperationRequest;
import org.example.jaeger_testing.service.CalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/calculator")
@RequiredArgsConstructor
public class CalculatorController {

    private final CalculatorService calculatorService;


    @PostMapping("/sum")
    public ResponseEntity<Double> sum(@RequestBody OperationRequest request) {
        return ResponseEntity.ok(calculatorService.sum(request.getA(), request.getB()));
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
