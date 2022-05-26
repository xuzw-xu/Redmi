package com.jingbabyadmin.service.impl;

import com.jingbabyadmin.dao.IBrandDao;
import com.jingbabyadmin.dao.impl.BrandDaoImpl;
import com.jingbabyadmin.entity.Brand;
import com.jingbabyadmin.entity.Page;
import com.jingbabyadmin.service.IBrandService;

import java.sql.SQLException;
import java.util.List;

public class BrandServiceImpl implements IBrandService {
        IBrandDao brandDao = new BrandDaoImpl();

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public Page<Brand> list(int page,int size) throws Exception {
        List<Brand> list = brandDao.list(page, size);
        int count = brandDao.count();
        Page<Brand> brandPage = new Page<Brand>(page, size, list, count);
        return brandPage;
    }

    /**
     * 添加
     * @param id
     * @param name
     * @param type
     * @param img
     * @throws SQLException
     */
    public void add(String id,String name,String type,String img) throws SQLException {
        brandDao.add(id,name,type,img);
    }

    /**
     * 删除
     * param id
     */
    public void delete(String[] str) throws SQLException {
        brandDao.delete(str);
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
        brandDao.update(id,name,type,img);
    }

    /**
     * 通过id查找
     * @param id
     * @return
     * @throws Exception
     */
    public Brand getById(String id) throws Exception {
       return brandDao.getById(id);
    }

    /**
     * 通过name查询
     * @param name
     * @return
     * @throws Exception
     */
    public Brand getByName(String name) throws Exception {
        return brandDao.getByName(name);
    }
}
