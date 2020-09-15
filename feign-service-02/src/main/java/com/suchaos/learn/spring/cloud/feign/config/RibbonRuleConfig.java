package com.suchaos.learn.spring.cloud.feign.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO description class
 *
 * @author suchao
 * @date 2020/9/11
 */
@Configuration
public class RibbonRuleConfig {

    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }
}
