package com.aaa.service;

import com.aaa.config.UserCredentialsMatcher;
import com.aaa.entity.*;
import com.aaa.mapper.SysPermissionMapper;
import com.aaa.mapper.SysRoleMapper;
import com.aaa.mapper.SysRolePermissionMapper;
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
    @Resource
    private SysPermissionMapper sysPermissionMapper;
    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;
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
        SysUser sysUser1=new SysUser();
        sysUser1.setId(sysUser.getId());
        sysUser1.setUsercode(sysUser.getUsercode());
        sysUser1.setUsername(sysUser.getUsername());
        //产生六位的盐
        String salt = UserCredentialsMatcher.generateSalt(6);
        String md5pwd= UserCredentialsMatcher.encryptPassword("MD5",sysUser.getPassword(),salt,1);
        sysUser1.setPassword(md5pwd);
       // String md5Pass=DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes());
       // sysUser1.setPassword(md5Pass);
        sysUser1.setSalt(salt);
        sysUser1.setLocked("0");
        int insert = sysUserMapper.insert(sysUser1);
        return insert;
    }

    @Override
    public void addSysUserRole(List<SysUserRole> mlist) {
        sysUserMapper.addSysUserRole(mlist);
    }

    @Override
    public List<SysUser> checkSysUser(String usercode) {
        return sysUserMapper.checkSysUser(usercode);
    }

    @Override
    public SysUserDB selSysUser(String id) {

        return  sysUserMapper.selSysUserOne(id);
    }

    @Override
    public List<SysRole> selSysRole() {
        return sysRoleMapper.selSysRole();
    }

    @Override
    public int updateSysUser(SysUser sysUser) {
        SysUserExample example=new SysUserExample();
        return  sysUserMapper.updateByExample(sysUser,example);
    }

    @Override
    public int updateSysUserByID(SysUserRole sysUserRole) {

        return sysUserMapper.updateByID(sysUserRole);
    }

    @Override
    public int changeState(SysUser su) {

        return sysUserMapper.changeState(su);
    }

    //查询角色
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

//    @Override
//    public List<SysRole> findSysRoleAll() {
//        return sysRoleMapper.findSysRoleAllExcel();
//    }

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
        role.setAvailable("0");
        int insert = sysRoleMapper.insert(role);
        return insert;
    }

    @Override
    public List<SysRole> selOneSysRole(String id) {
        SysRoleExample example=new SysRoleExample();
        example.createCriteria().andIdEqualTo(id);
        List<SysRole> sysRoleOne = sysRoleMapper.selectByExample(example);
        return sysRoleOne;
    }

    @Override
    public int updateRole(SysRole sysRole) {
        SysRole role=new SysRole();
        role.setId(sysRole.getId());
        role.setName(sysRole.getName());
        role.setAvailable("0");
        return  sysRoleMapper.updateByPrimaryKey(role);
    }

    @Override
    public int changeRoleState(SysRole sr) {
        return sysUserMapper.changeRoleState(sr);
    }

    @Override
    public List<SysPermission> idByUserid(String userid) {
        return sysUserMapper.idByUserid(userid);
    }

    @Override
    public List<SysPermission> menuAll(String m) {
        SysPermissionExample example1=new SysPermissionExample();
        example1.createCriteria().andTypeEqualTo("menu");
        List<SysPermission> menuAll = sysPermissionMapper.selectByExample(example1);
        return menuAll;
    }

    @Override
    public List<SysPermission> permissionAll(String p) {
        SysPermissionExample example1 = new SysPermissionExample();
        example1.createCriteria().andTypeEqualTo("permission");
        List<SysPermission> sysPermissionAll = sysPermissionMapper.selectByExample(example1);
        return sysPermissionAll;

    }

    @Override
    public List<SysPermission> spAll() {
        SysPermissionExample example=new SysPermissionExample();
        return  sysPermissionMapper.selectByExample(example);
    }

    @Override
    public int deletePer(String roleid) {
        SysRolePermissionExample example=new SysRolePermissionExample();
        example.createCriteria().andSysRoleIdEqualTo(roleid);
        int i = sysRolePermissionMapper.deleteByExample(example);
        return i;
    }

    @Override
    public void addSysRolePermission(List<SysRolePermission> sprlist) {
        sysUserMapper.addSysRolePermission(sprlist);
    }

    @Override
    public List<SysRole> findRoleAll() {
        return sysUserMapper.findRoleAll();
    }

    @Override
    public int updatePwd(SysUser sysUser) {

        return  sysUserMapper.updatePwd(sysUser);
    }


}
