package com.jingbabyadmin.dao;

import com.jingbabyadmin.entity.Brand;
import com.jingbabyadmin.entity.OrderProduct;
import com.jingbabyadmin.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductDao {
    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    List<Product> list(String name,int page, int size) throws Exception;

    /**
     * 通过name和productType查询
     * @param name
     * @param id
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    List<Product> list(String name,String id,int page, int size) throws Exception;

    /**
     *
     * @param id
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    List<Product> listByProtectType(String id,int page, int size) throws Exception;


    /**
     *分页
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    List<Product> list(int page, int size) throws Exception;
    /**
     * 查询总条数
     * @return
     * @throws SQLException
     */
    int count() throws SQLException;

    /**
     * 添加数据
     * @param product
     * @throws SQLException
     */
    void addProduct(Product product) throws SQLException;

    /**
     * 通过brand_type查找Brand对象
     * @param id
     * @return
     * @throws Exception
     */
    List<Brand> getBrandByProductType(String id) throws Exception;

    /**
     * 通过id查找product对象
     * @param id
     * @return
     */
    Product getById(String id) throws Exception;

    /**
     * 更新数据
     * @param product
     * @throws SQLException
     */
    void update(Product product) throws SQLException;

    /**
     * 删除数据
     * @param str
     * @throws SQLException
     */
    void delete(String[] str) throws SQLException;

    /**
     * 根据时间排序查询前六条数据
     * @return
     * @throws Exception
     */
    List<Product> listProductByTime() throws Exception;

    /**
     * 查询前四条销量最高的商品
     * @return
     * @throws Exception
     */
    List<Product> listOrderProductByNum() throws Exception;

    /**
     * 根据品牌类型查找五条商品
     * @param productName
     * @return
     * @throws Exception
     */
    List<Product> listProductByProductType(String productName,int num) throws Exception;

    /**
     * 获取商品的销售量
     * @param id
     * @return
     * @throws SQLException
     */
    int productSum(String id) throws SQLException;
}
