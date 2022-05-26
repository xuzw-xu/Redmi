package com.jingbabyadmin.servlet;

import com.jingbabyadmin.entity.Page;
import com.mysql.cj.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");

            String uri = req.getRequestURI();
            String methodName = uri.substring(uri.lastIndexOf("/")+1);

            Class<? extends BaseServlet> aClass = this.getClass();
            Method method = aClass.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 分页
     * @param req
     * @param resp
     * @return
     */
    protected<T> Page<T> getPageInfo(HttpServletRequest req, HttpServletResponse resp){
        String page = req.getParameter("page");
        String size = req.getParameter("size");

        Page<T> pageSet = new Page<>();

        if(!StringUtils.isNullOrEmpty(page)){
            pageSet.setPage(Integer.parseInt(page));
        }else{
            pageSet.setPage(1);
        }

        if(!StringUtils.isNullOrEmpty(size)){
            pageSet.setSize(Integer.parseInt(size));
        }else{
            pageSet.setSize(5);
        }

        return pageSet;
    }

    /**
     * 转发
     * @param url
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void forward(String url,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/" + url).forward(req,resp);
    }
}
