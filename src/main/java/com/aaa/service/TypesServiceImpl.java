package com.aaa.service;

import com.aaa.entity.Types;
import com.aaa.mapper.TypesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class TypesServiceImpl implements TypesService {
    @Resource
    private TypesMapper typesMapper;

    @Override
    public List<Types> selTypes(int limit, int page) {
        Types types=new Types();
        types.setStartRow((page-1)*limit);
        types.setPageSize(limit);
        return typesMapper.selTypes(types);
    }

    @Override
    public int selOne() {
        return typesMapper.selOne();
    }

    @Override
    public void addTypes(Types types) {
        typesMapper.addTypes(types);
    }

    @Override
    public List<Types> addTypesOne(String t_name) {
        return typesMapper.addTypesOne(t_name);
    }

    @Override
    public Integer updateTypes(Map map) {
         return typesMapper.updateTypes(map);
    }

    @Override
    public int selMenus(Integer t_id) {
        return typesMapper.selMenus(t_id);
    }
}
