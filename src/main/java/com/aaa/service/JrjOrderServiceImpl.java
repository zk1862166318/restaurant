package com.aaa.service;

import com.aaa.entity.OrderDetial;
import com.aaa.entity.Orders;
import com.aaa.mapper.JrjOrderMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class JrjOrderServiceImpl implements JrjOrderService {
    @Resource
    private JrjOrderMapper jrjOrderMapper;
    @Override
    public List<Orders> findOrderList(int limit, int page) {
        Orders ord=new Orders();
        ord.setStartRow((page-1)*limit);
        ord.setPageSize(limit);
        List<Orders> orderList = jrjOrderMapper.findOrderList(ord);
        return orderList;
    }

    @Override
    public int countOrder() {
        int i = jrjOrderMapper.countOrder();
        return i;
    }

    @Override
    public List<OrderDetial> findOrderDetial(Integer id) {
        return jrjOrderMapper.findOrderDetial(id);
    }


}
