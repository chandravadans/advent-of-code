package com.cv.aoc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.toList;

public class Dec3BinaryDiagnostic {
    private static final boolean READ_FROM_STDIN = false;

    public static void main(String[] args) throws IOException {
        BinaryDiagnosticSolver binaryDiagnosticSolver = new BinaryDiagnosticSolver();
        try (BufferedReader reader = new BufferedReader(
                READ_FROM_STDIN ?
                        new InputStreamReader(System.in, UTF_8) :
                        new FileReader("src/main/resources/2021_3.txt"))) {

            List<String> binaryNumbers = reader.lines().collect(toList());

            assert binaryDiagnosticSolver.powerConsumption(binaryNumbers) == 4139586;
            assert binaryDiagnosticSolver.lifeSupportRating(binaryNumbers) == 1800151;
        }
    }
}

class BinaryDiagnosticSolver {

    int powerConsumption(List<String> binaryNumbers) {
        int numberLength = binaryNumbers.get(0).length();
        StringBuilder gamma = new StringBuilder(), epsilon = new StringBuilder();
        for (int bit = 0; bit < numberLength; bit++) {
            gamma.append(significantBit(binaryNumbers, bit, true, '0'));
            epsilon.append(significantBit(binaryNumbers, bit, false, '0'));
        }
        return parseInt(gamma.toString(), 2) * parseInt(epsilon.toString(), 2);
    }

    int lifeSupportRating(List<String> binaryNumbers) {
        return parseInt(lifeSupportRating(binaryNumbers, 0, true), 2) *
                parseInt(lifeSupportRating(binaryNumbers, 0, false), 2);
    }

    private String lifeSupportRating(List<String> binaryNumbers, int bitNumber, boolean o2Rating) {
        if (binaryNumbers.size() == 1) {
            return binaryNumbers.get(0);
        } else {
            char bitCriterion = significantBit(binaryNumbers, bitNumber, o2Rating, o2Rating ? '1' : '0');
            int finalBitNumber = bitNumber;
            return lifeSupportRating(binaryNumbers.stream()
                            .filter(n -> n.charAt(finalBitNumber) == bitCriterion)
                            .collect(toList()),
                    ++bitNumber,
                    o2Rating);
        }
    }

    private char significantBit(List<String> binaryNumbers, int bitNumber,
                                boolean returnDominant, char tieBreaker) {
        int zeros = 0, ones = 0;
        for (String number : binaryNumbers) {
            if (number.charAt(bitNumber) == '0') {
                zeros++;
            } else {
                ones++;
            }
        }
        if (zeros == ones) {
            return tieBreaker;
        }
        if (returnDominant) {
            return zeros > ones ? '0' : '1';
        } else {
            return zeros < ones ? '0' : '1';
        }
    }
}
