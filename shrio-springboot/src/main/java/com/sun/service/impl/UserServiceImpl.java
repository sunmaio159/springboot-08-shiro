package com.sun.service.impl;

import com.sun.mapper.UserMapper;
import com.sun.pojo.User;
import com.sun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }
}
