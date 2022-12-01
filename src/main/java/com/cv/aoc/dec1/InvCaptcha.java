package com.cv.aoc.dec1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// http://adventofcode.com/2017/day/1
public class InvCaptcha {
    public static void main(String[] args) throws Exception {
        InvCaptcha sol = new InvCaptcha();
        sol.prob2();

    }

    public void prob1() throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String digits = in.readLine();
        int len = digits.length();
        int sum = 0;
        for (int i = 0; i < len; i++) {
            int thisDig = (int) digits.charAt(i) - '0';
            int nextDig = (int) digits.charAt(i == len - 1 ? 0 : i + 1) - '0';
            if (thisDig == nextDig) {
                sum += thisDig;
            }
        }
        System.out.println(sum);
        in.close();
    }

    public void prob2() throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String digits = in.readLine();
        int len = digits.length();
        int sum = 0;
        for (int i = 0; i < len; i++) {
            int thisDig = (int) digits.charAt(i) - '0';
            int destination = (i + (len / 2)) % len;
            int nextDig = (int) digits.charAt(destination) - '0';
            if (thisDig == nextDig) {
                sum += thisDig;
            }
        }
        System.out.println(sum);
        in.close();
    }
}
