package com.wby.springcloud.service;

import com.wby.springcloud.bean.CommonResult;
import com.wby.springcloud.bean.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "CLOUD-PROVIDER-PAYMENT8001")//调用远程的哪个微服务
public interface PaymentFeignService {
    /**
     * 微服务调用接口+@FeignClient。
     * 这里的接口不是狭义的接口，controller的方法也是接口，并不非得是interface声明的类
     *原封不动的把生产者controller的方法拿过来就可以
     */
    @PostMapping(value = "/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment);

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout();
}
