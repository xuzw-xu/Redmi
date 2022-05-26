package com.jingbabyadmin.dao.impl;

import com.jingbabyadmin.dao.IShopCartDao;
import com.jingbabyadmin.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ShopCartDaoImpl implements IShopCartDao {

    @Override
    public ResultSet list(String userId) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        ResultSet resultSet = JdbcUtils.excuteQuery(conn, "SELECT\n" +
                "\tsp.id,sp.product_name,sp.product_image,sp.price,sscp.product_num,ssc.id \n" +
                "FROM\n" +
                "\ts_user suer\n" +
                "\tLEFT JOIN s_shop_cart ssc ON suer.id = ssc.user_id\n" +
                "\tLEFT JOIN s_shop_cart_product sscp ON ssc.cart_id = sscp.shop_cart_id\n" +
                "\tLEFT JOIN s_product sp ON sscp.product_id = sp.id \n" +
                "WHERE\n" +
                "\tsuer.id = ?",userId);
//        JdbcUtils.close(conn);
        return resultSet;
    }



}
