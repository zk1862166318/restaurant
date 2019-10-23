package com.aaa.service;

import com.aaa.entity.SysRole;
import com.aaa.entity.SysRoleExample;
import com.aaa.entity.SysUser;
import com.aaa.entity.SysUserExample;
import com.aaa.mapper.SysRoleMapper;
import com.aaa.mapper.SysUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;



@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Override
    public List<SysUser> findSysUserList(int limit, int page) {
        SysUserExample example=new SysUserExample();
        example.setStartRow((page-1)*limit);
        example.setPageSize(limit);
        List<SysUser> sysUsers = sysUserMapper.sysUserList(example);
        return sysUsers;
    }

    @Override
    public int countSysUser() {
        SysUserExample example = new SysUserExample();
        int i = sysUserMapper.countByExample(example);
        return i;
    }

    @Override
    public List<SysRole> findSysRoleList() {
        SysRoleExample example=new SysRoleExample();
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(example);
        return sysRoles;
    }

    @Override
    public int addSysUser(SysUser sysUser) {
        return 0;
    }

    @Override
    public List<SysRole> findSysRoleList(int limit, int page) {
        SysRoleExample example=new SysRoleExample();
       example.setStartRow((page-1)*limit);
       example.setPageSize(limit);
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(example);
        return sysRoles;
    }

    @Override
    public int countSysRole() {
       SysRoleExample example=new SysRoleExample();
        int i = sysRoleMapper.countByExample(example);
        return i;
    }

    @Override
    public List<SysRole> checkRole(String name) {
        SysRoleExample example=new SysRoleExample();
         example.createCriteria().andNameEqualTo(name);
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(example);
        return sysRoles;
    }

    @Override
    public int addSysRole(SysRole sysRole) {
        SysRole role=new SysRole();
        role.setId(sysRole.getId());
        role.setName(sysRole.getName());
        role.setAvailable("1");
        int insert = sysRoleMapper.insert(role);
        return insert;
    }
}
