package com.wby.springcloud.config;

import org.springframework.boot.SpringBootConfiguration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.web.client.RestTemplate;

//@Configuration
@SpringBootConfiguration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced //RestTemplate继承ribben组件。进行负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}

