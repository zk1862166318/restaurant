package com.aaa.service;

import com.aaa.entity.MenusDB;
import com.aaa.entity.Menus;
import com.aaa.entity.Type;
import com.aaa.mapper.FoodMassageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class FoodMassageServiceimpl implements FoodMassageService {

    @Resource
    private FoodMassageMapper foodMassageMapper;

    @Override
    public List<Type> selType() {
        return foodMassageMapper.selType();
    }

    @Override
    public int foodAdd(Menus Menus) {
        return foodMassageMapper.foodAdd(Menus);
    }

    @Override
    public List<MenusDB> foodSel(Map map) {
        return foodMassageMapper.foodSel(map);
    }

    @Override
    public int foodUpd(Map map) {
        return foodMassageMapper.foodUpd(map);
    }

    @Override
    public int stateUpd(Map map) {
        return foodMassageMapper.stateUpd(map);
    }

    @Override
    public int countAll(Map map) {
        return foodMassageMapper.countAll(map);
    }

    @Override
    public int selName(String m_name) {
        return foodMassageMapper.selName(m_name);
    }
}