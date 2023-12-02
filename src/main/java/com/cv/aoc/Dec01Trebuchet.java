package com.cv.aoc;

import java.util.Arrays;
import java.util.List;

import static java.lang.Character.isDigit;

public class Dec01Trebuchet {

    public int part01(List<String> input) {
        return input.stream()
                .map(this::getCalibrationValue)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int part02(List<String> input) {
        return input.stream()
                .map(this::getCalibrationValue2)
                .mapToInt(Integer::intValue)
                .sum();
    }

    int getCalibrationValue(String input) {
        char first = 0, last = 0;
        for (char c : input.toCharArray()) {
            if (isDigit(c)) {
                if (first == 0) {
                    first = c;
                } else {
                    last = c;
                }
            }
        }
        //Only one digit was found, double up the digit that was
        if (last == 0) {
            last = first;
        }
        return Character.getNumericValue(first) * 10 + Character.getNumericValue(last);
    }

    int getCalibrationValue2(String input) {
        //Overlaps are overly annoying
        return getCalibrationValue(input.replaceAll("one", "one1one")
                .replaceAll("two", "two2two")
                .replaceAll("three", "three3three")
                .replaceAll("four", "four4four")
                .replaceAll("five", "five5five")
                .replaceAll("six", "six6six")
                .replaceAll("seven", "seven7seven")
                .replaceAll("eight", "eight8eight")
                .replaceAll("nine", "nine9nine")
                .replaceAll("zero", "zero0zero"));
    }
}
