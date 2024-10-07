package org.example.jaeger_testing.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;

public class JakartaFilterAdapter implements Filter {

    private final javax.servlet.Filter legacyFilter;

    public JakartaFilterAdapter(javax.servlet.Filter legacyFilter) {
        this.legacyFilter = legacyFilter;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            legacyFilter.init((javax.servlet.FilterConfig) filterConfig);
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            legacyFilter.doFilter((javax.servlet.ServletRequest) request, (javax.servlet.ServletResponse) response, (javax.servlet.FilterChain) chain);
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void destroy() {
        legacyFilter.destroy();
    }
}
