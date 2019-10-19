package com.aaa.controller;

import com.aaa.entity.Types;
import com.aaa.service.ZkqMenuShowService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/zkq")
public class ZkqMenuShowController {

    @Autowired
    private ZkqMenuShowService zkqMenuShowService;

    @RequestMapping("/types")
    @ResponseBody
    public List<Types> types(){

        List<Types> types = zkqMenuShowService.getTypes();
        for(Types type :types){
            System.out.println(type.getId());
            System.out.println(type.getTypename());
        }
        return types;
    }
    //@RequestMapping("/menu")
    //public
}
