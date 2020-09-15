package com.suchaos.learn.spring.cloud.feign.controller;

import com.suchaos.learn.spring.cloud.feign.service.GithubAPIFeignService;
import com.suchaos.learn.spring.cloud.feign.service.WeatherAPIFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloFeignController
 *
 * @author suchao
 * @date 2020/9/10
 */
@RestController
public class HelloFeignController {

    @Autowired
    private GithubAPIFeignService githubAPIFeignService;

    @Autowired
    private WeatherAPIFeignService weatherAPIFeignService;

    @GetMapping("/search/github")
    public String searchGithubRepoByStr(@RequestParam("str") String queryStr) {
        return githubAPIFeignService.searchRepo(queryStr);
    }

    @GetMapping("/search/weather/{city}")
    public String searchWeatherByCity(@PathVariable("city") String city) {
        return weatherAPIFeignService.searchWeatherForCity(city);
    }
}
