package com.yicj.study.kafka.producer;

import com.yicj.study.kafka.config.KafkaProperties;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author yicj
 * @Since 2024/1/21 12:12
 */
@Slf4j
public class HelloMessageProducer2Test {


    @Test
    public void send() throws InterruptedException, ExecutionException {
        String topicName = "hello-topic" ;
        //
        String bootstrapServer = "192.168.99.51:8989" ;
        Map<String, Object> properties = new KafkaProperties().init(bootstrapServer);
        DefaultKafkaProducerFactory<Object, Object> producerFactory = new DefaultKafkaProducerFactory<>(properties);
        //
        KafkaTemplate<Object, Object> kafkaTemplate = new KafkaTemplate<>(producerFactory);
        //
        ListenableFuture<SendResult<Object, Object>> future = kafkaTemplate.send(topicName, "hello world");
        future.get() ;
    }

}
