package com.jingbabyadmin.service;

import com.jingbabyadmin.entity.Page;
import com.jingbabyadmin.entity.ReceivingAddress;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IAddressService {

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    Page<ReceivingAddress> list(String userid,int page, int size) throws Exception;

    /**
     * 通过Id查找地址
     * @param id
     * @return
     * @throws Exception
     */
    ReceivingAddress getById(String id) throws Exception;

    /**
     * 添加地址
     * @param address
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

    ResultSet selectAddressCount(String id) throws SQLException;
}
