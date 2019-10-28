package com.aaa.service;

import com.aaa.entity.Users;
import com.aaa.mapper.WblLoginMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class WblLoginServiceImpl implements WblLoginService{
    @Resource
    private WblLoginMapper wblLoginMapper;
    @Override
    public Users login(String name, String password) {
       return   wblLoginMapper.findUserByNameAndPwd(name, password);
    }

    @Override
    public void addUser(Users users) {
        wblLoginMapper.addUser(users);
    }
}
