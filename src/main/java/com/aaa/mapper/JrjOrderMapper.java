package com.aaa.mapper;

import com.aaa.entity.OrderDetial;
import com.aaa.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface JrjOrderMapper {
    public List<Orders> findOrderList(Orders orders);
    public int countOrder();
    public List<OrderDetial> findOrderDetial(Integer id);
}
