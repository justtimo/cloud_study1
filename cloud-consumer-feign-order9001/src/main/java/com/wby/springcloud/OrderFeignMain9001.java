package com.wby.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients //启用feign远程调用
public class OrderFeignMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain9001.class,args);
    }
}

