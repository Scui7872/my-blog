package com.scs.blog.service;

import com.scs.blog.domain.UserDto;
import com.scs.blog.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> listUsers();

    Map<String,Object> signIn(UserDto userDto);

    Map<String,Object> signUp(UserDto userDto);
}
