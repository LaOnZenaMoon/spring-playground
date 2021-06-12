package me.lozm.global.filter;


import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.RequestFacade;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class CustomFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final String requestURI = ((RequestFacade) request).getRequestURI();
        log.debug("do Request Filtering: " + requestURI);
        chain.doFilter(request, response);
        log.debug("do Response Filtering: " + requestURI);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
