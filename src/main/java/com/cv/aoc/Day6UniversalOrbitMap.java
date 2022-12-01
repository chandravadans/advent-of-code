package com.cv.aoc;

import java.util.*;

public class Day6UniversalOrbitMap {
    public static void main(String[] args) {
        List<String> orbits = new ArrayList<>();
        try (Scanner in = new Scanner(System.in)) {
            while (in.hasNext()) {
                orbits.add(in.nextLine());
            }
        }
        System.out.println("Part A : " + Day6Solver.solve1(orbits));
        System.out.println("Part B : " + Day6Solver.solve2(orbits, "YOU", "SAN"));

    }
}

class Day6Solver {
    static Map<String, List<String>> directOrbits = new HashMap<>();

    static int solve1(List<String> orbits) {
        mapOrbits(orbits, false);
        int length = 0;
        for (String orbit : directOrbits.keySet()) {
            length += bfs(orbit, null);
        }
        return length;
    }

    static int solve2(List<String> orbits, String start, String target) {
        mapOrbits(orbits, true);
        return bfs(directOrbits.get(start).get(0), directOrbits.get(target).get(0));
    }

    private static void mapOrbits(List<String> orbits, boolean bidirectional) {
        for (String orbit : orbits) {
            String[] parts = orbit.split("\\)");
            directOrbits.putIfAbsent(parts[1], new ArrayList<>());
            directOrbits.get(parts[1]).add(parts[0]);
            if (bidirectional) {
                directOrbits.putIfAbsent(parts[0], new ArrayList<>());
                directOrbits.get(parts[0]).add(parts[1]);
            }
        }
    }

    private static int bfs(String node, String target) {
        Queue<String> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        Map<String, Integer> distances = new HashMap<>();
        int length = -1;
        q.offer(node);
        while (!q.isEmpty()) {
            node = q.poll();
            for (String neighbor : directOrbits.getOrDefault(node, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    distances.put(neighbor, distances.getOrDefault(node, 0) + 1);
                    q.offer(neighbor);
                    if(neighbor.equals(target)) {
                        return distances.getOrDefault(target, 0);
                    }
                }
            }
            length++;
        }
        return length;
    }
}