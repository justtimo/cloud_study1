package com.wby.springcloud.controller;

import com.wby.springcloud.bean.CommonResult;
import com.wby.springcloud.bean.Payment;
import com.wby.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


@RestController//服务提供者不跳转页面的，一般直接将数据以json返回
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    Integer port;

    @Resource
    private PaymentService paymentService;

    /**
     * @RequestBody:获取请求提数据，酱json封装为对象
     * @param payment
     * @return
     */
    @PostMapping(value = "/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){ //埋雷
        try {
            int result = paymentService.create(payment);
            log.debug("*****插入结果："+result);
            if (result>0){  //成功
                log.debug("*****插入数据库成功："+result);
                return new CommonResult(200,"插入数据库成功",result);

            }else {
                log.debug("*****插入数据库失败："+result);
                return new CommonResult(444,"插入数据库失败",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("*****插入数据发生异常："+e.getMessage());
            return new CommonResult(555,"插入数据发生异常",e.getMessage());
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.debug("*****查询结果："+payment);
        if (payment!=null){  //说明有数据，能查询成功
            log.debug("*****查询成功："+payment);
            return new CommonResult(200,"查询成功"+port,payment);
        }else {
            log.debug("*****没有对应记录："+payment);
            return new CommonResult(444,"没有对应记录，查询ID："+id,null);
        }
    }


    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e) {
            e.printStackTrace();
        } //单位秒
        return port+"";
    }

}

