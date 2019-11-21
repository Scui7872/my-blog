package com.scs.blog.dao;

import com.scs.blog.entity.Article;

import java.sql.SQLException;
import java.util.List;

public interface ArticleDao {
    int[] batchInsert(List<Article> articleList) throws SQLException;

    List<Article> selectAll() throws SQLException;
}
