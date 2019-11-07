package com.aaa.controller;

import com.aaa.entity.Types;
import com.aaa.service.TypesService;
import com.aaa.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("type")
public class TypesController {
    @Resource
    private TypesService typesService;

    @RequestMapping("selTypes")
    @ResponseBody
    public PageUtil<List> selTypes(@RequestParam("limit") int limit, @RequestParam("page") int page) {
        List<Types> types = typesService.selTypes(limit, page);
        int count = typesService.selOne();
        PageUtil<List> result = new PageUtil<>("", types, 0, count);
        return result;
    }

    @RequestMapping("aaa")
    public String aaa() {
        return "type/selTypes";
    }

    @RequestMapping("addTypes")
    public String addTypes() {
        return "type/addTypes";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public PageUtil<Types> add(Types types) {
        Types t = new Types();
        PageUtil result=new PageUtil();
        List<Types> type = typesService.addTypesOne(types.getT_name());
        if (type != null && type.size() > 0) {
            result.setMsg("添加的角色名已存在！");
            result.setData(false);
            return result;
        } else {
            t.setT_state(0);
            t.setT_name(types.getT_name());
            typesService.addTypes(t);
            result.setMsg("添加成功！");
            result.setData(true);
            return result;
        }
    }
    @RequestMapping("/updateTypes")
    @ResponseBody
//   public void updateTypes(int t_id, int t_state){
//        int menu= typesService.selMenus(t_id);
//        System.out.println(menu);
//        if (menu>0){
//            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//        }else {
//            Map map = new HashMap();
//            map.put("t_id",t_id);
//            map.put("t_state",t_state);
//            typesService.updateTypes(map);}
//    }
    public Integer updateTypes(Integer t_id,Integer t_state,Integer elems){
        Integer i = typesService.selMenus(t_id);
        if (i==0||elems==0){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("t_id",t_id);
            map.put("t_state",t_state);
            int num = typesService.updateTypes(map);
            return num;
        }else {
            return 2;
        }

    }
}
