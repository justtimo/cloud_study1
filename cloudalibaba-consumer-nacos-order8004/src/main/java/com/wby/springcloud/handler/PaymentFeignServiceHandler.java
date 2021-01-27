package com.wby.springcloud.handler;


import com.wby.springcloud.bean.CommonResult;
import com.wby.springcloud.bean.Payment;
import com.wby.springcloud.service.PaymentFeignService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class PaymentFeignServiceHandler implements PaymentFeignService {

    /**
     * 降级方法
     * @param id
     * @return
     */
    @Override
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        return new CommonResult<>(44444,"服务降级返回,---PaymentFallbackService",new Payment(id,"无此数据"));
    }
}

