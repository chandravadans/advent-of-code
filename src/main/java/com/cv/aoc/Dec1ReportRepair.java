package com.cv.aoc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.nio.charset.StandardCharsets.UTF_8;

/*
Sol1: 158916
Sol2: 165795564
 */

public class Dec1ReportRepair {

    private static final boolean READ_FROM_STDIN = false;

    public static void main(String[] args) throws IOException {
        boolean[] entries = new boolean[2020];  //2009 was the largest in the input

        try (BufferedReader reader = new BufferedReader(
                READ_FROM_STDIN ?
                        new InputStreamReader(System.in, UTF_8) :
                        new FileReader("src/main/resources/2020_1.txt"))) {

            reader.lines().mapToInt(Integer::parseInt).forEach(entry -> entries[entry] = true);
        }

        System.out.println(Day1Solver.solve1(entries));
        System.out.println(Day1Solver.solve2(entries));
    }
}

class Day1Solver {

    static int solve1(boolean... entries) {
        return helper(entries, 2020);
    }

    static int solve2(boolean... entries) {
        for (int i = 0; i < entries.length; i++) {
            if (entries[i]) {
                int initialSol = helper(entries, 2020 - i);
                if (initialSol != 0) {
                    return i * initialSol;
                }
            }
        }
        return 0;
    }

    private static int helper(boolean[] entries, int total) {
        for (int i = 0; i < entries.length; i++) {
            if (entries[i]) {
                int other = total - i;
                if (other > 0 && entries[other]) {
                    return i * other;
                }
            }
        }
        return 0;
    }
}
