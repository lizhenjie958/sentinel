package com.jie.sentinel.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class AsyncService {

    @Async
    public void hello() throws InterruptedException {
        System.out.println("异步开始");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("异步结束");
    }
}
