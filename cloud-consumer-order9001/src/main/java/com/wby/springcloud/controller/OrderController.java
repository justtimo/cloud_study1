package com.wby.springcloud.controller;

import com.wby.springcloud.bean.CommonResult;
import com.wby.springcloud.bean.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    //public static final String PAYMENT_URL = "http://localhost:8001";

    //通过注册中心获取服务名称对应的服务实例。远程调用需要使用负载均衡
    public static final String PAYMENT_URL = "http://CLOUD-PROVIDER-PAYMENT8001";

    @Autowired
    private RestTemplate restTemplate;

    /**
     * restTemplate是 spring封装的某班类，用于远程调用
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    @PostMapping("/consumer/payment/create")
    public CommonResult<Payment>   create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment, CommonResult.class);  //写操作
    }


}

