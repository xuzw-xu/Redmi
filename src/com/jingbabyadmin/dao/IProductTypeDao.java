package com.jingbabyadmin.dao;

import com.jingbabyadmin.entity.ProductType;

import java.sql.SQLException;
import java.util.List;

public interface IProductTypeDao {

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    List<ProductType> pageList(int page, int size) throws Exception;

    /**
     * 返回数据的总条数
     */
    int count() throws SQLException;

    /**
     * 添加数据
     * @param productType
     * @throws SQLException
     */
    void addProductType(ProductType productType) throws SQLException;

    /**
     * 查询所有数据
     * @return
     * @throws Exception
     */
    List<ProductType> getProductList() throws Exception;

    /**
     * 修改商品类型信息
     * @param productType
     * @throws SQLException
     */
    void updateProductType(ProductType productType) throws SQLException;

    /**
     * 根据Id查商品
     * @param id
     * @return
     * @throws Exception
     */
    ProductType getById(String id) throws Exception;

    /**
     * 删除数据
     * @param id
     * @throws SQLException
     */
    void deleteProductType(String id) throws SQLException;

    /**
     * 多选删除
     * @param ids
     * @throws SQLException
     */
    void deleteAll(String[] ids) throws SQLException;

    /**
     * 获取所有商品列表
     * @return
     * @throws Exception
     */
    List<ProductType> getAllProductTypeName() throws Exception;
}
