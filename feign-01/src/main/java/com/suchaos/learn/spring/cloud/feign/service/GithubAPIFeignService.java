package com.suchaos.learn.spring.cloud.feign.service;

import com.suchaos.learn.spring.cloud.feign.config.HelloFeignServiceConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 使用 {@link FeignClient}
 *
 * @author suchao
 * @date 2020/9/10
 */
@FeignClient(name = "github-client", url = "https://api.github.com",
        configuration = HelloFeignServiceConfig.class)
public interface GithubAPIFeignService {

    @GetMapping(value = "/search/repositories")
    String searchRepo(@RequestParam("q") String queryStr);
}
