package com.cv.aoc;

import java.util.List;

public class Dec04CeresSearch {

    private static final List<Direction> DIRECTIONS = List.of(
            new Direction("NW", -1, -1),
            new Direction("N", -1, 0),
            new Direction("NE", -1, 1),
            new Direction("E", 0, 1),
            new Direction("SE", 1, 1),
            new Direction("S", 1, 0),
            new Direction("SW", 1, -1),
            new Direction("W", 0, -1)
    );

    int part01(String input) {
        char[][] grid = parseInput(input);
        int r = grid.length, c = grid[0].length;
        int numOccurrences = 0;
        String word = "XMAS";
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                numOccurrences += search(grid, word, i, j);
            }
        }
        return numOccurrences;
    }

    int part02(String input) {
        char[][] grid = parseInput(input);
        int r = grid.length, c = grid[0].length;
        int numOccurrences = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (searchx(grid, i, j)) {
                    numOccurrences++;
                }
            }
        }
        return numOccurrences;
    }

    private static int search(char[][] grid, String word, int r, int c) {
        int rows = grid.length, cols = grid[0].length, wordLen = word.length();
        if (grid[r][c] != word.charAt(0)) {
            return 0;
        }
        int rowIdx, colIdx, matches = 0;
        for (Direction d : DIRECTIONS) {
            int wordIdx;
            rowIdx = r + d.dr();
            colIdx = c + d.dc();
            for (wordIdx = 1; wordIdx < wordLen; wordIdx++) {
                if (rowIdx < 0 || rowIdx >= rows ||
                        colIdx < 0 || colIdx >= cols) {
                    break;
                }
                if (grid[rowIdx][colIdx] != word.charAt(wordIdx)) {
                    break;
                }
                rowIdx = rowIdx + d.dr();
                colIdx = colIdx + d.dc();
            }
            if (wordIdx == wordLen) {
                matches++;
            }
        }
        return matches;
    }

    private static boolean searchx(char[][] grid, int r, int c) {
        int rows = grid.length, cols = grid[0].length;
        if (!(grid[r][c] == 'A' &&
                r <= rows - 2 && c <= cols - 2 &&
                r >= 1 && c >= 1)) {
            return false;
        }
        boolean leftLeg = grid[r - 1][c - 1] == 'M' && grid[r + 1][c + 1] == 'S' ||
                grid[r - 1][c - 1] == 'S' && grid[r + 1][c + 1] == 'M';

        boolean rightLeg = grid[r - 1][c + 1] == 'M' && grid[r + 1][c - 1] == 'S' ||
                grid[r - 1][c + 1] == 'S' && grid[r + 1][c - 1] == 'M';
        return leftLeg && rightLeg;
    }

    private static char[][] parseInput(String input) {
        String[] rows = input.split("\\n");
        int r = rows.length, c = rows[0].length();
        char[][] grid = new char[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                grid[i][j] = rows[i].charAt(j);
            }
        }
        return grid;
    }

    private record Direction(String name, int dr, int dc) {
    }

    private record Location(int r, int c) {
    }
}
