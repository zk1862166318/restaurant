package com.aaa.mapper;

import com.aaa.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SysPermissionMapperCustom {
	
	//根据用户id查询菜单
	public List<SysPermission> findMenuListByUserId(String userid)throws Exception;
	//根据用户id查询权限url
	public List<SysPermission> findPermissionListByUserId(String userid)throws Exception;

}
