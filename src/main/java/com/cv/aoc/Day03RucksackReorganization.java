package com.cv.aoc;

import java.io.StringReader;
import java.util.Scanner;

import static java.lang.Character.isLowerCase;

public class Day03RucksackReorganization {

    public static final int MAX_TYPES = 52;
    private final String input;

    public Day03RucksackReorganization(String input) {
        this.input = input;
    }

    public int part1() {
        try (Scanner in = new Scanner(new StringReader(input))) {
            int totalPriority = 0;
            while (in.hasNext()) {
                String ruckSack = in.nextLine();
                int numItems = ruckSack.length();
                int compartmentCapacity = (numItems / 2) - (numItems % 2);
                totalPriority += (getPriorityOfCommonType(
                        ruckSack.substring(0, compartmentCapacity),
                        ruckSack.substring(compartmentCapacity)) + 1);
            }
            return totalPriority;
        }
    }

    public int part2() {
        try (Scanner in = new Scanner(new StringReader(input))) {
            int totalPriority = 0;
            while (in.hasNext()) {
                totalPriority += (getPriorityOfCommonType(
                        in.nextLine(),
                        in.nextLine(),
                        in.nextLine()) + 1);
            }
            return totalPriority;
        }
    }

    private int getPriorityOfCommonType(String... rucksacks) {
        boolean[] commonTypes = new boolean[52];
        for (char c : rucksacks[0].toCharArray()) {
            commonTypes[getIndex(c)] = true;
        }
        int numRucksacks = rucksacks.length;
        for (int i = 1; i < numRucksacks; i++) {
            boolean[] typesInThisSack = new boolean[MAX_TYPES];
            for (char c : rucksacks[i].toCharArray()) {
                typesInThisSack[getIndex(c)] = true;
            }
            for (int j = 0; j < MAX_TYPES; j++) {
                if (!(commonTypes[j] && typesInThisSack[j])) {
                    commonTypes[j] = false;
                }
            }
        }
        for (int i = 0; i < MAX_TYPES; i++) {
            if (commonTypes[i]) {
                return i;
            }
        }
        throw new IllegalArgumentException("No common type!");
    }

    private int getIndex(char type) {
        return isLowerCase(type) ? type - 'a' : (MAX_TYPES / 2) + type - 'A';
    }
}
