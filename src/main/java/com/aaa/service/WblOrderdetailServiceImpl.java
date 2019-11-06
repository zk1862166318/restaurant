package com.aaa.service;

import com.aaa.entity.Orderdetail;
import com.aaa.entity.pageCount;
import com.aaa.mapper.WblOrderdetailMapper;
import com.aaa.mapper.WblOrdersMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class WblOrderdetailServiceImpl implements WblOrderdetailService{
    @Resource
    private WblOrderdetailMapper wblOrderdetailMapper;

    @Override
    public List<Map> selectAll(pageCount pageCount) {
        int begin=pageCount.getLimit()*(pageCount.getPage()-1);
        int end=pageCount.getLimit()*pageCount.getPage()-begin;
        Map<String,Object> map=new HashMap<>();
        map.put("begin",begin);
        map.put("end",end);
        return wblOrderdetailMapper.selectAll(map);
    }

    @Override
    public int selectCount(Orderdetail orderdetail) {
        return wblOrderdetailMapper.selectCount(orderdetail);
    }

    @Override
    public List<Map> selectMessage(pageCount pageCount, Orderdetail orderdetail) {
        int begin=pageCount.getLimit()*(pageCount.getPage()-1);
        int end=pageCount.getLimit()*pageCount.getPage()-begin;
        Map<String,Object> map=new HashMap<>();
        map.put("begin",begin);
        map.put("end",end);
        map.put("Obegin",orderdetail.Obegin);
        map.put("Oend",orderdetail.Oend);
        return wblOrderdetailMapper.selectMessage(map);
    }

    @Override
    public List<Map> alldata() {
        return wblOrderdetailMapper.alldata();
    }

    //实现分页与模糊查询

}
