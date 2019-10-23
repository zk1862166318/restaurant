package com.aaa.controller;

import com.aaa.entity.ActiveUser;
import com.aaa.entity.SysRole;
import com.aaa.entity.SysUser;
import com.aaa.service.SysService;
import com.aaa.service.SysUserService;
import com.aaa.util.PageUtil;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SysUserController {

    @Autowired
    private SysService sysService;

    @Autowired
    private SysUserService sysUserService;
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
    public String userQuery(){
        return "sysuser/sysuser";
    }
    @RequestMapping("user/queryData")
    @ResponseBody
    public PageUtil<List> sysUserList(@RequestParam("limit") int limit, @RequestParam("page") int page){
        List<SysUser> sysUserList = sysUserService.findSysUserList(limit, page);
        int count= sysUserService.countSysUser();
        PageUtil<List> list=new PageUtil<List>("",sysUserList,0,count);
        if (sysUserList!=null){
            return list;

        }

        return null;
    }
    @RequestMapping("user/addSysUser")
    public String addSysUser(Model model){
        List<SysRole> sysRoleList = sysUserService.findSysRoleList();
        model.addAttribute("sysRoleList",sysRoleList);
        return "sysuser/addSysUser";
    }
    @RequestMapping(value = "user/add",method = RequestMethod.POST)
    @ResponseBody
    public PageUtil<SysUser> addOneSysUser(SysUser sysUser){
        PageUtil result=new PageUtil();
        result.setMsg("添加成功！");
        return result;
    }
    @RequestMapping("user/editSysUser")
    public String editSysUser(){
        return "sysuser/editSysUser";
    }
//角色管理
    @RequestMapping("role/query")
    public String roleQuery(){
        return "sysuser/sysrole";
    }
    @RequestMapping("role/queryRoleData")
    @ResponseBody
    public PageUtil<List> sysRoleList(@RequestParam("limit") int limit, @RequestParam("page") int page){
        List<SysRole> sysRoleList =sysUserService.findSysRoleList(limit, page);
        int count= sysUserService.countSysRole();
        PageUtil<List> list=new PageUtil<>("",sysRoleList,0,count);
        if (sysRoleList!=null){
            return list;
        }
        return null;
    }
    @RequestMapping("role/addSysRole")
    public String addSysRole(){
        return "sysuser/addSysRole";
    }
    @RequestMapping(value = "role/add",method = RequestMethod.POST)
    @ResponseBody
    public PageUtil<SysRole> addOneSysRole(SysRole sysRole){
        PageUtil result=new PageUtil();
        List<SysRole> sysRoles = sysUserService.checkRole(sysRole.getName());
        if (sysRoles!=null&&sysRoles.size()>0){
            result.setMsg("添加的角色名已存在！");
            result.setData(false);
            return result;
        }else {
            sysUserService.addSysRole(sysRole);
            result.setMsg("添加成功！");
            result.setData(true);
            return result;
        }
    }
    @RequestMapping("role/editSysRole")
    public String editSysRole(){
        return "sysuser/editSysRole";
    }
}
