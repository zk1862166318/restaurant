package com.aaa.controller;

import com.aaa.entity.ActiveUser;
import com.aaa.entity.SysUser;
import com.aaa.service.SysService;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SysUserController {

    @Autowired
    private SysService sysService;
    @RequestMapping("index")
    public String index(Model model){
        //主体
        Subject subject = SecurityUtils.getSubject();
        //身份
        ActiveUser activeUser = (ActiveUser) subject.getPrincipal();

        model.addAttribute("activeUser", activeUser);
        return "index";
    }
    @RequestMapping("toLogin")
    public String toLogin(){
        return "backlogin";
    }
    @RequestMapping("login")
    @ResponseBody
    public Map<String, Object> login(SysUser sysUser, HttpSession session){
        Map<String, Object> map = new HashMap<String, Object>();
        //处理认证登录
        //1.建立subject
        Subject subject=SecurityUtils.getSubject();
        //2.封装token凭证
        UsernamePasswordToken token=new UsernamePasswordToken(sysUser.getUsercode(),sysUser.getPassword());
        //3.登录
        boolean flag = true;
        String msg = "";
        try {
            subject.login(token);
            msg = "登陆成功！";
            map.put("flag", flag);


        }catch (Exception exception) {
            if (exception instanceof UnknownAccountException) {
                msg = "登录失败，用户账号不存在！";
            } else if (exception instanceof IncorrectCredentialsException) {
                msg = "登录失败，用户密码不正确！";
            } else {
                msg = "登录失败，发生未知错误：" + exception;
            }
            map.put("flag", false);
        } finally {
            map.put("msg", msg);
            JSONObject result = new JSONObject(map);
            return result;
        }
    }

    @RequestMapping("noAuth")
    public String noAuth(){
        return "noAuth";
    }
    //用户管理
    @RequestMapping("user/query")
    public String sysUser(){
        return "sysuser/sysuser";
    }
    @RequestMapping("/user/create")
    public String userCreate(){
        return "sysuser/test1";
    }
}
