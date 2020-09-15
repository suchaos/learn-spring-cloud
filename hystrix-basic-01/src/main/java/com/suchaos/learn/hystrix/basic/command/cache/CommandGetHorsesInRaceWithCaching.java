package com.suchaos.learn.hystrix.basic.command.cache;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.suchaos.learn.hystrix.basic.domain.Horse;
import com.suchaos.learn.hystrix.basic.service.BettingService;

import java.util.ArrayList;
import java.util.List;

/**
 * Get List of Horses in a specific race. Note, calls via this command are cached.
 * <p>
 * 对 BettingService#getHorsesInRace 方法的 HystrixCommand 封装
 *
 * @author suchao
 * @date 2020/9/14
 */
public class CommandGetHorsesInRaceWithCaching extends HystrixCommand<List<Horse>> {

    private final BettingService service;
    private final String raceCourseId;

    /**
     * CommandGetHorsesInRaceWithCaching.
     *
     * @param service      Remote Broker Service
     * @param raceCourseId Id of race course
     */
    public CommandGetHorsesInRaceWithCaching(BettingService service, String raceCourseId) {
        super(Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("BettingServiceGroup"))
                .andThreadPoolKey(
                        HystrixThreadPoolKey.Factory.asKey("BettingServicePool")));

        this.service = service;
        this.raceCourseId = raceCourseId;
    }

    @Override
    protected List<Horse> run() {
        return service.getHorsesInRace(raceCourseId);
    }

    @Override
    protected List<Horse> getFallback() {
        // can log here, throw exception or return default
        return new ArrayList<Horse>();
    }

    @Override
    protected String getCacheKey() {
        return raceCourseId;
    }
}
