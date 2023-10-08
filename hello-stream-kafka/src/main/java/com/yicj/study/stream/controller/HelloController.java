package com.yicj.study.stream.controller;

import com.yicj.study.stream.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author yicj
 * @date 2023/10/8 21:53
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private SendService sendService ;

    @GetMapping("/send")
    public String send(){
        sendService.sendMsg2Sup("hello world");
        return "success" ;
    }
}
