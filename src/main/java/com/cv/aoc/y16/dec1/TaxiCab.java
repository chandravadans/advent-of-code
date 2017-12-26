package com.cv.aoc.y16.dec1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class TaxiCab {
    public static void main(String[] args) throws Exception {
        TaxiCab sol = new TaxiCab();
        sol.prob2();
    }

    public void prob1() throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int x = 0;
        int y = 0;
        int current = 0;    // 0 - N, 1 - E, 2 - S, 3 - W
        String dirSym[] = new String[]{"N", "E", "S", "W"};
        String directionsLine = in.readLine();
        String[] directions = directionsLine.split(",");
        for (String dir : directions) {
            dir = dir.trim();
            int steps = Integer.parseInt(dir.substring(1));
            switch (current) {
                case 0:
                    if (dir.charAt(0) == 'L') {
                        current = 3;
                        x -= steps;
                        System.out.println(dir + ", x: " + x + ", y: " + y + " Current dir = " + dirSym[current]);
                    } else {
                        current = 1;
                        x += steps;
                        System.out.println(dir + ", x: " + x + ", y: " + y + " Current dir = " + dirSym[current]);
                    }
                    break;
                case 1:
                    if (dir.charAt(0) == 'L') {
                        current = 0;
                        y += steps;
                        System.out.println(dir + ", x: " + x + ", y: " + y + " Current dir = " + dirSym[current]);
                    } else {
                        current = 2;
                        y -= steps;
                        System.out.println(dir + ", x: " + x + ", y: " + y + " Current dir = " + dirSym[current]);
                    }
                    break;
                case 2:
                    if (dir.charAt(0) == 'L') {
                        current = 1;
                        x += steps;
                        System.out.println(dir + ", x: " + x + ", y: " + y + " Current dir = " + dirSym[current]);
                    } else {
                        current = 3;
                        x -= steps;
                        System.out.println(dir + ", x: " + x + ", y: " + y + " Current dir = " + dirSym[current]);
                    }
                    break;
                case 3:
                    if (dir.charAt(0) == 'L') {
                        current = 2;
                        y -= steps;
                        System.out.println(dir + ", x: " + x + ", y: " + y + " Current dir = " + dirSym[current]);
                    } else {
                        current = 0;
                        y += steps;
                        System.out.println(dir + ", x: " + x + ", y: " + y + " Current dir = " + dirSym[current]);
                    }
                    break;
            }
        }
        System.out.println(Math.abs(x) + Math.abs(y));
    }

    class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        @Override
        public boolean equals(Object that) {
            return ((that instanceof Point) && (this.x == ((Point) that).getX()) && (this.y == ((Point) that).getY()));
        }

        @Override
        public int hashCode() {
            return (this.x + "," + this.y).hashCode();
        }

        @Override
        public String toString() {
            return (this.x + "," + this.y);
        }
    }

    public void prob2() throws Exception {
        Set<Point> visited = new HashSet<>();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Point now = new Point(0, 0);
        int current = 0;    // 0 - N, 1 - E, 2 - S, 3 - W
        String dirSym[] = new String[]{"N", "E", "S", "W"};
        String directionsLine = in.readLine();
        String[] directions = directionsLine.split(",");
        boolean done = false;
        for (String dir : directions) {
            if (done) {
                break;
            }
            dir = dir.trim();
            int steps = Integer.parseInt(dir.substring(1));
            Point nxt = null;
            switch (current) {
                case 0:
                    if (dir.charAt(0) == 'L') {
                        current = 3;
                        nxt = new Point(now.getX() - steps, now.getY());
                        for(int x = nxt.getX(); x <= now.getX(); x++) {
                            visited.add(new Point(x, now.getY()));
                        }
                    } else {
                        current = 1;
                        nxt = new Point(now.getX() + steps, now.getY());
                        for(int x = now.getX(); x <= nxt.getX(); x++) {
                            visited.add(new Point(x, now.getY()));
                        }
                    }
                    break;
                case 1:
                    if (dir.charAt(0) == 'L') {
                        current = 0;
                        nxt = new Point(now.getX(), now.getY() + steps);
                        for(int y = now.getY(); y < nxt.getY(); y++) {
                            visited.add(new Point(now.getX(), y));
                        }
                    } else {
                        current = 2;
                        nxt = new Point(now.getX(), now.getY() - steps);
                        for(int y = nxt.getY(); y < now.getY(); y++) {
                            visited.add(new Point(now.getX(), y));
                        }
                    }
                    break;
                case 2:
                    if (dir.charAt(0) == 'L') {
                        current = 1;
                        nxt = new Point(now.getX() + steps, now.getY());
                    } else {
                        current = 3;
                        nxt = new Point(now.getX() - steps, now.getY());
                    }
                    break;
                case 3:
                    if (dir.charAt(0) == 'L') {
                        current = 2;
                        nxt = new Point(now.getX(), now.getY() - steps);
                    } else {
                        current = 0;
                        nxt = new Point(now.getX(), now.getY() + steps);
                    }
                    break;
            }
            System.out.println("Now = " + now + " Nxt = "+nxt);
            for (int x = now.getX(); Math.abs(x) <= Math.abs(nxt.getX()); x++) {
                for (int y = now.getY(); Math.abs(y) <= Math.abs(nxt.getY()); y++) {
                    Point p = new Point(x, y);
                    System.out.println("Checking " + p);
                    if (!visited.contains(p) && !p.equals(now)) {
                        visited.add(p);
                    } else {
                        System.out.println("Crossed!");
                        System.out.println(Math.abs(p.getX())+Math.abs(p.getY()));
                        return;
                    }
                }
            }
            now = nxt;
        }
        System.out.println(Math.abs(now.getX()) + Math.abs(now.getY()));
    }
}
