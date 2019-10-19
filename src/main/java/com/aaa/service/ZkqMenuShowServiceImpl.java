package com.aaa.service;

import com.aaa.entity.Types;
import com.aaa.mapper.ZkqMenuShowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ZkqMenuShowServiceImpl implements ZkqMenuShowService {

    @Resource
    private ZkqMenuShowMapper zkqMenuShowMapper;

    @Override
    public List<Types> getTypes() {
        return zkqMenuShowMapper.getTypes();
    }
}
