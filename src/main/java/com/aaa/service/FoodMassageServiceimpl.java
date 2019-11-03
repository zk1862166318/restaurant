package com.aaa.service;

import com.aaa.entity.Orders;
import com.aaa.entity.OrdersDB;
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
    public int foodAdd(Orders orders) {
        return foodMassageMapper.foodAdd(orders);
    }

    @Override
    public List<OrdersDB> foodSel(Map map) {
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
    public int countAll() {
        return foodMassageMapper.countAll();
    }
}