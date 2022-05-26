package com.jingbabyadmin.service.impl;

import com.jingbabyadmin.dao.IFrontIndexDao;
import com.jingbabyadmin.dao.impl.FrontIndexDaoImpl;
import com.jingbabyadmin.entity.SearchHistory;
import com.jingbabyadmin.service.IFrontIndexService;

import java.sql.SQLException;
import java.util.List;

public class FrontServiceImpl implements IFrontIndexService {
    IFrontIndexDao frontIndexDao = new FrontIndexDaoImpl();

    /**
     * 查询所有历史记录
     * @return
     * @throws Exception
     */
    @Override
    public List<SearchHistory> getAllSearchHistory() throws Exception {
        return frontIndexDao.getAllSearchHistory();
    }

    /**
     * 添加历史记录
     * @param searchHistory
     */
    @Override
    public void addSearchHistory(SearchHistory searchHistory) throws SQLException {
        frontIndexDao.addSearchHistory(searchHistory);
    }
}
