package com.anhoo.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * author XueYuan
 * data  2017/05/03 20:48
 */
public class Is2Back implements javax.servlet.Filter {


    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Is2Back 过滤器被调用了");

        filterChain.doFilter(servletRequest, servletResponse);

    }

    public void destroy() {

    }
}
