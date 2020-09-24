package com.study.cloud.control;

import com.study.cloud.entities.CommonResult;
import com.study.cloud.entities.Payment;
import com.study.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wangyj
 * @create 2020-09-10-20:34
 */
@RestController
@Slf4j
public class PaymentControl {

    @Autowired
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value="/payment/create")
    public CommonResult create(@RequestBody Payment payment) {

        int result = paymentService.create(payment);
        log.info("****插入结果:" + result);

        if(result > 0){
            return  new CommonResult(200,"插入数据库成功: port:" + serverPort ,result);
        } else {
            return new CommonResult(444,"插入数据库失败：port:" + serverPort,null);
        }

    }

    @GetMapping(value="/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("****查询结果:" + payment);

        if(payment != null){
            return  new CommonResult(200,"查询成功port:" + serverPort ,payment);
        } else {
            return new CommonResult(444,"没有对应记录，查询ID: " + id + "port:" + serverPort,null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();

        for (String service : services) {
            log.info("*****element:" + service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t" + instance.getHost() +
                "\t" + instance.getPort() +"\t" + instance.getUri());
        }

        return  this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            return serverPort;
        }
    }

    @GetMapping(value="/payment/zipkin")
    public String paymentZipkin() {
        return "hello,i am paymentZipkin server fallback,O(∩_∩)O哈哈~";
    }
}
