package com.suchaos.learn.spring.cloud.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign 全局配置
 *
 * @author suchao
 * @date 2020/9/10
 */
@Configuration
public class FeignServiceConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
