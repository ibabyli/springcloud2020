package com.study.cloud.control;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * @Author wangyj
 * @Since 2020/9/17 14:54
 */
@RestController
@Slf4j
public class OrderController {


    @Autowired
    private RestTemplate restTemplate;

    private static  final String INVOKE_URL = "http://cloud-provider-payment";

    @RequestMapping(value = "/consumer/payment/zk")
    public String paymentInfo() {
        String result = restTemplate.getForObject(INVOKE_URL+"/payment/zk",String.class);
        return result;
    }
}
