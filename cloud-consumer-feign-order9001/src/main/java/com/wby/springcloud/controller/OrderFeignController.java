package com.wby.springcloud.controller;

import com.netflix.discovery.converters.Auto;
import com.wby.springcloud.bean.CommonResult;
import com.wby.springcloud.bean.Payment;
import com.wby.springcloud.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderFeignController {
    @Autowired
    PaymentFeignService paymentFeignService;//使用feign接口进行远程调用

    @PostMapping(value = "/consumer/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        CommonResult<Payment> paymentCommonResult = paymentFeignService.create(payment);
        return paymentCommonResult;

    }

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        CommonResult<Payment> paymentById = paymentFeignService.getPaymentById(id);
        return paymentById;
    }

    /**
     * 测试openfeign超时
     * @return
     */
    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        String s = paymentFeignService.paymentFeignTimeout();
        return s;
    }
}
