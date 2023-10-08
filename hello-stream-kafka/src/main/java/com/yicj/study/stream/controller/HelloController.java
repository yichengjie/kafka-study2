package com.yicj.study.stream.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Supplier;

/**
 * @author yicj
 * @date 2023/10/8 21:53
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private Supplier<String> pkSlowSourceX ;

    @GetMapping("/send")
    public String send(){
        String retValue = pkSlowSourceX.get();

        return retValue ;
    }
}
