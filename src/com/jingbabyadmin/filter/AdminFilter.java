package com.jingbabyadmin.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {
    String[] str = {"/admin/login.page","/admin/user/login"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       HttpServletRequest req = (HttpServletRequest)servletRequest;
       HttpServletResponse resp = (HttpServletResponse)servletResponse;

        Object admin = req.getSession().getAttribute("_admin");

        String requestURI = req.getRequestURI();
        for(String uri:str){
            if(uri.equals(requestURI)){
                filterChain.doFilter(req,resp);
                return;
            }
        }

        if(admin == null){
            resp.sendRedirect("/admin/login.page");
            return;
        }

        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {

    }
}
