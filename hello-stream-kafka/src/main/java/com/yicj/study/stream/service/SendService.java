package com.yicj.study.stream.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * @author yicj
 * @date 2023/10/8 22:34
 */
@RequiredArgsConstructor
@Service
public class SendService {

    private final StreamBridge streamBridgeTemplate;

    public void sendMsg2Sup(String message){
        streamBridgeTemplate.send(
                "pkSlowSourceX-out-0",
                MessageBuilder
                        .withPayload(message)
                        .build());
    }
}
