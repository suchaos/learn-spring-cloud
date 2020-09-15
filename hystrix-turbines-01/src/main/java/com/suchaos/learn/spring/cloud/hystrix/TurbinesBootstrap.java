package com.suchaos.learn.spring.cloud.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * Turbines 启动类
 *
 * @author suchao
 * @date 2020/9/15
 */
@SpringBootApplication
@EnableTurbine
@EnableEurekaClient
@EnableHystrixDashboard
public class TurbinesBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(TurbinesBootstrap.class, args);
    }
}
