package com.jingbabyadmin.dao;

import com.jingbabyadmin.entity.ReceivingAddress;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IAddressDao {

    /**
     * 通过Id查找地址
     * @param id
     * @return
     * @throws Exception
     */
    ReceivingAddress getById(String id) throws Exception;

    /**
     * 根据userId判断用户添加的地址条数
     * @param userID
     * @return
     */
    ResultSet selectAddressCount(String userID) throws SQLException;

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    List<ReceivingAddress> list(String userid,int page, int size) throws Exception;

    /**
     * 获取数据的总条数
     * @return
     * @throws SQLException
     */
    int count() throws SQLException;


    /**
     * 添加地址
     * @param address
     * @throws SQLException
     */
    void add(ReceivingAddress address) throws SQLException;


    /**
     * 删除地址
     * @param id
     * @throws SQLException
     */
    void delete(String id) throws SQLException;

    /**
     * 根据Id更改地址默认值
     * @param num
     * @param id
     * @throws SQLException
     */
    void setDefault(int num, String id) throws SQLException;

    /**
     * 设置其他id的地址默认值
     * @param id
     * @throws SQLException
     */
    void setOtherDefault(String id) throws SQLException;

    /**
     * 更新地址
     * @param receivingAddress
     */
    void update(ReceivingAddress receivingAddress) throws SQLException;
}
