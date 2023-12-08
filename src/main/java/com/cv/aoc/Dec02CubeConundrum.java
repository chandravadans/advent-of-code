package com.cv.aoc;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dec02CubeConundrum {

    private final Pattern gameIndexPattern = Pattern.compile("Game (?<index>\\d+)");
    private final Pattern outcomePattern = Pattern.compile(" (?<count>\\d+) (?<color>\\w+)");

    public int part01(List<String> gameSpecs) {
        return gameSpecs.stream()
                .map(gameSpec -> gameSpec.split(":"))
                .map(gameParts -> {
                    int gameIndex = -1;
                    Matcher gameIndexMatcher = gameIndexPattern.matcher(gameParts[0]);
                    if (gameIndexMatcher.matches()) {
                        gameIndex = Integer.parseInt(gameIndexMatcher.group("index"));
                    }

                    String[] games = gameParts[1].split(";");
                    int maxRed = Integer.MIN_VALUE, maxGreen = Integer.MIN_VALUE, maxBlue = Integer.MIN_VALUE;
                    for (String game : games) {
                        String[] outcomes = game.split(",");
                        for (String outcome : outcomes) {
                            Matcher outcomeMatcher = outcomePattern.matcher(outcome);
                            if (outcomeMatcher.matches()) {
                                switch (outcomeMatcher.group("color")) {
                                    case "red":
                                        maxRed = Math.max(maxRed, Integer.parseInt(outcomeMatcher.group("count")));
                                        break;
                                    case "green":
                                        maxGreen = Math.max(maxGreen, Integer.parseInt(outcomeMatcher.group("count")));
                                        break;
                                    case "blue":
                                        maxBlue = Math.max(maxBlue, Integer.parseInt(outcomeMatcher.group("count")));
                                        break;
                                    default:
                                        throw new IllegalArgumentException("Unknown color: " + outcomeMatcher.group("color"));
                                }
                            }
                        }
                    }
                    return (maxRed <= 12 &&  maxGreen <= 13 && maxBlue <= 14 ) ? gameIndex : 0;
                })
                .mapToInt(Integer::intValue)
                .sum();
    }

    public long part02(List<String> gameSpecs) {
        return gameSpecs.stream()
                .map(gameSpec -> gameSpec.split(":")[1].split(";"))
                .map(games -> {
                    int minBlue = Integer.MIN_VALUE, minRed = Integer.MIN_VALUE, minGreen = Integer.MIN_VALUE;
                    for (String game : games) {
                        String[] outcomes = game.split(",");
                        for (String outcome : outcomes) {
                            Matcher outcomeMatcher = outcomePattern.matcher(outcome);
                            if (outcomeMatcher.matches()) {
                                switch (outcomeMatcher.group("color")) {
                                    case "red":
                                        minRed = Math.max(minRed, Integer.parseInt(outcomeMatcher.group("count")));
                                        break;
                                    case "green":
                                        minGreen = Math.max(minGreen, Integer.parseInt(outcomeMatcher.group("count")));
                                        break;
                                    case "blue":
                                        minBlue = Math.max(minBlue, Integer.parseInt(outcomeMatcher.group("count")));
                                        break;
                                    default:
                                        throw new IllegalArgumentException("Unknown color: " + outcomeMatcher.group("color"));
                                }
                            }
                        }
                    }
                    return (long) (minRed != Integer.MAX_VALUE ? minRed : 1) *
                            (minGreen != Integer.MAX_VALUE ? minGreen : 1) *
                            (minBlue != Integer.MAX_VALUE ? minBlue : 1);
                })
                .mapToLong(Long::longValue)
                .sum();
    }
}
