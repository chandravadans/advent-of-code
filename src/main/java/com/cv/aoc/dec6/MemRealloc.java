package com.cv.aoc.dec6;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Chandravadan on 12/10/2017.
 */
public class MemRealloc {
    public static void main(String[] args) throws Exception {
        MemRealloc sol = new MemRealloc();
        sol.prob1();
    }

    public void prob1() throws Exception {
        try (Scanner in = new Scanner(System.in)) {
            String input = in.nextLine();
            String memBlocks[] = input.split("\\s+");
            List<Integer> memory = new ArrayList<>();
            Set<String> visited = new HashSet<>();
            Map<String, Integer> visitedIndexes = new HashMap<>();
            for(String mem : memBlocks) {
                memory.add(Integer.parseInt(mem.trim()));
            }
            visited.add(memory.stream().map(String::valueOf).collect(Collectors.joining(",")));
            int numSteps = 1;
            int loopLength = 0;
            boolean done = false;
            while(!done) {
                int maxIndex = memory.indexOf(Collections.max(memory));
                redistribute(memory, maxIndex);
                String thisConfig = memory.stream().map(String::valueOf).collect(Collectors.joining(","));
                System.out.println(thisConfig);
                if (!visited.contains(thisConfig)) {
                    visited.add(thisConfig);
                    visitedIndexes.put(thisConfig, numSteps);
                    numSteps++;
                } else {
                    done = true;
                    loopLength = (numSteps) - visitedIndexes.get(thisConfig);
                }
            }
            System.out.println(numSteps + ", "+ loopLength);
        }
    }

    private void redistribute(List<Integer> memoryBlocks, int startIndex) {
        int remaining = memoryBlocks.get(startIndex);
        memoryBlocks.set(startIndex, 0);
        int numBlocks = memoryBlocks.size();
        int pointer = (startIndex + 1)%numBlocks;
        while(remaining != 0) {
            memoryBlocks.set(pointer, memoryBlocks.get(pointer) + 1);
            pointer = (pointer+1)%numBlocks;
            remaining--;
        }
    }

    public void prob2() throws Exception {

    }
}
