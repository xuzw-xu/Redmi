package com.jingbabyadmin.dao.impl;

import com.jingbabyadmin.dao.IProductTypeDao;
import com.jingbabyadmin.entity.ProductType;
import com.jingbabyadmin.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductTypeDaoImpl implements IProductTypeDao {
    /**
     * 查询分页数据
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public List<ProductType> pageList(int page, int size) throws Exception {
        Connection conn = JdbcUtils.getConn();
        List<ProductType> list = JdbcUtils.getBeanList(conn, ProductType.class,
                "select * from s_product_type limit ?,?", (page - 1) * size, size);
        JdbcUtils.close(conn);
        return list;
    }

    /**
     * 获取数据库里面数据的总条数
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
     * 添加数据
     * @param productType
     * @throws SQLException
     */
    public void addProductType(ProductType productType) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        JdbcUtils.excute(conn,"insert into s_product_type values(?,?,?,?)",
                productType.getId(),productType.getProductTypeName(),productType.getProductTypeDesc(),productType.getProductTypeIcon());
        JdbcUtils.close(conn);
    }

    /**
     * 查询所有元素
     * @return
     * @throws Exception
     */
    public List<ProductType> getProductList() throws Exception {
        Connection conn = JdbcUtils.getConn();
        List<ProductType> list = JdbcUtils.getBeanList(conn, ProductType.class, "select * from s_product_type");
        JdbcUtils.close(conn);
        return list;
    }

    /**
     * 删除商品分类
     * @param id
     * @throws SQLException
     */
    public void deleteProductType(String id) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        JdbcUtils.excute(conn,"delete from s_product_type where id = ?",id);
        JdbcUtils.close(conn);
    }

    /**
     * 修改商品信息
     * @param productType
     */
    public void updateProductType(ProductType productType) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        JdbcUtils.excute(conn,"update s_product_type set product_type_name=?,product_type_desc=?,product_type_icon=? where id=?",
                productType.getProductTypeName(),productType.getProductTypeDesc(),productType.getProductTypeIcon(),productType.getId());
        JdbcUtils.close(conn);
    }

    /**
     * 根据Id查商品
     * @param id
     * @return
     */
    public ProductType getById(String id) throws Exception {
        Connection conn = JdbcUtils.getConn();
        ProductType productType = JdbcUtils.getBean(conn, ProductType.class, "select * from s_product_type where id = ?", id);
        JdbcUtils.close(conn);
        return productType;
    }

    /**
     * 多选删除
     * @param ids
     */
    public void deleteAll(String[] ids) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        String sql = "delete from s_product_type where id in (";
        for(String ste:ids){
            sql+="?,";
        }
        sql = sql.substring(0,sql.length()-1);
        sql+=")";
        JdbcUtils.excute(conn,sql,ids);
    }

    /**
     * 获取所有商品类型
     * @return
     * @throws SQLException
     */
    public List<ProductType> getAllProductTypeName() throws Exception {
        Connection conn = JdbcUtils.getConn();
        List<ProductType> list = JdbcUtils.getBeanList(conn, ProductType.class, "select * from s_product_type");
        JdbcUtils.close(conn);
        return list;
    }

}
