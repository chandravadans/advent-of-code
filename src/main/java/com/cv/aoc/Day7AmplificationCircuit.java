package com.cv.aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.max;

public class Day7AmplificationCircuit {
    public static void main(String[] args) {
        /*assert Day7Solver.solve("3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0") == 43210;
        assert Day7Solver.solve("3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0") == 54321;
        assert Day7Solver.solve("3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0") == 65210;
        assert Day7Solver.solve("3,8,1001,8,10,8,105,1,0,0,21,38,47,72,97,122,203,284,365,446,99999,3,9,1001,9,3,9,1002,9,5,9,1001,9,4,9,4,9,99,3,9,102,3,9,9,4,9,99,3,9,1001,9,2,9,102,5,9,9,101,3,9,9,1002,9,5,9,101,4,9,9,4,9,99,3,9,101,5,9,9,1002,9,3,9,101,2,9,9,102,3,9,9,1001,9,2,9,4,9,99,3,9,101,3,9,9,102,2,9,9,1001,9,4,9,1002,9,2,9,101,2,9,9,4,9,99,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,2,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,99,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,99,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,99") == 95757;*/

        System.out.println("------- PART B --------------");
        assert Day7Solver.solve2("3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5") == 139629729;
    }
}

class Day7Solver {

    static int solve(String program) {
        List<Integer> instructions = Arrays.stream(program.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        int maxOutput = MIN_VALUE;
        List<Integer> maxConfig = new ArrayList<>();
        for (int a = 0; a < 5; a++) {
            for (int b = 0; b < 5; b++) {
                for (int c = 0; c < 5; c++) {
                    for (int d = 0; d < 5; d++) {
                        for (int e = 0; e < 5; e++) {
                            if (allUnique(Arrays.asList(a, b, c, d, e))) {
                                int result = run(instructions, Arrays.asList(a, b, c, d, e), 0);
                                maxOutput = max(maxOutput, result);
                                maxConfig = Arrays.asList(a, b, c, d, e);
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Max config = " + maxConfig.stream().map(String::valueOf).collect(Collectors.joining(",")));
        System.out.println("Max output = " + maxOutput);
        return maxOutput;
    }

    static int solve2(String program) {
        List<Integer> instructions = Arrays.stream(program.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        int maxOutput = MIN_VALUE;
        //List<Integer> maxConfig = new ArrayList<>();
        List<Integer> maxConfig = Arrays.asList(9, 8, 7, 6, 5);
        /*for (int a = 0; a < 5; a++) {
            for (int b = 0; b < 6; b++) {
                for (int c = 0; c < 7; c++) {
                    for (int d = 0; d < 8; d++) {
                        for (int e = 0; e < 9; e++) {
                            if (allUnique(Arrays.asList(a, b, c, d, e))) {
                                int result = runWithFeedback(instructions, Arrays.asList(a, b, c, d, e));
                                maxOutput = max(maxOutput, result);
                                maxConfig = Arrays.asList(a, b, c, d, e);
                            }
                        }
                    }
                }
            }
        }*/
        maxOutput = runWithFeedback(instructions, maxConfig);
        System.out.println("Max config = " + maxConfig.stream().map(String::valueOf).collect(Collectors.joining(",")));
        System.out.println("Max output = " + maxOutput);
        return maxOutput;
    }


    private static int run(List<Integer> instructions, List<Integer> phaseSettings, int kickstart) {
        System.out.println("Running for phase settings " + phaseSettings.stream().map(String::valueOf).collect(Collectors.joining(",")));
        List<Integer> ops = new ArrayList<>();
        List<IntCodeComputer7> computers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            computers.add(new IntCodeComputer7(instructions));
        }
        for (int i = 0; i < 5; i++) {
            ops.add(computers.get(i).executeProgram(Arrays.asList(phaseSettings.get(i), i == 0 ? kickstart : ops.get(i - 1))).get(0));
            System.out.println("i : " + i + " output: " + ops.get(i));
        }
        return ops.get(4);
    }

    private static int runWithFeedback(List<Integer> instructions, List<Integer> phaseSettings) {
        System.out.println("Running for phase settings " + phaseSettings.stream().map(String::valueOf).collect(Collectors.joining(",")));
        List<Integer> ops = new ArrayList<>();
        List<IntCodeComputer7> computers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            computers.add(new IntCodeComputer7(instructions));
            ops.add(MAX_VALUE);
        }
        boolean[] halted = new boolean[5];
        List<Integer> output = computers.get(0).executeProgram(Arrays.asList(phaseSettings.get(0), 0));
        if (output.size() == 1) {
            ops.set(0, output.get(0));
        } else {
            halted[0] = true;
        }
        int i = 1;
        //Run as long as there's atleast one comp halted
        while (anyCompsHalted(halted)) {
            System.out.println("Setting memory for " + i);
            List<Integer> memory = i == 0 ? computers.get(4).getMemory() : computers.get(i - 1).getMemory();
            computers.get(i).setMemory(memory);
            System.out.println("Computing output for " + i);
            List<Integer> outputs = computers.get(i).executeProgram(Arrays.asList(phaseSettings.get(i), i == 0 ? ops.get(4) : ops.get(i - 1)));
            if (outputs.size() > 0) {
                ops.set(i, outputs.get(0));
                System.out.println("i : " + i + " output: " + ops.get(i));
                if (outputs.size() == 2) {
                    halted[i] = true;
                    System.out.println(i + " halted");
                } else {
                    halted[i] = false;
                    System.out.println(i + " finished!");
                }
            }
            i = (i + 1) % 5;
        }
        return ops.get(4);
    }

    private static boolean anyCompsHalted(boolean[] halted) {
        for(boolean b : halted) {
            if (b) {
                return true;
            }
        }
        return false;
    }


    private static boolean allUnique(List<Integer> integers) {
        int lo = Collections.min(integers);
        int[] hash = new int[integers.size()];
        integers.forEach(i -> hash[i - lo]++);
        for (int value : hash) {
            if (value != 1) {
                return false;
            }
        }
        return true;
    }
}

class IntCodeComputer7 {
    int inputPointer;
    int savedPc;
    private List<Integer> memory;
    private List<Integer> outputs;
    private List<Integer> inputs;

    public IntCodeComputer7(List<Integer> memory) {
        this.memory = memory;
        outputs = new ArrayList<>();
        outputs.add(MIN_VALUE);
    }

    public List<Integer> executeProgram(List<Integer> inputs) {
        if (this.inputs == null) {
            this.inputs = new ArrayList<>(inputs);
        } else {
            this.inputs.addAll(inputs);
        }
        if (outputs.size() > 1) {
            outputs.remove(1);
        }
        int pc = savedPc;
        int delta = 4;
        while (delta != MIN_VALUE) {
            delta = processOperation(pc);
            pc += delta;
        }
        return getOutputs();
    }

    private int processOperation(int pc) {
        if (memory.get(pc) == 99) {
            return MIN_VALUE;
        }
        String instructionOpCode = String.valueOf(memory.get(pc));
        int operation = memory.get(pc) > 99 ? Integer.parseInt(instructionOpCode.substring(instructionOpCode.length() - 2)) : memory.get(pc);
        switch (operation) {
            case 1:
            case 2:
                int[] addMulParams = extractParameters(pc, 2);
                memory.set(memory.get(pc + 3), operation == 1 ? addMulParams[0] + addMulParams[1] : addMulParams[0] * addMulParams[1]);
                return 4;
            case 3:
                if (inputPointer >= inputs.size() || inputs.get(inputPointer) == MAX_VALUE) {
                    System.out.println("No input available, saving pc and halting");
                    savedPc = pc;
                    outputs.add(MAX_VALUE);
                    return MIN_VALUE;
                }
                int input = inputs.get(inputPointer++);
                System.out.println("Read input #" + (inputPointer - 1));
                memory.set(memory.get(pc + 1), input);
                return 2;
            case 4:
                outputs.set(0, memory.get(memory.get(pc + 1)));
                return 2;
            case 5:
                int[] jitParams = extractParameters(pc, 2);
                return jitParams[0] != 0 ? jitParams[1] - pc : 3;
            case 6:
                int[] jifParams = extractParameters(pc, 2);
                return jifParams[0] == 0 ? jifParams[1] - pc : 3;
            case 7:
                int[] ltParams = extractParameters(pc, 2);
                memory.set(memory.get(pc + 3), ltParams[0] < ltParams[1] ? 1 : 0);
                return 4;
            case 8:
                int[] eqParams = extractParameters(pc, 2);
                memory.set(memory.get(pc + 3), eqParams[0] == eqParams[1] ? 1 : 0);
                return 4;
            default:
                throw new RuntimeException("Encountered unknown opcode " + memory.get(pc));
        }
    }

    private int[] extractParameters(int pc, int numParams) {
        String instructionOpCode = String.valueOf(memory.get(pc));
        String parameterModes = instructionOpCode.length() > 2 ? instructionOpCode.substring(0, instructionOpCode.length() - 2) : "";
        while (parameterModes.length() < numParams) {
            parameterModes = "0".concat(parameterModes);
        }
        int[] result = new int[numParams];
        for (int i = 0; i < numParams; i++) {
            result[i] = parameterModes.charAt(numParams - 1 - i) == '0' ? memory.get(memory.get(pc + 1 + i)) : memory.get(pc + 1 + i);
        }
        return result;
    }

    public List<Integer> getMemory() {
        return memory;
    }

    public void setMemory(List<Integer> memory) {
        this.memory = memory;
    }

    public List<Integer> getOutputs() {
        return outputs;
    }
}