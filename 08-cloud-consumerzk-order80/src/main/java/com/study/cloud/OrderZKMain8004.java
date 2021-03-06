package com.study.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wangyj
 * @create 2020-09-17-14:23
 */
@SpringBootApplication
@EnableDiscoveryClient //该注解用于向使用consul或者zookeeper作为注册中心时注册服务
public class OrderZKMain8004 {

    public static void main(String[] args) {
        SpringApplication.run(OrderZKMain8004.class,args);
    }
}
