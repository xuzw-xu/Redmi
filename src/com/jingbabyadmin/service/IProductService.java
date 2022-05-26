package com.jingbabyadmin.service;

import com.jingbabyadmin.entity.Brand;
import com.jingbabyadmin.entity.OrderProduct;
import com.jingbabyadmin.entity.Page;
import com.jingbabyadmin.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductService {

    Page<Product> list(String name,int page, int size) throws Exception;

    Page<Product> list(String name,String productType,int page, int size) throws Exception;

    Page<Product> listByProtectType(String productType,int page, int size) throws Exception;


    void add(Product product) throws SQLException;

    List<Brand> getBrandByProductType(String id) throws Exception;

    Product getById(String id) throws Exception;

    void update(Product product) throws SQLException;

    void delete(String[] str) throws SQLException;

    Page<Product> list(int page, int size) throws Exception;

    List<Product> listProductBytime() throws Exception;

    List<Product> listOrderProductByNum() throws Exception;

    List<Product> listProductByProductType(String productName,int num) throws Exception;

   int productSum(String id) throws SQLException;
}
