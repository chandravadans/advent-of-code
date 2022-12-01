package com.cv.aoc;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.stream.Stream;

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
                {PROB_NUM.P1, of(12), 2},
                {PROB_NUM.P1, of(12, 14), 4},
                {PROB_NUM.P1, of(12, 14, 1969), 658},
                {PROB_NUM.P1, of(12, 14, 1969, 100756), 34241},
                {PROB_NUM.P2, of(1, 2, 3), 0},
                {PROB_NUM.P2, of(14), 2},
                {PROB_NUM.P2, of(14, 1969, 100756), 51314},
                {PROB_NUM.P2, of(1, 2, 3), 0},
                {PROB_NUM.P1, of(102480,121446,118935,54155,102510,142419,73274,57571,123916,99176,143124,141318,72224,145479,97027,126427,94990,100521,105589,123009,77143,142861,92366,66478,102195,128373,128447,120178,99122,98671,89541,125720,107984,126544,145231,110241,123926,72793,76705,128338,74262,68845,65297,112536,59892,57115,73230,80569,146118,108843,59221,140492,122616,140652,64404,99782,104375,86926,143145,114969,108948,77236,143655,71406,97588,64892,105345,104393,93442,54525,94116,123606,106813,59904,149253,81620,80892,66309,142604,97984,79743,79448,123756,64927,139703,71448,135964,86083,94767,116856,73786,141083,122581,82239,122282,96092,80029,52957,72062,52124), 3347838},
                {PROB_NUM.P2, of(102480,121446,118935,54155,102510,142419,73274,57571,123916,99176,143124,141318,72224,145479,97027,126427,94990,100521,105589,123009,77143,142861,92366,66478,102195,128373,128447,120178,99122,98671,89541,125720,107984,126544,145231,110241,123926,72793,76705,128338,74262,68845,65297,112536,59892,57115,73230,80569,146118,108843,59221,140492,122616,140652,64404,99782,104375,86926,143145,114969,108948,77236,143655,71406,97588,64892,105345,104393,93442,54525,94116,123606,106813,59904,149253,81620,80892,66309,142604,97984,79743,79448,123756,64927,139703,71448,135964,86083,94767,116856,73786,141083,122581,82239,122282,96092,80029,52957,72062,52124), 5018888}
        });
    }

    @Test
    public void test() {
        if (problemNumber == PROB_NUM.P1) {
            Assert.assertThat(Day1Solver.solve1(input), is(output));
        } else {
            Assert.assertThat(Day1Solver.solve2(input), is(output));
        }
    }
}