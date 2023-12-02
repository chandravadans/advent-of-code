package com.cv.aoc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Dec01TrebuchetTest {

    private static Stream<Arguments> argsPart01() throws IOException {
        return Stream.of(
                arguments(List.of("a1b2c3d4e5f"), 15),
                arguments(List.of("treb7uchet"), 77),
                arguments("""
                        1abc2
                        pqr3stu8vwx
                        a1b2c3d4e5f
                        treb7uchet
                        """.lines().toList(), 142),
                arguments(givenInput(), 55002));
    }

    private static Stream<Arguments> argsPart02() throws IOException {
        return Stream.of(
                arguments(List.of("zoneight234"), 14),
                arguments(List.of("xtwone3four"), 24),
                arguments(List.of("eighthree"), 83),
                arguments("""
                        two1nine
                        eightwothree
                        abcone2threexyz
                        xtwone3four
                        4nineeightseven2
                        zoneight234
                        7pqrstsixteen
                        """.lines().toList(), 281),
                arguments(givenInput(), 55093));
    }

    private static List<String> givenInput() throws IOException {
        URL resource = requireNonNull(Dec01TrebuchetTest.class.getClassLoader().getResource("01.txt"), "resource");
        return Files.readAllLines(Paths.get(resource.getPath()));
    }

    private Dec01Trebuchet underTest;

    @BeforeEach
    public void setup() {
        underTest = new Dec01Trebuchet();
    }

    @ParameterizedTest
    @MethodSource("argsPart01")
    public void testPart01(List<String> input, int result) {
        assertEquals(result, underTest.part01(input));
    }

    @ParameterizedTest
    @MethodSource("argsPart02")
    public void testPart02(List<String> input, int result) {
        assertEquals(result, underTest.part02(input));
    }
}