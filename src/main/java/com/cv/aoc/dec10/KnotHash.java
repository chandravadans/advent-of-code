package com.cv.aoc.dec10;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class KnotHash {
    public static void main(String[] args) {

        KnotHash sol = new KnotHash();

        //sol.prob1();
        sol.prob2();

    }


    public void prob1() {
        Scanner in = new Scanner(System.in);
        String line = in.next();
        List<Integer> nums = Arrays.stream(line.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        int max = 5;
        Integer[] arr = new Integer[max];
        for (int i = 0; i < max; i++) {
            arr[i] = i;
        }
        solve1(nums, new Point(0, 0), arr);
        System.out.println(arr[0] * arr[1]);
        in.close();
    }

    public void prob2() {
        Scanner in = new Scanner(System.in);
        String line = in.next();
        List<Integer> nums = new ArrayList<>();
        for (Character c : line.toCharArray()) {
            nums.add((int) c);
        }
        nums.addAll(Arrays.asList(17, 31, 73, 47, 23));
        Point state = new Point(0, 0);
        int max = 5;
        Integer[] arr = new Integer[max];
        for (int i = 0; i < max; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < 64; i++) {
            state = solve1(nums, state, arr);
        }
        int denseHash[] = new int[16];
        int denseHashIdx = 0;
        int denseHashVal = arr[0];
        for (int cell = 1; cell <= 256; cell++) {
            if (cell % 16 == 0) {
                denseHash[denseHashIdx] = denseHashVal;
                denseHashIdx++;
                denseHashVal = 0;
            } else {
                denseHashVal = denseHashVal ^ arr[cell];
            }
        }
        String res = "";
        for(int i = 0; i < denseHash.length; i++) {
            res = res + toHex(denseHash[i]);
        }
        System.out.println(res);
    }

    private Point solve1(List<Integer> nums, Point state, Integer[] arr) {
        //nums.addAll(Arrays.asList(17, 31, 73, 47, 23));
        int max = arr.length;
        int current = state.x;
        int skipSz = state.y;

        for (Integer num : nums) {
            int st = current;
            int en = circadd(st, num - 1, max);

            for (int numSwaps = 0; numSwaps < (num / 2); numSwaps++) {
                if (en < 0) {
                    en = max - Math.abs(en);
                }
                swap(st, en, arr);
                st = (st + 1) % max;
                en--;
            }
            current = circadd(current, num + skipSz, max);
            skipSz++;
        }
        return new Point(current, skipSz);
    }

    private void swap(int i, int j, Integer[] nums) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

    private int circadd(int num, int add, int max) {
        int dest = (num + add) % max;
        if (dest < 0) {
            dest = max - Math.abs(dest);
        }
        return dest;
    }

    private String toHex(int i) {
        String res = Integer.toHexString(i);
        if (res.length() == 1) {
            return "0"+res;
        } else {
            return res;
        }
    }

}
