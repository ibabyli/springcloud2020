package com.study.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author wangyj
 * @create 2020-09-24-14:28
 */
@SpringBootApplication
@EnableConfigServer

public class ConfigCenterMain3344 {

    // http://localhost:3344/master/config-dev.yml
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain3344.class,args);
    }
}
