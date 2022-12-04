package com.cv.aoc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day04CampCleanupTest {

    private Day04CampCleanup underTest;

    @ParameterizedTest
    @MethodSource("argsPart1")
    @DisplayName("Part 1")
    public void test01(String input, int output) {
        underTest = new Day04CampCleanup(input);

        assertEquals(output, underTest.part1());
    }

    @ParameterizedTest
    @MethodSource("argsPart2")
    @DisplayName("Part 2")
    public void test02(String input, int output) {
        underTest = new Day04CampCleanup(input);

        assertEquals(output, underTest.part2());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/04.csv", maxCharsPerColumn = 65535)
    @DisplayName("Actual Input")
    public void test03(String input, int part01, int part02) {
        underTest = new Day04CampCleanup(input);

        assertEquals(part01, underTest.part1());
        assertEquals(part02, underTest.part2());
    }

    private static Stream<Arguments> argsPart1() {
        return Stream.of(Arguments.of("""
                2-4,6-8
                2-3,4-5
                5-7,7-9
                2-8,3-7
                6-6,4-6
                2-6,4-8
                """, 2),
                Arguments.of("""
                94-97,31-95
                7-8,11-95
                6-17,5-18
                """, 1));
    }

    private static Stream<Arguments> argsPart2() {
        return Stream.of(Arguments.of("""
                2-4,6-8
                2-3,4-5
                5-7,7-9
                2-8,3-7
                6-6,4-6
                2-6,4-8
                """, 4));
    }
}