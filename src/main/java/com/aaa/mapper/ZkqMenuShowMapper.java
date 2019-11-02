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
    public Integer  addOnes(Map map);

    public Integer addOrder(Map<String, Object> map);

    public Integer selOneOrder(Map<String, Object> map);

    public Integer addOrderDetail(Map<String, Object> map1);

    public List<Map<String,Object>> selOrders(Integer o_id);

    public List<Map<String, Object>> selInform();

    public Integer ona(Integer id);

    public Integer login(Map<String, Object> map);

    public Integer addUser(Map<String, Object> map);
}
