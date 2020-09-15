package com.suchaos.learn.spring.cloud.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 启动类
 *
 * @author suchao
 * @date 2020/9/10
 */
@SpringBootApplication
@EnableFeignClients
public class FeignBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(FeignBootstrap.class, args);
    }
}
