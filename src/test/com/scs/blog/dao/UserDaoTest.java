package com.scs.blog.dao;


import com.scs.blog.dao.impl.UserDaoImpl;
import com.scs.blog.domain.UserDto;
import com.scs.blog.entity.User;
import com.scs.blog.factory.DaoFactory;
import com.scs.blog.util.JSoupSpider;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;


public class UserDaoTest {
      private static Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
      private UserDao userDao = DaoFactory.getUserDaoInstance();

    @Test
    public void bathInsert() throws SQLException {
        int[] i = userDao.bathInsert((JSoupSpider.getUsers()));
        if(i.length !=0) {
            System.out.println(i.length);
            logger.info("数据添加成功");
        }else {
            logger.error("用户数据添加失败");
        }
        try {
            int[] result = userDao.bathInsert((JSoupSpider.getUsers()));
        } catch (SQLException e) {
            logger.error("批量新增用户出现异常");
        }

    }

    @Test
    public void insert() throws SQLException{
        UserDto userDto = new UserDto("13215527872","123456");
        int i = DaoFactory.getUserDaoInstance().insert(userDto);
        if(i!=0) {
            System.out.println("ok");
        } else {
            System.out.println("no");
        }
    }

    @Test
    public void selectAll() throws SQLException {

    }
}
