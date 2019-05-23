package com.liang.funny.controller;

import com.liang.funny.util.kafka.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/system")
public class KafkaController {
    private Logger logger = LoggerFactory.getLogger(KafkaController.class);

    @Autowired
    private Producer producer;

    @RequestMapping(value = "/kafka/send", method = RequestMethod.GET)
    public void WarnInfo() throws Exception {
        int count = 10;
        for (int i = 0; i < count; i++) {
            producer.sendMessage("生产者发送消息" + i);
        }
    }

}
