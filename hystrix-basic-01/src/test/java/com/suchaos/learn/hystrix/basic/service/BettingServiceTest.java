package com.suchaos.learn.hystrix.basic.service;

import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.suchaos.learn.hystrix.basic.command.CommandGetTodaysRaces;
import com.suchaos.learn.hystrix.basic.command.cache.CommandGetHorsesInRaceWithCaching;
import com.suchaos.learn.hystrix.basic.domain.Horse;
import com.suchaos.learn.hystrix.basic.domain.RaceCourse;
import com.suchaos.learn.hystrix.basic.exception.RemoteServiceException;
import org.junit.Before;
import org.junit.Test;
import rx.Observable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BettingServiceTest {

    private static final String RACE_1 = "course_france";
    private static final String HORSE_1 = "horse_white";
    private static final String HORSE_2 = "horse_black";

    private static final String ODDS_RACE_1_HORSE_1 = "10/1";
    private static final String ODDS_RACE_1_HORSE_2 = "100/1";

    private static final HystrixCommandKey GETTER_KEY = HystrixCommandKey.Factory.asKey("GetterCommand");

    private BettingService mockService;

    /**
     * Set up the shared Unit Test environment
     */
    @Before
    public void setUp() {
        mockService = mock(BettingService.class);
        when(mockService.getTodaysRaces()).thenReturn(getRaceCourses());
        when(mockService.getHorsesInRace(RACE_1)).thenReturn(getHorsesAtFrance());
        when(mockService.getOddsForHorse(RACE_1, HORSE_1)).thenReturn(ODDS_RACE_1_HORSE_1);
        when(mockService.getOddsForHorse(RACE_1, HORSE_2)).thenReturn(ODDS_RACE_1_HORSE_2);
    }

    private List<RaceCourse> getRaceCourses() {
        RaceCourse course1 = new RaceCourse(RACE_1, "France");
        // return Arrays.asList(course1);
        return Collections.singletonList(course1);
    }

    private List<Horse> getHorsesAtFrance() {
        Horse horse1 = new Horse(HORSE_1, "White");
        Horse horse2 = new Horse(HORSE_2, "Black");
        return Arrays.asList(horse1, horse2);
    }

    /**
     * Command GetRaces - Execute (synchronous call).
     */
    @Test
    public void testSynchronous() {

        // 创建一个 HystrixCommand，然后执行
        CommandGetTodaysRaces commandGetRaces = new CommandGetTodaysRaces(mockService);
        assertEquals(getRaceCourses(), commandGetRaces.execute());

        verify(mockService).getTodaysRaces();
        verifyNoMoreInteractions(mockService);
    }

    /**
     * Command GetRaces - Execute and Fail Silently.
     * Swallows remote server error and returns an empty list.
     */
    @Test
    public void testSynchronousFailSilently() {

        CommandGetTodaysRaces commandGetRacesFailure = new CommandGetTodaysRaces(mockService);
        // override mock to mimic an error being thrown for this test
        when(mockService.getTodaysRaces()).thenThrow(new RuntimeException("Error!!"));
        assertEquals(new ArrayList<RaceCourse>(), commandGetRacesFailure.execute());

        // Verify
        verify(mockService).getTodaysRaces();
        verifyNoMoreInteractions(mockService);
    }

    /**
     * Command GetRaces - Execute and Fail Fast.
     * Catches remote server error and throws a new Exception.
     */
    @Test
    public void testSynchronousFailFast() {
        CommandGetTodaysRaces commandGetRacesFailure = new CommandGetTodaysRaces(mockService, false);
        // override mock to mimic an error being thrown for this test
        when(mockService.getTodaysRaces()).thenThrow(new RuntimeException("Error!!"));
        try {
            commandGetRacesFailure.execute();
        } catch (HystrixRuntimeException hre) {
            assertEquals(RemoteServiceException.class, hre.getFallbackException().getClass());
        }

        verify(mockService).getTodaysRaces();
        verifyNoMoreInteractions(mockService);
    }

    /**
     * Command GetRaces - Queue (Asynchronous)
     */
    @Test
    public void testAsynchronous() throws Exception {
        CommandGetTodaysRaces commandGetRaces = new CommandGetTodaysRaces(mockService);
        Future<List<RaceCourse>> future = commandGetRaces.queue();
        assertEquals(getRaceCourses(), future.get());

        verify(mockService).getTodaysRaces();
        verifyNoMoreInteractions(mockService);
    }

    /**
     * Command - Observe (Hot Observable)
     */
    @Test
    public void testObservable() throws Exception {
        CommandGetTodaysRaces commandGetRaces = new CommandGetTodaysRaces(mockService);
        Observable<List<RaceCourse>> observable = commandGetRaces.observe();
        // blocking observable
        assertEquals(getRaceCourses(), observable.toBlocking().single());

        verify(mockService).getTodaysRaces();
        verifyNoMoreInteractions(mockService);
    }

    /**
     * Test - GetHorsesInRace - Uses Caching
     */
    @Test
    public void testWithCacheHits() {

        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        try {
            CommandGetHorsesInRaceWithCaching commandFirst = new CommandGetHorsesInRaceWithCaching(mockService, RACE_1);
            CommandGetHorsesInRaceWithCaching commandSecond = new CommandGetHorsesInRaceWithCaching(mockService, RACE_1);

            commandFirst.execute();
            // this is the first time we've executed this command with
            // the value of "2" so it should not be from cache
            assertFalse(commandFirst.isResponseFromCache());

            verify(mockService).getHorsesInRace(RACE_1);
            verifyNoMoreInteractions(mockService);

            commandSecond.execute();
            // this is the second time we've executed this command with
            // the same value so it should return from cache
            assertTrue(commandSecond.isResponseFromCache());

        } finally {
            context.shutdown();
        }

        // start a new request context
        context = HystrixRequestContext.initializeContext();
        try {
            CommandGetHorsesInRaceWithCaching commandThree = new CommandGetHorsesInRaceWithCaching(mockService, RACE_1);
            commandThree.execute();
            // this is a new request context so this
            // should not come from cache
            assertFalse(commandThree.isResponseFromCache());

            // Flush the cache
            HystrixRequestCache.getInstance(GETTER_KEY, HystrixConcurrencyStrategyDefault.getInstance()).clear(RACE_1);

        } finally {
            context.shutdown();
        }
    }
}