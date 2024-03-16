package com.yicj.study.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author yicj
 * @Since 2024/3/16 15:35
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class HelloConsumer {
    @Bean
    public Consumer<Message<List<String>>> helloFunc() {
        return message -> {
            log.info("---------------------> ");
            List<String> list = message.getPayload();
            boolean result = this.handle(list);
            if (result) {
                Acknowledgment acknowledgment = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
                if (acknowledgment != null) {
                    acknowledgment.acknowledge();
                }
            } else {
                throw new RuntimeException("消费数据出错!");
            }
        };
    }

    private boolean handle(List<String> list){
        log.info("list size : {}", list.size());
        if (!CollectionUtils.isEmpty(list)){
            log.info("group first message : {}", list.get(0));
        }
        return true ;
    }
}
