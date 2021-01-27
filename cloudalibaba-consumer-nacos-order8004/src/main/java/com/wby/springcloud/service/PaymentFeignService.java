package com.wby.springcloud.service;


import com.wby.springcloud.bean.CommonResult;
import com.wby.springcloud.bean.Payment;
import com.wby.springcloud.handler.PaymentFeignServiceHandler;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "nacos-payment-provider",fallback = PaymentFeignServiceHandler.class)
public interface PaymentFeignService{
    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);
}

