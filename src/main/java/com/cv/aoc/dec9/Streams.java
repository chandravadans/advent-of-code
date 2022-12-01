package com.cv.aoc.dec9;

import java.util.Scanner;

/**
 * Created by Chandravadan on 12/26/2017.
 */
public class Streams {

    public static void main(String[] args) throws Exception {
        Streams sol = new Streams();
        sol.prob1();
    }

    public void prob1() throws Exception {
        try (Scanner in = new Scanner(System.in)) {
            String line = in.nextLine();
            while (line.contains("!")) {
                line = line.replaceAll("!\\p{ASCII}", "");
            }
            //System.out.println(line);
            int totalScore = 0;
            int nxtScore = 0;
            boolean ignore = false;
            int numGarbage = 0;
            for (Character c : line.toCharArray()) {
                if (ignore) {
                    if (c != '>') {
                        numGarbage++;
                        continue;
                    } else {
                        ignore = false;
                    }
                }
                if (c == '{') {
                    nxtScore++;
                    totalScore += nxtScore;
                } else if (c == '}') {
                    nxtScore--;
                } else if (c == '<') {
                    ignore = true;
                }

            }
            System.out.println("Total = " + totalScore);
            System.out.println("Garbage = " + numGarbage);
            in.close();
        }
    }
}
