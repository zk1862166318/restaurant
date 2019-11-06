package com.aaa.service;

import com.aaa.entity.Users;
import com.aaa.mapper.WblLoginMapper;

public interface WblLoginService {
    //用户登录

    public Users login(String name, String password);

    //注册
    public void addUser(Users users);
}
