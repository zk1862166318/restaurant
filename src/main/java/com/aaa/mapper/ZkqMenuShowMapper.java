package com.aaa.mapper;

import com.aaa.entity.Types;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ZkqMenuShowMapper {
    public List<Types> getTypes();
}
