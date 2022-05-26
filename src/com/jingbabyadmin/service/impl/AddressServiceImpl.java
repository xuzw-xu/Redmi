package com.jingbabyadmin.service.impl;

import com.jingbabyadmin.dao.IAddressDao;
import com.jingbabyadmin.dao.impl.AddressDaoImpl;
import com.jingbabyadmin.entity.Page;
import com.jingbabyadmin.entity.ReceivingAddress;
import com.jingbabyadmin.service.IAddressService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AddressServiceImpl implements IAddressService {
    IAddressDao addressDao = new AddressDaoImpl();

    @Override
    public Page<ReceivingAddress> list(String userid,int page, int size) throws Exception {
        List<ReceivingAddress> list = addressDao.list(userid,page,size);
        int count = addressDao.count();
        Page<ReceivingAddress> addressPage = new Page<ReceivingAddress>(page, size, list, count);
        return addressPage;
    }

    /**
     * 通过Id查找地址
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public ReceivingAddress getById(String id) throws Exception {
        return addressDao.getById(id);
    }

    /**
     * 添加地址
     * @param address
     */
    @Override
    public void add(ReceivingAddress address) throws SQLException {
        addressDao.add(address);
    }

    /**
     * 删除地址
     * @param id
     * @throws SQLException
     */
    @Override
    public void delete(String id) throws SQLException {
        addressDao.delete(id);
    }

    /**
     * 根据Id更改地址默认值
     * @param num
     * @param id
     * @throws SQLException
     */
    @Override
    public void setDefault(int num, String id) throws SQLException {
     addressDao.setDefault(num,id);
    }

    /**
     * 设置其他id的默认地址值
     * @param id
     * @throws SQLException
     */
    @Override
    public void setOtherDefault(String id) throws SQLException {
        addressDao.setOtherDefault(id);
    }

    /**
     * 更新地址
     * @param receivingAddress
     * @throws SQLException
     */
    @Override
    public void update(ReceivingAddress receivingAddress) throws SQLException {
        addressDao.update(receivingAddress);
    }

    /**
     * 根据用户名查找他的地址数
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public ResultSet selectAddressCount(String id) throws SQLException {
        return addressDao.selectAddressCount(id);
    }

}
