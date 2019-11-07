package com.aaa.service;

import com.aaa.entity.Types;

import java.util.List;
import java.util.Map;

public interface TypesService {
    List<Types> selTypes(int limit, int page);
    int selOne();
    void addTypes(Types types);
    List<Types> addTypesOne(String t_name);
    Integer updateTypes(Map map);
    int selMenus(Integer t_id);
}
