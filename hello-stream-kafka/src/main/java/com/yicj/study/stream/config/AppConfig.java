package com.yicj.study.stream.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author yicj
 * @date 2023/10/8 21:14
 */
@Slf4j
@Configuration
public class AppConfig {

    @Bean
    public Supplier<String> pkSlowSourceX() {
        return () -> {
            String message = "www.pkslow.com";
            log.info("Sending value: " + message);
            return message;
        };
    }

    @Bean
    public Consumer<String> pkSlowSinkX() {
        return message -> {
            log.info("Received message " + message);
        };
    }
}
