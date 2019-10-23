package com.aaa.service;

import com.aaa.entity.SysRole;
import com.aaa.entity.SysUser;

import java.util.List;



public interface SysUserService {
    //用户查询
    public List<SysUser> findSysUserList(int limit, int page);
    public int countSysUser();
    //用户添加
    public List<SysRole> findSysRoleList();
    public int addSysUser(SysUser sysUser);
    //角色查询
    public List<SysRole> findSysRoleList(int limit,int page);
    public int countSysRole();
    //验证添加角色
    public List<SysRole> checkRole(String name);
    public int addSysRole(SysRole sysRole);

}
