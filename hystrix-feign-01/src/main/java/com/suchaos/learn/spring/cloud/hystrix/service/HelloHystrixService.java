package com.suchaos.learn.spring.cloud.hystrix.service;

import com.suchaos.learn.spring.cloud.hystrix.service.fallback.HelloHystrixFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 结合 hystrix 和 feign
 *
 * @author suchao
 * @date 2020/9/14
 */
@FeignClient(name = "feign-service-02", fallback = HelloHystrixFallback.class)
public interface HelloHystrixService {

    @GetMapping("/user/get")
    String helloUser();
}


