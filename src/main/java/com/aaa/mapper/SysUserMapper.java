package com.aaa.mapper;


import com.aaa.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserMapper {
    int countByExample(SysUserExample example);

    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser findUserByName(String username);
    List<SysUser> sysUserList(SysUserExample example);
    void addSysUserRole(List<SysUserRole> mlist);
    public List<SysUser> checkSysUser(String usercode);
    public List<SysPermission> idByUserid(String userid);
    void addSysRolePermission(List<SysRolePermission> srpList);
    SysUserDB selSysUserOne(String id);
    int updateByID(SysUserRole sysUserRole);
    int changeState(SysUser su);
    int changeRoleState(SysRole sr);
    List<SysRole> findRoleAll();
    //修改密码部分
    int updatePwd(SysUser sysUser);
}