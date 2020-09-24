package com.study.cloud.control;

import com.study.cloud.entities.CommonResult;
import com.study.cloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author wangyj
 * @create 2020-09-11-9:03
 */
@RestController
@Slf4j
public class OrderControl {


    // 单机
//    public static final String PAYMENT_URL = "http://localhost:8001";
    // 集群，调用payment-service(7001 7002)，通过服务名调用
    public static final String PAYMENT_URL = "http://cloud-payment-service";
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value="/consumer/payment/create")
    public CommonResult create(Payment payment) {
        CommonResult<Payment> res = restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        return res;

    }

    @GetMapping(value="/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }
}
