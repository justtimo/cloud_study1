package com.wby.springcloud.dao;


import com.wby.springcloud.bean.CommonResult;
import com.wby.springcloud.bean.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component       //代替@Repository声明bean
//@Mapper               //mybatis提供的，等价：@MapperScan("com.atguigu.springcloud.dao")
@Repository     //spring提供的。在此，只是为了声明bean对象
public interface PaymentDao {
    /**
     * CommonResult<Payment>：返回对象。其中的Payment是有住建的
     * @param payment
     * @return
     */
    public int create(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);

}

