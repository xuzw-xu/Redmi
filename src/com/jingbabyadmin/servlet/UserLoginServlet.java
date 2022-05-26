package com.jingbabyadmin.servlet;

import com.jingbabyadmin.service.IUserService;
import com.jingbabyadmin.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/user/*")
public class UserLoginServlet extends BaseServlet {


    public void login(HttpServletRequest req, HttpServletResponse resp){
        try {
            IUserService userService = new UserServiceImpl();

            String name = req.getParameter("username");
            String pwd = req.getParameter("password");
            String msg = userService.login(name, pwd);

            if(msg == null){
                req.getSession().setAttribute("_admin",name);
                req.getSession().setAttribute("usernameforchangepwd",name);
                resp.sendRedirect("/admin/index/index.page");
                return;
            }else{
                req.getSession().setAttribute("msg",msg);
                forward("/admin/login.jsp",req,resp);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


}
