package com.cv.aoc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dec03MullItOver {

    long part01(String input) {
        input = input.replaceAll("\\)mul", ").mul");
        return mullItOver(input);
    }

    long part02(String input) {
        input = input.replaceAll("\\)mul", ").mul");
        while (input.contains("don't")) {
            int indexOfDont = input.indexOf("don't");
            int indexOfNextDo = input.indexOf("do()", indexOfDont);
            input = erase(input, indexOfDont, indexOfNextDo);
        }
        return mullItOver(input);
    }

    private static String erase(String s, int start, int end) {
        String result = s.substring(0, start);
        if (end == -1) {
            return result;
        } else {
            return result + s.substring(end);
        }
    }

    private static long mullItOver(String input) {
        Matcher mulMatcher = Pattern.compile("(mul\\(\\d+,\\d+\\))").matcher(input);
        long result = 0;
        while (mulMatcher.find()) {
            Pattern mulPattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
            Matcher mulArgsMatcher = mulPattern.matcher(mulMatcher.group());
            if (mulArgsMatcher.find()) {
                result += Long.parseLong(mulArgsMatcher.group(1)) * Long.parseLong(mulArgsMatcher.group(2));
            }
        }
        return result;
    }
}
