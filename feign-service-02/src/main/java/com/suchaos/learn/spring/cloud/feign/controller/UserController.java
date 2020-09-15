package com.suchaos.learn.spring.cloud.feign.controller;

import com.suchaos.learn.spring.cloud.feign.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * UserController
 *
 * @author suchao
 * @date 2020/9/10
 */
@RestController
public class UserController {

    /*
        这里只是为了测试 Feign Get 传参
     */
    @GetMapping("/user/add")
    public String addUser(User user, @RequestHeader("oauthToken") String oauthToken, @RequestHeader("oauthtoken") String token) {
        System.out.println("oauthToken: " + token);
        System.out.println("oauthtoken: " + token);
        System.out.println(user);
        return user.getName();
    }

    @PostMapping("/user/update")
    public String updateUser(@RequestBody User user) {
        System.out.println(user);
        return user.getName();
    }

    private AtomicInteger number = new AtomicInteger(0);

    @GetMapping("/user/get")
    public String getUserName() {
        System.out.println(number.get());
        if (number.getAndIncrement() % 4 != 0) {
            sleep(3, TimeUnit.SECONDS);
        }
        return "hello, user" + number.get();
    }

    private static void sleep(long num, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
