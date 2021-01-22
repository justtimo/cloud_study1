package com.wby.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableEurekaClient//声明是一个eureka客户端
@MapperScan("com.wby.springcloud.dao")
public class ProviderPaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderPaymentApplication.class,args);
    }

}
