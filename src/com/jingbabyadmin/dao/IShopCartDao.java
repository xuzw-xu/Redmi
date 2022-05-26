package com.jingbabyadmin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IShopCartDao {

    /**
     * 返回分页查询集合
     * @return
     */
    ResultSet list(String userId) throws SQLException;

}
