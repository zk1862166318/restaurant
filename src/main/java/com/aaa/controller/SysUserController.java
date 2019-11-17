package com.aaa.controller;

import com.aaa.config.UserCredentialsMatcher;
import com.aaa.dtree.CheckArr;
import com.aaa.dtree.DTree;
import com.aaa.dtree.DTreeResponse;
import com.aaa.dtree.Status;
import com.aaa.entity.*;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
    public String index(Model model) {
        Subject subject = SecurityUtils.getSubject();
        ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
        model.addAttribute("activeUser", activeUser);

        return "index";
    }

    @RequestMapping("toLogin")
    public String toLogin() {
        return "backlogin";
    }

    @RequestMapping("login")
    @ResponseBody
    public Map<String, Object> login(SysUser sysUser, HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();
        //处理认证登录
        //1.建立subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装token凭证
        UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUsercode(), sysUser.getPassword());
        //3.登录
        boolean flag = true;
        String msg = "";
        SysUser sysUserByUserCode=null;
        try {
            sysUserByUserCode = sysService.findSysUserByUserCode(sysUser.getUsercode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
        if(sysUserByUserCode.getLocked().equals("0")){
            subject.login(token);

            msg = "登陆成功！";
            map.put("flag", flag);

        }else{
            msg="该账户已被禁用";
            map.put("flag",false);
        }


        } catch (Exception exception) {
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
    public String noAuth() {
        return "noAuth";
    }
    //用户管理

    @RequestMapping("user/query")
    public String userQuery() {
        return "sysuser/sysuser";
    }

    @RequestMapping("user/queryData")
    @ResponseBody
    public PageUtil<List> sysUserList(@RequestParam("limit") int limit, @RequestParam("page") int page) {
        List<SysUser> sysUserList = sysUserService.findSysUserList(limit, page);
        int count = sysUserService.countSysUser();
        PageUtil<List> list = new PageUtil<List>("", sysUserList, 0, count);
        if (sysUserList != null) {
            return list;

        }

        return null;
    }

    @RequestMapping("user/addSysUser")
    public String addSysUser(Model model) {
        List<SysRole> sysRoleList = sysUserService.findRoleAll();
        model.addAttribute("sysRoleList", sysRoleList);
        return "sysuser/addSysUser";
    }

    @RequestMapping(value = "user/add", method = RequestMethod.POST)
    @ResponseBody
    public PageUtil<SysUser> addOneSysUser(SysUser sysUser, String roleid) throws Exception {
        PageUtil result=new PageUtil();
        List<SysUser> sysUsers = sysUserService.checkSysUser(sysUser.getUsercode());
        if (sysUsers != null && sysUsers.size() > 0) {
            result.setMsg("添加的账号已存在！");
            result.setData(false);
            return result;
        }else {
            sysUserService.addSysUser(sysUser);
            SysUser sysUserAll = sysService.findSysUserByUsername(sysUser.getUsername());
            List<SysUserRole> mlist = new ArrayList<SysUserRole>();
            //以逗号形式分割字符串转化为数组
            String[] split = roleid.split(",");
            if (split.length > 0) {
                for (String str : split) {
                    if (str != "" && str != null) {
                        SysUserRole m = new SysUserRole();
                        m.setSysUserId(sysUserAll.getId());
                        m.setSysRoleId(str);
                        mlist.add(m);
                    }
                }
            }
            sysUserService.addSysUserRole(mlist);
            result.setMsg("添加成功！");
            result.setData(true);
            return result;
        }
    }
    //用户编辑
    @RequestMapping("user/editSysUser")
    public String editSysUser(String id,Model model){
        SysUserDB sysUserDB = sysUserService.selSysUser(id);
        List<SysRole> sysRoles = sysUserService.selSysRole();
        model.addAttribute("sysUser",sysUserDB);
        model.addAttribute("sysRoles",sysRoles);
        return "sysuser/editSysUser";
    }
    @RequestMapping("user/updateSysUser")
    @ResponseBody
    public PageUtil updateSysUser(SysUser sysUser,String roleid){
      //   sysUserService.updateSysUser(sysUser);
        PageUtil result=new PageUtil();
        SysUserRole sur=new SysUserRole();
        sur.setSysRoleId(roleid);
        sur.setSysUserId(sysUser.getId());
        sysUserService.updateSysUserByID(sur);
        result.setData(true);
        result.setMsg("修改成功!");
        return result;
    }

    @RequestMapping("user/changeState")
    public String changeState(String id,String locked){
        SysUser su=new SysUser();
        su.setId(id);
        su.setLocked(locked);
        sysUserService.changeState(su);
        return "sysuser/sysuser";
    }
    @RequestMapping("role/changeRoleState")
    public String changeRoleState(String id,String available){
        SysRole sr=new SysRole();
        sr.setId(id);
        sr.setAvailable(available);
        if(available.equals("1")){
            sysUserService.deletePer(id);
        }
        sysUserService.changeRoleState(sr);
        return "sysuser/sysrole";
    }
    //角色管理
    @RequestMapping("role/query")
    public String roleQuery() {
        return "sysuser/sysrole";
    }

    @RequestMapping("role/queryRoleData")
    @ResponseBody
    public PageUtil<List> sysRoleList(@RequestParam("limit") int limit, @RequestParam("page") int page) {
        List<SysRole> sysRoleList = sysUserService.findSysRoleList(limit, page);
        int count = sysUserService.countSysRole();
        PageUtil<List> list = new PageUtil<>("", sysRoleList, 0, count);
        if (sysRoleList != null) {
            return list;
        }
        return null;
    }

    @RequestMapping("role/addSysRole")
    public String addSysRole() {
        return "sysuser/addSysRole";
    }

    @RequestMapping(value = "role/add", method = RequestMethod.POST)
    @ResponseBody
    public PageUtil<SysRole> addOneSysRole(SysRole sysRole) {
        PageUtil result = new PageUtil();
        List<SysRole> sysRoles = sysUserService.checkRole(sysRole.getName());
        if (sysRoles != null && sysRoles.size() > 0) {
            result.setMsg("添加的角色名已存在！");
            result.setData(false);
            return result;
        } else {
            sysUserService.addSysRole(sysRole);
            result.setMsg("添加成功！");
            result.setData(true);
            return result;
        }
    }

    @RequestMapping("role/editSysRole")
    public String editSysRole(String id, Model model) {
        System.out.println(id);
        List<SysRole> sysRoleOne = sysUserService.selOneSysRole(id);
        model.addAttribute("sysRoleOne", sysRoleOne);
        return "sysuser/editSysRole";
    }

    @RequestMapping(value = "role/updateRole", method = RequestMethod.POST)
    @ResponseBody
    public PageUtil<SysRole> updateRole(SysRole sysRole) {
        PageUtil result = new PageUtil();
        int i = sysUserService.updateRole(sysRole);
        result.setMsg("修改成功！");
        result.setData(true);
        return result;
    }
//@RequestMapping("role/AllRole")
//@ResponseBody
//public PageUtil<List> AllRole(){
//    List<SysRole> roleAll = sysUserService.findSysRoleAll();
//    PageUtil<List> list = new PageUtil<>("", roleAll, 0, 0);
//    if (roleAll != null) {
//        return list;
//    }
//    return null;
//}
    //角色权限
    @RequestMapping("role/rolePermission")
    public String rolePermission(String id, Model model){
        model.addAttribute("mid",id);
        List<SysPermission> sid = sysUserService.idByUserid(id);

        return "sysuser/rolePermission";
    }
    @RequestMapping("role/editRolePermission")
    @ResponseBody
    public DTreeResponse editRolePermission(HttpServletRequest request) {
        String type = request.getParameter("type");
        String mid=request.getParameter("mid");
        List<SysPermission> sid = sysUserService.idByUserid(mid);
        List<SysPermission> sysPermissions = sysUserService.spAll();
        List<SysPermission> menuAll = sysUserService.menuAll(type);
        List<SysPermission> permissionAll = sysUserService.permissionAll(type);
        List<DTree> sons=null;
        DTreeResponse response = null;
        DTree father = null;
        List<DTree> fa  = new ArrayList<>();
        for (SysPermission f : menuAll) {
            father = new DTree();
            father.setId((f.getId()).toString());
            father.setTitle(f.getName());
            father.setIsLast(false);
            father.setIconClass("-1");
            int fid = 0 ;
            if(sid.size()==0){
                father.setCheckArr(this.checkArrs("0"));
            }else {
                for (int i = 0; i < sid.size(); i++) {
                    fid++;
                    if (f.getId() == sid.get(i).getId()) {
                        father.setCheckArr(this.checkArrs("1"));
                        break;
                    } else {
                        if (sid.size() == fid) {
                            father.setCheckArr(this.checkArrs("0"));
                            break;
                        }
                    }
                }
            }
            father.setParentId(f.getParentid().toString());
            sons = new ArrayList<DTree>();
            for (SysPermission s : permissionAll) {
                DTree d = new DTree();
                d.setId(s.getId().toString());
                d.setParentId(s.getParentid().toString());
                d.setTitle(s.getName());
                d.setIsLast(true);
                int sonid = 0 ;
                if(sid.size()==0){
                    d.setCheckArr(this.checkArrs("0"));
                }else {
                    for (int i = 0; i < sid.size(); i++) {
                        sonid++;
                        if (s.getId() == sid.get(i).getId()) {
                            d.setCheckArr(this.checkArrs("1"));
                            break;
                        } else {
                            if (sid.size() == sonid) {
                                d.setCheckArr(this.checkArrs("0"));
                                break;
                            }
                        }
                    }
                }
                if (f.getId()==s.getParentid()) {
                    sons.add(d);
                    father.setChildren(sons);
                }

            }

            fa.add(father);
        }

        response = new DTreeResponse();
        response.setData(fa);
        response.setStatus(new Status());
        System.out.println(response.toString());
        return response;
    }
    private List<CheckArr> checkArrs(String num){
        List<CheckArr> checkArrs = new ArrayList<>();
        CheckArr checkArr = new CheckArr();
        checkArr.setType("0");
        checkArr.setChecked(num);
        checkArrs.add(checkArr);
        return checkArrs;
    }

    @RequestMapping("role/updatePermission")
    @ResponseBody
    public PageUtil<SysRolePermission> updatePermission(String[] params,String[] roleid){
        sysUserService.deletePer(roleid[0]);
        List<SysRolePermission> srplist = new ArrayList<SysRolePermission>();
         for(String s:params ){
            if (s!= "" && s != null) {
                 SysRolePermission srp=new SysRolePermission();
                 srp.setSysRoleId(roleid[0]);
                 srp.setSysPermissionId(s);
                 srplist.add(srp);
            }
        }
       sysUserService.addSysRolePermission(srplist);
        PageUtil result = new PageUtil();
        result.setMsg("授权成功！");
        return result;
    }
    @RequestMapping("updPwd")
    public String updPwd(String usercode,Model model){
        model.addAttribute("usercode",usercode);
        return "sysuser/updPwd";
    }
    @RequestMapping("checkOld")
    @ResponseBody
    public Map<String,Object> checkOld(String oldPwd,String usercode){
       Map<String,Object> map=new HashMap<>();
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(usercode,oldPwd);
        boolean flag = true;
        String msg = "";
        try {
            subject.login(token);
            map.put("msg",msg);
            map.put("flag",flag);

        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            msg="密码输入不正确,请重新输入";
            map.put("msg",msg);
            map.put("flag",false);

        }finally {
            JSONObject result = new JSONObject(map);
            return result;
        }

    }
        @RequestMapping("xiugaiPwd")
        @ResponseBody
    public Map<String,Object> xiugaiPwd(String newPwd,String usercode){
            Map<String,Object> map=new HashMap<>();
            String salt=UserCredentialsMatcher.generateSalt(6);
            String md5pwd=UserCredentialsMatcher.encryptPassword("MD5",newPwd,salt,1);
            SysUser sysUser=new SysUser();
            sysUser.setUsercode(usercode);
            sysUser.setPassword(md5pwd);
            sysUser.setSalt(salt);
            sysUserService.updatePwd(sysUser);
            map.put("msg","密码修改成功");
            map.put("flag",true);
            JSONObject result = new JSONObject(map);
            System.out.println(result);
            return result;
        }
}
