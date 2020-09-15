package com.suchaos.learn.spring.cloud.feign.service;

import com.suchaos.learn.spring.cloud.feign.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Feign 的 post 和 get 的多参数传递
 *
 * @author suchao
 * @date 2020/9/10
 */
@FeignClient(value = "feign-service-02")
public interface MultiParamFeignService {

    /*
     * Feign Get 将 POJO 参数放到了 http body 中，解决方法：
     * 1. 将 POJO　拆成一个个属性放在参数中
     * 2. 将 方法参数变成 Map 传递
     * 3. 使用 Get 传递 @RequestBody，但是违反规定 -- 在服务端是 get，但是需要 @RequestBody
     * 4. 使用 RequestInterceptor
     * 5. 使用 @SpringQueryMap
     */

    /**
     * 如果没有 RequestInterceptor 等处理的话，最终发出的请求是：
     * GET http://feign-service-02/user/add
     * 但是带着一个 Http Body
     * <p>
     * 4. 使用 RequestInterceptor
     */
    @GetMapping(value = "/user/add")
    public String addUser(User user);

    /**
     * 5. 最佳实践：使用 @SpringQueryMap
     */
    @GetMapping(value = "/user/add")
    public String addUserByQueryMap(@SpringQueryMap User user);

    /**
     * 1. 将 POJO　拆成一个个属性放在参数中
     * <p>
     * 最终 Feign 向服务端发送的请求是：GET http://feign-service-02/user/add?name=xxx&age=12 HTTP/1.1
     * <p>
     * 注意：不加 @RequestParam("name") 会报错，Method has too many Body parameters
     */
    @GetMapping(value = "/user/add")
    public String addUser1(@RequestParam("name") String name, @RequestParam("age") Integer age);

    /**
     * 2. 将 方法参数变成 Map 传递
     * <p>
     * 最终 Feign 向服务端发送的请求是：GET http://feign-service-02/user/add?name=xxx&age=12 HTTP/1.1
     */
    @GetMapping(value = "/user/add")
    public String addUser2(Map<String, Object> map);

    /**
     * 最终 Feign 发送的是一个 Post 请求，带着 Http Body，所以在服务端，Post + @RequestBody 就没有问题
     */
    @PostMapping(value = "/user/update")
    public String updateUser(@RequestBody User user);
}
