package com.aaa.controller;

import com.aaa.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/item")
public class MenuController {
    @Autowired
    private SysService sysService;
@RequestMapping("/queryItem")
    public String queryItem(){
    return "item/queryItem";
}


}
