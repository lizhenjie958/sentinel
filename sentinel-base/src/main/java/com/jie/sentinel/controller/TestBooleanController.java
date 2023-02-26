package com.jie.sentinel.controller;

import com.alibaba.csp.sentinel.SphO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestBooleanController {
    @GetMapping("boolean")
    public boolean hello() {
        //使用限流规则
        if(SphO.entry("sentinel_boolean")){
            try {
                System.out.println("Hello Sentinel!");
                return true;
            } finally {
                SphO.exit();
            }
        }else {
            System.err.println("系统繁忙，请稍候");
            return false;
        }
    }

}
