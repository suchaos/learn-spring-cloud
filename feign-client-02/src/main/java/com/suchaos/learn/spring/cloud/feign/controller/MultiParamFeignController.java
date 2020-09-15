package com.suchaos.learn.spring.cloud.feign.controller;

import com.suchaos.learn.spring.cloud.feign.domain.User;
import com.suchaos.learn.spring.cloud.feign.service.MultiParamFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Feign 的 post 和 get 的多参数传递
 *
 * @author suchao
 * @date 2020/9/10
 */
@RestController
public class MultiParamFeignController {

    @Autowired
    private MultiParamFeignService multiParamFeignService;

    @GetMapping("/add")
    public String addUser(User user) {
        System.out.println(user);
        return multiParamFeignService.addUser(user);
    }

    @GetMapping("/add3")
    public String addUser3(User user) {
        System.out.println(user);
        return multiParamFeignService.addUserByQueryMap(user);
    }

    @GetMapping("/add2")
    public String addUser2(User user) {
        System.out.println(user);
        return multiParamFeignService.addUser1(user.getName(), user.getAge());
    }

    @PostMapping("/update")
    public String updateUser(@RequestBody User user) {
        System.out.println(user);
        return multiParamFeignService.updateUser(user);
    }
}
