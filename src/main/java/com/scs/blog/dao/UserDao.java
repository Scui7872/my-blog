package com.scs.blog.dao;

import com.scs.blog.domain.UserDto;
import com.scs.blog.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    int[] bathInsert(List<User> userList) throws SQLException;

    User findUserByMobile(String mobile) throws SQLException;

    List<User> selectAll() throws SQLException;

    int insert(UserDto userDto) throws SQLException;

}
