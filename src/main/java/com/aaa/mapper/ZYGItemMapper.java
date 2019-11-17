package com.aaa.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ZYGItemMapper {
    public List<Map> itemList(Integer uid);

}
