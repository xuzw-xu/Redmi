package com.jingbabyadmin.service;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IShopCartService  {
    /**
     * 返回分页查询集合
     * @return
     */
    ResultSet list(String userId) throws SQLException;

}
