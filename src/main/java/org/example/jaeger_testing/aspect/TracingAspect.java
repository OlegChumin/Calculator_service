//package org.example.jaeger_testing.aspect;
//
//import io.opentracing.Span;
//import io.opentracing.Tracer;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//
//@Slf4j
//@Aspect
//@Component
//public class TracingAspect {
//
//    private final Tracer tracer;
//
//    public TracingAspect(Tracer tracer) {
//        this.tracer = tracer;
//    }
//
//    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
//    public void restControllerPointcut() {
//        // pointcut for all methods in classes annotated with @RestController
//    }
//
//    @Pointcut("within(@org.springframework.stereotype.Service *)")
//    public void serviceLayerPointcut() {
//        // pointcut for all methods in classes annotated with @Service
//    }
//
//    @Before("restControllerPointcut() || serviceLayerPointcut()")
//    public void beforeMethod(JoinPoint joinPoint) {
//        String methodName = joinPoint.getSignature().toShortString();
//        Span span = tracer.buildSpan(methodName).start();
//        tracer.scopeManager().activate(span);
//
//        log.info("Started tracing for method: {}", methodName);
//    }
//
//    @AfterReturning("restControllerPointcut() || serviceLayerPointcut()")
//    public void afterMethod(JoinPoint joinPoint) {
//        String methodName = joinPoint.getSignature().toShortString();
//        Span span = tracer.activeSpan();
//        if (span != null) {
//            span.finish();
//        }
//
//        log.info("Finished tracing for method: {}", methodName);
//    }
//}
