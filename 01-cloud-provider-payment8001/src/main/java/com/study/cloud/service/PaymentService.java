package com.study.cloud.service;

import com.study.cloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author wangyj
 * @create 2020-09-10-20:31
 */
public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
