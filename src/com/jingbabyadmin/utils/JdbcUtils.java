package com.jingbabyadmin.utils;

import com.zaxxer.hikari.HikariDataSource;

import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by iuoily on 2022/4/19.
 */
public class JdbcUtils {
    private static HikariDataSource hikariDataSource;

    static {
        hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariDataSource.setJdbcUrl("jdbc:mysql:///eshop?serverTimezone=UTC");
        hikariDataSource.setUsername("root");
        hikariDataSource.setPassword("root");
        hikariDataSource.setMaximumPoolSize(10);
    }

    /**
     *  获取连接
     * @return 数据库连接
     * @throws SQLException sql
     */
    public static Connection getConn() throws SQLException {
        return hikariDataSource.getConnection();
    }

    /**
     *  执行
     * @param connection 数据库连接
     * @param sql sql语句
     * @param args 可变长参数
     * @throws SQLException 异常
     */
    public static void excute(Connection connection, String sql, Object... args) throws SQLException {
        setArgs(connection, sql, args).execute();
    }

    /**
     *  执行查询
     * @param connection 数据库连接
     * @param sql sql语句
     * @param args 可变长参数
     * @return 数据库查询结果集
     * @throws SQLException sql
     */
    public static ResultSet excuteQuery(Connection connection, String sql, Object... args) throws SQLException {
        return setArgs(connection, sql, args).executeQuery();
    }

    /**
     *  设置参数
     * @param connection 数据库连接
     * @param sql sql语句
     * @param args 可变长参数
     * @return 返回预编译对象
     * @throws SQLException sql
     */
    private static PreparedStatement setArgs(Connection connection, String sql, Object... args) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i+1, args[i]);
        }
        return preparedStatement;
    }


    /**
     *  获取对象
     * @param aClass 对象的类型
     * @param sql sql语句
     * @param para 查询字段
     * @param <T> 泛型--查询的类
     * @return 单个对象
     * @throws Exception sql classnotfound
     */
    public static <T> T getBean(Connection conn, Class<T> aClass, String sql, Object... para) throws Exception {
        ResultSet resultSet = excuteQuery(conn, sql, para);
        ResultSetMetaData metaData = resultSet.getMetaData();
        if (resultSet.next()){
            T instance = aClass.newInstance();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String fName = toupperCamelCase(metaData.getColumnLabel(i));
                Method method = aClass.getDeclaredMethod("set" + fName, Class.forName(metaData.getColumnClassName(i)));
                method.setAccessible(true);
                method.invoke(instance,resultSet.getObject(i));
            }
            return instance;
        }
        return null;
    }

    /**
     *  获取对象集合
     * @param aClass 对象类型
     * @param sql sql语句
     * @param <T> 泛型
     * @return 对象集合
     * @throws Exception sql class
     */
    public static <T> List<T> getBeanList(Connection conn, Class<T> aClass, String sql, Object...para) throws Exception {
        ResultSet resultSet = excuteQuery(conn, sql, para);
        ResultSetMetaData metaData = resultSet.getMetaData();
        List<T> list = new ArrayList<>();
        while (resultSet.next()){
            T instance = aClass.newInstance();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String fName = toupperCamelCase(metaData.getColumnLabel(i));
                Method method = aClass.getDeclaredMethod("set" + fName, Class.forName(metaData.getColumnClassName(i)));
                method.setAccessible(true);
                method.invoke(instance,resultSet.getObject(i));
            }
            list.add(instance);
        }
        return list;
    }

    /**
     * 释放连接
     * @param conn 连接
     */
    public static void close(Connection conn) {
        hikariDataSource.evictConnection(conn);
    }

    /**
     *  小驼峰转换
     * @param str 字符串
     * @return 小驼峰
     */
    public static String tolowerCamelCase(String str) {
        String[] s = str.split("_");
        str = s[0];
        for (String value : s) {
            String name = value.replace(value.substring(0, 1), value.substring(0, 1).toUpperCase());
            str = str.concat(name);
        }
        return str;
    }

    /**
     *  将带下划线 _ 的字符串转换为大驼峰形式
     * @param str 原始字符串
     * @return 转换后的大驼峰字符串
     */
    private static String toupperCamelCase(String str) {
        String[] s = str.split("_");
        str = "";
        for (String value : s) {
            String name = value.replace(value.substring(0, 1), value.substring(0, 1).toUpperCase());
            str = str.concat(name);
        }
        return str;
    }

}
