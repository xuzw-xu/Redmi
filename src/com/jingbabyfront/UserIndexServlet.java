package com.jingbabyfront;

import com.jingbabyadmin.entity.*;
import com.jingbabyadmin.service.ICarouselfigureService;
import com.jingbabyadmin.service.IFrontIndexService;
import com.jingbabyadmin.service.IProductService;
import com.jingbabyadmin.service.IProductTypeService;
import com.jingbabyadmin.service.impl.CarouselfigureServiceImpl;
import com.jingbabyadmin.service.impl.FrontServiceImpl;
import com.jingbabyadmin.service.impl.ProductServiceImpl;
import com.jingbabyadmin.service.impl.ProductTypeServiceImpl;
import com.jingbabyadmin.servlet.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/front/index")
public class UserIndexServlet extends BaseServlet {

    private IProductTypeService productTypeService = new ProductTypeServiceImpl();
    private ICarouselfigureService carouselfigureService = new CarouselfigureServiceImpl();
    private IProductService productService = new ProductServiceImpl();
    private IFrontIndexService frontIndexService = new FrontServiceImpl();

    public void index(HttpServletRequest req, HttpServletResponse resp){
        try {
            List<ProductType> productTypeName = productTypeService.getAllProductTypeName();
            List<CarouselFigure> carouselFigures = carouselfigureService.listCraous();
            List<Product> products = productService.listProductBytime();
            List<Product> orderProducts = productService.listOrderProductByNum();
            List<Product> list1 = productService.listProductByProductType("全球进口",5);
            List<Product> list2 = productService.listProductByProductType("服装服饰",12);
            List<Product> list3 = productService.listProductByProductType("护肤美妆",5);
            List<Product> list4 = productService.listProductByProductType("图书学习",12);
            List<SearchHistory> allSearchHistory = frontIndexService.getAllSearchHistory();

            req.getSession().setAttribute("allProductTypes",productTypeName);
            req.setAttribute("allcarouselFigures",carouselFigures);
            req.getSession().setAttribute("newProducts",products);
            req.setAttribute("rankings",orderProducts);
            req.setAttribute("list",list1);
            req.setAttribute("list2",list2);
            req.setAttribute("list3",list3);
            req.setAttribute("list4",list4);
            req.getSession().setAttribute("searchHistorys",allSearchHistory);

            req.getRequestDispatcher("/front/index/index.page").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
