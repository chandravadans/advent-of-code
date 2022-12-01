package com.cv.aoc;

public class Dec21DiracDice {
    public static void main(String[] args) {
        DiracDiceSolver s = new DiracDiceSolver();

        System.out.println(s.solve1(4, 2));
    }
}

class DiracDiceSolver {
    long solve1(int p1, int p2) {
        int p1Score = 0, p2Score = 0, dice = 0, p1Pos = p1, p2Pos = p2, numDieRolls = 0;
        do {
            for (int i = 1; i <= 6 && p1Score < 1000 && p2Score < 1000; i++) {
                dice = (dice + 1) % 100;
                dice = dice == 0 ? 100 : dice;
                numDieRolls++;
                if (i <= 3) {
                    p1Pos = (p1Pos + dice) % 10;
                    p1Pos = p1Pos == 0 ? 10 : p1Pos;
                    if (i == 3) {
                        p1Score += p1Pos;
                    }
                } else {
                    p2Pos = (p2Pos + dice) % 10;
                    p2Pos = p2Pos == 0 ? 10 : p2Pos;
                    if (i == 6) {
                        p2Score += p2Pos;
                    }
                }
            }
        } while (p1Score < 1000 && p2Score < 1000);
        return (long) (numDieRolls) * Math.min(p1Score, p2Score);
    }
}
