package com.cv.aoc;

public class Day4SecureContainer {
    public static void main(String[] args) {
        //System.out.println(Day4Solver.solve1(666777, 666777));
        System.out.println(Day4Solver.solve1(235741, 706948));
    }
}

class Day4Solver {
    static int solve1(int lo, int hi) {
        int validPasswords = 0;
        for (int pass = lo; pass <= hi; pass++) {
            if (validPassword(pass, lo, hi)) {
                System.out.println(pass);
                validPasswords++;
            }
        }
        return validPasswords;
    }

    private static boolean validPassword(int pass, int lo, int hi) {
        if (pass < lo || pass > hi) {
            return false;
        }
        char lastChar = '0';
        boolean repeatChars = false;
        char[] passChars = String.valueOf(pass).toCharArray();
        int index = 0;
        while (index < passChars.length) {
            char c = passChars[index];
            if (c < lastChar) {
                return false;
            }
            if (c == lastChar && !repeatChars) {
                int numReps = 1;
                while (index < passChars.length && passChars[index] == c) {
                    index++;
                    numReps++;
                }
                if (numReps == 2) {
                    repeatChars = true;
                }
                lastChar = passChars[index - 1];
            } else {
                lastChar = c;
                index++;
            }
        }
        return repeatChars;
    }
}
