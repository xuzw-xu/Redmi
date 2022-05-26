package com.jingbabyfront;

import com.jingbabyadmin.entity.Page;
import com.jingbabyadmin.entity.Product;
import com.jingbabyadmin.entity.ProductType;
import com.jingbabyadmin.service.IProductService;
import com.jingbabyadmin.service.IProductTypeService;
import com.jingbabyadmin.service.impl.ProductServiceImpl;
import com.jingbabyadmin.service.impl.ProductTypeServiceImpl;
import com.jingbabyadmin.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/front/productType/*")
public class ProductTypeServlet extends BaseServlet {

     private IProductService productService = new ProductServiceImpl();
    private IProductTypeService productTypeService = new ProductTypeServiceImpl();

    /**
     * 搜索首页
     * @param req
     * @param resp
     */
    public void index(HttpServletRequest req, HttpServletResponse resp){
        try {

            req.getRequestDispatcher("/front/product_type/product_type.page").forward(req,resp);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 加载商品详情页
     */
    public void productDetail(HttpServletRequest req, HttpServletResponse resp){
        try {
            String id = req.getParameter("id");
            String productId = req.getParameter("productId");
            String type = productService.getById(id).getProductType();
            Page<Product> pageInfo = getPageInfo(req, resp);
            pageInfo = productService.listByProtectType(type,pageInfo.getPage(), pageInfo.getSize());
            List<ProductType> productTypeName = productTypeService.getAllProductTypeName();
            Product product = productService.getById(id);
            int sum = productService.productSum(product.getId());
            int othersum = productService.productSum(productId);

            req.setAttribute("list",pageInfo);
            req.setAttribute("products",product);
            req.setAttribute("allProductTypes",productTypeName);
            req.setAttribute("sum",sum);
            req.setAttribute("othersum",othersum);
            req.getRequestDispatcher("/front/product_detail/product_detail.page").forward(req,resp);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
