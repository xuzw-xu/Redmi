package com.jingbabyadmin.dao;

import com.jingbabyadmin.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDao {

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    List<User> userList(int page, int size) throws Exception;

    /**
     * 查询总条数
     * @return
     * @throws SQLException
     */
    int count() throws SQLException;

    /**
     * 添加用户
     * @param user
     * @throws SQLException
     */
    void addUser(User user) throws SQLException;

    /**
     * 查询所有用户
     * @return
     * @throws Exception
     */
    List<User> getUserList() throws Exception;

    /**
     * 删除用户
     * @param id
     * @throws SQLException
     */
    void deleteUser(String id) throws SQLException;

    /**
     * 更新用户信息
     * @throws SQLException
     */
    void updateUserInfo(String id,String name,String pwd) throws SQLException;

    /**
     * 根据id查找用户
     * @param name
     * @return
     * @throws Exception
     */
    User getByName(String name) throws Exception;

    /**
     * 修改密码
     * @param name
     * @param pwd
     * @throws SQLException
     */
    void changePwd(String name,String pwd) throws SQLException;

    /**
     * 根据ID查找用户
     * @param id
     * @return
     * @throws Exception
     */
    User getById(String id) throws Exception;
}
