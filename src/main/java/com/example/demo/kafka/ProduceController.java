package com.example.demo.kafka;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Random;

/**
 * 配置文件添加topic和分区
 * 根据分区分配到不同的接收端
 * 如下: 取模为0,Group1Listener收到消息
 * 为1 Group2Listener收到消息
 */
@RestController
@EnableScheduling
@RequestMapping("/kafka")
@Slf4j
public class ProduceController {
    @Value("${kafka.consumer.topic}")
    private String topic;
    @Autowired
    private KafkaTemplate kafkaTemplate;
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    @ResponseBody
    public String sendKafka(HttpServletRequest request, HttpServletResponse response){
        String msg = request.getParameter("msg");

        JSONObject result = new JSONObject();
        result.put("msg", msg);
        result.put("code", 200);

        kafkaTemplate.send("test-topic",result.toJSONString());
        log.info("发送kafka成功.");

        return result.toJSONString();
    }
    //使用一个定时任务，每隔10秒发送一条消息
    @Scheduled(cron = "0 0/2 * * * ?")
    public void send(){
        JSONObject result = new JSONObject();
        result.put("msg", new Date().getTime());
        result.put("code", 200);

        log.info("定时发送kafka成功.");
//        Random ra =new Random();
//        int kk = ra.nextInt(100);
//        log.info("kk=="+kk%2);
//        kafkaTemplate.send(topic,kk%2,"key",result.toJSONString());
        //如果不选择分区和key,则轮询分配分区,没用的分区也会去发送
        //比如有5个分区,两个接收端,则就有三个消息就等于没用
        //既然zookeeper不能智能判断节点是否存在,为何还要用分区,直接创建多个主题不就行了?
        kafkaTemplate.send(topic,result.toJSONString());

    }

}
