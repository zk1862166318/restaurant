package com.aaa.controller;

import com.aaa.service.ZYGItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
