package com.jingbabyfront;

import com.jingbabyadmin.entity.User;
import com.jingbabyadmin.service.IAddressService;
import com.jingbabyadmin.service.IShopCartService;
import com.jingbabyadmin.service.impl.AddressServiceImpl;
import com.jingbabyadmin.service.impl.ShopCartServiceImpl;
import com.jingbabyadmin.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/front/shopCart/*")
public class ShopCartServlet extends BaseServlet {
    private IAddressService addressService = new AddressServiceImpl();
    private IShopCartService shopCartService = new ShopCartServiceImpl();
    /**
     * 购物车页面
     * @param req
     * @param resp
     */
    public void shopProductCart(HttpServletRequest req, HttpServletResponse resp){
        try {
            User user = (User) req.getSession().getAttribute("user");
            req.getRequestDispatcher("/front/shop_cart/shop_cart.page").forward(req,resp);

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
