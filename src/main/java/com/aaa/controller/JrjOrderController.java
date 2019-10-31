package com.aaa.controller;

import com.aaa.entity.OrderDetial;
import com.aaa.entity.Orders;
import com.aaa.service.JrjOrderService;
import com.aaa.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class JrjOrderController {
    @Autowired
    private JrjOrderService jrjOrderService;
    @RequestMapping("jrj/selOrder")
    public String selOrder(){
        return "jrj/selOrder";
    }
    @RequestMapping("jrj/queryOrderData")
    @ResponseBody
    public PageUtil<List> queryOrderData(@RequestParam("limit") int limit, @RequestParam("page") int page) {
        List<Orders> orderList = jrjOrderService.findOrderList(limit, page);
        int count = jrjOrderService.countOrder();
        PageUtil<List> list = new PageUtil<>("", orderList, 0, count);
        if (orderList != null) {
            return list;
        }
        return null;
    }
    @RequestMapping("jrj/JrjOrderDetial")
    public String JrjOrderDetial(Integer id,Model model){
        model.addAttribute("id",id);
        return "jrj/OrderDetial";
    }
    @RequestMapping("jrj/queryOrderDetialData")
    @ResponseBody
    public PageUtil<List> queryOrderDetialData(Integer id){
        List<OrderDetial> odList=jrjOrderService.findOrderDetial(id);
        PageUtil<List> list = new PageUtil<>("", odList, 0, 0);
        return list;
    }

}
