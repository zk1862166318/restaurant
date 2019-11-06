package com.aaa.controller;

import com.aaa.service.ZygDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/zyg")
public class ZygDetailsController {

    @Autowired
    private ZygDetailsService zygDetailsService;
    @RequestMapping("/details")
    @ResponseBody
    public List<Map> details(Integer o_id){
        return zygDetailsService.details(o_id);
    }
}
