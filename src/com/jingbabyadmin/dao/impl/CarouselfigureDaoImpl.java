package com.jingbabyadmin.dao.impl;

import com.jingbabyadmin.dao.ICarouselfigureDao;
import com.jingbabyadmin.entity.Brand;
import com.jingbabyadmin.entity.CarouselFigure;
import com.jingbabyadmin.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CarouselfigureDaoImpl implements ICarouselfigureDao {

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public List<CarouselFigure> list(int page,int size) throws Exception {
        Connection conn = JdbcUtils.getConn();
        List<CarouselFigure> list = JdbcUtils.getBeanList(conn, CarouselFigure.class,
                "select * from s_carousel_figure limit ?,?", (page - 1) * size, size);
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
        ResultSet set = JdbcUtils.excuteQuery(conn, "select count(*) count from s_carousel_figure");
        set.next();
        int count = set.getInt("count");
        JdbcUtils.close(conn);
        return count;
    }

    /**
     * 添加信息
     * @param carouselFigure
     * @throws SQLException
     */
    public void add(CarouselFigure carouselFigure) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        JdbcUtils.excute(conn,"insert into s_carousel_figure (id,url,sequence_num) values(?,?,?)"
                ,carouselFigure.getId(),carouselFigure.getUrl(),carouselFigure.getSequenceNum());
        JdbcUtils.close(conn);
    }

    /**
     * 返回所有CarouselFigure对象集合
     * @return
     * @throws Exception
     */
    public List<CarouselFigure> selectAll() throws Exception {
        Connection conn = JdbcUtils.getConn();
        List<CarouselFigure> list = JdbcUtils.getBeanList(conn, CarouselFigure.class, "select * from s_carousel_figure");
        JdbcUtils.close(conn);
        return list;
    }

    /**
     * 删除数据库
     * @param str
     * @throws SQLException
     */
    public void delete(String[] str) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        String sql = "delete from s_carousel_figure where id in (";
        for(String ste:str){
            sql+="?,";
        }
        sql = sql.substring(0,sql.length()-1);
        sql+=")";
        JdbcUtils.excute(conn,sql,str);
    }

    /**
     * 通过id查找
     * @param id
     * @return
     * @throws Exception
     */
    public CarouselFigure getById(String id) throws Exception {
        Connection conn = JdbcUtils.getConn();
        CarouselFigure carouselFigure = JdbcUtils.getBean(conn, CarouselFigure.class, "select * from s_carousel_figure where id = ?", id);
        return carouselFigure;
    }

    /**
     * 更新
     * @param id
     * @param url
     * @param num
     * @throws SQLException
     */
    public void update(String id,String url,int num) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        JdbcUtils.excute(conn,"update s_carousel_figure set url = ? , sequence_num = ? where id =?",url,num,id);
        JdbcUtils.close(conn);
    }

    /**
     * 获取前5条轮播图
     * @return
     * @throws Exception
     */
    public List<CarouselFigure> listCraous() throws Exception
     {
        Connection conn = JdbcUtils.getConn();
        List<CarouselFigure> beanList = JdbcUtils.getBeanList(conn, CarouselFigure.class, "select * from s_carousel_figure order by sequence_num limit 5");
        JdbcUtils.close(conn);
        return beanList;
    }
}
