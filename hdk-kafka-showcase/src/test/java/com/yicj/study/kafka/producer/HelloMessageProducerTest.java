package com.yicj.study.kafka.producer;

import com.yicj.study.kafka.HdkKafkaApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yicj
 * @Since 2024/1/21 12:12
 */
@SpringBootTest(classes = HdkKafkaApplication.class)
public class HelloMessageProducerTest {

    @Autowired
    private HelloMessageProducer producer ;

    @Test
    public void send(){
        String key = "1" ;
        String message = "hello world 1222222222222222222222" ;
        producer.send(key, message);
    }

}
