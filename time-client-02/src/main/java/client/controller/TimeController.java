package client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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

    @Autowired
    private DiscoveryClient discoveryClient;


    @GetMapping("/time")
    public String callTimeService() {
        return restTemplate.getForEntity("http://time-service/hello", String.class).getBody();
    }

    @GetMapping("/discovery")
    public List<ServiceInstance> discoveryClient() {
        // TODO 可以对 RestTemplate 不使用 @LoadBalanced，然后在这里自己实现 LB
        return discoveryClient.getInstances("time-service");
    }
}
