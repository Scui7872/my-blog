package com.scs.blog.dao.impl;

import com.scs.blog.dao.UserDao;
import com.scs.blog.domain.UserDto;
import com.scs.blog.entity.Student;
import com.scs.blog.entity.User;
import com.scs.blog.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.scs.blog.util.JSoupSpider.logger;

public class UserDaoImpl implements UserDao {

    public int[] bathInsert(List<User> userList) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "INSERT INTO t_user (mobile,password,nickname,avatar,gender,birthday,introduction,create_time) VALUES (?,?,?,?,?,?,?,?) ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        connection.setAutoCommit(false);
        userList.forEach(user -> {
            try {
                pstmt.setString(1, user.getMobile());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getNickname());
                pstmt.setString(4, user.getAvatar());
                pstmt.setString(5, user.getGender());
                //日期的设置，可以使用setObject
                pstmt.setObject(6, user.getBirthday());
                pstmt.setString(7, user.getIntroduction());
                pstmt.setObject(8, user.getCreateTime());
                pstmt.addBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        int[] result = pstmt.executeBatch();
        connection.commit();
        DbUtil.close(null,pstmt,connection);
        return  result;
    }

    @Override
    public User findUserByMobile(String mobile) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_user WHERE mobile = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,mobile);
        ResultSet rs = pstmt.executeQuery();
        User user = null;
        while (rs.next()){
            user = new User();
            user.setId(rs.getLong("id"));
            user.setMobile(rs.getString("mobile"));
            user.setPassword(rs.getString("password"));
            user.setNickname(rs.getString("nickname"));
            user.setAvatar(rs.getString("avatar"));
            user.setGender(rs.getString("gender"));
            user.setBirthday(rs.getDate("birthday").toLocalDate());
            user.setIntroduction(rs.getString("introduction"));
            user.setAddress(rs.getString("address"));
            user.setFollows(rs.getShort("follows"));
            user.setFans(rs.getShort("fans"));
            user.setArticle(rs.getShort("articles"));
            user.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
            user.setStatus(rs.getShort("status"));

        }
        return user;
    }

    @Override
    public List<User> selectAll() throws SQLException {
        List<User> userList = new ArrayList<>();
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_user ORDER BY id DESC";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs =pstmt.executeQuery();
       try {
           while (rs.next()) {
               User user = new User();
               user.setId(rs.getLong("id"));
               user.setNickname(rs.getString("nickname"));
               user.setAvatar(rs.getString("avatar"));
               Timestamp timestamp = rs.getTimestamp("create_time");
               user.setCreateTime(timestamp.toLocalDateTime());
               userList.add(user);
           }
       }catch (SQLException e) {
           logger.error("查询用户数据产生异常");
       }
        return userList;
    }

    @Override
    public int insert(UserDto userDto) throws SQLException {
        Connection connection = DbUtil.getConnection();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO t_user(mobile,password) VALUES(?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,userDto.getMobile());
        pstmt.setString(2,userDto.getPassword());
        int n = pstmt.executeUpdate();
        connection.commit();
        return  n;
    }
}
