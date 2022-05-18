package com.example.demo.filter;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.MDC;

@WebFilter(urlPatterns = "/*")
public class LogUUIDFilter implements Filter
{
    private static final String LOG_UUID = "log.uuid";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException
    {
        try
        {
            // Use an unique value as log ID.
            MDC.put(LOG_UUID, UUID.randomUUID().toString().replace("-", ""));
            chain.doFilter(request, response);
        }
        finally
        {
            MDC.remove(LOG_UUID);
        }
    }

    @Override
    public void destroy()
    {

    }
}
