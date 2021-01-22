package com.wby.springcloud.service;


import com.wby.springcloud.bean.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    public int create(Payment payment); //写
    public Payment getPaymentById(Long id);  //读取
}

