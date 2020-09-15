package com.suchaos.learn.spring.cloud.hystrix.service.fallback;

import com.suchaos.learn.spring.cloud.hystrix.service.HelloHystrixService;
import org.springframework.stereotype.Component;

/**
 * HelloHystrixFallback
 *
 * @author suchao
 * @date 2020/9/14
 */
@Component
public class HelloHystrixFallback implements HelloHystrixService {
    @Override
    public String helloUser() {
        return "Hystrix Fallback";
    }
}
