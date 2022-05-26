package com.jingbabyadmin.servlet;

import com.alibaba.fastjson.JSONObject;
import com.jingbabyadmin.entity.Brand;
import com.jingbabyadmin.entity.Page;
import com.jingbabyadmin.entity.Product;
import com.jingbabyadmin.entity.ProductType;
import com.jingbabyadmin.service.IProductService;
import com.jingbabyadmin.service.IProductTypeService;
import com.jingbabyadmin.service.impl.ProductServiceImpl;
import com.jingbabyadmin.service.impl.ProductTypeServiceImpl;
import com.jingbabyadmin.utils.UUIDUtils;
import com.mysql.cj.util.StringUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/admin/products/*")
public class ProductServlet extends BaseServlet{
    IProductService productService = new ProductServiceImpl();
    IProductTypeService productTypeService = new ProductTypeServiceImpl();

    /**
     * 分页查询
     * @param req
     * @param resp
     */
    public void list(HttpServletRequest req, HttpServletResponse resp){
        try {
            Page<Product> pageInfo = getPageInfo(req, resp);
            List<ProductType> productType = productTypeService.getAllProductTypeName();

            String productName = req.getParameter("productName");
            String productTypes = req.getParameter("productType");

            System.out.println(productName);
            System.out.println(productTypes);

            if(!StringUtils.isNullOrEmpty(productName) && !StringUtils.isNullOrEmpty(productTypes)){
                pageInfo = productService.list(productName,productTypes,pageInfo.getPage(), pageInfo.getSize());
            }else if(StringUtils.isNullOrEmpty(productName) && !StringUtils.isNullOrEmpty(productTypes)){
                pageInfo = productService.listByProtectType(productTypes,pageInfo.getPage(), pageInfo.getSize());
            }else if(!StringUtils.isNullOrEmpty(productName) && StringUtils.isNullOrEmpty(productTypes)){
                pageInfo = productService.list(productName,pageInfo.getPage(), pageInfo.getSize());
            }else {
                pageInfo = productService.list(pageInfo.getPage(), pageInfo.getSize());
            }

            System.out.println(pageInfo);
            req.setAttribute("type",productTypes);
            req.getSession().setAttribute("productName",productName);
            req.getSession().setAttribute("productTypes",productType);
            req.setAttribute("productPages",pageInfo);
            req.getRequestDispatcher("/admin/product_info/list.page").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转到addpage界面
     * @param req
     * @param resp
     */
    public void addPage(HttpServletRequest req, HttpServletResponse resp){
        try {
            List<ProductType> productType = productTypeService.getAllProductTypeName();
            req.setAttribute("productTypes",productType);
            req.getRequestDispatcher("/admin/product_info/add.page").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过product_type查找brand
     * @param req
     * @param resp
     */
    public void getBrandByProductType(HttpServletRequest req, HttpServletResponse resp){
        try {
            String id = req.getParameter("id");
            List<Brand> list = productService.getBrandByProductType(id);
            String str = JSONObject.toJSONString(list);
            resp.getWriter().write(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 添加数据
     * @param req
     * @param resp
     */
    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String id = UUIDUtils.getId();
            String name = req.getParameter("productName");
            String productImage = req.getParameter("productImage");
            Double price = Double.parseDouble(req.getParameter("price")) ;
            String productType = req.getParameter("productType");
            String productDesc = req.getParameter("productDesc");
            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
            String productBrand = req.getParameter("productBrand");
            Product product = new Product(id,name,productImage,price,productType,productDesc,timestamp,productBrand);
            productService.add(product);
            resp.getWriter().write("0");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            resp.getWriter().write(e.getMessage());
        }
    }

    /**
     * 跳转更新页面
     * @param req
     * @param resp
     */
    public void updatePage(HttpServletRequest req, HttpServletResponse resp){
        try {
            String id = req.getParameter("id");
            Product product = productService.getById(id);
            List<ProductType> productType = productTypeService.getAllProductTypeName();
            req.setAttribute("productTypes",productType);
            req.setAttribute("product",product);
            req.getRequestDispatcher("/admin/product_info/update.page").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新功能
     * @param req
     * @param resp
     */
    public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String id = req.getParameter("id");
            String productName = req.getParameter("productName");
            String productImage = req.getParameter("productImage");
            Double price = Double.parseDouble(req.getParameter("price")) ;
            String productType = req.getParameter("productType");
            String productDesc = req.getParameter("productDesc");
            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
            String productBrand = req.getParameter("productBrand");
            Product product = new Product(id,productName,productImage,price,productType,productDesc,timestamp,productBrand);
            productService.update(product);
            resp.getWriter().write("0");
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().write(e.getMessage());
        }
    }

    /**
     * 删除
     * @param req
     * @param resp
     */
    public void delete(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        try {
            String id = req.getParameter("id");
            String[] str = {id};
            productService.delete(str);
            resp.getWriter().write("0");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            resp.getWriter().write(e.getMessage());
        }
    }

    /**
     * 删除全部
     * @param req
     * @param resp
     */
    public void deleteAll(HttpServletRequest req,HttpServletResponse resp) throws IOException {

        try {
            String ids = req.getParameter("ids");
            String[] split = ids.split(",");
            productService.delete(split);
            resp.getWriter().write("0");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            resp.getWriter().write(e.getMessage());
        }
    }

}
