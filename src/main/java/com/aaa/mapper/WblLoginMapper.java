package com.aaa.mapper;

import com.aaa.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WblLoginMapper {
    //根据用户名和密码查询用户
    //注解的两个参数会自动封装成map集合，括号内即为键

    public Users findUserByNameAndPwd(@Param("name") String name, @Param("password")String password);

    //注册
    public void addUser(Users users);
}
