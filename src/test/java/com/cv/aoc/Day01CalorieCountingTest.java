package com.cv.aoc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day01CalorieCountingTest {

    Day01CalorieCounting underTest;

    @ParameterizedTest
    @MethodSource("argsPart1")
    @DisplayName("Part 1")
    public void test01(String input, long output) {
        underTest = new Day01CalorieCounting(input);

        assertEquals(output, underTest.part1());
    }

    @ParameterizedTest
    @MethodSource("argsPart2")
    @DisplayName("Part 2")
    public void test02(String input, long output) {
        underTest = new Day01CalorieCounting(input);

        assertEquals(output, underTest.part2());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/01.csv", maxCharsPerColumn = 65535)
    @DisplayName("Actual Input")
    public void test03(String input, long part01, long part02) {
        underTest = new Day01CalorieCounting(input);

        assertEquals(part01, underTest.part1());
        assertEquals(part02, underTest.part2());
    }

    private static Stream<Arguments> argsPart1() {
        return Stream.of(
                Arguments.of("""
                        1000
                        2000
                        3000

                        4000

                        5000
                        6000

                        7000
                        8000
                        9000

                        10000
                        """, 24000L)
        );
    }

    private static Stream<Arguments> argsPart2() {
        return Stream.of(
                Arguments.of("""
                        1000
                        2000
                        3000

                        4000

                        5000
                        6000

                        7000
                        8000
                        9000

                        10000
                        """, 45000L)
        );
    }

}