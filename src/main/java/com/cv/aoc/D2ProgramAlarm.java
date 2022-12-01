package com.cv.aoc;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class D2ProgramAlarm {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            String input = in.nextLine();
            System.out.println(Day2Solver.solve1(input, 12, 2));
            System.out.println(Day2Solver.solve2(input));
        }
    }
}

class Day2Solver {

    static int solve1(String ints, int noun, int verb) {
        List<Integer> intCodes = Arrays.stream(ints.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        intCodes.set(1, noun);
        intCodes.set(2, verb);
        int opcodePosition = 0;
        while (processOperation(opcodePosition, intCodes)) {
            opcodePosition += 4;
        }
        return intCodes.get(0);
    }

    static int solve2(String ints) {
        for (int i = 0; i < 99; i++) {
            for (int j = 0; j < 99; j++) {
                int res = solve1(ints, i, j);
                if (res == 19690720) {
                    return 100 * i + j;
                }
            }
        }
        return -1;
    }

    private static boolean processOperation(int opcodePosition, List<Integer> codes) {
        if (opcodePosition >= codes.size() - 3 || codes.get(opcodePosition) == 99) {
            return false;
        }
        switch (codes.get(opcodePosition)) {
            case 1:
                codes.set(codes.get(opcodePosition + 3), codes.get(codes.get(opcodePosition + 1)) + codes.get(codes.get(opcodePosition + 2)));
                return true;
            case 2:
                codes.set(codes.get(opcodePosition + 3), codes.get(codes.get(opcodePosition + 1)) * codes.get(codes.get(opcodePosition + 2)));
                return true;
            default:
                throw new RuntimeException("Encountered unknown opcode " + codes.get(opcodePosition));
        }
    }
}
