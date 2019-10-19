package com.aaa.config;

import com.aaa.entity.ActiveUser;
import com.aaa.entity.SysPermission;
import com.aaa.entity.SysUser;
import com.aaa.service.SysService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class UserRealm extends AuthorizingRealm {
    @Autowired
    private SysService sysService;
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String usercode=(String)authenticationToken.getPrincipal();
        SysUser sysUser=null;
        try {
            sysUser=sysService.findSysUserByUserCode(usercode);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (sysUser==null) {
            return null;
        }
      //SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(sysUser,sysUser.getPassword(),ByteSource.Util.bytes(sysUser.getSalt()),getName());
      // return info;

// 根据用户id取出菜单
        List<SysPermission> menus = null;
        List<SysPermission> permissions=null;
        try {
            menus = sysService.findMenuListByUserId(sysUser.getId());
            permissions=sysService.findPermissionListByUserId(sysUser.getId());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 用户密码
        String password = sysUser.getPassword();
        //盐
        String salt = sysUser.getSalt();

        // 构建用户身体份信息
        ActiveUser activeUser = new ActiveUser();
        activeUser.setUserid(sysUser.getId());
        activeUser.setUsername(sysUser.getUsername());
        activeUser.setUsercode(sysUser.getUsercode());
        activeUser.setMenus(menus);
        activeUser.setPermissions(permissions);

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                activeUser, password, ByteSource.Util.bytes(salt),getName());

        return simpleAuthenticationInfo;


    }
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //获取认证过的用户
        Subject subject=SecurityUtils.getSubject();
        ActiveUser user=(ActiveUser) subject.getPrincipal();
        //查询该用户所有的权限
        List<SysPermission> ps = null;
        try {
            ps=sysService.findPermissionListByUserId(user.getUserid());
            System.out.println(ps);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Set<String> permissions = new HashSet<String>();
        for(SysPermission p:ps){
            permissions.add(p.getPercode());
        }
        info.setStringPermissions(permissions);
        return info;
    }


}
