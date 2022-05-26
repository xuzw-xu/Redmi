package com.jingbabyadmin.service;

import com.jingbabyadmin.entity.Page;
import com.jingbabyadmin.entity.ProductType;

import java.sql.SQLException;
import java.util.List;

public interface IProductTypeService {

    Page<ProductType> pageList(int page, int size) throws Exception;

    int count() throws SQLException;

    void add(ProductType productType) throws SQLException;

    String getProductType(ProductType productType) throws Exception;

    void updateProductType(ProductType productType) throws SQLException;

    ProductType getById(String id) throws Exception;

    void deleteProductType(String id) throws SQLException;

    void deleteAll(String[] ids) throws SQLException;

    List<ProductType> getAllProductTypeName() throws Exception;
}
