package com.suchaos.learn.hystrix.basic.service;

import com.suchaos.learn.hystrix.basic.domain.Horse;
import com.suchaos.learn.hystrix.basic.domain.RaceCourse;

import java.util.List;

/**
 * 模仿跑马比赛
 * <p>
 * Simulates the interface for a remote betting service.
 *
 * @author suchao
 * @date 2020/9/14
 */
public interface BettingService {
    /**
     * Get a list the names of all Race courses with races on today.
     *
     * @return List of race course names
     */
    List<RaceCourse> getTodaysRaces();

    /**
     * Get a list of all Horses running in a particular race.
     *
     * @param raceCourseId Id of race course
     * @return List of the names of all horses running in the specified race
     */
    List<Horse> getHorsesInRace(String raceCourseId);

    /**
     * Get current odds for a particular horse in a specific race today.
     *
     * @param raceCourseId Id of race course
     * @param horseId      Id of horse
     * @return Current odds as a string (e.g. "10/1")
     */
    String getOddsForHorse(String raceCourseId, String horseId);
}
