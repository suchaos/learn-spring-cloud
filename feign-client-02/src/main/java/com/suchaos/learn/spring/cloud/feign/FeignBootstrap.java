package com.suchaos.learn.spring.cloud.feign;

import feign.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;
import org.springframework.context.ApplicationContext;

/**
 * 启动类
 *
 * @author suchao
 * @date 2020/9/10
 */
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class FeignBootstrap implements CommandLineRunner {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Client client;

    public static void main(String[] args) {
        SpringApplication.run(FeignBootstrap.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("client: " + client);
        System.out.println(applicationContext.getBean("feignClient", Client.class) == client);

        if (client instanceof LoadBalancerFeignClient) {
            LoadBalancerFeignClient loadBalancerFeignClient = (LoadBalancerFeignClient) client;
            System.out.println("loadBalancerFeignClient.getDelegate(): " + loadBalancerFeignClient.getDelegate());
        }
    }
}
