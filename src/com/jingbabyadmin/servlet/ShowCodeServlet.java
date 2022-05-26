package com.jingbabyadmin.servlet;

import com.jingbabyadmin.utils.CodeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/common/getVerificationCode")
public class ShowCodeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = CodeUtil.getCerificationCode();
        System.out.println("code = " + code);
        req.getSession().setAttribute("code",code);
        resp.getWriter().write(code);
    }
}
