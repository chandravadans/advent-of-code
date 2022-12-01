package com.cv.aoc.dec8;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Chandravadan on 12/26/2017.
 */
public class Registers {
    Map<String, Integer> registerValues;

    public Registers() {
        registerValues = new HashMap<>();
    }

    public static void main(String[] args) throws Exception {
        Registers sol = new Registers();
        sol.prob1();

    }

    public void prob1() throws Exception {
        try (Scanner in = new Scanner(System.in)) {

            String line = in.nextLine();
            int maxVal = Integer.MIN_VALUE;
            while (line != null && line.length() > 0) {
                String[] instr = line.split("\\s+");
                String regName = instr[0];
                String operation = instr[1];
                Integer val = Integer.parseInt(instr[2]);
                String ifCond = instr[3];
                String destRegister = instr[4];
                String destConditionOperator = instr[5];
                Integer destValue = Integer.parseInt(instr[6]);

                registerValues.putIfAbsent(destRegister, 0);
                registerValues.putIfAbsent(regName, 0);

                switch (destConditionOperator) {
                case ">":
                    if (registerValues.get(destRegister) > destValue) {
                        registerValues.put(regName, computeResult(registerValues.get(regName), operation, val));
                    }
                    break;
                case "<":
                    if (registerValues.get(destRegister) < destValue) {
                        registerValues.put(regName, computeResult(registerValues.get(regName), operation, val));
                    }
                    break;
                case "==":
                    if (registerValues.get(destRegister).equals(destValue)) {
                        registerValues.put(regName, computeResult(registerValues.get(regName), operation, val));
                    }
                    break;
                case "!=":
                    if (!registerValues.get(destRegister).equals(destValue)) {
                        registerValues.put(regName, computeResult(registerValues.get(regName), operation, val));
                    }
                    break;
                case ">=":
                    if (registerValues.get(destRegister) >= destValue) {
                        registerValues.put(regName, computeResult(registerValues.get(regName), operation, val));
                    }
                    break;
                case "<=":
                    if (registerValues.get(destRegister) <= destValue) {
                        registerValues.put(regName, computeResult(registerValues.get(regName), operation, val));
                    }
                    break;
                }
                System.out.println("After " + line);
                //System.out.println(gson.toJson(registerValues));
                if (registerValues.get(regName) > maxVal) {
                    maxVal = registerValues.get(regName);
                }
                line = in.nextLine();
            }
            System.out.println("Greatest value in the end = " + Collections.max(registerValues.values()));
            System.out.println("Greatest value ever = " + maxVal);
        }
    }

    private int computeResult(int operand1, String operator, int operand2) {
        if (operator.equals("inc")) {
            return operand1 + operand2;
        } else if (operator.equals("dec")) {
            return operand1 - operand2;
        } else {
            return operand1;
        }
    }
}
