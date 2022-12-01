package com.cv.aoc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Dec4GiantSquid {
    private static final boolean READ_FROM_STDIN = false;

    public static void main(String[] args) throws IOException {
        GiantSquidSolver solver = new GiantSquidSolver();

        try (BufferedReader reader = new BufferedReader(
                READ_FROM_STDIN ?
                        new InputStreamReader(System.in, UTF_8) :
                        new FileReader("src/main/resources/2021_4.txt"))) {

            String bingoNumbersInput = reader.readLine();
            


        }
    }
}

class GiantSquidSolver {

    private class BingoBoard {
        private final int[][] board;
        private final int BOARD_SIZE = 5;
        private final boolean[][] state = new boolean[BOARD_SIZE][BOARD_SIZE];

        public BingoBoard(int[][] board) {
            this.board = board;
        }

        public void markOff(int number) {
            for (int r = 0; r < BOARD_SIZE; r++) {
                for (int c = 0; c < BOARD_SIZE; c++) {
                    if (board[r][c] == number) {
                        state[r][c] = true;
                    }
                }
            }
        }

        public boolean isWinner() {
            for(int i = 0; i < BOARD_SIZE; i++) {
                if(isRowDone(i) || isColDone(i)) {
                    return true;
                }
            }
            return false;
        }

        private boolean isRowDone(int r) {
            for (int c = 0; c < BOARD_SIZE; c++) {
                if (!state[r][c]) {
                    return false;
                }
            }
            return true;
        }

        private boolean isColDone(int c) {
            for (int r = 0; r < BOARD_SIZE; r++) {
                if (!state[r][c]) {
                    return false;
                }
            }
            return true;
        }
    }
}
