package org.example.jaeger_testing.aspect;

import io.opentracing.Span;
import io.opentracing.Tracer;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class TraceIdLoggingFilter extends OncePerRequestFilter {


    private Tracer tracer;

    public TraceIdLoggingFilter(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Span activeSpan = tracer.activeSpan();
        if (activeSpan != null) {
            String traceId = activeSpan.context().toTraceId();
            log.info("Trace ID for request {}: {}", request.getRequestURI(), traceId);
        }
        filterChain.doFilter(request, response);
    }
}
