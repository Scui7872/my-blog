package com.scs.blog.service.impl;

import com.scs.blog.dao.ArticleDao;
import com.scs.blog.entity.Article;
import com.scs.blog.factory.DaoFactory;
import com.scs.blog.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleServiceImpl implements ArticleService {
    private ArticleDao articleDao = DaoFactory.getArticleDaoInstance();
    private static Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
    @Override
    public List<Article> listArticle() {
        List<Article> articleList = new ArrayList<>();
        try {
            articleList = articleDao.selectAll();
        } catch (SQLException e) {
            logger.error("查询出现异常");
        }
        logger.error("查询成功");
        return  articleList;
    }
}
