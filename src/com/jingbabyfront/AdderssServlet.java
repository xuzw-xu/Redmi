package com.jingbabyfront;

import com.jingbabyadmin.entity.Page;
import com.jingbabyadmin.entity.ReceivingAddress;
import com.jingbabyadmin.entity.User;
import com.jingbabyadmin.service.IAddressService;
import com.jingbabyadmin.service.IUserService;
import com.jingbabyadmin.service.impl.AddressServiceImpl;
import com.jingbabyadmin.service.impl.UserServiceImpl;
import com.jingbabyadmin.servlet.BaseServlet;
import com.jingbabyadmin.utils.UUIDUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/front/receiveing_address/*")
public class AdderssServlet extends BaseServlet {

    private IUserService userService = new UserServiceImpl();
    private IAddressService addressService = new AddressServiceImpl();

    /**
     * 收货地址页面
     * @param req
     * @param resp
     */
    public void index(HttpServletRequest req, HttpServletResponse resp){
        try {
            User user = (User) req.getSession().getAttribute("user");
            Page<ReceivingAddress> pageInfo = getPageInfo(req, resp);
            pageInfo = addressService.list(user.getId(),pageInfo.getPage(), pageInfo.getSize());
            req.setAttribute("addressList",pageInfo);
            req.getRequestDispatcher("/front/receiving_address/deliverAddress.page").forward(req,resp);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加收货地址
     * @param req
     * @param resp
     */
    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String addressId = UUIDUtils.getId();                                   //订单id
            String receivingAddress = req.getParameter("receivingAddress");     //地址
            String name = req.getParameter("receivingPerson");                   //订单人名
            Long num = Long.parseLong( req.getParameter("mobilePhone"));        //手机号
            String isDefault = req.getParameter("isDefault");                    //默认值
            User user = (User) req.getSession().getAttribute("user");             //获取Session域里存的用户
            String userId = user.getId();                                             //用户id
            int def = 0;                                                             //默认地址

            if("on".equals(isDefault)){
                def = 1;
            }else{
                def = -1;
            }

            int count = 0;
            if(addressService.selectAddressCount(userId).next()) {
                count++;
            }

            if(count<5){
                ReceivingAddress address = new ReceivingAddress(addressId, receivingAddress, name, num, userId, def);
                addressService.add(address);
                resp.getWriter().write("添加成功！");
            }else{
                resp.getWriter().write("添加失败！该账号不能再添加新地址！");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().write("添加失败！");
        }
    }

    /**
     * 删除地址
     * @param req
     * @param resp
     */
    public void delete(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        try {
            String id = req.getParameter("id");
            addressService.delete(id);
            resp.getWriter().write("删除成功！");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            resp.getWriter().write("删除失败！");
        }
    }

    /**
     * 设置默认地址
     * @param req
     * @param resp
     */
    public void setDefault(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        try {
            String id = req.getParameter("id");
            addressService.setOtherDefault(id);
            addressService.setDefault(1,id);
            resp.getWriter().write("设置成功！");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            resp.getWriter().write("设置失败！");

        }
    }

    /**
     * 更新地址
     * @param req
     * @param resp
     */
    public void update(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        try {
            String id = req.getParameter("id");                                 //订单id
            String receivingAddress = req.getParameter("receivingAddress");     //地址
            String name = req.getParameter("receivingPerson");                   //订单人名
            Long num = Long.parseLong( req.getParameter("mobilePhone"));        //手机号
            String isDefault = req.getParameter("isDefault");                    //默认值
            User user = (User) req.getSession().getAttribute("user");             //获取Session域里存的用户
            String userId = user.getId();                                             //用户id
            int def = 0;                                                             //默认地址

            if("on".equals(isDefault)){
                def = 1;
                addressService.setOtherDefault(id);
            }else{
                def = -1;
            }

            ReceivingAddress address = new ReceivingAddress(id,receivingAddress,name,num,userId,def);
            addressService.update(address);
            resp.getWriter().write("修改成功！");

//            if(addressService.getById(id)!=null){
//                addressService.delete(id);
//                addressService.add(address);
//                resp.getWriter().write("修改成功！");
//            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("修改失败！");
        }
    }
}
