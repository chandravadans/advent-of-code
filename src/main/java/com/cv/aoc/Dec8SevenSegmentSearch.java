package com.cv.aoc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class Dec8SevenSegmentSearch {
    public static void main(String[] args) throws Exception {
        SevenSegmentSearchSolver solver = new SevenSegmentSearchSolver();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/2021_8.txt"))) {
            System.out.println(solver.solve2(reader.lines().collect(Collectors.toList())));
        }
    }
}

class SevenSegmentSearchSolver {
    long solve1(List<String> lines) {
        long res = 0L;
        for (String line : lines) {
            String output = line.split("\\|")[1].trim();
            for (String o1 : output.split(" ")) {
                int len = o1.trim().length();
                if (len == 2 || len == 3 || len == 4 || len == 7) {
                    res++;
                }
            }
        }
        return res;
    }

    int solve2(List<String> lines) {
        int sum = 0;
        for (String line : lines) {
            String[] parts = line.split(" \\| ");
            String[] inputs = parts[0].split(" ");
            String[] outputs = parts[1].split(" ");
            String one = "", four = "";
            for (String input : inputs) {
                if (input.length() == 2) {
                    one = input;
                } else if (input.length() == 4) {
                    four = input;
                }
            }
            for (String output : outputs) {
                if (output.length() == 2) {
                    one = output;
                } else if (output.length() == 4) {
                    four = output;
                }
            }
            StringBuilder number = new StringBuilder();
            for (String output : outputs) {
                switch (output.length()) {
                    case 2:
                        number.append("1");
                        break;
                    case 3:
                        number.append("7");
                        break;
                    case 4:
                        number.append("4");
                        break;
                    case 7:
                        number.append("8");
                        break;
                    case 5:
                        if (intersect(output, one) == 2) {
                            number.append("3");
                        } else if (intersect(output, four) == 2) {
                            number.append("2");
                        } else {
                            number.append("5");
                        }
                        break;
                    case 6:
                        if (intersect(output, four) == 4) {
                            number.append("9");
                        } else if (intersect(output, one) == 2) {
                            number.append("0");
                        } else {
                            number.append("6");
                        }
                }
            }
            sum += Integer.parseInt(number.toString());
        }
        return sum;
    }

    private int intersect(String s, String t) {
        String s1 = s.length() < t.length() ? s : t;
        String s2 = s1.equals(s) ? t : s;
        int res = 0;
        for (char c : s1.toCharArray()) {
            for (char c1 : s2.toCharArray()) {
                res += c == c1 ? 1 : 0;
            }
        }
        return res;
    }
}