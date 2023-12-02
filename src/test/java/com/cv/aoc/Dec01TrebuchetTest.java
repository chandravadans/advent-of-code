package com.cv.aoc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Dec01TrebuchetTest {

    private Dec01Trebuchet underTest;

    @BeforeEach
    public void setup() {
        underTest = new Dec01Trebuchet();
    }

    @Test
    public void testParsesNumber() {
        assertEquals(15, underTest.getCalibrationValue("a1b2c3d4e5f"));
        assertEquals(77, underTest.getCalibrationValue("treb7uchet"));
    }

    @Test
    public void testPart01SampleInput() {
        assertEquals(142,
                underTest.part01("""
                        1abc2
                        pqr3stu8vwx
                        a1b2c3d4e5f
                        treb7uchet"""
                        .lines().toList()));
    }

    @Test
    public void testPart01GivenInput() {
        assertEquals(55002, underTest.part01(getInput()));
    }

    @Test
    public void testParsesNumber2() {
        assertEquals(14, underTest.getCalibrationValue2("zoneight234"));
        //Overlaps, twone3four => twone1one3four => two2twone1one3four4four => 24
        assertEquals(24, underTest.getCalibrationValue2("xtwone3four"));
    }

    @Test
    public void testPart02SampleInput() {
        assertEquals(281,
                underTest.part02("""
                        two1nine
                        eightwothree
                        abcone2threexyz
                        xtwone3four
                        4nineeightseven2
                        zoneight234
                        7pqrstsixteen
                        """
                        .lines().toList()));
    }

    @Test
    public void testPart02GivenInput() {
        assertEquals(55093, underTest.part02(getInput()));
    }

    private List<String> getInput() {
        URL resource = getClass().getClassLoader().getResource("01.txt");
        if (resource != null) {
            try {
                return Files.readAllLines(Paths.get(resource.getPath()));
            } catch (IOException e) {
                throw new IllegalArgumentException("Couldn't read from URL: " + resource, e);
            }
        }
        throw new IllegalArgumentException("No resource found in classpath!");
    }
}