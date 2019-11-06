package com.aaa.service;

import com.aaa.mapper.ZygDetailsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ZygDetailsServiceImpl implements ZygDetailsService  {

    @Resource
    private ZygDetailsMapper zygDetailsMapper;
    @Override
    public List<Map> details(Integer o_id) {
        return zygDetailsMapper.details(o_id);
    }
}
