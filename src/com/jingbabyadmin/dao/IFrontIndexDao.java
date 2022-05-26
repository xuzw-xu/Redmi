package com.jingbabyadmin.dao;

import com.jingbabyadmin.entity.SearchHistory;

import java.sql.SQLException;
import java.util.List;

public interface IFrontIndexDao {
    /**
     * 查询所有历史记录
     * @return
     * @throws Exception
     */
    List<SearchHistory> getAllSearchHistory() throws Exception;

    /**
     * 添加历史记录
     * @param searchHistory
     */
    void addSearchHistory(SearchHistory searchHistory) throws SQLException;
}
