package com.jingbabyadmin.dao.impl;

import com.jingbabyadmin.dao.IUserDao;
import com.jingbabyadmin.entity.User;
import com.jingbabyadmin.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements IUserDao {

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public List<User> userList(int page, int size) throws Exception {
        Connection conn = JdbcUtils.getConn();
        List<User> users = JdbcUtils.getBeanList(conn, User.class,
                "select * from s_user limit ?,?", (page - 1) * size, size);
        JdbcUtils.close(conn);
        return users;
    }

    /**
     * 获取数据库里面数据的总条数
     * @return
     * @throws SQLException
     */
    public int count() throws SQLException {
        Connection conn = JdbcUtils.getConn();
        ResultSet set = JdbcUtils.excuteQuery(conn, "select count(*) count from s_user");
        set.next();
        int count = set.getInt("count");
        JdbcUtils.close(conn);
        return count;
    }


    /**
     * 添加用户
     * @param user
     * @throws SQLException
     */
    public void addUser(User user) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        JdbcUtils.excute(conn,"insert into s_user values(?,?,?,?)",
                user.getId(),user.getUsername(),user.getPassword(),user.getType());
        JdbcUtils.close(conn);
    }

    /**
     * 查询所有用户
     * @return
     * @throws Exception
     */
    public List<User> getUserList() throws Exception {
        Connection conn = JdbcUtils.getConn();
        List<User> list = JdbcUtils.getBeanList(conn, User.class, "select * from s_user");
        JdbcUtils.close(conn);
        return list;
    }

    /**
     * 删除用户
     * @param id
     * @throws SQLException
     */
    public void deleteUser(String id) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        JdbcUtils.excute(conn,"delete from s_user where id = ?",id);
        JdbcUtils.close(conn);
    }

    /**
     * 更新用户信息
     * @throws SQLException
     */
    public void updateUserInfo(String id,String name,String pwd) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        JdbcUtils.excute(conn,"update s_user set username=?,password=? where id=?",name,pwd,id);
        JdbcUtils.close(conn);
    }

    /**
     * 根据name查询用户
     * @param name
     * @return
     * @throws Exception
     */
    public User getByName(String name) throws Exception {
        Connection conn = JdbcUtils.getConn();
        User user = JdbcUtils.getBean(conn, User.class, "select * from s_user where username = ?", name);
        JdbcUtils.close(conn);
        return user;
    }

    /**
     * 根据ID查找用户
     * @param id
     * @return
     * @throws Exception
     */
    public User getById(String id) throws Exception {
        Connection conn = JdbcUtils.getConn();
        User user = JdbcUtils.getBean(conn, User.class, "select * from s_user where id = ?", id);
        JdbcUtils.close(conn);
        return user;
    }

    /**
     * 修改密码
     * @param name
     * @param pwd
     * @throws SQLException
     */
    public void changePwd(String name,String pwd) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        JdbcUtils.excute(conn,"update s_user set password = ? where username = ?",pwd,name);
        JdbcUtils.close(conn);
    }
}
