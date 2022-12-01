package com.cv.aoc.dec3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//https://adventofcode.com/2018/day/3

public class NoMatterHowYouSliceIt {

    enum STATUS {
        FREE,
        CLAIMED,
        OVERLAPPING
    }

    private STATUS[][] map = new STATUS[1007][1007];

    public static void main(String[] args) throws IOException {
        NoMatterHowYouSliceIt sol = new NoMatterHowYouSliceIt();
        /*Stream<String> inp = Stream.of("#1 @ 1,3: 4x4",
                "#2 @ 3,1: 4x4",
                "#3 @ 5,5: 2x2");*/
        System.out.println(sol.prob1(Files.lines(Paths.get("src/main/resources/2018_3.txt"))));
        System.out.println(sol.prob2(Files.lines(Paths.get("src/main/resources/2018_3.txt"))));
    }

    int prob1(Stream<String> inp) {
        int[] overlaps = {0};

        IntStream.range(0, map.length).forEach(i ->
                IntStream.range(0, map[0].length).forEach(j ->
                        map[i][j] = STATUS.FREE
                )
        );

        List<String> input = inp.collect(Collectors.toList());

        input.forEach(in -> {
            int[] parsed = parseInput(in);
            int x = parsed[0];
            int y = parsed[1];
            int xdim = parsed[2];
            int ydim = parsed[3];
            IntStream.range(x, x + xdim).forEach(i ->
                    IntStream.range(y, y + ydim).forEach(j -> {
                        if (map[i][j] == STATUS.FREE) {
                            map[i][j] = STATUS.CLAIMED;
                        } else if (map[i][j] == STATUS.CLAIMED) {
                            map[i][j] = STATUS.OVERLAPPING;
                            overlaps[0]++;
                        }

                    })
            );
        });
        return overlaps[0];
    }


    String prob2(Stream<String> inp) {
        List<String> res = new ArrayList<>();
        inp.forEach(in -> {
            // Find non overlapping claims
            int[] parsed = parseInput(in);
            int x = parsed[0];
            int y = parsed[1];
            int xdim = parsed[2];
            int ydim = parsed[3];
            final List<Boolean> overlappingClaim = new ArrayList<>();
            overlappingClaim.add(false);
            IntStream.range(x, x + xdim).forEach(i ->
                    IntStream.range(y, y + ydim).forEach(j -> {
                        if (!overlappingClaim.get(0)) {
                            if (map[i][j] == STATUS.OVERLAPPING) {
                                overlappingClaim.set(0, true);
                            }
                        }
                    }));
            if (!overlappingClaim.get(0)) {
                res.add(in);
            }
        });
        return res.get(0);
    }

    // returns x, y, xdim, ydim
    private int[] parseInput(String in) {
        int[] res = new int[4];
        String[] parts = in.split(" ");
        String coords[] = parts[2].substring(0, parts[2].length() - 1).split(",");
        String dims[] = parts[3].split("x");
        res[0] = Integer.parseInt(coords[0]);
        res[1] = Integer.parseInt(coords[1]);
        res[2] = Integer.parseInt(dims[0]);
        res[3] = Integer.parseInt(dims[1]);
        return res;
    }
}
