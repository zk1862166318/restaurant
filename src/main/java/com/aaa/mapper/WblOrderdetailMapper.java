package com.aaa.mapper;


import com.aaa.entity.Orderdetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface WblOrderdetailMapper {
    List<Map> selectAll(Map<String,Object> map);
    int selectCount(Orderdetail orderdetail);
    List<Map> selectMessage(Map<String,Object> map);
}
