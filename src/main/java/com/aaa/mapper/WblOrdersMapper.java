package com.aaa.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WblOrdersMapper {
    List<Map<String,Object>> selTickets();
}
