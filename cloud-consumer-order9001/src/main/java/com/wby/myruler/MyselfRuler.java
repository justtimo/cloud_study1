package com.wby.myruler;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//自定义规则配置类。不能放在@ComponentScan扫描的包中
@Configuration
public class MyselfRuler {

    @Bean
    public IRule rule(){
        return new RandomRule();//修改ribben默认的轮询策略
    }
}
