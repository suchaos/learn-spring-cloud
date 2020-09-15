package com.suchaos.learn.spring.cloud.feign.service;

import com.suchaos.learn.spring.cloud.feign.config.HelloFeignServiceConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 使用 {@link FeignClient}
 * <p>
 * 具体 API 看：https://www.apishop.net/#/api/detail/?productID=76
 * <p>
 * https://www.tianqiapi.com/api/?appid=23035354&appsecret=8YvlPNrz&version=v9&cityid=0&city=%E5%A4%AA%E5%8E%9F&ip=0&callback=0
 *
 * @author suchao
 * @date 2020/9/10
 */
@FeignClient(name = "weather-client", url = "https://www.tianqiapi.com")
public interface WeatherAPIFeignService {

    @GetMapping(value = "/api?appid=23035354&appsecret=8YvlPNrz&version=v9&cityid=0&city={city}&ip=0&callback=0")
    String searchWeatherForCity(@PathVariable("city") String city);
}
