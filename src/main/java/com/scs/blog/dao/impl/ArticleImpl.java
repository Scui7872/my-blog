package com.scs.blog.dao.impl;

import com.scs.blog.dao.ArticleDao;
import com.scs.blog.entity.Article;
import com.scs.blog.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleImpl implements ArticleDao {

    @Override
    public int[] batchInsert(List<Article> articleList) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "INSERT INTO t_article(title,intro,cover,diamond,nickname,comments,likes,publish_time,user_id) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        connection.setAutoCommit(false);
        articleList.forEach(article -> {
            try {
                pstmt.setString(1, article.getTitle());
                pstmt.setString(2, article.getIntro());
                pstmt.setString(3, article.getCover());
                pstmt.setInt(4, article.getDiamond());
                pstmt.setString(5, article.getNickname());
                pstmt.setInt(6, article.getComments());
                pstmt.setInt(7, article.getLikes());
                pstmt.setObject(8, article.getPublishtime());
                pstmt.setInt(9, article.getUserid());
                pstmt.addBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        int[] result = pstmt.executeBatch();
        connection.commit();
        DbUtil.close(null, pstmt, connection);
        return result;
    }

    @Override
    public List<Article> selectAll() throws SQLException {
       List<Article> articleList = new ArrayList<>();
       Connection connection = DbUtil.getConnection();
       connection.setAutoCommit(false);
       String sql = "SELECT * FROM t_article ORDER BY id DESC";
       PreparedStatement pstmt = connection.prepareStatement(sql);
       ResultSet rs = pstmt.executeQuery();
       while (rs.next()){
           Article article = new Article();
           article.setTitle(rs.getString("title"));
           article.setIntro(rs.getString("intro"));
           article.setCover(rs.getString("cover"));
           article.setDiamond(rs.getInt("diamond"));
           article.setNickname(rs.getString("nickname"));
           article.setComments(rs.getInt("comments"));
           article.setLikes(rs.getInt("likes"));
           article.setPublishtime(rs.getTimestamp("publish_time").toLocalDateTime());
           article.setUserid(rs.getInt("user_id"));
           articleList.add(article);
       }
       return articleList;
    }
}
