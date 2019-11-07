package com.aaa.service;

import com.aaa.mapper.ItemMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Resource
    private ItemMapper itemMapper;
    @Override
    public List<Map> itemList(Integer uid) {
        return itemMapper.itemList(uid);
    }
}
