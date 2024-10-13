package org.example.jaeger_testing.rest;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.jaeger_testing.dto.OperationRequestDTO;
import org.example.jaeger_testing.service.CalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;


@Slf4j
@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping("/sum")
    public ResponseEntity<Double> sum(@RequestBody OperationRequestDTO request) {
        double result = calculatorService.sum(request.getA(), request.getB());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/subtract")
    public ResponseEntity<Double> subtract(@RequestBody OperationRequestDTO request) {
        return ResponseEntity.ok(calculatorService.subtract(request.getA(), request.getB()));
    }

    @PostMapping("/multiply")
    public ResponseEntity<Double> multiply(@RequestBody OperationRequestDTO request) {
        return ResponseEntity.ok(calculatorService.multiply(request.getA(), request.getB()));
    }

    @PostMapping("/divide")
    public ResponseEntity<Double> divide(@RequestBody OperationRequestDTO request) {
        return ResponseEntity.ok(calculatorService.divide(request.getA(), request.getB()));
    }

}
