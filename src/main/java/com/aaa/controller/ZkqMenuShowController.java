package com.aaa.controller;

import com.aaa.entity.Menus;
import com.aaa.entity.Types;
import com.aaa.service.ZkqMenuShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@EnableScheduling
@RequestMapping("/zkq")
public class ZkqMenuShowController {

    @Autowired
    private ZkqMenuShowService zkqMenuShowService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

   // WebLink wsLink = new WebLink();
    //类型的查询
    @RequestMapping("/types")
    @ResponseBody
    public List<Types> types(){
        List<Types> types = zkqMenuShowService.getTypes();
        return types;
    }
    //菜单的查询
    @RequestMapping("/menus")
    @ResponseBody
    public List<Menus> menus(Integer t_id){
        List<Menus> menus = zkqMenuShowService.getMenus(t_id);
        return menus;
    }
    //添加订单
    @RequestMapping("/addOrder")
    @ResponseBody
    //@SendTo("/topic/send")
    public Integer addOrder(String orderDate, Integer u_id, Double amount, Integer deskNumber,Integer[] m_id,Integer[] count){
        Integer num = zkqMenuShowService.addOrder(orderDate,u_id,amount,deskNumber,m_id,count);
        if(m_id.length == num){
            this.send(orderDate,u_id,deskNumber);
        }
        return num;
    }
 //   @Scheduled(fixedRate = 1000)
    //自动推送浏览器
    @SendTo("/topic/send")
    @ResponseBody
    public String send(String orderDate, Integer u_id,Integer deskNumber){
        List<Map<String,Object>> socket = zkqMenuShowService.selOrders(orderDate,u_id,deskNumber);
        messagingTemplate.convertAndSend("/topic/send", socket);
        return "send";
    }
    //第一此加载访问时显示没有上菜的数据   ,method = RequestMethod.POST
    @RequestMapping(value = "/webtest")
    @ResponseBody
    public List<Map<String,Object>> webtest(){
        List<Map<String,Object>> list =  zkqMenuShowService.selInform();
        return list;
    }
    //点击以上菜改变状态
    @RequestMapping("/ona")
    @ResponseBody
    public Integer ona(Integer id){
        Integer num = zkqMenuShowService.ona(id);
        return num;
    }
    //拉起微信登录与数据库对比
    @RequestMapping("/login")
    @ResponseBody
    public Integer login(String userImg,String userName,String openid){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userImg",userImg);
        map.put("userName",userName);
        map.put("openid",openid);
        Integer num =  zkqMenuShowService.login(map);
      return num;
    }
}
