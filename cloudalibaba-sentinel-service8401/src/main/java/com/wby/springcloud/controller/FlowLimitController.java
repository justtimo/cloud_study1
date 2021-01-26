package com.wby.springcloud.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController{
    @GetMapping("/testA")
    public String testA() {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "------testB";
    }


    /**
     * 降级test：RT
     * @return
     */
    @GetMapping("/testD")
    public String testD(){
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        log.info("testD 测试RT");
        return "------testD";
    }

    /**
     * 降级test：异常比列
     * @return
     */
    @GetMapping("/testD")
    public String testE() {
        log.info("testE 测试异常比例");
        int age = 10/0;
        return "------testD";
    }

    /**
     * 降级test：异常数
     * @return
     */
    @GetMapping("/testE")
    public String testE1(){
        log.info("testE1 测试异常数");
        int age = 10/0;
        return "------testE1 测试异常数";
    }



}

