package com.jingbabyadmin.servlet;

import com.jingbabyadmin.entity.Brand;
import com.jingbabyadmin.entity.Page;
import com.jingbabyadmin.entity.ProductType;
import com.jingbabyadmin.service.IBrandService;
import com.jingbabyadmin.service.IProductTypeService;
import com.jingbabyadmin.service.impl.BrandServiceImpl;
import com.jingbabyadmin.service.impl.ProductTypeServiceImpl;
import com.jingbabyadmin.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/brands/*")
public class BrandServlet extends BaseServlet {
    IBrandService brandService = new BrandServiceImpl();
    IProductTypeService productTypeService = new ProductTypeServiceImpl();

    /**
     * 轮播图列表分页查询
     * @param req
     * @param resp
     */
    public void list(HttpServletRequest req, HttpServletResponse resp){
        try {
            Page<Brand> pageInfo = getPageInfo(req, resp);
            pageInfo = brandService.list(pageInfo.getPage(), pageInfo.getSize());
            req.setAttribute("BrandPages",pageInfo);
            req.getRequestDispatcher("/admin/brand_info/list.page").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 新增的页面
     * @param req
     * @param resp
     */
    public void addPage(HttpServletRequest req,HttpServletResponse resp){
        try {
            List<ProductType> list = productTypeService.getAllProductTypeName();
            req.setAttribute("productTypes",list);
            req.getRequestDispatcher("/admin/brand_info/add.page").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *新增
     *
     */
    public void add(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        try {
            String id = UUIDUtils.getId();
            String name = req.getParameter("brandName");
            String type = req.getParameter("brandType");
            String img = req.getParameter("brandImg");
            brandService.add(id,name,type,img);
            resp.getWriter().write("0");
        } catch (Exception e) {
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
            brandService.delete(str);
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
            brandService.delete(split);
            resp.getWriter().write("0");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            resp.getWriter().write(e.getMessage());
        }
    }

    /**
     * 更新页面
     * @param req
     * @param resp
     */
    public void updatePage(HttpServletRequest req,HttpServletResponse resp){
        try {
            String id = req.getParameter("id");
            Brand brand = brandService.getById(id);
            List<ProductType> list = productTypeService.getAllProductTypeName();
            req.setAttribute("productTypes",list);
            req.setAttribute("brand",brand);
            req.getRequestDispatcher("/admin/brand_info/update.page").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新
     * @param req
     * @param resp
     */
    public void update(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        try {
            String id = req.getParameter("id");
            String name = req.getParameter("brandName");
            String type = req.getParameter("brandType");
            String img = req.getParameter("brandImg");
           if(brandService.getByName(name)==null){
               brandService.update(id,name,type,img);
               resp.getWriter().write("0");
           }else{
               resp.getWriter().write("1");
           }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write(e.getMessage());
        }
    }
}
