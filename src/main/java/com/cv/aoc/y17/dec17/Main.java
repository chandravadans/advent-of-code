package com.cv.aoc.y17.dec17;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Chandravadan on 12/17/2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Main sol = new Main();
        sol.prob1();

    }

    public void prob1() throws Exception {
        try(Scanner in = new Scanner(System.in)) {
            Integer numSteps = in.nextInt();
            int curr = 0;
            int numTimes = 0;
            int ptr = 1;
            Integer buff[] = new Integer[2017];
            buff[0] = curr;
            curr++;
            int sz = 1;
            while (numTimes != 10) {
                System.out.println("sz = " + sz);
                System.out.println("ptr = " + ptr);
                for(int i = 0; i < numSteps; i++) {
                    ptr = (ptr + 1)%(sz+1);
                }
                for(int i = sz ; i > ptr ; i--) {
                    buff[i] = buff[i-1];
                }
                buff[ptr + 1] = curr;
                System.out.println("New ptr = " + ptr);
                curr++;
                numTimes++;
                sz++;
                System.out.println(StringUtils.join(buff, ", ") + "\n");
            }
            in.close();
        }
    }



}
