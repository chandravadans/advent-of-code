package com.cv.aoc;

import java.io.StringReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Day01CalorieCounting {

    private final String calorieList;

    Day01CalorieCounting(String calorieList) {
        this.calorieList = calorieList;
    }

    public long part1() {
        try(Scanner in = new Scanner(new StringReader(calorieList))) {
            long caloriesByElf = 0L, maxCalories = 0L;
            while (in.hasNext()) {
                String calorieCount = in.nextLine();
                if (!calorieCount.isEmpty()) {
                    caloriesByElf += Long.parseLong(calorieCount);
                } else {
                    if (caloriesByElf > maxCalories) {
                        maxCalories = caloriesByElf;
                    }
                    caloriesByElf = 0L;
                }
            }
            return maxCalories;
        }
    }

    public long part2() {
        try (Scanner in = new Scanner(new StringReader(calorieList))) {
            Queue<Long> topElves = new PriorityQueue<>();
            long caloriesByElf = 0L;
            while (in.hasNext()) {
                String calories = in.nextLine();
                if (!calories.isEmpty()) {
                    caloriesByElf += Long.parseLong(calories);
                } else {
                    recordCalories(topElves, caloriesByElf);
                    caloriesByElf = 0L;
                }
            }
            if (caloriesByElf != 0) {
                recordCalories(topElves, caloriesByElf);
            }

            return topElves.stream().mapToLong(Long::longValue).sum();
        }
    }

    private void recordCalories(Queue<Long> topElves, long calorieCount) {
        topElves.add(calorieCount);
        if (topElves.size() > 3) {
            topElves.poll();
        }
    }
}