package com.aaa.service;

import com.aaa.entity.Menus;
import com.aaa.entity.Types;
import com.aaa.mapper.ZkqMenuShowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ZkqMenuShowServiceImpl implements ZkqMenuShowService {

    @Resource
    private ZkqMenuShowMapper zkqMenuShowMapper;

    @Override
    public List<Types> getTypes() {
        return zkqMenuShowMapper.getTypes();
    }

    @Override
    public List<Menus> getMenus(Integer t_id) {

        return zkqMenuShowMapper.getMenus(t_id);
    }
    @Override
    public Integer addOrder(String orderDate, Integer u_id, Double amount, Integer deskNumber, Integer[] m_id, Integer[] count) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("orderDate",orderDate);
        map.put("u_id",u_id);
        map.put("amount",amount);
        map.put("deskNumber",deskNumber);
        //添加订单
        Integer num = zkqMenuShowMapper.addOrder(map);
        Integer integer = 0;
        if(num>0){
            integer = this.addOrderDetail(orderDate, u_id, amount, deskNumber, m_id, count);
        }
        return integer;
    }

    @Override
    public List<Map<String,Object>> selOrders(String orderDate, Integer u_id, Integer deskNumber) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("orderDate",orderDate);
        map.put("u_id",u_id);
        map.put("deskNumber",deskNumber);
        Integer  o_id =  zkqMenuShowMapper.selOneOrder(map);
        List<Map<String,Object>> message = zkqMenuShowMapper.selOrders(o_id);
        return message;
    }

    @Override
    public List<Map<String, Object>> selInform() {
        return zkqMenuShowMapper.selInform();
    }

    @Override
    public Integer ona(Integer id) {
        return zkqMenuShowMapper.ona(id);
    }

    //微信登录
    @Override
    public Integer login(Map<String, Object> map) {
        Integer num = zkqMenuShowMapper.login(map);
        if(num <= 0){
            Integer addnum =  zkqMenuShowMapper.addUser(map);
                if(addnum>0){
                     num = zkqMenuShowMapper.login(map);
                     return num;
                }else {
                    return 0;
                }
        }else {
            return num;
        }

    }
    //调用循环添加
    public Integer addOrderDetail(String orderDate, Integer u_id, Double amount, Integer deskNumber, Integer[] m_id, Integer[] count){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("orderDate",orderDate);
        map.put("u_id",u_id);
        map.put("deskNumber",deskNumber);
        Integer  o_id =  zkqMenuShowMapper.selOneOrder(map);
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("o_id",o_id);
        int counts = 0;
        for(int i = 0;i < m_id.length;i++){
            map1.put("m_id",m_id[i]);
            map1.put("count",count[i]);
            counts += zkqMenuShowMapper.addOrderDetail(map1);
        }
        return counts;
    }
    //
//    public void addOnes(Map map) {
//        zkqMenuShowMapper.addOnes(map);
//    }
}
