package com.cv.aoc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day02RockPaperScissorsTest {

    private Day02RockPaperScissors underTest;

    @ParameterizedTest
    @MethodSource("argsPart1")
    @DisplayName("Part 1")
    public void test01(String input, int output) {
        underTest = new Day02RockPaperScissors(input);

        assertEquals(output, underTest.part1());
    }

    @ParameterizedTest
    @MethodSource("argsPart2")
    @DisplayName("Part 2")
    public void test02(String input, int output) {
        underTest = new Day02RockPaperScissors(input);

        assertEquals(output, underTest.part2());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/02.csv", maxCharsPerColumn = 65535)
    @DisplayName("Actual Input")
    public void test03(String input, int part01, int part02) {
        underTest = new Day02RockPaperScissors(input);

        assertEquals(part01, underTest.part1());
        assertEquals(part02, underTest.part2());
    }

    private static Stream<Arguments> argsPart1() {
        return Stream.of(Arguments.of("""
                A Y
                B X
                C Z
                """, 15));
    }

    private static Stream<Arguments> argsPart2() {
        return Stream.of(Arguments.of("""
                A Y
                B X
                C Z
                """, 12));
    }
}