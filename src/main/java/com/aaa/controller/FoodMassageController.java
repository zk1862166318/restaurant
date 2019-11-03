package com.aaa.controller;


import com.aaa.entity.OrderVO;
import com.aaa.entity.Orders;
import com.aaa.entity.OrdersDB;
import com.aaa.entity.Type;
import com.aaa.service.FoodMassageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.Soundbank;
import java.io.Console;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/foodMassage")
public class FoodMassageController {
    @Autowired
    private FoodMassageService foodMassageService;

    /**
     * 用于查找类型
     * @return
     */
    @RequestMapping("type")
    @ResponseBody
    public List<Type> selType(){
        List<Type> type = foodMassageService.selType();
        return type;
    }

    @RequestMapping("/selType")
    public String selT(Model model) {
        List<Type> types = selType();
        model.addAttribute("types", types);
        return "foodMassage/foodAdd";
    }


    @ResponseBody
    @RequestMapping("/uploading")
    public Map<String, Object> uploading(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
        Assert.notNull(file, "上传文件不能为空");
        String filePath = request.getSession().getServletContext().getRealPath("img/");
//        String a = System.currentTimeMillis();
        String filename = file.getOriginalFilename();
        //确保路径存在
        File file2 = new File(filePath);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        String savepath = filePath + filename;
        System.out.println("轮播图保存路径:" + savepath);
        Map map = new HashMap<String, Object>();
        try {
            //保存文件到服务器
            file.transferTo(new File(savepath));
            //保存到数据库
            //返回json
            map.put("msg", "ok");
            map.put("code", 200);
            map.put("data", new HashMap<String, Object>() {
                {
                    put("src", savepath);
                }
            });
        } catch (Exception e) {
            map.put("msg", "error");
            map.put("code", 0);
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("/foodAdd")
    @ResponseBody
    public String foodAdd(String m_name,int m_price,String m_img,int m_state,int t_id) {
        Orders orders = new Orders();
        System.out.println(m_img);
        String filename = "img/"+m_img;
        orders.setM_img(filename);
        orders.setM_name(m_name);
        orders.setM_price(m_price);
        orders.setM_state(m_state);
        orders.setT_id(t_id);
        int foodAdd = foodMassageService.foodAdd(orders);
        if (foodAdd == 1) {
            return "1";
        }else {
            return  "0";
        }
    }

    @RequestMapping("/foodSel")
    @ResponseBody
    public OrderVO foodSel(int limit,Integer page) {
        Map map = new HashMap();
        map.put("limit",limit);
        map.put("page",page);
        List<OrdersDB> order = foodMassageService.foodSel(map);
        int count = foodMassageService.countAll();
        OrderVO orderVO = new OrderVO();
        orderVO.setCode(0);
        orderVO.setCount(count);
        orderVO.setData(order);
        return orderVO;
    }

    @RequestMapping("/toUpd")
    public String toUpd(Model model){
        List<Type> types = selType();
        model.addAttribute("type", types);
        return "foodMassage/foodUpd";
    }

    @RequestMapping("/foodUpd")
    @ResponseBody
    public Integer foodUpd(String nm_name,int nm_price,String nm_img,int nm_id,int nt_id){
        Map map = new HashMap();
        map.put("m_name",nm_name);
        map.put("m_price",nm_price);
        String filename = "img/"+nm_img;
        map.put("m_img",filename);
        map.put("m_id",nm_id);
        map.put("t_id",nt_id);
        System.out.println(map);
        int i = foodMassageService.foodUpd(map);
        return i;
    }

    @RequestMapping("/foodState")
    @ResponseBody
    public String foodState(int m_id,int m_state){
        Map map = new HashMap();
        map.put("m_id",m_id);
        int state = 0;
        if (m_state==1){
            state=0;
        }else if(m_state==0){
            state=1;
        }
        map.put("m_state",state);
        int i = foodMassageService.stateUpd(map);
        if (i == 1) {
            return "1";
        }else {
            return  "0";
        }
    }

}
