package com.jingbabyadmin.service.impl;

import com.jingbabyadmin.dao.IProductTypeDao;
import com.jingbabyadmin.dao.impl.ProductTypeDaoImpl;
import com.jingbabyadmin.entity.Page;
import com.jingbabyadmin.entity.ProductType;
import com.jingbabyadmin.service.IProductTypeService;

import java.sql.SQLException;
import java.util.List;


public class ProductTypeServiceImpl implements IProductTypeService {
    IProductTypeDao productTypeDao = new ProductTypeDaoImpl();

    /**
     * 获取商品分页列表
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public Page<ProductType> pageList(int page, int size) throws Exception {
        List<ProductType> productTypes = productTypeDao.pageList(page, size);
        int count = productTypeDao.count();
        return new Page<ProductType>(page,size,productTypes,count);
    }

    /**
     * 获取总条数
     * @return
     * @throws SQLException
     */
    public int count() throws SQLException {
        return productTypeDao.count();
    }

    /**
     * 添加信息
     * @param productType
     * @throws SQLException
     */
    public void add(ProductType productType) throws SQLException {
        productTypeDao.addProductType(productType);
    }

    /**
     * 判断数据是否存在
     * @return
     */
    public String getProductType(ProductType productType) throws Exception {
        List<ProductType> productTypes = productTypeDao.getProductList();
       for(ProductType productType1:productTypes){
           if(productType.getProductTypeName().equals(productType1.getProductTypeName())){
               return "商品已存在！";
           }
       }
        return null;
    }

    /**
     * 修改信息
     * @param productType
     * @throws SQLException
     */
    public void updateProductType(ProductType productType) throws SQLException {
        productTypeDao.updateProductType(productType);
    }

    /**
     * 通过Id查商品
     * @param id
     * @return
     * @throws Exception
     */
    public ProductType getById(String id) throws Exception {
        return productTypeDao.getById(id);
    }

    /**
     * 删除商品分类
     * @param id
     * @throws SQLException
     */
    public void deleteProductType(String id) throws SQLException {
        productTypeDao.deleteProductType(id);
    }

    /**
     * 多选删除
     * @param ids
     * @throws SQLException
     */
    public void deleteAll(String[] ids) throws SQLException {
        productTypeDao.deleteAll(ids);
    }

    /**
     * 获取所有列表集合
     * @return
     * @throws Exception
     */
    public List<ProductType> getAllProductTypeName() throws Exception {
       return productTypeDao.getAllProductTypeName();
    }
}
