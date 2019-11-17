package com.aaa.service;

import com.aaa.entity.*;

import java.util.List;



public interface SysUserService {
    //用户查询
    public List<SysUser> findSysUserList(int limit, int page);
    public int countSysUser();
    //用户添加
    public List<SysRole> findSysRoleList();
    public int addSysUser(SysUser sysUser);
    void addSysUserRole(List<SysUserRole> mlist);
    public List<SysUser> checkSysUser(String usercode);
    //用户编辑
   SysUserDB selSysUser(String id);
    List<SysRole> selSysRole();
    int updateSysUser(SysUser sysUser);
   int  updateSysUserByID(SysUserRole sysUserRole);
    int changeState(SysUser su);
    //角色查询
    public List<SysRole> findSysRoleList(int limit,int page);
    public int countSysRole();
    //导出所有数据
//    List<SysRole> findSysRoleAll();
    //验证添加角色
    public List<SysRole> checkRole(String name);
    public int addSysRole(SysRole sysRole);
    //修改角色
    public List<SysRole> selOneSysRole(String id);
    public int updateRole(SysRole sysRole);
    int changeRoleState(SysRole sr);
    //角色授权
    public List<SysPermission> idByUserid(String userid);
    public List<SysPermission> menuAll(String m);
    public List<SysPermission> permissionAll(String p);
    //查询所有权限
    public List<SysPermission> spAll();
    //修改权限
    public int deletePer(String roleid);
    void addSysRolePermission(List<SysRolePermission> sprlist);
    //
    List<SysRole> findRoleAll();
    //修改密码
    int updatePwd(SysUser sysUser);

}
