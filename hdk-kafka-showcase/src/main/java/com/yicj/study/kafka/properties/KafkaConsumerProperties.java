package com.yicj.study.kafka.properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yicj
 * @Since 2024/1/22 22:04
 */
public class KafkaConsumerProperties {

    public Map<String, Object> init(String bootstrapServers){
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test1");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        // 这个配置会将订阅者的offset置为0，这样会接受队列中所有的消息
        // props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest") ;
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // ssl config
        props.put("security.protocol","SSL");
        props.put("ssl.endpoint.identification.algorithm","");
        props.put("ssl.truststore.location","client.truststore.jks");
        props.put("ssl.truststore.password","hello123");
        return props ;
    }
}
