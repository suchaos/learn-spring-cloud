package com.suchaos.learn.hystrix.basic.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.suchaos.learn.hystrix.basic.domain.RaceCourse;
import com.suchaos.learn.hystrix.basic.exception.RemoteServiceException;
import com.suchaos.learn.hystrix.basic.service.BettingService;

import java.util.ArrayList;
import java.util.List;

/**
 * Get a list of all Race courses with races on today.
 * <p>
 * 对 BettingService#getTodaysRaces() 这个具体的实现方法的 HystrixCommand 封装
 */
public class CommandGetTodaysRaces extends HystrixCommand<List<RaceCourse>> {

    private final BettingService service;
    private final boolean failSilently;

    /**
     * CommandGetTodaysRaces
     *
     * @param service      Remote Broker Service
     * @param failSilently If <code>true</code> will return an empty list if a remote service exception is thrown, if
     *                     <code>false</code> will throw a BettingServiceException.
     */
    public CommandGetTodaysRaces(BettingService service, boolean failSilently) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("BettingServiceGroup"))
                .andThreadPoolKey(
                        HystrixThreadPoolKey.Factory.asKey("BettingServicePool")));

        this.service = service;
        this.failSilently = failSilently;
    }

    public CommandGetTodaysRaces(BettingService service) {
        this(service, true);
    }

    @Override
    protected List<RaceCourse> run() {
        return service.getTodaysRaces();
    }

    @Override
    protected List<RaceCourse> getFallback() {
        // can log here, throw exception or return default
        if (failSilently) {
            return new ArrayList<>();
        }

        throw new RemoteServiceException("Unexpected error retrieving todays races");
    }
}