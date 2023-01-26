package com.pavel.shopweb.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @KafkaListener(topics = "shop1", groupId = "shop-group")
    public void consumeMessage(String message){

        System.out.println(message);
    }
}
