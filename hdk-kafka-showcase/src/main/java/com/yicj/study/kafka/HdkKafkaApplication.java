package com.yicj.study.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yicj
 * @Since 2024/1/21 9:59
 */
@SpringBootApplication//(exclude = KafkaAutoConfiguration.class)
public class HdkKafkaApplication {

    public static void main(String[] args) {

        SpringApplication.run(HdkKafkaApplication.class, args) ;
    }
}
