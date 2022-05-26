package com.jingbabyadmin.dao.impl;

import com.jingbabyadmin.dao.IBrandDao;
import com.jingbabyadmin.entity.Brand;
import com.jingbabyadmin.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BrandDaoImpl implements IBrandDao {

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public List<Brand> list(int page,int size) throws Exception {
        Connection conn = JdbcUtils.getConn();
        List<Brand> list = JdbcUtils.getBeanList(conn, Brand.class,
                "select sb.id,sb.brand_name,spt.product_type_name brand_type,sb.brand_img from s_brand sb left join s_product_type spt on sb.brand_type=spt.id limit ?,?", (page - 1) * size, size);
        JdbcUtils.close(conn);
        return list;
    }

    /**
     * 查询总条数
     * @return
     * @throws SQLException
     */
    public int count() throws SQLException {
        Connection conn = JdbcUtils.getConn();
        ResultSet set = JdbcUtils.excuteQuery(conn, "select count(*) count from s_product_type");
        set.next();
        int count = set.getInt("count");
        JdbcUtils.close(conn);
        return count;
    }

    /**
     * 新增
     * @param id
     * @param name
     * @param type
     * @param img
     */
    public void add(String id,String name,String type,String img) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        JdbcUtils.excute(conn,"insert into s_brand set id=?,brand_name=?,brand_type=?,brand_img=?",id,name,type,img);
        JdbcUtils.close(conn);
    }

    /**
     * 删除数据
     * @param str
     * @throws SQLException
     */
    public void delete(String[] str) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        String sql = "delete from s_brand where id in (";
        for(String ste:str){
            sql+="?,";
        }
        sql = sql.substring(0,sql.length()-1);
        sql+=")";
        JdbcUtils.excute(conn,sql,str);
    }

    /**
     * 修改
     * @param id
     * @param name
     * @param type
     * @param img
     * @throws SQLException
     */
    public void update(String id,String name,String type,String img) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        JdbcUtils.excute(conn,"update s_brand set brand_name = ?,brand_type = ?,brand_img=? where id =?",name,type,img,id);
        JdbcUtils.close(conn);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     * @throws Exception
     */
    public Brand getById(String id) throws Exception {
        Connection conn = JdbcUtils.getConn();
        Brand brand = JdbcUtils.getBean(conn, Brand.class, "select * from s_brand where id = ?", id);
        return brand;
    }

    /**
     * 根据name查询
     * @param name
     * @return
     * @throws Exception
     */
    public Brand getByName(String name) throws Exception {
        Connection conn = JdbcUtils.getConn();
        Brand brand = JdbcUtils.getBean(conn, Brand.class, "select * from s_brand where brand_name = ?", name);
        JdbcUtils.close(conn);
        return brand;
    }


}
