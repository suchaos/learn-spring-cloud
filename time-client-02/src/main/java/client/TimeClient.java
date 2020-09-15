package client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * TimeClient
 *
 * @author suchao
 * @date 2020/9/7
 */
@SpringBootApplication
@EnableEurekaClient
public class TimeClient {

    public static void main(String[] args) {
        SpringApplication.run(TimeClient.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
