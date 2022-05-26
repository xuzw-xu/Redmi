package com.jingbabyadmin.dao.impl;

import com.jingbabyadmin.dao.IProductDao;
import com.jingbabyadmin.entity.Brand;
import com.jingbabyadmin.entity.OrderProduct;
import com.jingbabyadmin.entity.Product;
import com.jingbabyadmin.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl implements IProductDao {

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public List<Product> list(int page, int size) throws Exception {
        Connection conn = JdbcUtils.getConn();
        List<Product> list = JdbcUtils.getBeanList(conn, Product.class,
                "SELECT\n" +
                        "\tsp.id,\n" +
                        "\tproduct_name,\n" +
                        "\tproduct_image,\n" +
                        "\tprice,\n" +
                        "\tproduct_type_name product_type,\n" +
                        "\tproduct_desc,\n" +
                        "\tcreate_time,\n" +
                        "\tbrand_name product_brand from s_product sp\n" +
                        "\tLEFT JOIN s_product_type spt on sp.product_type = spt.id\n" +
                        "\tLEFT JOIN s_brand sb on sb.id = sp.product_brand limit ?,?", (page - 1) * size, size);
        JdbcUtils.close(conn);
        return list;
    }

    /**
     * 搜索商品名不为空
     * @param name
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @Override
    public List<Product> list(String name, int page, int size) throws Exception {
        Connection conn = JdbcUtils.getConn();
        List<Product> list = JdbcUtils.getBeanList(conn, Product.class,
                "SELECT\n" +
                        "\tsp.id,\n" +
                        "\tproduct_name,\n" +
                        "\tproduct_image,\n" +
                        "\tprice,\n" +
                        "\tproduct_type_name product_type,\n" +
                        "\tproduct_desc,\n" +
                        "\tcreate_time,\n" +
                        "\tbrand_name product_brand from s_product sp\n" +
                        "\tLEFT JOIN s_product_type spt on sp.product_type = spt.id\n" +
                        "\tLEFT JOIN s_brand sb on sb.id = sp.product_brand where sp.product_name like ? limit ?,?", "%" + name + "%",(page - 1) * size, size);
        JdbcUtils.close(conn);
        return list;
    }

    /**
     * 通过name和productType查询
     * @param name
     * @param id
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public List<Product> list(String name,String id,int page, int size) throws Exception {
        Connection conn = JdbcUtils.getConn();
        List<Product> list = JdbcUtils.getBeanList(conn, Product.class,
                "SELECT\n" +
                        "\tsp.id,\n" +
                        "\tproduct_name,\n" +
                        "\tproduct_image,\n" +
                        "\tprice,\n" +
                        "\tproduct_type_name product_type,\n" +
                        "\tproduct_desc,\n" +
                        "\tcreate_time,\n" +
                        "\tbrand_name product_brand from s_product sp\n" +
                        "\tLEFT JOIN s_product_type spt on sp.product_type = spt.id\n" +
                        "\tLEFT JOIN s_brand sb on sb.id = sp.product_brand where sp.product_name like ? and sp.product_type=? limit ?,?", "%" + name + "%", id ,(page - 1) * size, size);
        JdbcUtils.close(conn);
        return list;
    }

    /**
     * 通过productType查询
     * @param id
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public List<Product> listByProtectType(String id,int page, int size) throws Exception {
        Connection conn = JdbcUtils.getConn();
        List<Product> list = JdbcUtils.getBeanList(conn, Product.class,
                "SELECT\n" +
                        "\tsp.id,\n" +
                        "\tproduct_name,\n" +
                        "\tproduct_image,\n" +
                        "\tprice,\n" +
                        "\tproduct_type_name product_type,\n" +
                        "\tproduct_desc,\n" +
                        "\tcreate_time,\n" +
                        "\tbrand_name product_brand from s_product sp\n" +
                        "\tLEFT JOIN s_product_type spt on sp.product_type = spt.id\n" +
                        "\tLEFT JOIN s_brand sb on sb.id = sp.product_brand where product_type=? limit ?,?", id,(page - 1) * size, size);
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
        ResultSet set = JdbcUtils.excuteQuery(conn, "select count(*) count from s_product");
        set.next();
        int count = set.getInt("count");
        JdbcUtils.close(conn);
        return count;
    }

    /**
     * 通过brand_type查找Brand对象
     * @param id
     * @return
     * @throws Exception
     */
    public List<Brand> getBrandByProductType(String id) throws Exception {
        Connection conn = JdbcUtils.getConn();
        List<Brand> list = JdbcUtils.getBeanList(conn, Brand.class,
                "select * from s_brand where brand_type = ?", id);
        JdbcUtils.close(conn);
        return list;
    }

    /**
     * 通过Id查找product
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Product getById(String id) throws Exception {
        Connection conn = JdbcUtils.getConn();
        Product product = JdbcUtils.getBean(conn, Product.class, "select * from s_product where id = ?", id);
        JdbcUtils.close(conn);
        return product;
    }

    /**
     * 更新
     * @param product
     */
    @Override
    public void update(Product product) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        JdbcUtils.excute(conn,"update s_product set product_name=?,product_image=?,price=?,product_type=?,product_desc=?,create_time=?,product_brand=? where id = ?",
               product.getProductName(),product.getProductImage(),product.getPrice(),product.getProductType(),product.getProductDesc(),product.getCreateTime(),product.getProductBrand(), product.getId());
        JdbcUtils.close(conn);
    }

    /**
     * 添加数据
     * @param product
     */
    public void addProduct(Product product) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        JdbcUtils.excute(conn,"insert  into `s_product`(`id`,`product_name`,`product_image`,`price`,`product_type`,`product_desc`,`create_time`,`product_brand`) values (?,?,?,?,?,?,?,?)",
                product.getId(),product.getProductName(),product.getProductImage(),product.getPrice(),product.getProductType(),product.getProductDesc(),product.getCreateTime(),product.getProductBrand());
        JdbcUtils.close(conn);
    }

    /**
     * 删除数据
     * @param str
     * @throws SQLException
     */
    public void delete(String[] str) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        String sql = "delete from s_product where id in (";
        for(String ste:str){
            sql+="?,";
        }
        sql = sql.substring(0,sql.length()-1);
        sql+=")";
        JdbcUtils.excute(conn,sql,str);
    }

    /**
     * 根据时间排序查询前六条数据
     * @return
     * @throws Exception
     */
    @Override
    public List<Product> listProductByTime() throws Exception {
        Connection conn = JdbcUtils.getConn();
        List<Product> beanList = JdbcUtils.getBeanList(conn, Product.class, "select * from s_product order by create_time desc limit 6");
        JdbcUtils.close(conn);
        return beanList;
    }

    /**
     * 查询前6条销量最高的商品
     * @return
     * @throws Exception
     */
    @Override
    public List<Product> listOrderProductByNum() throws Exception {
        Connection conn = JdbcUtils.getConn();
        List<Product> beanList = JdbcUtils.getBeanList(conn, Product.class,
                "select sp.id, sp.product_name, sp.product_image, sp.price, sp.product_type, " +
                        "sp.product_desc, sp.create_time, sp.product_brand FROM s_product sp left " +
                        "JOIN (select sum(product_num) sum ,product_id from s_order_product group by product_id) sop " +
                        "ON sp.id = sop.product_id ORDER BY sop.sum DESC LIMIT 6");
        JdbcUtils.close(conn);
        return  beanList;
    }


    /**
     * 根据品牌类型查找五条商品
     * @param productName
     * @return
     * @throws Exception
     */
    public List<Product> listProductByProductType(String productName,int num) throws Exception {
        Connection conn = JdbcUtils.getConn();
        List<Product> beanList = JdbcUtils.getBeanList(conn, Product.class,
                "select sp.id,sp.product_name,sp.product_image,sp.price,sp.product_type,sp.product_desc," +
                        "sp.create_time,sp.product_brand from  s_product sp left join s_product_type spt on sp.product_type = spt.id" +
                        " where spt.product_type_name = ? limit ?",productName,num);
        JdbcUtils.close(conn);
        return  beanList;
    }

    /**
     * 获取商品的销售量
     * @param id
     * @return
     * @throws SQLException
     */
    public int productSum(String id) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        ResultSet resultSet = JdbcUtils.excuteQuery(conn, " select sum(product_num) sum from s_order_product where product_id= ? group by product_id", id);
        int sum = 0;
        if(resultSet.next()) {
            sum = resultSet.getInt("sum");
        }
        JdbcUtils.close(conn);
        return sum;
    }
}
