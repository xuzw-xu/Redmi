package com.jingbabyadmin.dao.impl;

import com.jingbabyadmin.dao.IAddressDao;
import com.jingbabyadmin.entity.ReceivingAddress;
import com.jingbabyadmin.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AddressDaoImpl implements IAddressDao {



    /**
     * 通过Id查找地址
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public ReceivingAddress getById(String id) throws Exception {
        Connection conn = JdbcUtils.getConn();
        ReceivingAddress address = JdbcUtils.getBean(conn, ReceivingAddress.class, "select * from s_receiving_address where id = ?", id);
        JdbcUtils.close(conn);
        return address;
    }

    /**
     * 根据用户名查找他的地址数
     * @param userID
     * @return
     * @throws SQLException
     */
    @Override
    public ResultSet selectAddressCount(String userID) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        ResultSet resultSet = JdbcUtils.excuteQuery(conn, "select * from s_receiving_address where user_id = ?", userID);
//        JdbcUtils.close(conn);
        return resultSet;
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @Override
    public List<ReceivingAddress> list(String userid,int page, int size) throws Exception {
        Connection conn = JdbcUtils.getConn();
        List<ReceivingAddress> list = JdbcUtils.getBeanList(conn, ReceivingAddress.class,
                "select * from s_receiving_address where user_id=? order by is_default desc limit ?,?", userid,(page - 1) * size, size);
        JdbcUtils.close(conn);
        return list;
    }

    /**
     * 获取数据的总条数
     * @return
     * @throws SQLException
     */
    @Override
    public int count() throws SQLException {
        Connection conn = JdbcUtils.getConn();
        ResultSet set = JdbcUtils.excuteQuery(conn, "select count(*) count from s_receiving_address");
        set.next();
        int count = set.getInt("count");
        JdbcUtils.close(conn);
        return count;
    }

    /**
     * 添加
     * @param address
     * @throws SQLException
     */
    @Override
    public void add(ReceivingAddress address) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        JdbcUtils.excute(conn,"insert into s_receiving_address values(?,?,?,?,?,?)",
                address.getId(),address.getReceivingAddress(),address.getReceivingPerson(),address.getMobilePhone(),
                address.getUserId(),address.getIsDefault());
        JdbcUtils.close(conn);
    }

    /**
     * 删除地址
     * @param id
     * @throws SQLException
     */
    @Override
    public void delete(String id) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        JdbcUtils.excute(conn,"delete from s_receiving_address where id = ?",id);
        JdbcUtils.close(conn);
    }

    /**
     * 根据Id更改地址默认值
     * @param num
     * @param id
     * @throws SQLException
     */
    public void setDefault(int num,String id) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        JdbcUtils.excute(conn,"update s_receiving_address set is_default =? where id = ?",num,id);
        JdbcUtils.close(conn);
    }

    /**
     * 设置其他id的地址默认值
     * @param id
     * @throws SQLException
     */
    public void setOtherDefault(String id) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        JdbcUtils.excute(conn,"update s_receiving_address set is_default = -1 where id != ?",id);
        JdbcUtils.close(conn);
    }

    /**
     * 更新地址
     * @param address
     */
    @Override
    public void update(ReceivingAddress address) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        JdbcUtils.excute(conn,"update s_receiving_address set receiving_address=?,receiving_person=?,mobile_phone=?,user_id=?,is_default=?" +
                        " where id = ?",address.getReceivingAddress(),address.getReceivingPerson(),address.getMobilePhone(),
                       address.getUserId(),address.getIsDefault(),address.getId());
        JdbcUtils.close(conn);
    }
}
