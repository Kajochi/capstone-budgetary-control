package de.neuefische.capstone.backend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntervalTest {

    @Test
    void getMultiplierShouldReturn1ForMonthly() {
        //GIVEN
        Interval interval = Interval.MONTHLY;
        //WHEN
        int actual = Interval.getMultiplier(interval);
        //THEN
        assertEquals(1, actual);
    }

    @Test
    void getMultiplierShouldReturn0ForOnce() {
        //GIVEN
        Interval interval = Interval.ONCE;
        //WHEN
        int actual = Interval.getMultiplier(interval);
        //THEN
        assertEquals(0, actual);
    }

}