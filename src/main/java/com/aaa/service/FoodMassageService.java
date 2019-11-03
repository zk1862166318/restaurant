package com.aaa.service;

import com.aaa.entity.Orders;
import com.aaa.entity.OrdersDB;
import com.aaa.entity.Type;

import java.util.List;
import java.util.Map;

public interface FoodMassageService {
    public List<Type> selType();

    public int foodAdd(Orders orders);
    public List<OrdersDB> foodSel(Map map);
    public int foodUpd(Map map);
    public int stateUpd(Map map);
    public int countAll();
}
