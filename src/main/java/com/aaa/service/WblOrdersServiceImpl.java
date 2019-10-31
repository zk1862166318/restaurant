package com.aaa.service;

import com.aaa.mapper.WblOrdersMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class WblOrdersServiceImpl implements WblOrdersService{
    @Resource
    private WblOrdersMapper wblOrdersMapper;
    @Override
    public List<Map<String, Object>> selTickets() {
        return wblOrdersMapper.selTickets();
    }


}
