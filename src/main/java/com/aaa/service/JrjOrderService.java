package com.aaa.service;

import com.aaa.entity.OrderDetial;
import com.aaa.entity.Orders;

import java.util.List;

public interface JrjOrderService {
    public List<Orders> findOrderList(int limit, int page);
    public int countOrder();
    List<OrderDetial> findOrderDetial(Integer id);


}
