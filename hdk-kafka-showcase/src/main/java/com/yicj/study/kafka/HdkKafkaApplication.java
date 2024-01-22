package com.yicj.study.kafka;

import com.yicj.study.kafka.config.KafkaConsumerRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author yicj
 * @Since 2024/1/21 9:59
 */
@Import(KafkaConsumerRegistrar.class)
@SpringBootApplication//(exclude = KafkaAutoConfiguration.class)
public class HdkKafkaApplication {

    public static void main(String[] args) {

        SpringApplication.run(HdkKafkaApplication.class, args) ;
    }
}
