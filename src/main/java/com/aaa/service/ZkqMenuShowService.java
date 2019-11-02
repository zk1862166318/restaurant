package com.aaa.service;

import com.aaa.entity.Menus;
import com.aaa.entity.Types;

import java.util.List;
import java.util.Map;

public interface ZkqMenuShowService {

    public List<Types> getTypes();

    public List<Menus> getMenus(Integer t_id);

    public Integer addOrder(String orderDate, Integer u_id, Double amount, Integer deskNumber, Integer[] m_id, Integer[] count);

    public List<Map<String,Object>> selOrders(String orderDate, Integer u_id, Integer deskNumber);

    public List<Map<String, Object>> selInform();

    public Integer ona(Integer id);

    public Integer login(Map<String, Object> map);

}
