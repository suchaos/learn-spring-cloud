package com.suchaos.learn.spring.cloud.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;

/**
 * 启动类
 *
 * @author suchao
 * @date 2020/9/10
 */
@SpringBootApplication
@EnableEurekaClient
public class FeignBootstrap {

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(FeignBootstrap.class, args);
    }

}
