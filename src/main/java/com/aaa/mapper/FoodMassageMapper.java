package com.aaa.mapper;

import com.aaa.entity.MenusDB;
import com.aaa.entity.Menus;
import com.aaa.entity.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FoodMassageMapper {
    public List<Type> selType();

    public int foodAdd(Menus Menus);

    public List<MenusDB> foodSel(Map map);
    public int foodUpd(Map map);
    public int stateUpd(Map map);
    public int countAll(Integer m_state);
}
