package com.cv.aoc;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class Dec9SmokeBasin {
    public static void main(String[] args) throws Exception {
        SmokeBasinSolver solver = new SmokeBasinSolver();
        int MAX = 1024;
        int[][] map = new int[MAX][MAX];
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/2021_9.txt"))) {
            String line;
            line = reader.readLine();
            int lineNum = 0;
            int cols = 0;
            while(line != null) {
                int col = 0;
                cols = line.length();
                for(char c : line.toCharArray()) {
                    map[lineNum][col++] = c - '0';
                }
                lineNum++;
                line = reader.readLine();
            }
            System.out.println(solver.solve1(map, lineNum-1, cols));
        }
    }
}

class SmokeBasinSolver {
    public int solve1(int[][] map, int rows, int cols) {
        List<Point> directions = Arrays.asList(new Point(0, -1), new Point(-1, 0), new Point(0, 1), new Point(1,0));
        int res = 0;
        for(int r = 0; r < rows; r++) {
            for(int c = 0; c < cols; c++) {
                boolean isLowest = true;
                for(Point d : directions) {
                        int tr = (int) (r + d.getY());
                        int tc = (int) (c + d.getX());
                        if (tr >= 0 && tc >= 0 && tr < rows && tc < cols) {
                            if (map[r][c] > map[tr][tc]) {
                                isLowest = false;
                            }
                        }
                }
                if (isLowest) {
                    res += map[r][c] + 1;
                }
            }
        }
        return res;
    }
}