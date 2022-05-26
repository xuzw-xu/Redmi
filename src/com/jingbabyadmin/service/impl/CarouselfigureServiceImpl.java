package com.jingbabyadmin.service.impl;

import com.jingbabyadmin.dao.ICarouselfigureDao;
import com.jingbabyadmin.dao.impl.CarouselfigureDaoImpl;
import com.jingbabyadmin.entity.CarouselFigure;
import com.jingbabyadmin.entity.Page;
import com.jingbabyadmin.service.ICarouselfigureService;

import java.sql.SQLException;
import java.util.List;

public class CarouselfigureServiceImpl implements ICarouselfigureService {
    ICarouselfigureDao carouselfigureDao = new CarouselfigureDaoImpl();

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public Page<CarouselFigure> list(int page, int size) throws Exception {
        List<CarouselFigure> list = carouselfigureDao.list(page, size);
        int count = carouselfigureDao.count();
        Page<CarouselFigure> carouselFigurePage = new Page<CarouselFigure>(page, size, list, count);
        return carouselFigurePage;
    }

    /**
     * 添加数据
     * @param carouselFigure
     * @throws SQLException
     */
    public void add(CarouselFigure carouselFigure) throws SQLException {
        carouselfigureDao.add(carouselFigure);
    }

    /**
     * 查询所有数据
     * @return
     * @throws Exception
     */
    public List<CarouselFigure> selectAll() throws Exception {
        return carouselfigureDao.selectAll();
    }

    /**
     * 删除数据
     * @param str
     * @throws SQLException
     */
    public void delete(String[] str) throws SQLException {
        carouselfigureDao.delete(str);
    }

    /**
     * 根据Id查找
     * @param id
     * @return
     * @throws Exception
     */
    public CarouselFigure getById(String id) throws Exception {
        return carouselfigureDao.getById(id);
    }

    /**
     * 更新数据
     * @param id
     * @param url
     * @param num
     * @throws SQLException
     */
    public void update(String id,String url,int num) throws SQLException {
        carouselfigureDao.update(id,url,num);
    }

    /**
     * 获取前5条轮播图
     * @return
     * @throws Exception
     */
    @Override
    public List<CarouselFigure> listCraous() throws Exception {
        return carouselfigureDao.listCraous();
    }
}
