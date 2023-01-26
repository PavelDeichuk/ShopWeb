package com.pavel.shopweb.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class Producer {
    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    public boolean SendMessage(String topic, Object message){
        CompletableFuture<SendResult<String, Object>> send = kafkaTemplate.send(topic, message);
        return send.isDone();
    }

    public NewTopic CreateTopic(String name, int partion, short replicationFactor){
        return new NewTopic(name, partion, replicationFactor);
    }
}
