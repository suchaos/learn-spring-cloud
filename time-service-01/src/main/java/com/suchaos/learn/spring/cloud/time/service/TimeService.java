package com.suchaos.learn.spring.cloud.time.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * TimeService
 *
 * @author suchao
 * @date 2020/9/7
 */
@SpringBootApplication
@EnableEurekaClient
public class TimeService {

    public static void main(String[] args) {
        SpringApplication.run(TimeService.class, args);
    }
}
