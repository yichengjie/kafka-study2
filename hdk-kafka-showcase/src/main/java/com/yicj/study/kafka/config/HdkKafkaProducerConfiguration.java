package com.yicj.study.kafka.config;

import com.yicj.study.kafka.utils.KafkaUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author yicj
 * @Since 2024/1/21 10:18
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "hdk.kafka.producer", value = "enable", havingValue = "true", matchIfMissing = true)
public class HdkKafkaProducerConfiguration {


    @Bean
    @ConditionalOnMissingBean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.99.51:8989");
        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        properties.put(ProducerConfig.RETRIES_CONFIG,"0");
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,"16384");
        properties.put(ProducerConfig.LINGER_MS_CONFIG,"1");
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,"33554432");
        // ssl config
        properties.put("security.protocol","SSL");
        properties.put("ssl.endpoint.identification.algorithm","");
        //properties.put("ssl.truststore.location","client.truststore.jks");
        properties.put("ssl.truststore.password","hello123");
        File file = KafkaUtils.copyClassPathTempFile(
                "client.truststore.jks", "client.truststore.jks");
        properties.put("ssl.truststore.location",file.getPath());
        //log.info("===========> path : {}", file.getPath());
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        return new DefaultKafkaProducerFactory<>(properties);
    }


    @Bean
    @ConditionalOnMissingBean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}

