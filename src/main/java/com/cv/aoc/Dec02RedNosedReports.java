package com.cv.aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dec02RedNosedReports {

    private static List<List<Integer>> parseInput(String input) {
        return Arrays.stream(input.split("\\n"))
                .map(levels -> Arrays.stream(levels.split("\\s+"))
                        .map(Integer::parseInt)
                        .toList())
                .toList();
    }

    private static boolean isValid(List<Integer> report) {
        int numLevels = report.size();
        boolean increasing = report.get(0) < report.get(1);
        for (int i = 0; i < numLevels - 1; i++) {
            int delta = Math.abs(report.get(i) - report.get(i + 1));
            if (delta < 1 || delta > 3) {
                return false;
            }
            if (increasing && report.get(i) >= report.get(i + 1)) {
                return false;
            }
            if (!increasing && report.get(i) <= report.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    long part01(String input) {
        return parseInput(input).stream()
                .filter(Dec02RedNosedReports::isValid)
                .count();
    }

    long part02(String input) {
        var reports = parseInput(input);
        long validReports = 0;
        for (var report : reports) {
            if (isValid(report)) {
                validReports++;
            } else {
                for (int i = 0; i < report.size(); i++) {
                    var newReport = new ArrayList<>(report);
                    newReport.remove(i);
                    if (isValid(newReport)) {
                        validReports++;
                        break;
                    }
                }
            }
        }
        return validReports;
    }
}
