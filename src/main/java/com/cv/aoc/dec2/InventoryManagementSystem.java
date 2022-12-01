package com.cv.aoc.dec2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


// https://adventofcode.com/2018/day/2

public class InventoryManagementSystem {
    public static void main(String[] args) throws IOException {
        InventoryManagementSystem sol = new InventoryManagementSystem();
        Stream<String> in = Files.lines(Paths.get("src/main/resources/2018_2.txt"));
        //Stream<String> in = Stream.of("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab");
        //Stream<String> in = Stream.of("abcde", "fghij", "klmno", "pqrst", "fguij", "axcye", "wvxyz");
        //System.out.println(sol.prob1(in));
        System.out.println(sol.prob2(in));
    }

    public int prob1(Stream<String> inp) {
        Set<String> exactlyTwo = new HashSet<>();
        Set<String> exactlyThree = new HashSet<>();
        inp.forEach(id -> {
            int[] freq = countChars(id);
            Arrays.stream(freq)
                    .filter(x -> x == 2)
                    .forEach(r -> exactlyTwo.add(id));
            Arrays.stream(freq)
                    .filter(x -> x == 3)
                    .forEach(r -> exactlyThree.add(id));

        });
        return exactlyTwo.size() * exactlyThree.size();
    }

    private int[] countChars(String str) {
        int[] res = new int[26];
        str.chars().forEach(c -> res[c - 'a']++);
        return res;
    }

    public String prob2(Stream<String> inp) {
        List<String> results = new ArrayList<>();
        List<Integer> differingIdx = new ArrayList<>();
        List<String> self = inp.collect(Collectors.toList());
        self.forEach(id -> {
            if (!results.contains("DONE")) {
                self.forEach(id2 -> {
                    int differingIndex = differingIdx(id, id2);
                    if (differingIndex != -1) {
                        results.add(id);
                        results.add(id2);
                        results.add("DONE");
                        differingIdx.add(differingIndex);
                    }
                });
            }
        });
        return results.get(0).substring(0, differingIdx.get(0)) + results.get(0).substring(differingIdx.get(0) + 1);
    }

    int differingIdx(String s1, String s2) {
        List<Integer> differingIndices = IntStream.range(0, s1.length())
                .filter(i -> s1.charAt(i) != s2.charAt(i))
                .boxed()
                .collect(Collectors.toList());
        if (differingIndices.size() != 1) {
            return -1;
        } else {
            return differingIndices.get(0);
        }
    }
}
