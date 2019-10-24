package com.aaa.mapper;

import com.aaa.entity.Menus;
import com.aaa.entity.Types;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ZkqMenuShowMapper {
    //查询类型
    public List<Types> getTypes();
    //查询菜单
    public List<Menus> getMenus(Integer t_id);
    //添加菜单
    public void  addOnes(Map map);
}
