package com.suchaos.learn.spring.cloud.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * HystrixDashboard 启动类
 *
 * @author suchao
 * @date 2020/9/15
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardBootstrap.class, args);
    }
}
