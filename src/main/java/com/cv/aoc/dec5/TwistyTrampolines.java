package com.cv.aoc.dec5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TwistyTrampolines {
    public static void main(String[] args) throws Exception {
        TwistyTrampolines sol = new TwistyTrampolines();
        sol.prob2();
    }

    public void prob1() throws Exception {
        List<Integer> instructions = new ArrayList<>();
        int instrPointer = 0;
        int numSteps = 0;

        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String instrStr = null;
            while ((instrStr = in.readLine()) != null && instrStr.length() > 0) {
                instructions.add(Integer.parseInt(instrStr));
            }
            System.out.println("Number of instructions = " + instructions.size());
        }
        int numInstr = instructions.size();
        while (instrPointer >= 0 && instrPointer < numInstr) {
            int jumpSz = instructions.get(instrPointer);
            instructions.set(instrPointer, jumpSz + 1);
            instrPointer += jumpSz;
            numSteps++;
        }
        System.out.println(numSteps);
    }

    public void prob2() throws Exception {
        List<Integer> instructions = new ArrayList<>();
        int instrPointer = 0;
        int numSteps = 0;

        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String instrStr = null;
            while ((instrStr = in.readLine()) != null && instrStr.length() > 0) {
                instructions.add(Integer.parseInt(instrStr));
            }
            //System.out.println("Number of instructions = " + instructions.size());
        }
        int numInstr = instructions.size();
        while (instrPointer >= 0 && instrPointer < numInstr) {
            int jumpSz = instructions.get(instrPointer);
            if (jumpSz >= 3) {
                instructions.set(instrPointer, jumpSz - 1);
            } else {
                instructions.set(instrPointer, jumpSz + 1);
            }
            instrPointer += jumpSz;
            numSteps++;
        }
        //System.out.println(instructions);
        System.out.println(numSteps);
    }
}
