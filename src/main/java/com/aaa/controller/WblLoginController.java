package com.aaa.controller;

import com.aaa.entity.Users;
import com.aaa.mapper.WblLoginMapper;
import com.aaa.service.WblLoginService;
import com.aaa.service.WblLoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("wbl")
public class WblLoginController {
    @Autowired
    private WblLoginServiceImpl wblLoginServiceImpl;
    @Autowired
    private WblLoginService wblLoginService;

    @RequestMapping("login")
    @ResponseBody
    public Users login(String name, String password) {
        System.out.println("用户登录：" + name + password);
        Users users = wblLoginService.login(name,password);
        return users;
    }
    @RequestMapping("save")
    public String saveUser(Users users, String u_tel, String u_pwd){

        return "redirect:login" ;
    }
}
