package com.cv.aoc;

import java.io.StringReader;
import java.util.Scanner;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class Day04CampCleanup {

    private final Pattern inputPattern = Pattern.compile("(\\d+)-(\\d+),(\\d+)-(\\d+)");

    private final String assignments;

    public Day04CampCleanup(String assignments) {
        this.assignments = assignments;
    }

    public int part1() {
        try (var in = new Scanner(new StringReader(assignments))) {
            var totalContaining = 0;
            while (in.hasNext()) {
                var intervals = parseIntervals(in.nextLine());
                if (intervals[0].contains(intervals[1]) || intervals[1].contains(intervals[0])) {
                    totalContaining++;
                }
            }
            return totalContaining;
        }
    }

    public int part2() {
        try (var in = new Scanner(new StringReader(assignments))) {
            var totalOverlapping = 0;
            while (in.hasNext()) {
                var intervals = parseIntervals(in.nextLine());
                if (intervals[0].overlaps(intervals[1]) || intervals[1].overlaps(intervals[0])) {
                    totalOverlapping++;
                }
            }
            return totalOverlapping;
        }
    }

    private Interval[] parseIntervals(String line) {
        var m = inputPattern.matcher(line);
        if (m.find()) {
            return new Interval[]{
                    new Interval(parseInt(m.group(1)), parseInt(m.group(2))),
                    new Interval(parseInt(m.group(3)), parseInt(m.group(4))),
            };
        }
        throw new IllegalArgumentException("Unknown format of input : " + line);
    }

    private record Interval(int start, int end) {
        boolean contains(Interval i) {
            return (start <= i.start && end >= i.end);
        }

        boolean overlaps(Interval i) {
            return !(start > i.end || end < i.start);
        }
    }
}
