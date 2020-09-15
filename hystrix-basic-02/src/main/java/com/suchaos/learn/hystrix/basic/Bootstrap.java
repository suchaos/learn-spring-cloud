package com.suchaos.learn.hystrix.basic;

import com.netflix.hystrix.examples.demo.HystrixCommandDemo;

/**
 * 启动类
 *
 * @author suchao
 * @date 2020/9/14
 */
public class Bootstrap {

    public static void main(String[] args) {
        HystrixCommandDemo hystrixCommandDemo = new HystrixCommandDemo();
        hystrixCommandDemo.startDemo();
    }
}
