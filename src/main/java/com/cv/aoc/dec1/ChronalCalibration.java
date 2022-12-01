package com.cv.aoc.dec1;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// https://adventofcode.com/2018/day/1

public class ChronalCalibration {
    public static void main(String[] args) throws Exception {
        ChronalCalibration sol = new ChronalCalibration();
        Stream<String> inp = Files.lines(Paths.get("src/main/resources/2018_1.txt"));
        //Stream<String> inp = Stream.of(-6, +3, +8, +5, -6).map(String::valueOf);
        //System.out.println(sol.prob1(inp));
        System.out.println(sol.prob2(inp));
    }

    int prob1(Stream<String> inp) {
        return inp
                .map(Integer::parseInt)
                .reduce((i1, i2) -> i1 + i2)
                .orElse(Integer.MIN_VALUE);
    }

    int prob2(Stream<String> inp) {
        List<Integer> inpList = inp.map(Integer::parseInt).collect(Collectors.toList()); // So that we can keep processing this stream
        Set<Integer> seen = new HashSet<>();
        List<Integer> resultArr = new ArrayList<>();
        seen.add(0);
        int sum = 0;
        int runNumber = 1;
        while (!seen.contains(Integer.MIN_VALUE)) {
            Stream<Integer> inputStream = inpList.stream();
            final int prevSum = sum;
            final int thisRunNumber = runNumber++;
            sum = inputStream.collect(Collector.of(
                    () -> new int[]{prevSum},                           // Generator. 'Seed' accumulator
                    (acc, f) -> {                                       // Accumulator. Adding an element to an existing accumulator
                        if (!seen.contains(Integer.MIN_VALUE)) {
                            int r = acc[0] + f;
                            if (!seen.contains(r)) {
                                acc[0] = r;
                                seen.add(r);
                            } else {
                                seen.add(Integer.MIN_VALUE);            // Acts as a sentinel to stop calculation
                                System.out.println("Shorted at " + f + " for " + r + " on run number " + thisRunNumber);
                                resultArr.add(r);                       // Records final value when short circuited
                            }
                        }
                    },
                    (result1, result2) -> {                             // Combiner. Combining 2 accumulators
                        result1[0] += result2[0];
                        return result1;
                    },
                    total -> total[0]                                   // Finalizer. Transformation on the final accumulator
            ));

        }
        return resultArr.get(0);
    }
}