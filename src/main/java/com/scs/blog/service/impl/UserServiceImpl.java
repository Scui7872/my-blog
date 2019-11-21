package com.scs.blog.service.impl;

import com.scs.blog.dao.UserDao;
import com.scs.blog.domain.UserDto;
import com.scs.blog.entity.User;
import com.scs.blog.factory.DaoFactory;
import com.scs.blog.service.UserService;
import com.scs.blog.util.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
  private UserDao userDao = DaoFactory.getUserDaoInstance();
  private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  @Override
  public List<User> listUsers() {
    List<User> userList = null;
    try {
      userList = userDao.selectAll();
    } catch (SQLException e) {
      System.out.println("查询所有用户出现异常");
    }
    return userList;
  }

  @Override
  public Map<String, Object> signIn(UserDto userDto) {
    User user = null;
    Map<String, Object> map = new HashMap<>();
    try {
      user = userDao.findUserByMobile(userDto.getMobile());
    } catch (SQLException e) {
      logger.error("根据手机号查询用户出现异常");
    }
    if (user != null) {
      if (user.getPassword().equals(userDto.getPassword())) {
        map.put("msg", Message.SIGN_IN_SUCCESS);
        map.put("data", user);
      } else {
        map.put("msg", Message.PASSWORD_ERROR);
      }
    } else {
      map.put("msg", Message.MOBILE_NOT_FOUND);
    }
    return map;
  }

  @Override
  public Map<String, Object> signUp(UserDto userDto) {
    Map<String,Object> map = new HashMap<>();
    int i = 0;
    try {
      i = DaoFactory.getUserDaoInstance().insert(userDto);
    } catch (SQLException e) {
      logger.error("注册失败");
    }
    if(i == 1) {
      map.put("msg","注册成功");
    } else {
      map.put("msg","注册失败");
    }
    return map;
  }
}

