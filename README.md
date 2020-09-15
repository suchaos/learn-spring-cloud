
## Eureka 实验1

组件：
* discovery-server-01: standalone 启动
* time-server-01: 服务提供者
    * 启动时添加参数：server.port 4444 和 server.port 5555 启动两个
* time-client-01: 服务消费者

## Eureka 实验2 -- Eureka Server 集群配置
组件：
* discovery-server-02: 集群启动
    * 启动时添加参数：spring.active.profile discovery1 和 spring.active.profile discovery2 启动两个
* time-server-02: 服务提供者
    * 启动时添加参数：server.port 4444 和 server.port 5555 启动两个
* time-client-02: 服务消费者

### Eureka 配置优化
具体看 discovery-server-02 和 time-server-02 中的配置信息

## 实验3 Feign 的入门案例
组件：
* feign01: 启动后不需要服务注册，直接访问即可

访问地址：localhost:9090/search/github?str=spring-cloud-dubbo

## 实验4 Feign 的进阶案例
组件:
* discovery-server-01: standalone 启动
* feign-client-02: feign 的客户端
* feign-client-02: feign 的服务端
    * 替换默认的 http 客户端 --> HttpClient or OkHttp
    * Feign Get 多参数
    * Feign 拦截器的使用 -- 传递 token
 
## 实验5 Hystrix 基础实验
组件：
* hystrix-basic-01

实验样例：

1. 同步调用testSynchronous
2. 同步安静失败testSynchronousFailSilently
3. 同步快速失败testSynchronousFailFast
4. 异步调用testAsynchronous
5. 响应式调用testObservable
6. 缓存调用testWithCacheHist

## 实验6 Hystrix 官网案例
组件：
* hystrix-basic-02

是一个模拟交易的实例：

---> GetUserAccount ---> GetPaymentInfo ---> GetOrder ---> PayCreditCard

## 实验7 Hystrix 和 Feign 结合使用
组件：
* discovery-server-01: standalone 启动
* hystrix-feign-01 作为 feign 的客户端，并启用 Hystrix
* feign-service-02 作为 feign 的服务端

实验有关 feign,hystrix,ribbon 超时与重试的相关配置

## 实验8 Hystrix 和 Hystrix Dashboard 使用
组件：
* discovery-server-01: standalone 启动
* hystrix-feign-01 作为 feign 的客户端，并启用 Hystrix
* feign-service-02 作为 feign 的服务端
* hystrix-dashboard-01 作为 hystrix dashboard 的单独应用

## 实验9 Hystrix 和 turbine 使用
组件：
* discovery-server-01: standalone 启动
* hystrix-feign-01 作为 feign 的客户端，并启用 Hystrix
* feign-service-02 作为 feign 的服务端，启动两个实例，两个端口
    * 8081
    * 8082
* hystrix-turbines-01 作为 turbine 和 hystrix dashboard 的应用

访问： http://localhost:9001/turbine.stream?cluster=hystrix-feign-service

然后将 http://localhost:9000/turbine.stream?cluster=hystrix-feign-service  这个地址放到  Hystrix Dashboard （http://localhost:9001/hystrix）上去
