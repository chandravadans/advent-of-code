package com.cv.aoc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class D1TyrannyOfRocketEquation {
    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>();
        try (Scanner in = new Scanner(System.in)) {
            while (in.hasNext()) {
                input.add(in.nextInt());
            }
        }
        System.out.println(Day1Solver.solve1(input.stream()));
        System.out.println(Day1Solver.solve2(input.stream()));
    }
}

class Day1Solver {
    static int solve1(Stream<Integer> modules) {
        return modules
                .map(Day1Solver::fuelValue)
                .reduce(0, Integer::sum);
    }

    static int solve2(Stream<Integer> modules) {
        return modules
                .map(module -> Stream.iterate(fuelValue(module), fuel -> fuel > 0, Day1Solver::fuelValue).reduce(Integer::sum).orElse(0))
                .reduce(0, Integer::sum);
    }

    private static int fuelValue(int val) {
        return (int) Math.max(Math.floor(((double) val / 3) - 2), 0);
    }
}