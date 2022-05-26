package com.jingbabyadmin.service.impl;

import com.jingbabyadmin.dao.IUserDao;
import com.jingbabyadmin.dao.impl.UserDaoImpl;
import com.jingbabyadmin.entity.Page;
import com.jingbabyadmin.entity.User;
import com.jingbabyadmin.service.IUserService;
import com.jingbabyadmin.utils.EncryptionUtils;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements IUserService {
    IUserDao userDao = new UserDaoImpl();

    /**
     * 登录
     * @param name
     * @param pwd
     * @return
     * @throws Exception
     */
    public String login(String name,String pwd) throws Exception {
        User user = userDao.getByName(name);
        if(user == null){
            return "用户不存在！";
        }else{
            if(EncryptionUtils.encryptMD5(pwd).equals(user.getPassword())){
                return null;
            }else{
                return "密码错误！";
            }
        }
    }
    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public Page<User> userList(int page, int size) throws Exception {
        List<User> users = userDao.userList(page, size);
        int count = userDao.count();
        return new Page<User>(page,size,users,count);
    }

    /**
     * 添加用户
     * @param user
     * @throws SQLException
     */
    public void addUser(User user) throws SQLException {
        userDao.addUser(user);
    }

    /**
     * 更新用户信息
     * @throws SQLException
     */
    public void updateUserInfo(String id,String name,String pwd) throws SQLException {
        userDao.updateUserInfo(id,name,pwd);
    }

    /**
     * 删除用户
     * @param id
     * @throws SQLException
     */
    public void delete(String id) throws SQLException {
        userDao.deleteUser(id);
    }

    /**
     * 根据姓名查找用户
     * @param name
     * @throws Exception
     */
    public User getByName(String name) throws Exception {
       return userDao.getByName(name);
    }

    /**
     * 根据Id查找用户
     * @param id
     * @return
     * @throws Exception
     */
    public User getById(String id) throws Exception {
        return userDao.getById(id);
    }

    /**
     * 修改密码
     * @param name
     * @param pwd
     */
    public void changePwd(String name,String pwd) throws SQLException {
        userDao.changePwd(name,pwd);
    }
}
