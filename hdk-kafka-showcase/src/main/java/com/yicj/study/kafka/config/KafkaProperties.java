package com.yicj.study.kafka.config;

import com.yicj.study.kafka.utils.KafkaUtils;
import org.apache.kafka.clients.producer.ProducerConfig;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yicj
 * @Since 2024/1/21 16:07
 */
public class KafkaProperties {

    public Map<String, Object> init(String bootstrapServers){
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
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
        return properties ;
    }
}
