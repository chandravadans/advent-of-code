package com.cv.aoc;

import org.junit.Before;
import org.junit.Test;

import static java.lang.Integer.MAX_VALUE;
import static org.junit.Assert.*;

public class HelloAoCTest {

    private HelloAoC underTest;

    @Before
    public void setup() {
        underTest = new HelloAoC();
    }

    @Test
    public void testAddsIntegers() {
        assertEquals(5, underTest.addIntegers(3, 2));
    }

    @Test
    public void testAddsIntegersAndOverflows() {
        assertTrue(underTest.addIntegers(MAX_VALUE, MAX_VALUE) < 0);
    }
}