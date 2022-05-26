package com.jingbabyadmin.servlet;

import com.jingbabyadmin.entity.Page;
import com.jingbabyadmin.entity.ProductType;
import com.jingbabyadmin.service.IProductTypeService;
import com.jingbabyadmin.service.impl.ProductTypeServiceImpl;
import com.jingbabyadmin.utils.IconfontUtils;
import com.jingbabyadmin.utils.UUIDUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/productType/*")
public class ProductTypeServlet extends BaseServlet{
    IProductTypeService productTypeService = new ProductTypeServiceImpl();

    /**
     * 分页查询
     * @param req
     * @param resp
     */
    public void list(HttpServletRequest req, HttpServletResponse resp){
        try {
            Page<ProductType> pageInfo = getPageInfo(req, resp);
            pageInfo = productTypeService.pageList(pageInfo.getPage(), pageInfo.getSize());
            req.setAttribute("productTypePages",pageInfo);
            req.getRequestDispatcher("/admin/product_type/list.page").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取添加页面
     * @param req
     * @param resp
     */
    public void addPage(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<String> iconfonts = IconfontUtils.getIconfonts(req);
            req.getSession().setAttribute("iconfonts",iconfonts);
            resp.sendRedirect("/admin/product_type/add.page");
        } catch (IOException e) {
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
            String productTypeId = UUIDUtils.getId();
            String productTypeName = req.getParameter("productTypeName");
            String productTypeDesc = req.getParameter("productTypeDesc");
            String productTypeIcon = req.getParameter("productTypeIcon");
            ProductType productType = new ProductType(productTypeId,productTypeName,productTypeDesc,productTypeIcon);
            if (productTypeService.getProductType(productType) == null){
                productTypeService.add(productType);
                resp.getWriter().write("0");
                return;
            }
            resp.getWriter().write("1");

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write(e.getMessage());
        }
    }


    /**
     * 修改
     * @param req
     * @param resp
     */
    public void updatePage(HttpServletRequest req, HttpServletResponse resp){
        try {
            String id = req.getParameter("id");
            List<String> iconfonts = IconfontUtils.getIconfonts(req);
            ProductType productType = productTypeService.getById(id);
            req.setAttribute("productType",productType);
            req.getSession().setAttribute("iconfonts",iconfonts);
            req.getRequestDispatcher("/admin/product_type/update.page").forward(req,resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新商品数据分类
     * @param req
     * @param resp
     * @throws IOException
     */
    public void update (HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String productTypeId = req.getParameter("id");
            String productTypeName = req.getParameter("productTypeName");
            String productTypeDesc = req.getParameter("productTypeDesc");
            String productTypeIcon = req.getParameter("productTypeIcon");
            ProductType productType = new ProductType(productTypeId, productTypeName, productTypeDesc, productTypeIcon);
            productTypeService.updateProductType(productType);
            resp.getWriter().write("0");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            resp.getWriter().write(e.getMessage());
        }
    }

    /**
     * 删除单条商品分类数据
     * @param req
     * @param resp
     * @throws IOException
     */
    public void deleteAData(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String id = req.getParameter("id");
            productTypeService.deleteProductType(id);
            resp.getWriter().write("0");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            resp.getWriter().write(e.getMessage());
        }
    }

    /**
     * 删除所有数据
     * @param req
     * @param resp
     * @throws IOException
     */
    public void deleteAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String ids = req.getParameter("ids");
            String[] split = ids.split(",");
            productTypeService.deleteAll(split);
            resp.getWriter().write("0");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            resp.getWriter().write(e.getMessage());
        }
    }
}
