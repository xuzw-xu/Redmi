package com.jingbabyadmin.service;

import com.jingbabyadmin.entity.Page;
import com.jingbabyadmin.entity.User;

import java.sql.SQLException;

public interface IUserService {

    String login(String name,String pwd) throws Exception;

    Page<User> userList(int page, int size) throws Exception;

    void addUser(User user) throws SQLException;

    void updateUserInfo(String id,String name,String pwd) throws SQLException;

    void delete(String id) throws SQLException;

    User getByName(String name) throws Exception;

    User getById(String id) throws Exception;

    void changePwd(String name,String pwd) throws SQLException;

}
