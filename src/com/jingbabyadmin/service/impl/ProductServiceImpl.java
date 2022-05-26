package com.jingbabyadmin.service.impl;

import com.jingbabyadmin.dao.IProductDao;
import com.jingbabyadmin.dao.impl.ProductDaoImpl;
import com.jingbabyadmin.entity.Brand;
import com.jingbabyadmin.entity.OrderProduct;
import com.jingbabyadmin.entity.Page;
import com.jingbabyadmin.entity.Product;
import com.jingbabyadmin.service.IProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements IProductService {
    IProductDao products = new ProductDaoImpl();

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public Page<Product> list(String name,int page, int size) throws Exception {
        List<Product> list = products.list(name,page, size);
        int count = products.count();
        Page<Product> productPage = new Page<Product>(page, size, list, count);
        return productPage;
    }


    /**
     * 通过name和productType查询
     * @param name
     * @param productType
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @Override
    public Page<Product> list(String name, String productType, int page, int size) throws Exception {
        List<Product> list = products.list(name,productType,page,size);
        int count = products.count();
        Page<Product> productPage = new Page<Product>(page, size, list, count);
        return productPage;
    }

    /**
     * 通过productType查询
     * @param productType
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @Override
    public Page<Product> listByProtectType(String productType, int page, int size) throws Exception {
        List<Product> list = products.listByProtectType(productType,page, size);
        int count = products.count();
        Page<Product> productPage = new Page<Product>(page, size, list, count);
        return productPage;
    }

    /**
     * 添加数据
     * @param product
     * @throws SQLException
     */
    public void add(Product product) throws SQLException {
        products.addProduct(product);
    }

    /**
     * 通过brand_type查找Brand对象
     * @param id
     * @return
     */
    public List<Brand> getBrandByProductType(String id) throws Exception {
       return products.getBrandByProductType(id);
    }

    /**
     * 通过id查找product对象
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Product getById(String id) throws Exception {
        return products.getById(id);
    }

    /**
     * 更新
     * @param product
     * @throws SQLException
     */
    @Override
    public void update(Product product) throws SQLException {
        products.update(product);
    }

    /**
     * 删除
     * @param str
     * @throws SQLException
     */
    @Override
    public void delete(String[] str) throws SQLException {
        products.delete(str);
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @Override
    public Page<Product> list(int page, int size) throws Exception {
        List<Product> list = products.list(page, size);
        int count = products.count();
        Page<Product> productPage = new Page<Product>(page, size, list, count);
        return productPage;
    }

    /**
     * 按时间顺序获取前六条商品
     * @return
     * @throws Exception
     */
    @Override
    public List<Product> listProductBytime() throws Exception {
        return products.listProductByTime();
    }

    /**
     * 查询前四条销量最高的商品
     * @return
     * @throws Exception
     */
    @Override
    public List<Product> listOrderProductByNum() throws Exception {
        return products.listOrderProductByNum();
    }

    /**
     * 根据品牌类型查找五条商品
     * @param productName
     * @return
     * @throws Exception
     */
    @Override
    public List<Product> listProductByProductType(String productName,int num) throws Exception {
        return  products.listProductByProductType(productName,num);
    }

    /**
     * 获取商品的销售量
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public int productSum(String id) throws SQLException {
        return products.productSum(id);
    }


}
