package com.aaa.mapper;

import com.aaa.entity.Types;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TypesMapper {
    List<Types> selTypes(Types types);
    int selOne();
    void addTypes(Types types);
    List<Types> addTypesOne(String t_name);
    Integer updateTypes(Map map);
    int selMenus(Integer t_id);
}
