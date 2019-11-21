package com.scs.blog.dao;

import com.scs.blog.factory.DaoFactory;
import com.scs.blog.util.JSoupSpider;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class ArticleDaoTest {
    private static Logger logger = LoggerFactory.getLogger(ArticleDaoTest.class);
    private ArticleDao articleDao  = DaoFactory.getArticleDaoInstance();

    @Test
    public void batchInsert() {
        try {
            int[] result = articleDao.batchInsert(JSoupSpider.getArticle());
            if(result.length !=0 ) {
                logger.info("批量增加文章成功");
            }
        } catch (SQLException e) {
            logger.error("批量增加文章失败");
        }
    }
}
