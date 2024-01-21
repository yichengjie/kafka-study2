package com.yicj.study.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class HelloMessageProducer {
    @Value("${kafka.topic.name}")
    private String topicName ;
    
    @Autowired
    private KafkaTemplate<String, String> hdkKafkaTemplate ;

    public void send(String key, String jsonContent){
        hdkKafkaTemplate.send(topicName, key, jsonContent) ;
    }
}