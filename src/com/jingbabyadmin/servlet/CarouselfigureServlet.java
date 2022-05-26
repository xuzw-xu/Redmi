package com.jingbabyadmin.servlet;

import com.jingbabyadmin.entity.CarouselFigure;
import com.jingbabyadmin.entity.Page;
import com.jingbabyadmin.service.ICarouselfigureService;
import com.jingbabyadmin.service.impl.CarouselfigureServiceImpl;
import com.jingbabyadmin.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/carousels/*")
public class CarouselfigureServlet extends BaseServlet {

    ICarouselfigureService carouselfigureService = new CarouselfigureServiceImpl();

    /**
     * 分页查询
     * @param req
     * @param resp
     */
    public void list(HttpServletRequest req, HttpServletResponse resp){
        try {
            Page<CarouselFigure> carouselFigurePage = getPageInfo(req, resp);
            carouselFigurePage = carouselfigureService.list(carouselFigurePage.getPage(), carouselFigurePage.getSize());
            req.setAttribute("CarouselFigurePages",carouselFigurePage);
            req.getRequestDispatcher("/admin/carousels_info/list.page").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * addPage
     * @param req
     * @param resp
     */
    public void addPage(HttpServletRequest req, HttpServletResponse resp){
        try {
            req.getRequestDispatcher("/admin/carousels_info/add.page").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
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
            String id = UUIDUtils.getId();
            Integer num = Integer.parseInt(req.getParameter("sequenceNum"));
            String img = req.getParameter("carouselsImg");

            CarouselFigure carouselFigure = new CarouselFigure();
            carouselFigure.setId(id);
            carouselFigure.setSequenceNum(num);
            carouselFigure.setUrl(img);

            List<CarouselFigure> list = carouselfigureService.selectAll();
            for(CarouselFigure car:list){
                if(num.equals(car.getSequenceNum())){
                    resp.getWriter().write("1");
                    return;
                }
            }
            carouselfigureService.add(carouselFigure);
            resp.getWriter().write("0");
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write(e.getMessage());
        }
    }

    /**
     * 删除单条
     * @param req
     * @param resp
     */
    public void delete(HttpServletRequest req, HttpServletResponse resp){
        try {
            String id = req.getParameter("id");
            String[] str = {id};
            carouselfigureService.delete(str);
            resp.getWriter().write("0");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 删除多条
     * @param req
     * @param resp
     * @throws IOException
     */
    public void deleteAll(HttpServletRequest req,HttpServletResponse resp) throws IOException {

        try {
            String ids = req.getParameter("ids");
            String[] split = ids.split(",");
            carouselfigureService.delete(split);
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
            CarouselFigure carouselFigure = carouselfigureService.getById(id);
            req.setAttribute("carouselfigure",carouselFigure);
            req.getRequestDispatcher("/admin/carousels_info/update.page").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        try {
            String id = req.getParameter("id");
            String url = req.getParameter("url");
            Integer num =Integer.parseInt( req.getParameter("sequenceNum"));
            carouselfigureService.update(id,url,num);
            resp.getWriter().write("0");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            resp.getWriter().write(e.getMessage());
        }
    }
}
