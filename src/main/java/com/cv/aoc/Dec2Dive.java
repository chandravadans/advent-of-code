package com.cv.aoc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Dec2Dive {
    private static final boolean READ_FROM_STDIN = false;

    public static void main(String[] args) throws IOException {
        DiveSolver solver = new DiveSolver();
        try (BufferedReader reader = new BufferedReader(
                READ_FROM_STDIN ?
                        new InputStreamReader(System.in, UTF_8) :
                        new FileReader("src/main/resources/2021_2.txt"))) {

            List<String> instructions = reader.lines().collect(Collectors.toList());

            assert solver.solve(instructions, false) == 1427868;
            assert solver.solve(instructions, true) == 1568138742;
        }
    }
}

class DiveSolver {
    int solve(List<String> instructions, boolean updateAim) {
        AtomicInteger position = new AtomicInteger(0),
                depth = new AtomicInteger(0),
                aim = new AtomicInteger(0);
        instructions.forEach(i -> {
            String[] parts = i.split(" ");
            int steps = Integer.parseInt(parts[1]);
            switch (parts[0]) {
                case "forward":
                    position.addAndGet(steps);
                    depth.addAndGet(updateAim ? aim.intValue() * steps : 0);
                    break;
                case "up":
                    depth.addAndGet(updateAim ? 0 : -steps);
                    aim.addAndGet(updateAim ? -steps : 0);
                    break;
                case "down":
                    depth.addAndGet(updateAim ? 0 : steps);
                    aim.addAndGet(updateAim ? steps : 0);
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected instruction: " + i);
            }
        });
        return position.intValue() * depth.intValue();
    }
}
