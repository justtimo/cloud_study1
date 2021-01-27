package com.wby.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.wby.springcloud.bean.CommonResult;
import com.wby.springcloud.bean.Payment;
import com.wby.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;

@RestController
@Slf4j
public class CircleBreakerController {

    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback")//没有配置，有异常抛给浏览器，这样不友好
    //@SentinelResource(value = "fallback",fallback = "handlerFallback") //代码中有异常则去降级方法。fallback只负责业务异常
    //@SentinelResource(value="fallback",blockHandler="blockHandler")//既违规又异常：blockHandler只负责sentinel控制台配置违规
    //@SentinelResource(value="fallback",blockHandler="blockHandler",fallback = "handlerFallback")//blockHandler优先于fallback
    @SentinelResource(value="fallback",
            blockHandler="blockHandler",
            fallback = "handlerFallback",
        exceptionsToIgnore = IllegalArgumentException.class//指定出现某种异常，不进行降级处理
    )
    public CommonResult<Payment> fallback(@PathVariable("id") Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/{id}", CommonResult.class,id);
        if (id == 4) {
            throw new IllegalArgumentException ("IllegalArgumentException,非法参数异常....");
        }else if (result.getData() == null) {
            throw new NullPointerException ("NullPointerException,该ID没有对应记录,空指针异常");
        }
        return result;
    }

    //fallback
    public CommonResult<Payment> handlerFallback(@PathVariable("id")  Long id,Throwable e) {
        Payment payment = new Payment(id,"null");
        return new CommonResult<Payment>(444,"兜底异常handlerFallback,exception内容  "+e.getMessage(),payment);
    }
    //blockHandler
    public CommonResult<Payment> blockHandler(@PathVariable("id")  Long id, BlockException blockException) {
        Payment payment = new Payment(id,"null");
        return new CommonResult<Payment>(445,"blockHandler-sentinel限流,无此流水: blockException  "+blockException.getMessage(),payment);
    }


    //============演示Feign调用=======================
    // OpenFeign
    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        return paymentFeignService.paymentSQL(id);
    }


}

