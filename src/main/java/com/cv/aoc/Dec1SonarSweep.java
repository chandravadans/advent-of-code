package com.cv.aoc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Dec1SonarSweep {
    private static final boolean READ_FROM_STDIN = false;

    public static void main(String[] args) throws IOException {
        SonarSweepSolver solver = new SonarSweepSolver();
        try (BufferedReader reader = new BufferedReader(
                READ_FROM_STDIN ?
                        new InputStreamReader(System.in, UTF_8) :
                        new FileReader("src/main/resources/2021_1.txt"))) {

            List<Integer> depths = reader.lines()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            assert solver.solve(depths, 1) == 1162;
            assert solver.solve(depths, 3) == 1190;
        }
    }
}

class SonarSweepSolver {
    int solve(List<Integer> depths, int windowSize) {
        int numDepths = depths.size(), result = 0, prevWindow = 0;
        for (int windowEnd = 0; windowEnd < numDepths; windowEnd++) {
            if (windowEnd < windowSize) {
                prevWindow += depths.get(windowEnd);
            } else {
                int currWindow = prevWindow - depths.get(windowEnd - windowSize) + depths.get(windowEnd);
                if (currWindow > prevWindow) {
                    result++;
                }
                prevWindow = currWindow;
            }
        }
        return result;
    }
}