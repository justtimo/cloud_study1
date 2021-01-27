package com.wby.springcloud.controller.myHandler;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.wby.springcloud.bean.CommonResult;
import com.wby.springcloud.bean.Payment;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 自定义降级处理类
 */
public class CustomerBlockHandler {
    /**
     * 可以定义多种不同的降级方法
     * 必须是public static；返回值类型与业务方法相同；参数必须与业务方法一致，
     *      必须增加一个BlockException参数
     * @param exception
     * @return
     */
    public static CommonResult handleException(BlockException exception){
        return new CommonResult(2020,"自定义限流处理信息.... CustomerBlockHandler --- 111");
    }
    /*@GetMapping("/byResource")
    @SentinelResource(value = "byResource111",blockHandler = "handleException")
    public CommonResult byResource(){
        return new CommonResult(200,"按资源名称限流测试OK",new Payment(2020L,"serial001"));
    }
    public CommonResult handleException(BlockException exception){
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t 服务不可用");
    }
    */

    public static CommonResult handleException2(BlockException exception){
        return new CommonResult(2020,"自定义限流处理信息.... CustomerBlockHandler --- 222");
    }

}
