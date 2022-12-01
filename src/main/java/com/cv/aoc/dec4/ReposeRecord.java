package com.cv.aoc.dec4;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// https://adventofcode.com/2018/day/4

class ReposeRecord {

    Map<String, Long> idVsNaptime = new HashMap<>();
    Map<String, int[]> idVsNapFrequencyByMinute = new HashMap<>();

    public static void main(String[] args) throws Exception {
        ReposeRecord sol = new ReposeRecord();
        sol.prob1(Files.lines(Paths.get("src/main/resources/2018_4.txt")));
        sol.prob2();
    }

    void prob1(Stream<String> input) {
        List<String> sorted = input.sorted((l1, l2) -> {
            l1 = l1.substring(1, l1.indexOf("]"));
            l2 = l2.substring(1, l2.indexOf("]"));
            LocalDateTime d1 = LocalDateTime.parse(l1, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            LocalDateTime d2 = LocalDateTime.parse(l2, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            return d1.compareTo(d2);
        }).collect(Collectors.toList());

        String activeGuard = "";
        String checkpointLine = "";

        String laziestGuard = "";
        Long longestNap = 0L;
        for (String thisLine : sorted) {
            if (thisLine.contains("Guard")) {
                activeGuard = thisLine.substring(thisLine.indexOf('G'), thisLine.indexOf("b") - 1);
            }

            if (thisLine.contains("falls asleep")) {
                checkpointLine = thisLine;
            }

            if (thisLine.contains("wakes up")) {
                String st = checkpointLine.substring(0, checkpointLine.indexOf("]"));
                String en = thisLine.substring(0, thisLine.indexOf("]"));
                long asleepMin = numMin(st, en);

                idVsNaptime.computeIfPresent(activeGuard, (k, v) -> v + asleepMin);
                idVsNaptime.putIfAbsent(activeGuard, asleepMin);
                idVsNapFrequencyByMinute.putIfAbsent(activeGuard, new int[60]);


                if (idVsNaptime.get(activeGuard) > longestNap) {
                    longestNap = idVsNaptime.get(activeGuard);
                    laziestGuard = activeGuard;
                }

                long startMinute = parseString(st)[4];
                long endMinute = parseString(en)[4];
                int[] napFrequencyByMinute = idVsNapFrequencyByMinute.get(activeGuard);
                for (long i = startMinute; i < endMinute; i++) {
                    napFrequencyByMinute[(int) i]++;
                }
                checkpointLine = "";
            }
        }
        System.out.println("Prob 1: " + laziestGuard + " (" + longestNap + " min), favorite nap minute = " +
                           getMax(idVsNapFrequencyByMinute.get(laziestGuard))[0]);
    }

    // Max index, max val
    int[] getMax(int[] arr) {
        int[] res = new int[2];
        int max = Integer.MIN_VALUE;
        int maxIdx = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                maxIdx = i;
            }
        }
        res[0] = maxIdx;
        res[1] = max;
        return res;
    }

    void prob2() {
        String maxGuardId = "NULL";
        Integer maxMinuteFreq = Integer.MIN_VALUE;
        Integer maxMinute = 0;
        for (String id : idVsNapFrequencyByMinute.keySet()) {
            Integer maxFreqOfThisGuard = getMax(idVsNapFrequencyByMinute.get(id))[1];
            if (maxFreqOfThisGuard > maxMinuteFreq) {
                maxGuardId = id;
                maxMinuteFreq = maxFreqOfThisGuard;
                maxMinute = getMax(idVsNapFrequencyByMinute.get(id))[0];
            }
        }
        System.out.println("Prob 2: " + maxGuardId + ", fav minute = " + maxMinute);
    }

    // year,month,day,hour,minute
    int[] parseString(String input) {
        int res[] = new int[6];
        input = input.replace("[", "").replace("]", "");
        String[] l1Parts = input.split(" ");
        res[0] = Integer.parseInt(l1Parts[0].split("-")[0]);
        res[1] = Integer.parseInt(l1Parts[0].split("-")[1]);
        res[2] = Integer.parseInt(l1Parts[0].split("-")[2]);

        res[3] = Integer.parseInt(l1Parts[1].split(":")[0]);
        res[4] = Integer.parseInt(l1Parts[1].split(":")[1]);

        return res;
    }

    long numMin(String dateTime1, String dateTime2) {
        dateTime1 = dateTime1.replace("[", "").replace("]", "");
        dateTime2 = dateTime2.replace("[", "").replace("]", "");
        LocalDateTime d1 = LocalDateTime.parse(dateTime1, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime d2 = LocalDateTime.parse(dateTime2, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return ChronoUnit.MINUTES.between(d1, d2);
    }
}
