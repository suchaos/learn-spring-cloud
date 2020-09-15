package com.suchaos.learn.spring.cloud.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 启动类
 *
 * @author suchao
 * @date 2020/9/14
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableFeignClients
public class HystrixAndFeignBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(HystrixAndFeignBootstrap.class, args);
    }
}
