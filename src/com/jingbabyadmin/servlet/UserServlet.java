package com.jingbabyadmin.servlet;

import com.jingbabyadmin.entity.Page;
import com.jingbabyadmin.entity.User;
import com.jingbabyadmin.service.IUserService;
import com.jingbabyadmin.service.impl.UserServiceImpl;
import com.jingbabyadmin.utils.EncryptionUtils;
import com.jingbabyadmin.utils.UUIDUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/userInformation/*")
public class UserServlet extends BaseServlet {
    IUserService userService = new UserServiceImpl();
    /**
     * 分页查询
     * @param req
     * @param resp
     */
    public void list(HttpServletRequest req, HttpServletResponse resp){
        try {
            Page<User> userInfo = getPageInfo(req, resp);
            userInfo = userService.userList(userInfo.getPage(), userInfo.getSize());
            req.setAttribute("userPages",userInfo);
            req.getRequestDispatcher("/admin/user_info/list.page").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取添加用户界面
     * @param req
     * @param resp
     */
    public void addPage(HttpServletRequest req, HttpServletResponse resp){
        try {
            resp.sendRedirect("/admin/user_info/add.page");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加用户
     * @param req
     * @param resp
     */
   public void addUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
       try {
           String id = UUIDUtils.getId();
           String name = req.getParameter("userName");
           String password = req.getParameter("password");
           String pwd = EncryptionUtils.encryptMD5(password);
           User user = new User(id, name, pwd, 1);
          if(userService.getByName(name)==null){
              userService.addUser(user);
              resp.getWriter().write("0");
          }else{
              resp.getWriter().write("1");
          }
       } catch (Exception e) {
           e.printStackTrace();
           resp.getWriter().write(e.getMessage());
       }
   }

    /**
     * 修改用户信息页面
     * @param req
     * @param resp
     */
   public void updatePage(HttpServletRequest req, HttpServletResponse resp){
       try {
           String name = req.getParameter("name");
           User user = userService.getByName(name);
            req.setAttribute("user",user);
            req.getRequestDispatcher("/admin/user_info/update.page").forward(req,resp);
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

    /**
     * 修改用户信息
     * @param req
     * @param resp
     */
   public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {

       try {
           String id = req.getParameter("id");
           String username = req.getParameter("username");
           String password = req.getParameter("password");
           String pwd = EncryptionUtils.encryptMD5(password);
           User user = userService.getByName(username);

           if(user!=null && !username.equals(userService.getById(id).getUsername())){
               resp.getWriter().write("1");
           }else{
               userService.updateUserInfo(id, username, pwd);
               resp.getWriter().write("0");
           }
       } catch (Exception e) {
           e.printStackTrace();
           resp.getWriter().write(e.getMessage());
       }
   }

    /**
     * 删除用户
     * @param req
     * @param resp
     */
   public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
       try {
           String id = req.getParameter("id");
           userService.delete(id);
           resp.getWriter().write("0");
       } catch (SQLException | IOException e) {
           e.printStackTrace();
           resp.getWriter().write(e.getMessage());
       }
   }

    /**
     * 修改登录账号密码页面
     * @param req
     * @param resp
     */
   public void changePasswordPage(HttpServletRequest req, HttpServletResponse resp){
       try {
           String name = req.getParameter("name");
           User user = userService.getByName(name);
           req.setAttribute("user",user);
           req.getRequestDispatcher("/admin/user_info/changePassword.page").forward(req,resp);
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

    /**
     * 修改登录账号的密码
     * @param req
     * @param resp
     */
   public void changeUserPwd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
       try {
           String name =  req.getParameter("type");
           String oldPassword1 = req.getParameter("oldPassword");
           oldPassword1 = EncryptionUtils.encryptMD5(oldPassword1);

           String oldPassword =userService.getByName(name).getPassword();

           String newPassword = req.getParameter("newPassword");
           newPassword = EncryptionUtils.encryptMD5(newPassword);

           String confirmPassword = req.getParameter("confirmPassword");
           confirmPassword = EncryptionUtils.encryptMD5(confirmPassword);

           if(newPassword.equals(oldPassword)){
               resp.getWriter().write("0");
           }else if(!newPassword.equals(confirmPassword)){
               resp.getWriter().write("1");
           }else if(!oldPassword.equals(oldPassword1)){
               resp.getWriter().write("3");
           }else{
               userService.changePwd(name,newPassword);
               resp.getWriter().write("2");
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
   public void logout(HttpServletRequest req, HttpServletResponse resp){
       try {
           req.getSession().removeAttribute("_admin");
           resp.sendRedirect("/admin/login.page");
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}

