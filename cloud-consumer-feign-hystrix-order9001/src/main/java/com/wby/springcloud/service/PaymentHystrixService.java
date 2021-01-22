package com.wby.springcloud.service;

import com.wby.springcloud.service.impl.PaymentHystixServiceHandler;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//如果使用Feign组件远程调用，建议使用处理类进行降级处理
//如果使用RestTemplate远程调用，则使用全局默认降级方法处理
/**
 * Feign接口：远程接口
 * 调用远程出问题，需要降级处理时，通过制定fallback来进行处理
 *
 */
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT-SERVICE",fallback= PaymentHystixServiceHandler.class)
public interface PaymentHystrixService {
    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id);
}

