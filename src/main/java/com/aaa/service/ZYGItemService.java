package com.aaa.service;

import java.util.List;
import java.util.Map;

public interface ZYGItemService {
    public List<Map> itemList(Integer uid);
    public int selOd(int oid);
    public int selOdw(int oid);
    public int selCmp(int oid);
}
