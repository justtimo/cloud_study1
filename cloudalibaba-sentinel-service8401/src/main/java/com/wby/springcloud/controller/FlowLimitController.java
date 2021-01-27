package com.wby.springcloud.controller;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @GetMapping("/testE")
    public String testE() {
        log.info("testE 测试异常比例");
        int age = 10/0;
        return "------testD";
    }

    /**
     * 降级test：异常数
     * @return
     */
    @GetMapping("/testE1")
    public String testE1(){
        log.info("testE1 测试异常数");
        int age = 10/0;
        return "------testE1 测试异常数";
    }


    /**
     * 热点key限流：一般针对特殊数据访问进行流控
     * @SentinelResource等价于Hystrix中的@HystirxCommand注解的作用
     * 在sentinel控制台中配置规则，资源名等于value的值
     * @param p1
     * @param p2
     * @return
     */
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2) {
        //int age = 10/0;
        return "------testHotKey";
    }

    //兜底方法:必须是public，返回类型必须一致，参数必须一致，可以再原有参数后面增加BlockException类型的异常
    public String deal_testHotKey (String p1, String p2, BlockException exception){
        return "------deal_testHotKey,o(╥﹏╥)o";
    }




}

