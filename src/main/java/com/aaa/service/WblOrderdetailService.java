package com.aaa.service;

import com.aaa.entity.Orderdetail;
import com.aaa.entity.pageCount;

import java.util.List;
import java.util.Map;

public interface WblOrderdetailService {
    List<Map> selectAll(pageCount pageCount);
    int selectCount(Orderdetail orderdetail);
    public List<Map> selectMessage(pageCount pageCount,Orderdetail orderdetail);
    public List<Map> alldata();
}
