package com.study.cloud.control;

import com.study.cloud.entities.CommonResult;
import com.study.cloud.entities.Payment;
import com.study.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangyj
 * @create 2020-09-10-20:34
 */
@RestController
@Slf4j
public class PaymentControl {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @PostMapping(value="/payment/create")
    public CommonResult create(@RequestBody Payment payment) {

        int result = paymentService.create(payment);
        log.info("****插入结果:" + result);

        if(result > 0){
            return  new CommonResult(200,"插入数据库成功: port:" + port ,result);
        } else {
            return new CommonResult(444,"插入数据库失败：port:" + port,null);
        }

    }

    @GetMapping(value="/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("****查询结果:" + payment);

        if(payment != null){
            return  new CommonResult(200,"查询成功port:" + port ,payment);
        } else {
            return new CommonResult(444,"没有对应记录，查询ID: " + id + "port:" + port,null);
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return port;
    }
}
