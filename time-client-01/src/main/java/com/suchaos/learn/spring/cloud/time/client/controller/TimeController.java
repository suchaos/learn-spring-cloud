package com.suchaos.learn.spring.cloud.time.client.controller;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * TimeController
 *
 * @author suchao
 * @date 2020/9/7
 */
@RestController
public class TimeController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/time")
    public String callTimeService() {
        return restTemplate.getForEntity("http://time-service/hello", String.class).getBody();
    }
}
