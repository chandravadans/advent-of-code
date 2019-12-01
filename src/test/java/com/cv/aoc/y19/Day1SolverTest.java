package com.cv.aoc.y19;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.stream.Stream;

import static com.cv.aoc.y19.Day1SolverTest.PROB_NUM.P1;
import static com.cv.aoc.y19.Day1SolverTest.PROB_NUM.P2;
import static java.util.Arrays.asList;
import static java.util.stream.Stream.of;
import static org.hamcrest.Matchers.is;

@RunWith(Parameterized.class)
public class Day1SolverTest {
    enum PROB_NUM {P1, P2}

    @Parameterized.Parameter
    public PROB_NUM problemNumber;

    @Parameterized.Parameter(1)
    public Stream<Integer> input;

    @Parameterized.Parameter(2)
    public int output;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return asList(new Object[][]{
                {P1, of(12), 2},
                {P1, of(12, 14), 4},
                {P1, of(12, 14, 1969), 658},
                {P1, of(12, 14, 1969, 100756), 34241},
                {P2, of(14), 2},
                {P2, of(14, 1969, 100756), 51314}
        });
    }

    @Test
    public void test() {
        if (problemNumber == P1) {
            Assert.assertThat(Day1Solver.solve1(input), is(output));
        } else {
            Assert.assertThat(Day1Solver.solve2(input), is(output));
        }
    }
}