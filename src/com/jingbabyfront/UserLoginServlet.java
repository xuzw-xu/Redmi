package com.jingbabyfront;

import com.jingbabyadmin.entity.User;
import com.jingbabyadmin.service.IUserService;
import com.jingbabyadmin.service.impl.UserServiceImpl;
import com.jingbabyadmin.servlet.BaseServlet;
import com.jingbabyadmin.utils.EncryptionUtils;
import com.jingbabyadmin.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.CollationKey;

@WebServlet("/front/login/*")
public class UserLoginServlet extends BaseServlet {

    private IUserService userService = new UserServiceImpl();

    /**
     * 登录界面
     * @param req
     * @param resp
     */
    public void loginPage(HttpServletRequest req, HttpServletResponse resp){
        try {
            req.getRequestDispatcher("/front/login.page").forward(req,resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册界面
     * @param req
     * @param resp
     */
    public void registerPage(HttpServletRequest req, HttpServletResponse resp){
        try {
            req.getRequestDispatcher("/front/register.page").forward(req,resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录
     * @param req
     * @param resp
     * @throws IOException
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");

            String username = req.getParameter("username");
            String password = req.getParameter("password");
            password = EncryptionUtils.encryptMD5(password);
            String code = req.getParameter("code");
            String autoLogin = req.getParameter("autoLogin");

            User user = userService.getByName(username);

            if("on".equals(autoLogin)){
                Cookie cookie = new Cookie("JSESSIONID",req.getSession().getId());
                cookie.setPath("/");
                cookie.setMaxAge(60*60*24*7);
                req.getSession().setMaxInactiveInterval(60*60*24*7);
                resp.addCookie(cookie);
            }

            if(user==null || user.getType() == 0){
                resp.getWriter().write("账户不存在！请先注册！");
            }else if(user!=null && !password.equals(user.getPassword())){
                resp.getWriter().write("密码输入错误！请重新登录！");
            }else{
                if(!code.equalsIgnoreCase((String) req.getSession().getAttribute("code"))){
                    resp.getWriter().write("验证码输入错误！");
                }else {
                    req.getSession().setAttribute("user", user);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("登录失败！" + e.getMessage());
        }    }

    /**
     * 注册
     * @param req
     * @param resp
     */
    public void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String name = req.getParameter("name");
            String pwd = req.getParameter("password");
            String canpwd = req.getParameter("confirmPassword");
            String code = req.getParameter("code");
            User user = userService.getByName(name);

            if(user!=null){
                resp.getWriter().write("账户已存在！去登录吧！");
            }else{
                if(!pwd.equals(canpwd)){
                    resp.getWriter().write("两次密码输入不一致！再来一次！");
                }else{
                    if(!code.equalsIgnoreCase((String) req.getSession().getAttribute("code"))){
                        resp.getWriter().write("验证码输入错误！");
                    }else {
                        String id = UUIDUtils.getId();
                        pwd = EncryptionUtils.encryptMD5(pwd);
                        User newUser = new User(id,name,pwd,1);
                        userService.addUser(newUser);
                        resp.getWriter().write("0");
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write(e.getMessage());
        }

    }

    /**
     * 登出
     * @param req
     * @param resp
     */
    public void logout(HttpServletRequest req,HttpServletResponse resp){
        try {
            req.getSession().removeAttribute("user");
            resp.sendRedirect("/front/index");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
