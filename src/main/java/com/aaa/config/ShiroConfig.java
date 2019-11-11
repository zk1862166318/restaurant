package com.aaa.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //数据域
    @Bean(name = "userRealm")
    public UserRealm getUserRealm(@Qualifier("hashedCredentialsMatcher")HashedCredentialsMatcher hashedCredentialsMatcher){
        UserRealm userRealm=new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return userRealm;
    }
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> filemap=new LinkedHashMap<String,String>();
        //设置不拦截静态资源
        filemap.put("/css/**","anon");
        filemap.put("/js/**","anon");
        filemap.put("/images/**","anon");
        filemap.put("/layui/**","anon");
        filemap.put("/layui_ext/**","anon");
        filemap.put("/upload/**","anon");
        filemap.put("/zkq/**","anon");
        filemap.put("/**/**","anon");
        //
        filemap.put("/login","anon");

        //授权
        filemap.put("/user/query","perms[user:query]");
        filemap.put("/user/create","perms[user:create]");//为添加用户授予权限
        filemap.put("/role/query","perms[role:query]");
        filemap.put("/item/queryItem","perms[item:query]");
        filemap.put("/jrj/selOrder","perms[jrj:selOrder]");
        filemap.put("/foodMassage/selType","perms[foodMassage:selType]");
        filemap.put("/foodMassage/foodSelUp.html","perms[foodMassage:foodSelUp.html]");
        filemap.put("/foodMassage/foodSel.html","perms[foodMassage:foodSel.html]");
        filemap.put("/type/aaa","perms[type:aaa]");
        filemap.put("/wbl/caiwu","perms[wbl:caiwu]");
        filemap.put("/wbl/tables","perms[wbl:table]");
        filemap.put("/booth.html","perms[booth.html]");
        filemap.put("/logout", "logout");
        //设置拦截所有
        filemap.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filemap);
        //设置拦截后的跳转页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        //没有权限时跳转noAuth页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");
        return shiroFilterFactoryBean;
    }
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher getHashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1);
        return hashedCredentialsMatcher;
    }
    @Bean(name = "shiroDialect")
    public ShiroDialect getShiroDialect(){
        ShiroDialect shiroDialect=new ShiroDialect();
        return shiroDialect;
    }
}
