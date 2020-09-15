package com.suchaos.learn.spring.cloud.hystrix.controller;

import com.suchaos.learn.spring.cloud.hystrix.service.HelloHystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 结合 hystrix 和 feign
 *
 * @author suchao
 * @date 2020/9/14
 */
@RestController
public class HelloHystrixController {

    @Autowired
    private HelloHystrixService helloHystrixService;

    @GetMapping("/hello/hystrix")
    public String helloHysttrix() {
        return helloHystrixService.helloUser();
    }
}
