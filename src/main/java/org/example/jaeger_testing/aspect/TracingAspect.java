package org.example.jaeger_testing.aspect;

import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TracingAspect {

    @Autowired
    private Tracer tracer;

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping) || @annotation(org.springframework.web.bind.annotation.PostMapping)")
    public Object traceMethod(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();

        // Проверяем наличие активного спана перед созданием нового
        Span activeSpan = tracer.activeSpan();
        if (activeSpan != null) {
            log.info("Active span found before creating a new one: {}", activeSpan.context().toTraceId());
        } else {
            log.warn("No active span found before creating a new one!");
        }

        // Создаем новый спан
        Span span = tracer.buildSpan(methodName).start();
        log.info("Started tracing method: {}", methodName);

        // Активируем новый спан в Scope
        try (Scope scope = tracer.scopeManager().activate(span)) {
            // Убедимся, что спан активен
            Span currentSpan = tracer.activeSpan();
            if (currentSpan != null) {
                log.info("Active span during method execution: {}", currentSpan.context().toTraceId());
            }

            // Выполняем целевой метод
            return pjp.proceed();
        } catch (Throwable throwable) {
            // В случае ошибки помечаем спан как ошибочный
            span.setTag("error", true);
            throw throwable;
        } finally {
            // Закрываем спан
            span.finish();
            log.info("Finished tracing method: {}", methodName);
        }
    }
}
