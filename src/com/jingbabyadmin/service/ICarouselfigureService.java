package com.jingbabyadmin.service;

import com.jingbabyadmin.entity.CarouselFigure;
import com.jingbabyadmin.entity.Page;

import java.sql.SQLException;
import java.util.List;

public interface ICarouselfigureService {

    Page<CarouselFigure> list(int page, int size) throws Exception;

    void add(CarouselFigure carouselFigure) throws SQLException;

    List<CarouselFigure> selectAll() throws Exception;

    void delete(String[] str) throws SQLException;

    CarouselFigure getById(String id) throws Exception;

    void update(String id,String url,int num) throws SQLException;

    List<CarouselFigure> listCraous() throws Exception;
}
