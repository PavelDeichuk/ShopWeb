package com.pavel.shopweb.Container;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@SpringBootTest
class KafkaContainerTest {

    @Container
    static KafkaContainer kafkaContainer =
            new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka"));

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @BeforeAll
    public static void beforeAll(){
        kafkaContainer.start();
        String addressKafka = kafkaContainer.getBootstrapServers();
        System.setProperty("kafka.bootstrapAddress", addressKafka);
    }

    @Test
    void SendMessageTopic(){
        kafkaTemplate.send("shop1", "Test1");
        kafkaTemplate.send("shop1", "Test2");
        kafkaTemplate.send("shop1", "Test3");
        for (int i = 0; i < 1000; i++){
            kafkaTemplate.send("shop1", "Testing for" + " " + i);
        }
    }
}
