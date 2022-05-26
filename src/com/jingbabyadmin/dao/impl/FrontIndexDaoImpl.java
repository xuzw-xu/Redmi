package com.jingbabyadmin.dao.impl;

import com.jingbabyadmin.dao.IFrontIndexDao;
import com.jingbabyadmin.entity.SearchHistory;
import com.jingbabyadmin.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FrontIndexDaoImpl implements IFrontIndexDao {


    /**
     * 查询所有历史记录
     * @return
     * @throws Exception
     */
    @Override
    public List<SearchHistory> getAllSearchHistory() throws Exception {
        Connection conn = JdbcUtils.getConn();
        List<SearchHistory> list = JdbcUtils.getBeanList(conn, SearchHistory.class, "select * from s_search_history order by num limit 13");
        JdbcUtils.close(conn);
        return list;
    }

    /**
     * 添加历史记录
     * @param searchHistory
     */
    @Override
    public void addSearchHistory(SearchHistory searchHistory) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        JdbcUtils.excute(conn,"insert into s_search_words values(?,?,?,?)",
                searchHistory.getId(),searchHistory.getSearchWords(),searchHistory.getNum(),searchHistory.getSearchTime());
        JdbcUtils.close(conn);
    }


}
