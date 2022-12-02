package com.cv.aoc;

import java.io.StringReader;
import java.util.Scanner;

public class Day02RockPaperScissors {

    private final String strategyGuide;

    public Day02RockPaperScissors(String strategyGuide) {
        this.strategyGuide = strategyGuide;
    }

    public int part1() {
        //r -> opponent, c-> user
        return scoreAccordingToStrategy(new int[][] {
                {(1 + 3), (2 + 6), (3 + 0)},
                {(1 + 0), (2 + 3), (3 + 6)},
                {(1 + 6), (2 + 0), (3 + 3)}
        });
    }

    public int part2() {
        //r -> opponent, c -> lose/draw/win
        return scoreAccordingToStrategy(new int[][] {
                {(3 + 0), (1 + 3), (2 + 6)},
                {(1 + 0), (2 + 3), (3 + 6)},
                {(2 + 0), (3 + 3), (1 + 6)}
        });
    }

    private int scoreAccordingToStrategy(int[][] pointsGuide) {
        try (Scanner in = new Scanner(new StringReader(strategyGuide))) {
            int score = 0;
            while (in.hasNext()) {
                String line = in.nextLine();
                score += pointsGuide[line.charAt(0) - 'A'][line.charAt(2) - 'X'];
            }
            return score;
        }
    }
}
