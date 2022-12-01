package com.cv.aoc.dec7;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Chandravadan on 12/14/2017.
 */
public class RecCircus {
    private Map<String, Integer> weights;
    private Map<String, String> parents;
    private Map<String, Integer> finalWeights;
    private Map<String, List<String>> tower;
    Set<String> progNames;

    public RecCircus() {
        weights = new HashMap<>();
        progNames = new HashSet<>();
        parents = new HashMap<>();
        finalWeights = new HashMap<>();
        tower = new HashMap<>();
    }
    public static void main(String[] args) throws Exception {
        RecCircus sol = new RecCircus();
        sol.prob1();
    }

    public void prob1() throws Exception {

        try (Scanner in = new Scanner(System.in)) {
            String line = in.nextLine();
            while(line != null && line.length() > 0) {
                String parts[] = line.split("->");
                String progName = parts[0].split("\\s+")[0].trim();
                Integer progWeight = Integer.parseInt(parts[0].split("\\s+")[1].replace('(',' ').replace(')',' ').trim());

                weights.put(progName, progWeight);
                finalWeights.put(progName, progWeight);
                progNames.add(progName);

                if (!tower.containsKey(progName)) {
                    tower.put(progName, new ArrayList<>());
                }

                // Check if it has children
                if (parts.length > 1) {
                    String adjList[] = parts[1].split(",");
                    for (String neighbor : adjList) {
                        neighbor = neighbor.trim();
                        tower.get(progName).add(neighbor);

                        // Update parent
                        parents.put(neighbor, progName);

                        progNames.add(progName);
                    }
                }
                line = in.nextLine();
            }

            // Node with no parent is the root
            List<String> roots = progNames
                .stream()
                .filter(x -> !parents.keySet().contains(x))
                .collect(Collectors.toList());

            System.out.println(roots.get(0));

            // Update final weights
            progNames.stream().forEach(progName -> {
                //System.out.println("Processing " + progName);
               if (!finalWeights.containsKey(progName)) {
                   finalWeights.put(progName, weights.get(progName));
               }
                int progWeight = finalWeights.get(progName);
               String parentProg = parents.get(progName);
                while (parentProg != null) {
                    int parentProgWeight = finalWeights.containsKey(parentProg)
                                                        ?finalWeights.get(parentProg)
                                                        :weights.get(parentProg);
                    finalWeights.put(parentProg, parentProgWeight + progWeight);
                    parentProg = parents.get(parentProg);
                }
            });
            unbalanced(roots.get(0));
        }
    }

    private void unbalanced (String root) {
        Deque<String> q = new ArrayDeque<>();
        q.push(root);
        while (!q.isEmpty()) {
            String tmp = q.poll();
            int childsEqualWeight = Integer.MIN_VALUE;
            String idealChild = "";
            for (String child : tower.get(tmp)) {
                System.out.println("Checking " + child);
                if (childsEqualWeight == Integer.MIN_VALUE) {
                    childsEqualWeight = finalWeights.get(child);
                    idealChild = child;
                } else {
                    if (finalWeights.get(child) != childsEqualWeight) {
                        System.out.println("Discrepancy : " + child + " -> " + finalWeights.get(child) + "; " + idealChild + " -> " + childsEqualWeight);
                        q.offer(child);
                        q.offer(idealChild);
                        break;
                        //System.out.println("Delta = " + Math.abs(finalWeights.get(child) - childsEqualWeight));
                        //return;
                    }
                }
                //q.offer(child);
            }
            System.out.println("Finished checking " + tmp);
        }
    }
}
