package com.aaa.controller;

import com.aaa.service.ZYGItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("item")
public class ZYGItemController {
    @Autowired
    private ZYGItemService ZYGItemService;
    @RequestMapping("ItemList")
    @ResponseBody
    public List<Map> itemList(Integer uid){
        List<Map> item= ZYGItemService.itemList(uid);

        return item;
    };
    @RequestMapping("showCmp")
    @ResponseBody
    public Map showCmp(int oid){
            Map map=new HashMap();
            int all = ZYGItemService.selOd(oid);
            int part= ZYGItemService.selOdw(oid);
        int sc = ZYGItemService.selCmp(oid);
        map.put("all",all);
            map.put("part",part);
            map.put("sc",sc);
            return map;

    }
}
