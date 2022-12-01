package com.cv.aoc.dec4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Passphrases {
    public static void main(String[] args) throws Exception {
        Passphrases sol = new Passphrases();
        sol.prob2();

    }
    public void prob1() throws Exception {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String line = in.readLine();
            int numValid = 0;
            while (line != null && line.length() > 0) {
                boolean valid = true;
                Set<String> alreadySeen = new HashSet<>();
                for (String pass : line.split("\\s+")) {
                    if (!alreadySeen.contains(pass)) {
                        alreadySeen.add(pass);
                    } else {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    numValid++;
                }
                line = in.readLine();
            }
            System.out.println(numValid);
        }
    }

    public void prob2() throws Exception {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String line = in.readLine();
            int numValid = 0;
            while (line != null && line.length() > 0) {
                boolean valid = true;
                String passwds[] = line.split("\\s+");
                for (int i = 0; i < passwds.length - 1; i++) {
                    for(int j = i+1; j < passwds.length; j++) {
                        if (areAnagrams(passwds[i], passwds[j])) {
                            valid = false;
                            break;
                        }
                    }
                }
                if (valid) {
                    numValid++;
                }
                line = in.readLine();
            }
            System.out.println(numValid);
        }
    }

    private boolean areAnagrams(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int hash1[] = new int[26];
        int hash2[] = new int[26];
        for(int i = 0; i < s1.length(); i++) {
            hash1[(int) s1.charAt(i) - 'a']++;
        }
        for(int i = 0; i < s2.length(); i++) {
            hash2[(int) s2.charAt(i) - 'a']++;
        }
        for(int i = 0; i < 26; i++) {
            if (hash1[i] != hash2[i]) {
                return false;
            }
        }
        return true;
    }
}
