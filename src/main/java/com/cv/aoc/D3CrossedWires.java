package com.cv.aoc;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class D3CrossedWires {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            String wire1 = in.nextLine();
            String wire2 = in.nextLine();
            System.out.println(Day3Solver.solve1(wire1, wire2));
            //System.out.println(Day3Solver.solve2(wire1, wire2));
        }
    }
}

class Day3Solver {
    static int maxx = 2;
    static int maxy = 2;
    static char[][] grid = new char[maxx][maxy];
    static int[][] gridSteps = new int[maxx][maxy];
    static int closestIntersection = Integer.MAX_VALUE;
    static int closestIntersectionSteps = Integer.MAX_VALUE;

    static Map<Character, Integer> dx = Map.of('U', 0, 'D', 0, 'L', -1, 'R', 1);
    static Map<Character, Integer> dy = Map.of('U', 1, 'D', -1, 'L', 0, 'R', 0);

    static Map<Point, Integer> pathA = new HashMap<>();
    static Map<Point, Integer> pathB = new HashMap<>();

    static int solve1(String wire1, String wire2) {
        for (int i = 0; i < maxx; i++) {
            for (int j = 0; j < maxy; j++) {
                grid[i][j] = 'x';
            }
        }
        String[] instructions1 = wire1.split(",");
        String[] instructions2 = wire2.split(",");
        Point current = new Point(0, 0);
        int stepCounter = 0;
        for (String s : instructions1) {
            current = processInstruction(current, s, 'a', stepCounter);
            stepCounter += Integer.parseInt(s.substring(1));
        }
        stepCounter = 0;
        current = new Point(0, 0);
        for (String s : instructions2) {
            current = processInstruction(current, s, 'b', stepCounter);
            stepCounter += Integer.parseInt(s.substring(1));
        }
        Set<Point> common = pathA.keySet();
        common.retainAll(pathB.keySet());
        for (Point p : common) {
            closestIntersection = Math.min(closestIntersectionSteps, Math.abs(p.x) + Math.abs(p.y));
        }
        return closestIntersection;
    }

    static int solve2(String wire1, String wire2) {
        solve1(wire1, wire2);
        return closestIntersectionSteps;
    }

    private static Point processInstruction(Point currPos, String instruction, char marker, int stepNumber) {
        int numSteps = Integer.parseInt(instruction.substring(1));
        Point finalPos = new Point(currPos);
        for (int i = 0; i < numSteps; i++) {
            finalPos.x += dx.get(instruction.charAt(0));
            finalPos.y += dy.get(instruction.charAt(0));
            if (marker == 'a') {
                pathA.put(finalPos, pathA.getOrDefault(finalPos, 0) + stepNumber + 1);
            } else if (marker == 'b') {
                pathB.put(finalPos, pathB.getOrDefault(finalPos, 0) + stepNumber + 1);
            }
        }
        return finalPos;
    }
}

// 207, 21196