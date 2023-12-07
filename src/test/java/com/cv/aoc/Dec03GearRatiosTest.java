package com.cv.aoc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Dec03GearRatiosTest {

    private static Stream<Arguments> argsPart01() throws IOException {
        return Stream.of(
                arguments(parseInput("""
                        467..114..
                        ...*......
                        ..35..633.
                        ......#...
                        617*......
                        .....+.58.
                        ..592.....
                        ......755.
                        ...$.*....
                        .664.598..
                        """), 4361),
                arguments(parseInput(givenInput()), 519444));
    }

    private static char[][] parseInput(String input) {
        String[] lines = input.split("\n");
        char[][] result = new char[lines.length][lines[0].length()];
        int row = 0;
        for (String line : lines) {
            int col = 0;
            for (char c : line.toCharArray()) {
                result[row][col++] = c;
            }
            row++;
        }
        return result;
    }

    private static String givenInput() throws IOException {
        URL resource = requireNonNull(Dec01TrebuchetTest.class.getClassLoader().getResource("03.txt"), "resource");
        return String.join("\n", Files.readAllLines(Paths.get(resource.getPath())));
    }

    private Dec03GearRatios underTest;

    @BeforeEach
    public void setup() {
        underTest = new Dec03GearRatios();
    }

    @ParameterizedTest
    @MethodSource("argsPart01")
    public void testPart01(char[][] input, int result) {
        assertEquals(result, underTest.part01(input));
    }
}