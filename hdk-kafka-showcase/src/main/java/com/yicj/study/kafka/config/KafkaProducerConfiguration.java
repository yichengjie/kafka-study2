//package com.yicj.study.kafka.config;
//
//import com.yicj.study.kafka.utils.KafkaUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//
//import java.io.File;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author yicj
// * @Since 2024/1/21 10:18
// */
//@Slf4j
//@Configuration
//public class HdkKafkaProducerConfiguration {
//
//
//    @Bean
//    public ProducerFactory<String, String> producerFactory() {
//        String bootstrapServer = "192.168.99.51:8989" ;
//        Map<String, Object> properties = new KafkaProperties().init(bootstrapServer);
//        return new DefaultKafkaProducerFactory<>(properties);
//    }
//
//
//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
//}
//
