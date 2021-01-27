package com.wby.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.wby.springcloud.bean.CommonResult;
import com.wby.springcloud.bean.Payment;
import com.wby.springcloud.controller.myHandler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController{

    /**
     * 1.通过value资源名称进行限流，就会执行blockHandler指定的降级方法
     * 2.通过uri路径进行限流，不执行blockHandler指定的降级方法
     * @return
     */
    @GetMapping("/byResource")
    //@SentinelResource(value = "byResource111",blockHandler = "handleException")
    @SentinelResource(value = "byResource111",
            blockHandler = "handleException",
            blockHandlerClass = CustomerBlockHandler.class )
    public CommonResult byResource(){
        return new CommonResult(200,"按资源名称限流测试OK",new Payment(2020L,"serial001"));
    }
    /*public CommonResult handleException(BlockException exception){
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t 服务不可用");
    }*/
}
