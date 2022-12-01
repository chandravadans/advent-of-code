package com.cv.aoc.dec3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SpiralMem {
    private List<Integer> squares;

    public SpiralMem() {
        squares = new ArrayList<>();
        for (int i = 0; i < 700; i++) {
            squares.add(i * i);
        }
    }

    public static void main(String[] args) throws Exception {

        SpiralMem sol = new SpiralMem();
        sol.prob1();

    }

    public void prob1() throws Exception {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = Collections.binarySearch(squares, n);
        int x = 0, y = 0, x0 = 0, y0 = 0;
        k = Math.abs(k) - 1;
        if (k % 2 == 0) {
            k = k + 1;
        }
        x0 = k / 2 + 1;
        y0 = k / 2 + 1;
        int bottomRight = k * k;
        int bottomLeft = bottomRight - (k - 1);
        int topLeft = bottomLeft - (k - 1);
        int topRight = topLeft - (k - 1);
        // Check if in bottom row
        if (n >= bottomLeft && n <= bottomRight) {
            y = k;
            x = Math.abs(n - bottomLeft) + 1;
            System.out.println(manhattanDist(x0, y0, x, y));
            return;
        }

        // Check if in

        in.close();
    }

    public void prob2() {
        // Checked in OEIS!
    }

    private int manhattanDist(int x0, int y0, int x, int y) {
        return (Math.abs(Math.abs(x0) - Math.abs(x)) + Math.abs(Math.abs(y0) - Math.abs(y)));
    }

}
