package com.jie.sentinel.controller;

import com.alibaba.csp.sentinel.AsyncEntry;
import com.alibaba.csp.sentinel.SphU;
import com.jie.sentinel.service.AsyncService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestAsyncController {
    @Resource
    private AsyncService asyncService;

    @GetMapping("async")
    public void hello() {
        AsyncEntry asyncEntry = null;
        try {
            asyncEntry = SphU.asyncEntry("sentinel_async");
            asyncService.hello();
        }  catch (Exception e) {
            e.printStackTrace();
            System.err.println("系统繁忙请稍后");
        }finally {
            if(asyncEntry != null){
                asyncEntry.exit();
            }
        }

    }

}
