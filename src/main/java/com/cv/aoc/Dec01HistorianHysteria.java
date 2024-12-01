package com.cv.aoc;

import java.util.*;
import java.util.stream.Collectors;

public class Dec01HistorianHysteria {

    int part01(String input) {
        Queue<Integer> left = new PriorityQueue<>(), right = new PriorityQueue<>();
        Arrays.stream(input.split("\\n")).forEach(line -> {
            String[] locations = line.split("\\s+");
            left.offer(Integer.parseInt(locations[0]));
            right.offer(Integer.parseInt(locations[1]));
        });
        int result = 0;
        while (!left.isEmpty() && !right.isEmpty()) {
            result += Math.abs(left.poll() - right.poll());
        }
        return result;
    }

    int part02(String input) {
        List<Integer> left = new ArrayList<>();
        Map<Integer, Integer> countByRight = new HashMap<>();
        Arrays.stream(input.split("\\n")).forEach(line -> {
            String[] parts = line.split("\\s+");
            left.add(Integer.parseInt(parts[0]));
            int right = Integer.parseInt(parts[1]);
            countByRight.compute(right, (k, v) -> v == null ?
                    1 :
                    v + 1);
        });
        return left.stream()
                .mapToInt(l -> l * countByRight.getOrDefault(l, 0))
                .sum();
    }
}
