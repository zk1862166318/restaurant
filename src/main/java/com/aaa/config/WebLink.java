package com.aaa.config;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

@Controller
@EnableScheduling
public class WebLink{

 //   @Autowired
//    private SimpMessagingTemplate messagingTemplate;
//    @Autowired
//    private ZkqMenuShowService zkqMenuShowService;
//    @MessageMapping("/send")
//    @SendTo(value = "/topic/send")
//    public SocketMessage send() throws Exception {
//        SocketMessage message = new SocketMessage();
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        message.date = df.format(new Date());
//        message.name="张凯强";
//        System.out.println("ok---send");
//        return message;
//    }

//    @Scheduled(fixedRate = 1000)
//    @SendTo("/topic/callback")
//    public Object callback() throws Exception {
//        // 发现消息
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        messagingTemplate.convertAndSend("/topic/callback", df.format(new Date()));
//        return "callback";
//    }

//    @SendTo(value="/topic/indent")
//    public Map<String,Object> notify(String orderDate, Integer u_id,Integer deskNumber){
//        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("id",1);
//        map.put("name","name1");
//        list.add(map);
//        map.put("id",2);
//        map.put("name","name2");
//        list.add(map);
//        System.out.println("ok");
//        return map;
//    }


//    public void aaa(){
//        try {
//            ((WebLink) AopContext.currentProxy()).send();
////            context.getBean(WebLink.class);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
