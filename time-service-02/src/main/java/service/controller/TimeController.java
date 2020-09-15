package service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * HelloController
 *
 * @author suchao
 * @date 2020/9/7
 */
@RestController
public class TimeController {

    @Value("${server.port}") // 用于演示负载均衡
    public String port;

    @GetMapping("/hello")
    public String getTimeAndPort() {
        return LocalDateTime.now() + ", and port is: " + port;
    }
}
