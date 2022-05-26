package com.jingbabyadmin.service.impl;

import com.jingbabyadmin.dao.IShopCartDao;
import com.jingbabyadmin.dao.impl.ShopCartDaoImpl;
import com.jingbabyadmin.service.IShopCartService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShopCartServiceImpl implements IShopCartService {
    IShopCartDao shopCartDao = new ShopCartDaoImpl();
    @Override
    public ResultSet list(String userId) throws SQLException {
        return shopCartDao.list(userId);
    }
}
