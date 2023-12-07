package com.cv.aoc;

public class Dec03GearRatios {
    public int part01(char[][] input) {
        int r = input.length, c = input[0].length, result = 0;
        boolean admitIntoSum = false, parsingNumber = false;
        StringBuilder partNumber = new StringBuilder();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (Character.isDigit(input[i][j])) {
                    //A digit
                    if (!parsingNumber) {
                        parsingNumber = true;
                        partNumber = new StringBuilder();
                    }
                    partNumber.append(input[i][j]);
                    //If there's a symbol around this number, maybe mark for admission into sum
                    if (!admitIntoSum && symbolAround(input, i, j)) {
                        admitIntoSum = true;
                    }
                } else {
                    //A symbol. End parsing run, maybe add existing number to result
                    if (admitIntoSum) {
                        result += Integer.parseInt(partNumber.toString());
                    }
                    partNumber = new StringBuilder();
                    admitIntoSum = false;
                    parsingNumber = false;
                }
            }
            //End of row, maybe add to result and reset state
            if (parsingNumber) {
                if (admitIntoSum) {
                    result += Integer.parseInt(partNumber.toString());
                }
                partNumber = new StringBuilder();
                admitIntoSum = false;
                parsingNumber = false;
            }
        }
        return result;
    }

    private boolean symbolAround(char[][] grid, int r, int c) {
        int rows = grid.length, cols = grid[0].length;
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                int row = r + dr, col = c + dc;
                if (row < 0 || row >= rows || col < 0 || col >= cols) {
                    //out of bounds
                    continue;
                }
                if (grid[row][col] != '.' && !Character.isDigit(grid[row][col])) {
                    return true;
                }

            }
        }
        return false;
    }
}
