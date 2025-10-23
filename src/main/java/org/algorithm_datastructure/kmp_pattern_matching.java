package org.algorithm_datastructure;

import java.util.*;

/**
 * KMP (Knuth-Morris-Pratt) String Matching Algorithm
 *
 * An efficient string matching algorithm that avoids unnecessary comparisons
 * by using information from previous matches.
 *
 * Algorithm:
 * 1. Preprocess pattern to build LPS (Longest Proper Prefix which is also Suffix) array
 * 2. Use LPS array to skip characters while matching
 *
 * Time Complexity: O(n + m) where n is text length, m is pattern length
 * Space Complexity: O(m) for LPS array
 *
 * Advantages over naive approach:
 * - Never backtracks in text
 * - Linear time complexity
 * - Efficient for large texts
 *
 * Applications:
 * - Text editors (find and replace)
 * - Plagiarism detection
 * - DNA sequence matching
 * - Intrusion detection systems
 */
public class kmp_pattern_matching {

    /**
     * Builds the LPS (Longest Proper Prefix which is also Suffix) array
     * Time Complexity: O(m) where m is pattern length
     * @param pattern Pattern string
     * @return LPS array
     */
    private static int[] computeLPSArray(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0;  // Length of previous longest prefix suffix
        int i = 1;

        lps[0] = 0;  // lps[0] is always 0

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }

    /**
     * Searches for pattern in text using KMP algorithm
     * Time Complexity: O(n + m)
     * @param text Text to search in
     * @param pattern Pattern to search for
     * @return List of starting indices where pattern is found
     */
    public static List<Integer> kmpSearch(String text, String pattern) {
        List<Integer> matches = new ArrayList<>();

        if (pattern.isEmpty() || text.isEmpty() || pattern.length() > text.length()) {
            return matches;
        }

        int n = text.length();
        int m = pattern.length();

        // Preprocess pattern
        int[] lps = computeLPSArray(pattern);

        int i = 0;  // Index for text
        int j = 0;  // Index for pattern

        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }

            if (j == m) {
                // Pattern found at index (i - j)
                matches.add(i - j);
                j = lps[j - 1];
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                // Mismatch after j matches
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return matches;
    }

    /**
     * Finds first occurrence of pattern in text
     * Time Complexity: O(n + m)
     * @param text Text to search in
     * @param pattern Pattern to search for
     * @return Index of first match, or -1 if not found
     */
    public static int kmpSearchFirst(String text, String pattern) {
        List<Integer> matches = kmpSearch(text, pattern);
        return matches.isEmpty() ? -1 : matches.get(0);
    }

    /**
     * Counts occurrences of pattern in text
     * Time Complexity: O(n + m)
     * @param text Text to search in
     * @param pattern Pattern to search for
     * @return Number of occurrences
     */
    public static int kmpCount(String text, String pattern) {
        return kmpSearch(text, pattern).size();
    }

    /**
     * Checks if pattern exists in text
     * Time Complexity: O(n + m)
     * @param text Text to search in
     * @param pattern Pattern to search for
     * @return true if pattern exists, false otherwise
     */
    public static boolean kmpContains(String text, String pattern) {
        return kmpSearchFirst(text, pattern) != -1;
    }

