package com.jingbabyadmin.service;

import com.jingbabyadmin.entity.Brand;
import com.jingbabyadmin.entity.Page;

import java.sql.SQLException;


public interface IBrandService {
    Page<Brand> list(int page, int size) throws Exception;

    void add(String id,String name,String type,String img) throws SQLException;

    void delete(String[] str) throws SQLException;

    void update(String id,String name,String type,String img) throws SQLException;

    Brand getById(String id) throws Exception;

    Brand getByName(String name) throws Exception;
}
