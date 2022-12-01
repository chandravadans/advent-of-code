package com.cv.aoc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Dec10SyntaxScoring {
    private static final boolean REAL_INPUT = true;

    public static void main(String[] args) throws Exception {
        SyntaxScoringSolver solver = new SyntaxScoringSolver();
        if (REAL_INPUT) {
            try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/2021_10.txt"))) {
                List<String> lines = reader.lines().collect(Collectors.toList());
                assert solver.solve1(lines) == 319233 ;
                assert solver.solve2(lines) == 1118976874;
            }
        } else {
            String input = "[({(<(())[]>[[{[]{<()<>>\n" +
                    "[(()[<>])]({[<{<<[]>>(\n" +
                    "{([(<{}[<>[]}>{[]{[(<()>\n" +
                    "(((({<>}<{<{<>}{[]{[]{}\n" +
                    "[[<[([]))<([[{}[[()]]]\n" +
                    "[{[{({}]{}}([{[{{{}}([]\n" +
                    "{<[[]]>}<{[{[{[]{()[[[]\n" +
                    "[<(<(<(<{}))><([]([]()\n" +
                    "<{([([[(<>()){}]>(<<{{\n" +
                    "<{([{{}}[<[[[<>{}]]]>[]]";
            assert solver.solve1(new ArrayList<>(Arrays.asList(input.split("\n")))) == 319233;
            assert solver.solve2(new ArrayList<>(Arrays.asList(input.split("\n")))) == 288957;
        }

    }
}

class SyntaxScoringSolver {
    int solve1(List<String> lines) {

        List<Character> openings = Arrays.asList('(', '<', '{', '[');
        List<Character> closings = Arrays.asList(')', '>', '}', ']');
        List<Integer> scores = Arrays.asList(3, 25137, 1197, 57);

        Integer totalErrorScore = 0;
        for (String line : lines) {
            Stack<Character> braceStack = new Stack<>();
            for (Character c : line.toCharArray()) {
                if (openings.contains(c)) {
                    braceStack.push(c);
                } else {
                    Character opening = openings.get(closings.indexOf(c));
                    if (braceStack.peek() == opening) {
                        braceStack.pop();
                    } else {
                        totalErrorScore += scores.get(closings.indexOf(c));
                        break;
                    }
                }
            }
        }
        return totalErrorScore;
    }

    long solve2(List<String> lines) {
        List<Long> scores = lines.stream()
                .filter(this::isPristine)
                .map(this::getCompletionString)
                .filter(c -> !c.isEmpty())
                .map(this::scoreCompletionString)
                .sorted()
                .collect(Collectors.toList());
        return scores.get(scores.size() / 2);
    }

    private boolean isPristine(String line) {
        List<Character> openings = Arrays.asList('(', '<', '{', '[');
        List<Character> closings = Arrays.asList(')', '>', '}', ']');

        Stack<Character> braceStack = new Stack<>();
        for (Character c : line.toCharArray()) {
            if (openings.contains(c)) {
                braceStack.push(c);
            } else {
                Character opening = openings.get(closings.indexOf(c));
                if (braceStack.peek() == opening) {
                    braceStack.pop();
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    private String getCompletionString(String line) {
        List<Character> openings = Arrays.asList('(', '<', '{', '[');
        List<Character> closings = Arrays.asList(')', '>', '}', ']');

        Stack<Character> braceStack = new Stack<>();
        for (Character c : line.toCharArray()) {
            if (openings.contains(c)) {
                braceStack.push(c);
            } else {
                Character opening = openings.get(closings.indexOf(c));
                if (braceStack.peek() == opening) {
                    braceStack.pop();
                } else {
                    throw new IllegalArgumentException("[" + line + "] : line is corrupt");
                }
            }
        }
        StringBuilder result = new StringBuilder();
        while (!braceStack.isEmpty()) {
            result.append(closings.get(openings.indexOf(braceStack.pop())));
        }
        return result.toString();
    }

    private long scoreCompletionString(String line) {
        long totalScore = 0;
        Map<Character, Integer> scores = new HashMap<>();
        scores.put(')', 1);
        scores.put(']', 2);
        scores.put('}', 3);
        scores.put('>', 4);
        for (Character c : line.toCharArray()) {
            totalScore = totalScore * 5;
            totalScore += scores.get(c);
        }
        return totalScore;
    }
}
