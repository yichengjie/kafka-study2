package com.yicj.study.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloMessageConsumer {
    @KafkaListener(
            id = "helloMessageConsumer",
            groupId = "groupId",
            containerFactory = "containerFactory222",
            topics = "hello-topic"
    )
    public void listener(ConsumerRecord<String, Object> record) {
        String content = (String) record.value();
        try {
            log.info("containerFactory222 kafka message : {}", content) ;
            // todo 业务方法处理
        } catch (Exception e) {
            log.error("消息异常信息: ", e);
        } finally {
            //ack.acknowledge();
        }
    }



    @KafkaListener(
            id = "helloMessageConsumer2",
            groupId = "groupId-1",
            containerFactory = "containerFactory",
            topics = "hello-topic"
    )
    public void listener2(ConsumerRecord<String, Object> record) {
        String content = (String) record.value();
        try {
            log.info("helloMessageConsumer2 kafka message : {}", content) ;
            // todo 业务方法处理
        } catch (Exception e) {
            log.error("消息异常信息: ", e);
        } finally {
            //ack.acknowledge();
        }
    }
}