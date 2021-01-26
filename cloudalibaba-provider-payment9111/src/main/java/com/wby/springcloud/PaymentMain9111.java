package com.wby.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableEurekaClient   专门用于Eureka组件
@EnableDiscoveryClient  //可以用于多种注册中心：Eureka,Consul,Zookeeper,Nacos
@SpringBootApplication
public class PaymentMain9111 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain9111.class,args);
    }

}
