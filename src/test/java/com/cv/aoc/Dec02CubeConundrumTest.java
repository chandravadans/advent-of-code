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
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Dec02CubeConundrumTest {

    private static Stream<Arguments> argsPart01() throws IOException {
        return Stream.of(
                arguments(List.of("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"), 1),
                arguments("""
                        Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
                        Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
                        Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
                        Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
                        Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
                        """.lines().toList(), 8),
                arguments(givenInput(), 2406));
    }

    private static Stream<Arguments> argsPart02() throws IOException {
        return Stream.of(
                arguments(List.of("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red"), 1560),
                arguments("""
                        Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
                        Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
                        Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
                        Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
                        Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
                        """.lines().toList(), 2286),
                arguments(givenInput(), 78375));
    }

    private static List<String> givenInput() throws IOException {
        URL resource = requireNonNull(Dec01TrebuchetTest.class.getClassLoader().getResource("02.txt"), "resource");
        return Files.readAllLines(Paths.get(resource.getPath()));
    }

    private Dec02CubeConundrum underTest;

    @BeforeEach
    public void setup() {
        underTest = new Dec02CubeConundrum();
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