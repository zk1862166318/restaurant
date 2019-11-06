package com.aaa.controller;

import com.aaa.service.WblOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("wbl")
public class WblOrdersController {
    @Autowired
    private WblOrdersService wblOrdersService;

    @RequestMapping("caiwu")
    public String caiWuTongJi(){
        return "cartogram";
    }

    @RequestMapping("caiwutubiao")
    @ResponseBody
    public Object caiWuTuBiao(){
        List <Map<String,Object>> list = wblOrdersService.selTickets();
        return list;
    }


}
