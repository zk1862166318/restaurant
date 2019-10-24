package com.aaa.service;

import com.aaa.entity.Menus;
import com.aaa.entity.Types;

import java.util.List;

public interface ZkqMenuShowService {

    public List<Types> getTypes();

    List<Menus> getMenus(Integer t_id);
}
