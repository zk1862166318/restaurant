package com.aaa.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ZygDetailsMapper {

    public List<Map> details(Integer o_id);
}
