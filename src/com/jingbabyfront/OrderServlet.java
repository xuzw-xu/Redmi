package com.jingbabyfront;

import com.jingbabyadmin.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/front/order/*")
public class OrderServlet extends BaseServlet{
    /**
     * 跳转到我的订单首页
     * @param req
     * @param resp
     */
    public void index(HttpServletRequest req, HttpServletResponse resp){
        try {
            req.getRequestDispatcher("/front/order/order.page").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
