package com.jingbabyadmin.dao;

import com.jingbabyadmin.entity.Brand;

import java.sql.SQLException;
import java.util.List;

public interface IBrandDao {
    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    List<Brand> list(int page, int size) throws Exception;

    /**
     * 获取数据的总条数
     * @return
     * @throws SQLException
     */
    int count() throws SQLException;

    /**
     * 添加数据
     * @param id
     * @param name
     * @param type
     * @param img
     * @throws SQLException
     */
    void add(String id,String name,String type,String img) throws SQLException;

    /**
     * 删除数据
     * @param str
     * @throws SQLException
     */
    void delete(String[] str) throws SQLException;


    /**
     * 修改
     * @param id
     * @param name
     * @param type
     * @param img
     * @throws SQLException
     */
    void update(String id,String name,String type,String img) throws SQLException;

    /**
     * 根据Id查找
     * @param id
     * @return
     * @throws Exception
     */
    Brand getById(String id) throws Exception;

    /**
     * 根据name查询
     * @param name
     * @return
     * @throws Exception
     */
    Brand getByName(String name) throws Exception;
}
