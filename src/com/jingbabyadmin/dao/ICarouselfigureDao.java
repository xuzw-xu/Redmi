package com.jingbabyadmin.dao;

import com.jingbabyadmin.entity.CarouselFigure;

import java.sql.SQLException;
import java.util.List;

public interface ICarouselfigureDao {

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    List<CarouselFigure> list(int page, int size) throws Exception;

    /**
     * 查询总条数
     * @return
     * @throws SQLException
     */
    int count() throws SQLException;

    /**
     * 添加信息
     * @param carouselFigure
     * @throws SQLException
     */
    void add(CarouselFigure carouselFigure) throws SQLException;

    /**
     * 返回所有数据的集合
     * @return
     * @throws Exception
     */
    List<CarouselFigure> selectAll() throws Exception;

    /**
     * 删除数据
     * @param str
     * @throws SQLException
     */
    void delete(String[] str) throws SQLException;

    /**
     * 通过id查找
     * @param id
     * @return
     * @throws Exception
     */
    CarouselFigure getById(String id) throws Exception;

    /**
     * 更新数据
     * @param id
     * @param url
     * @param num
     * @throws SQLException
     */
    void update(String id,String url,int num) throws SQLException;

    /**
     * 获取前5条轮播图
     * @return
     * @throws Exception
     */
     List<CarouselFigure> listCraous() throws Exception;
}