    public static void main(String[] args) {
        System.out.println("KMP String Matching Algorithm Demo\n");

        // Test 1: Basic pattern matching
        System.out.println("Test 1: Basic Pattern Matching");
        String text1 = "ABABDABACDABABCABAB";
        String pattern1 = "ABABCABAB";

        System.out.println("Text: " + text1);
        System.out.println("Pattern: " + pattern1);

        List<Integer> matches = kmpSearch(text1, pattern1);
        System.out.println("Pattern found at indices: " + matches);
        System.out.println();

        // Test 2: Multiple occurrences
        System.out.println("Test 2: Multiple Occurrences");
        String text2 = "AABAACAADAABAABA";
        String pattern2 = "AABA";

        System.out.println("Text: " + text2);
        System.out.println("Pattern: " + pattern2);

        matches = kmpSearch(text2, pattern2);
        System.out.println("Pattern found at indices: " + matches);
        System.out.println("Total occurrences: " + kmpCount(text2, pattern2));
        System.out.println();

        // Test 3: No match
        System.out.println("Test 3: Pattern Not Found");
        String text3 = "ABCDEFGH";
        String pattern3 = "XYZ";

        System.out.println("Text: " + text3);
        System.out.println("Pattern: " + pattern3);
        System.out.println("Pattern found: " + kmpContains(text3, pattern3));
        System.out.println();

        // Test 4: LPS array demonstration
        System.out.println("Test 4: LPS Array Demonstration");
        String pattern4 = "ABABACA";
        int[] lps = computeLPSArray(pattern4);

        System.out.println("Pattern: " + pattern4);
        System.out.print("LPS Array: [");
        for (int i = 0; i < lps.length; i++) {
            System.out.print(lps[i]);
            if (i < lps.length - 1) System.out.print(", ");
        }
        System.out.println("]");
        System.out.println();

        // Test 5: Overlapping patterns
        System.out.println("Test 5: Overlapping Patterns");
        String text5 = "AAAAAAA";
        String pattern5 = "AAA";

        System.out.println("Text: " + text5);
        System.out.println("Pattern: " + pattern5);
        matches = kmpSearch(text5, pattern5);
        System.out.println("Pattern found at indices: " + matches);
        System.out.println();

        // Test 6: Word search in sentence
        System.out.println("Test 6: Word Search in Sentence");
        String text6 = "The quick brown fox jumps over the lazy dog";
        String pattern6 = "fox";

        System.out.println("Text: \"" + text6 + "\"");
        System.out.println("Pattern: \"" + pattern6 + "\"");

        int index = kmpSearchFirst(text6, pattern6);
        if (index != -1) {
            System.out.println("Pattern found at index: " + index);
        } else {
            System.out.println("Pattern not found");
        }
        System.out.println();

        // Test 7: DNA sequence matching
        System.out.println("Test 7: DNA Sequence Matching");
        String dna = "GATCCTCAGATGCCTTAAGGCCTCAG";
        String sequence = "CTCAG";

        System.out.println("DNA: " + dna);
        System.out.println("Sequence: " + sequence);
        matches = kmpSearch(dna, sequence);
        System.out.println("Sequence found at positions: " + matches);
        System.out.println();

        // Test 8: Edge cases
        System.out.println("Test 8: Edge Cases");
        System.out.println("Empty pattern: " +
            kmpSearch("ABCDEF", "").size());
        System.out.println("Empty text: " +
            kmpSearch("", "ABC").size());
        System.out.println("Pattern longer than text: " +
            kmpSearch("AB", "ABCD").size());
        System.out.println("Single character match: " +
            kmpSearch("ABCABC", "A"));
        System.out.println();

        // Test 9: Performance comparison
        System.out.println("Test 9: Performance Test");
        StringBuilder largeSb = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeSb.append("ABCD");
        }
        largeSb.append("PATTERN");
        String largeText = largeSb.toString();
        String searchPattern = "PATTERN";

        long startTime = System.nanoTime();
        int result = kmpSearchFirst(largeText, searchPattern);
        long kmpTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        int javaResult = largeText.indexOf(searchPattern);
        long javaTime = System.nanoTime() - startTime;

        System.out.println("Text length: " + largeText.length());
        System.out.println("KMP result: " + result +
            " (time: " + kmpTime / 1_000_000.0 + " ms)");
        System.out.println("Java indexOf result: " + javaResult +
            " (time: " + javaTime / 1_000_000.0 + " ms)");
        System.out.println();

        // Test 10: Repeated pattern
        System.out.println("Test 10: Find All 'the' in Text");
        String text10 = "the cat and the dog played with the ball";
        String pattern10 = "the";

        System.out.println("Text: \"" + text10 + "\"");
        System.out.println("Pattern: \"" + pattern10 + "\"");
        matches = kmpSearch(text10, pattern10);
        System.out.println("Found at indices: " + matches);
        System.out.println("Total occurrences: " + matches.size());
    }
}
