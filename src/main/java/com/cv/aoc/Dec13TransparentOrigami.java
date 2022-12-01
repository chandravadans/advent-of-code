package com.cv.aoc;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class Dec13TransparentOrigami {
    private static final boolean REAL_INPUT = true;

    public static void main(String[] args) throws Exception {
        TransparentOrigamiSolver solver = new TransparentOrigamiSolver();

        if (REAL_INPUT) {
            try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/2021_13.txt"))) {
                List<String> lines = reader.lines().collect(Collectors.toList());
                Set<Point> points = new HashSet<>();
                List<String> instructions = new ArrayList<>();
                for (String l : lines) {
                    if (!l.isEmpty()) {
                        if (l.charAt(0) - '0' <= 9) {
                            String[] parts = l.split(",");
                            points.add(new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
                        } else {
                            instructions.add(l);
                        }
                    }
                }
                System.out.println(solver.solve1(points, instructions));
            }
        } else {
            String input = "6,10\n" +
                    "0,14\n" +
                    "9,10\n" +
                    "0,3\n" +
                    "10,4\n" +
                    "4,11\n" +
                    "6,0\n" +
                    "6,12\n" +
                    "4,1\n" +
                    "0,13\n" +
                    "10,12\n" +
                    "3,4\n" +
                    "3,0\n" +
                    "8,4\n" +
                    "1,10\n" +
                    "2,14\n" +
                    "8,10\n" +
                    "9,0\n" +
                    "\n" +
                    "fold along y=7\n" +
                    "fold along x=5";
            List<String> lines = Arrays.stream(input.split("\n")).collect(Collectors.toList());
            Set<Point> points = new LinkedHashSet<>();
            List<String> instructions = new ArrayList<>();
            for (String l : lines) {
                if (!l.isEmpty()) {
                    if (l.charAt(0) - '0' <= 9) {
                        String[] parts = l.split(",");
                        points.add(new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
                    } else {
                        instructions.add(l);
                    }
                }
            }
            //System.out.println(solver.solve1(points, instructions));
            assert solver.solve1(points, instructions) == 17;
        }

    }


}
class TransparentOrigamiSolver {

    int solve1(Set<Point> points, List<String> instructions) {
        String instruction = instructions.get(0);

        Set<Point> result = new LinkedHashSet<>();
        for (Point p : points) {
            Point ref = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
            int mirror = Integer.parseInt(instruction.split("=")[1]);

            if (instruction.contains("y=")) {
                if(p.getX() > mirror) {
                    ref = reflectY(p, mirror);
                } else {
                    result.add(p);
                }
            } else if (instruction.contains("x=")) {
                if(p.getY() > mirror) {
                    ref = reflectX(p, mirror);
                } else {
                    result.add(p);
                }
            }
            if (ref.getX() != Integer.MAX_VALUE && ref.getY() != Integer.MAX_VALUE) {
                result.add(ref);
            }
        }
        /*int MAX = 1024;
        for(int x = 0; x < MAX; x++) {
            for(int y = 0; y < MAX; y++) {
                if(result.contains(new Point(x,y))) {
                    System.out.print("x");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }*/
        return result.size();
    }

    private Point reflectY(Point p, int y) {
        Point res = new Point();
        double dist = Math.abs(p.getX() - y);
        res.setLocation(p.getX() - 2 * dist, p.getY());
        return res;
    }

    private Point reflectX(Point p, int x) {
        Point res = new Point();
        double dist = Math.abs(p.getY() - x);
        res.setLocation(p.getX(), p.getY() - 2 * dist);
        return res;
    }

}
