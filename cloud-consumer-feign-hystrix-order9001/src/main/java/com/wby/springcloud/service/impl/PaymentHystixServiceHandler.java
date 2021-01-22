package com.wby.springcloud.service.impl;

import com.wby.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

@Component//自定义降级处理类，如果使用openFeign进行远程调用，需要实现Feign接口
public class PaymentHystixServiceHandler implements PaymentHystrixService {
    //为每一个远程接口方法，都定义一个降级方法

    @Override
    public String paymentInfo_OK(Integer id) {
        return "调用远程【CLOUD-PROVIDER-HYSTRIX-PAYMENT-SERVICE】paymentInfo_OK出现异常";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "调用远程【CLOUD-PROVIDER-HYSTRIX-PAYMENT-SERVICE】paymentInfo_TimeOut出现异常";
    }
}
