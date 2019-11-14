package com.aaa.service;

import com.aaa.mapper.ZYGItemMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ZYGItemServiceImpl implements ZYGItemService {
    @Resource
    private ZYGItemMapper ZYGItemMapper;
    @Override
    public List<Map> itemList(Integer uid) {
        return ZYGItemMapper.itemList(uid);
    }

    @Override
    public int selOd(int oid) {
        return ZYGItemMapper.selOd(oid);
    }

    @Override
    public int selOdw(int oid) {
        return ZYGItemMapper.selOdw(oid);
    }

    @Override
    public int selCmp(int oid) {
        return ZYGItemMapper.selCmp(oid);
    }
}
