package com.aaa.mapper;

import com.aaa.entity.Orders;
import com.aaa.entity.OrdersDB;
import com.aaa.entity.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FoodMassageMapper {
    public List<Type> selType();

    public int foodAdd(Orders orders);

    public List<OrdersDB> foodSel(Map map);
    public int foodUpd(Map map);
    public int stateUpd(Map map);
    public int countAll();
}
