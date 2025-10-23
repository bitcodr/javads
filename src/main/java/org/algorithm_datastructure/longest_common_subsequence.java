package org.algorithm_datastructure;

/**
 * Longest Common Subsequence (LCS) using Dynamic Programming
 *
 * Given two sequences, find the length of longest subsequence present in both.
 * A subsequence is a sequence that appears in the same relative order,
 * but not necessarily contiguous.
 *
 * Example: LCS of "ABCDGH" and "AEDFHR" is "ADH" of length 3
 *
 * Time Complexity: O(m * n) where m, n are lengths of sequences
 * Space Complexity: O(m * n) or O(min(m, n)) optimized
 *
 * Applications:
 * - Diff utility (file comparison)
 * - DNA sequence alignment
 * - Version control systems
 * - Plagiarism detection
 */
public class longest_common_subsequence {

    /**
     * Finds length of LCS using tabulation
     * Time Complexity: O(m * n)
     * Space Complexity: O(m * n)
     * @param text1 First string
     * @param text2 Second string
     * @return Length of LCS
     */
    public static int lcsLength(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Build DP table bottom-up
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // Characters match, add 1 to diagonal
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // Characters don't match, take max from left or top
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    /**
     * Finds the actual LCS string
     * Time Complexity: O(m * n)
     * Space Complexity: O(m * n)
     * @param text1 First string
     * @param text2 Second string
     * @return LCS string
     */
    public static String lcsString(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Build DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Backtrack to find LCS string
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;

        while (i > 0 && j > 0) {
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                lcs.append(text1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return lcs.reverse().toString();
    }

    /**
     * Space optimized version (only stores current and previous row)
     * Time Complexity: O(m * n)
     * Space Complexity: O(min(m, n))
     * @param text1 First string
     * @param text2 Second string
     * @return Length of LCS
     */
    public static int lcsOptimized(String text1, String text2) {
        // Make text1 the shorter string to save space
        if (text1.length() > text2.length()) {
            String temp = text1;
            text1 = text2;
            text2 = temp;
        }

        int m = text1.length();
        int n = text2.length();
        int[] prev = new int[m + 1];
        int[] curr = new int[m + 1];

        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= m; i++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    curr[i] = prev[i - 1] + 1;
                } else {
                    curr[i] = Math.max(prev[i], curr[i - 1]);
                }
            }
            // Swap arrays
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[m];
    }

    /**
     * Finds LCS of arrays (generic)
     * @param arr1 First array
     * @param arr2 Second array
     * @return Length of LCS
     */
    public static int lcsArray(int[] arr1, int[] arr2) {
        int m = arr1.length;
        int n = arr2.length;
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (arr1[i - 1] == arr2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println("Longest Common Subsequence Demo\n");

        // Test 1: Basic example
        System.out.println("Test 1: Basic LCS");
        String s1 = "ABCDGH";
        String s2 = "AEDFHR";

        System.out.println("String 1: " + s1);
        System.out.println("String 2: " + s2);
        System.out.println("LCS Length: " + lcsLength(s1, s2));
        System.out.println("LCS String: " + lcsString(s1, s2));
        System.out.println();

        // Test 2: Another example
        System.out.println("Test 2: Another Example");
        String s3 = "AGGTAB";
        String s4 = "GXTXAYB";

        System.out.println("String 1: " + s3);
        System.out.println("String 2: " + s4);
        System.out.println("LCS Length: " + lcsLength(s3, s4));
        System.out.println("LCS String: " + lcsString(s3, s4));
        System.out.println();

        // Test 3: No common subsequence
        System.out.println("Test 3: No Common Subsequence");
        String s5 = "ABC";
        String s6 = "DEF";

        System.out.println("String 1: " + s5);
        System.out.println("String 2: " + s6);
        System.out.println("LCS Length: " + lcsLength(s5, s6));
        System.out.println("LCS String: \"" + lcsString(s5, s6) + "\"");
        System.out.println();

        // Test 4: Identical strings
        System.out.println("Test 4: Identical Strings");
        String s7 = "HELLO";
        String s8 = "HELLO";

        System.out.println("String 1: " + s7);
        System.out.println("String 2: " + s8);
        System.out.println("LCS Length: " + lcsLength(s7, s8));
        System.out.println("LCS String: " + lcsString(s7, s8));
        System.out.println();

        // Test 5: One is subsequence of other
        System.out.println("Test 5: One is Subsequence of Other");
        String s9 = "ACE";
        String s10 = "ABCDE";

        System.out.println("String 1: " + s9);
        System.out.println("String 2: " + s10);
        System.out.println("LCS Length: " + lcsLength(s9, s10));
        System.out.println("LCS String: " + lcsString(s9, s10));
        System.out.println();

        // Test 6: Compare optimized version
        System.out.println("Test 6: Compare Regular vs Optimized");
        String s11 = "ABCDEFGH";
        String s12 = "ACDGH";

        int regular = lcsLength(s11, s12);
        int optimized = lcsOptimized(s11, s12);

        System.out.println("String 1: " + s11);
        System.out.println("String 2: " + s12);
        System.out.println("Regular result: " + regular);
        System.out.println("Optimized result: " + optimized);
        System.out.println("Results match: " + (regular == optimized));
        System.out.println();

        // Test 7: Array LCS
        System.out.println("Test 7: LCS of Integer Arrays");
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {2, 4, 5, 6};

        System.out.print("Array 1: ");
        for (int num : arr1) System.out.print(num + " ");
        System.out.println();

        System.out.print("Array 2: ");
        for (int num : arr2) System.out.print(num + " ");
        System.out.println();

        System.out.println("LCS Length: " + lcsArray(arr1, arr2));
        System.out.println();

        // Test 8: DNA sequence alignment
        System.out.println("Test 8: DNA Sequence Alignment");
        String dna1 = "AGGTABCGTACG";
        String dna2 = "GXTXAYB";

        System.out.println("DNA Sequence 1: " + dna1);
        System.out.println("DNA Sequence 2: " + dna2);
        System.out.println("Common subsequence length: " + lcsLength(dna1, dna2));
        System.out.println("Common pattern: " + lcsString(dna1, dna2));
        System.out.println();

        // Test 9: Edge cases
        System.out.println("Test 9: Edge Cases");
        System.out.println("Empty strings: " + lcsLength("", "ABC"));
        System.out.println("Both empty: " + lcsLength("", ""));
        System.out.println("Single char match: " + lcsLength("A", "A"));
        System.out.println("Single char no match: " + lcsLength("A", "B"));
        System.out.println();

        // Test 10: File diff simulation
        System.out.println("Test 10: File Diff Simulation");
        String file1 = "lineABlineClineD";
        String file2 = "lineAlineBlineDlineE";

        System.out.println("File 1: " + file1);
        System.out.println("File 2: " + file2);
        System.out.println("Common content length: " + lcsLength(file1, file2));
        System.out.println("Common content: " + lcsString(file1, file2));
    }
}
