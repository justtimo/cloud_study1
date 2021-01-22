package com.wby.springcloud;

import com.wby.myruler.MyselfRuler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
//在远程调用指定的微服务时，使用自定义的负载均衡策略
@RibbonClient(name = "CLOUD-PROVIDER-PAYMENT8001",configuration = MyselfRuler.class)
@EnableEurekaClient
public class OrderMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain9001.class,args);
    }
}

