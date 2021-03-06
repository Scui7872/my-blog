package com.scs.blog.service;

import com.scs.blog.domain.UserDto;
import com.scs.blog.factory.ServiceFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Map;

import static org.junit.Assert.*;

public class UserServiceTest {
    private  UserService userService = ServiceFactory.getUserServiceInstance();
    @Test
    public void signIn() {
        UserDto userDto = new UserDto("13923554302","bfa010ab6c01e42b2e86aa0e26f21d4f");
        Map<String,Object> map = userService.signIn(userDto);
        System.out.println(map.get("data"));
    }

    @Test
    public void signUp() throws SQLException {
        UserDto userDto = new UserDto("13215527871","111111");
        Map<String,Object> map = userService.signUp(userDto);
        System.out.println(map.get("msg"));
    }

}