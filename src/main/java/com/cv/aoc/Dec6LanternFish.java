package com.cv.aoc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Dec6LanternFish {
    private static final boolean REAL_INPUT = false;

    public static void main(String[] args) throws Exception {
        LanternFishSolver solver = new LanternFishSolver();

        if (REAL_INPUT) {
            try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/2021_6.txt"))) {
                String input = reader.readLine();
                assert solver.simulateSlow(Arrays.stream(input.split(",")).map(Integer::parseInt).collect(Collectors.toList()), 80) == 380758;
                assert solver.simulateFast(Arrays.stream(input.split(",")).map(Integer::parseInt).collect(Collectors.toList()), 256) == 1710623015163L;
            }
        } else {
            String input = "3,4,3,1,2";
            assert solver.simulateSlow(Arrays.stream(input.split(",")).map(Integer::parseInt).collect(Collectors.toList()), 80) == 5934;
            assert solver.simulateFast(Arrays.stream(input.split(",")).map(Integer::parseInt).collect(Collectors.toList()), 256) == 26984457539L;
        }
    }
}

class LanternFishSolver {

    long simulateFast(List<Integer> fishes, int numDays) {
        long[] fishesByAge = new long[9];
        for (Integer fish : fishes) {
            fishesByAge[fish]++;
        }
        int day = 0;
        while (day < numDays) {
            long[] newGeneration = new long[9];
            for (int j = 0; j < 9; j++) {
                newGeneration[j] = fishesByAge[(j + 1) % 9];
            }
            newGeneration[6] += fishesByAge[0];
            fishesByAge = newGeneration;
            day++;
        }

        long totalFishes = 0;
        for (int i = 0; i < 9; i++) {
            totalFishes += fishesByAge[i];
        }
        return totalFishes;
    }

    long simulateSlow(List<Integer> fishes, int numDays) {
        return simulateSlow(fishes, 0, numDays);
    }

    private long simulateSlow(List<Integer> fishes, int day, int targetDays) {
        if (day == targetDays) {
            return fishes.size();
        }
        int numFishes = fishes.size();
        for (int i = 0; i < numFishes; i++) {
            int fish = fishes.get(i);
            if (fish == 0) {
                fishes.set(i, 6);
                fishes.add(8);
            } else {
                fishes.set(i, fish - 1);
            }
        }
        return simulateSlow(fishes, day + 1, targetDays);
    }
}