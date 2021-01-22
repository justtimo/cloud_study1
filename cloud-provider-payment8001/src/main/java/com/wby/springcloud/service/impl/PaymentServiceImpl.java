package com.wby.springcloud.service.impl;


import com.wby.springcloud.bean.Payment;
import com.wby.springcloud.dao.PaymentDao;
import com.wby.springcloud.service.PaymentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    @Transactional(readOnly = true,timeout = 1000)
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }


}

