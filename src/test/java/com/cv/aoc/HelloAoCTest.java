package com.cv.aoc;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Integer.MAX_VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HelloAoCTest {

    private HelloAoC underTest;

    @BeforeEach
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