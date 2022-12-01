package com.cv.aoc;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Dec5HydroThermalVenture {
    private static final boolean REAL_INPUT = false;

    public static void main(String[] args) throws Exception {
        HydroThermalVentureSolver solver = new HydroThermalVentureSolver();

        if (REAL_INPUT) {
            try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/2021_5.txt"))) {
                System.out.println(solver.solve1(reader.lines().collect(Collectors.toList())));
            }
        } else {
            String input = "0,9 -> 5,9\n" +
                    "8,0 -> 0,8\n" +
                    "9,4 -> 3,4\n" +
                    "2,2 -> 2,1\n" +
                    "7,0 -> 7,4\n" +
                    "6,4 -> 2,0\n" +
                    "0,9 -> 2,9\n" +
                    "3,4 -> 1,4\n" +
                    "0,0 -> 8,8\n" +
                    "5,5 -> 8,2";
            System.out.println(solver.solve1(Arrays.stream(input.split("\n")).collect(Collectors.toList())));
        }
    }
}

class HydroThermalVentureSolver {

    int solve1(List<String> lines) {
        int GRID_SIZE = 1000;
        int[][] state = new int[GRID_SIZE][GRID_SIZE];
        lines.forEach(line -> {
            String[] parts = line.split("->");
            String[] startPart = parts[0].trim().split(",");
            String[] endPart = parts[1].trim().split(",");
            Point start = new Point(Integer.parseInt(startPart[0]), Integer.parseInt(startPart[1]));
            Point end = new Point(Integer.parseInt(endPart[0]), Integer.parseInt(endPart[1]));
            if (start.x == end.x || start.y == end.y) {
                if (start.x == end.x) {
                    for (int i = min(start.y, end.y); i <= max(start.y, end.y); i++) {
                        state[start.x][i]++;
                    }
                } else {
                    for (int i = min(start.x, end.x); i <= max(start.x, end.x); i++) {
                        state[i][start.y]++;
                    }
                }
            }
        });

        int numIntersections = 0;
        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {
                if (state[r][c] >= 2) {
                    numIntersections++;
                }
            }
        }
        return numIntersections;
    }

    int solve2(List<String> lines) {
            int GRID_SIZE = 1000;
            int[][] state = new int[GRID_SIZE][GRID_SIZE];
            lines.forEach(line -> {
                String[] parts = line.split("->");
                String[] startPart = parts[0].trim().split(",");
                String[] endPart = parts[1].trim().split(",");
                Point start = new Point(Integer.parseInt(startPart[0]), Integer.parseInt(startPart[1]));
                Point end = new Point(Integer.parseInt(endPart[0]), Integer.parseInt(endPart[1]));
                if (start.x == end.x || start.y == end.y) {
                    if (start.x == end.x) {
                        for (int i = min(start.y, end.y); i <= max(start.y, end.y); i++) {
                            state[start.x][i]++;
                        }
                    } else {
                        for (int i = min(start.x, end.x); i <= max(start.x, end.x); i++) {
                            state[i][start.y]++;
                        }
                    }
                } else {
                    Point s = start.x < end.x ? start : end;
                    Point e = start.x > end.x ? start : end;
                    int yd = 0;

                    Point thisPoint = new Point(s.x, s.y);
                    while(thisPoint.x != e.x && thisPoint.y != e.y) {
                        state[thisPoint.y][thisPoint.x]++;
                        thisPoint.x++;
                        thisPoint.y += yd;
                    }
                }
            });

            int numIntersections = 0;
            for (int r = 0; r < GRID_SIZE; r++) {
                for (int c = 0; c < GRID_SIZE; c++) {
                    if (state[r][c] >= 2) {
                        numIntersections++;
                    }
                }
            }
            return numIntersections;
        }
}
