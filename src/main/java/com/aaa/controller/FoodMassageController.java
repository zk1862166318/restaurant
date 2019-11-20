package com.aaa.controller;

import com.aaa.entity.MenusDB;
import com.aaa.entity.MenusVO;
import com.aaa.entity.Menus;
import com.aaa.entity.Type;
import com.aaa.service.FoodMassageService;
import com.alibaba.druid.sql.PagerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
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
        File file1 = new File("c:\\img");
        if (!file1.exists()){
            file1.mkdirs();
        }
        String filename= file.getOriginalFilename();
        String savepath = file1+"/"+filename;
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

    @RequestMapping("/selName")
    @ResponseBody
    public String selName(String m_name){
        int i = foodMassageService.selName(m_name);
        if (i!=0){
            return "1";
        }else{
            return "0";
        }

    }

    @RequestMapping("/foodAdd")
    @ResponseBody
    public String foodAdd(String m_name,int m_price,String m_img,int m_state,int t_id) {
        Menus Menus = new Menus();
        System.out.println(m_img);
        String filename = "img/"+m_img;
        Menus.setM_img(filename);
        Menus.setM_price(m_price);
        Menus.setM_name(m_name);
        Menus.setM_state(m_state);
        Menus.setT_id(t_id);
        int foodAdd = foodMassageService.foodAdd(Menus);
        if (foodAdd == 1) {
            return "1";
        }else {
            return  "0";
        }
    }

    @RequestMapping("/foodSel")
    @ResponseBody
    public MenusVO foodSel(int limit, Integer page,Integer m_state,String m_name,Integer t_id) {
        Map map = new HashMap();
        map.put("limit",limit);
        map.put("page",page);
        map.put("m_state",m_state);
        int start = (page-1)*limit;
        map.put("start",start);
        map.put("m_name",m_name);
        map.put("t_id",t_id);
        int count = foodMassageService.countAll(map);
        List<MenusDB> order = foodMassageService.foodSel(map);
        MenusVO menusVO = new MenusVO();
        menusVO.setCode(0);
        menusVO.setCount(count);
        menusVO.setData(order);
        return menusVO;
    }

    @RequestMapping("/toUpd")
    public String toUpd(Model model){
        List<Type> types = selType();
        model.addAttribute("type", types);
        return "foodSelUp";
    }

    @RequestMapping("/foodUpd")
    @ResponseBody
    public Integer foodUpd(String nm_name,int nm_price,String nm_img,int nm_id,int nt_id){
        Map map = new HashMap();
        map.put("m_name",nm_name);
        map.put("m_price",nm_price);
        map.put("m_img",nm_img);
        map.put("m_id",nm_id);
        map.put("t_id",nt_id);
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
