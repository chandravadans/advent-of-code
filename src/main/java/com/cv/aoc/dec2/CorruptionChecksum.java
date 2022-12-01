package com.cv.aoc.dec2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CorruptionChecksum {
    public static void main(String[] args) throws Exception {
        CorruptionChecksum c = new CorruptionChecksum();
        c.prob2();

    }

    public void prob1() throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String digits = in.readLine();
        Integer result = 0;
        while (digits != null && digits.length() > 0) {
            int max = max(digits);
            int min = min(digits);
            result += (max - min);
            digits = in.readLine();
        }
        System.out.println(result);
    }

    private int max(String numbers) {
        int max = Integer.MIN_VALUE;
        for (String s : numbers.split("\\s+")) {
            Integer i = Integer.parseInt(s.trim());
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    private int min(String numbers) {
        int min = Integer.MAX_VALUE;
        for (String s : numbers.split("\\s+")) {
            Integer i = Integer.parseInt(s.trim());
            if (i < min) {
                min = i;
            }
        }
        return min;
    }

    public void prob2() throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String digits = in.readLine();
        Integer result = 0;
        while (digits != null && digits.length() > 0) {
            result += sumOfEvenlyDivisible(digits);
            digits = in.readLine();
        }
        System.out.println(result);
    }

    private int sumOfEvenlyDivisible(String numbers) throws Exception {
        String numStrings[] = numbers.split("\\s+");
        List<Integer> nums = new ArrayList<>();
        int result = 0;
        for (String numStr : numStrings) {
            nums.add(Integer.parseInt(numStr.trim()));
        }
        Collections.sort(nums, (i1, i2) -> i2.compareTo(i1));

        int size = nums.size();
        for(int i = 0; i < size - 1; i++) {
            for(int j = i + 1; j < size; j++) {
                if (nums.get(i) % nums.get(j) == 0) {
                    result += (nums.get(i)/nums.get(j));
                    break;
                }
            }
        }
        return result;
    }
}
