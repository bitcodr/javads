package org.algorithm_datastructure;

/**
 * Edit Distance (Levenshtein Distance) using Dynamic Programming
 *
 * Given two strings, find the minimum number of operations required to
 * convert one string to another. Allowed operations:
 * 1. Insert a character
 * 2. Delete a character
 * 3. Replace a character
 *
 * Time Complexity: O(m * n) where m, n are string lengths
 * Space Complexity: O(m * n) or O(min(m, n)) optimized
 *
 * Applications:
 * - Spell checking
 * - DNA sequence alignment
 * - Plagiarism detection
 * - Fuzzy string matching
 * - Autocorrect systems
 */
public class edit_distance {

    /**
     * Calculates minimum edit distance between two strings
     * Time Complexity: O(m * n)
     * Space Complexity: O(m * n)
     * @param word1 First string
     * @param word2 Second string
     * @return Minimum number of operations
     */
    public static int editDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];

        // Base cases: converting empty string
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;  // Delete all characters
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;  // Insert all characters
        }

        // Fill DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // Characters match, no operation needed
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // Take minimum of:
                    // 1. Insert: dp[i][j-1] + 1
                    // 2. Delete: dp[i-1][j] + 1
                    // 3. Replace: dp[i-1][j-1] + 1
                    dp[i][j] = 1 + Math.min(
                        Math.min(dp[i][j - 1], dp[i - 1][j]),
                        dp[i - 1][j - 1]
                    );
                }
            }
        }

        return dp[m][n];
    }

    /**
     * Calculates edit distance with space optimization
     * Time Complexity: O(m * n)
     * Space Complexity: O(min(m, n))
     * @param word1 First string
     * @param word2 Second string
     * @return Minimum number of operations
     */
    public static int editDistanceOptimized(String word1, String word2) {
        // Make word1 the shorter string
        if (word1.length() > word2.length()) {
            String temp = word1;
            word1 = word2;
            word2 = temp;
        }

        int m = word1.length();
        int n = word2.length();

        int[] prev = new int[m + 1];
        int[] curr = new int[m + 1];

        // Initialize base case
        for (int i = 0; i <= m; i++) {
            prev[i] = i;
        }

        for (int j = 1; j <= n; j++) {
            curr[0] = j;

            for (int i = 1; i <= m; i++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    curr[i] = prev[i - 1];
                } else {
                    curr[i] = 1 + Math.min(
                        Math.min(curr[i - 1], prev[i]),
                        prev[i - 1]
                    );
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
     * Gets edit operations sequence
     * @param word1 First string
     * @param word2 Second string
     * @return String describing operations
     */
    public static String getEditOperations(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Fill DP table
        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int j = 0; j <= n; j++) dp[0][j] = j;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
                        Math.min(dp[i][j - 1], dp[i - 1][j]),
                        dp[i - 1][j - 1]
                    );
                }
            }
        }

        // Backtrack to find operations
        StringBuilder operations = new StringBuilder();
        int i = m, j = n;

        while (i > 0 || j > 0) {
            if (i == 0) {
                operations.insert(0, "Insert '" + word2.charAt(j - 1) + "'\n");
                j--;
            } else if (j == 0) {
                operations.insert(0, "Delete '" + word1.charAt(i - 1) + "'\n");
                i--;
            } else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                i--;
                j--;
            } else {
                int minOp = Math.min(
                    Math.min(dp[i][j - 1], dp[i - 1][j]),
                    dp[i - 1][j - 1]
                );

                if (dp[i - 1][j - 1] == minOp) {
                    operations.insert(0, "Replace '" + word1.charAt(i - 1) +
                        "' with '" + word2.charAt(j - 1) + "'\n");
                    i--;
                    j--;
                } else if (dp[i - 1][j] == minOp) {
                    operations.insert(0, "Delete '" + word1.charAt(i - 1) + "'\n");
                    i--;
                } else {
                    operations.insert(0, "Insert '" + word2.charAt(j - 1) + "'\n");
                    j--;
                }
            }
        }

        return operations.toString();
    }

    public static void main(String[] args) {
        System.out.println("Edit Distance (Levenshtein Distance) Demo\n");

        // Test 1: Basic example
        System.out.println("Test 1: Basic Edit Distance");
        String word1 = "horse";
        String word2 = "ros";

        System.out.println("Word 1: " + word1);
        System.out.println("Word 2: " + word2);
        System.out.println("Edit distance: " + editDistance(word1, word2));
        System.out.println("\nOperations:");
        System.out.println(getEditOperations(word1, word2));

        // Test 2: Another example
        System.out.println("Test 2: Another Example");
        String word3 = "intention";
        String word4 = "execution";

        System.out.println("Word 1: " + word3);
        System.out.println("Word 2: " + word4);
        System.out.println("Edit distance: " + editDistance(word3, word4));
        System.out.println("\nOperations:");
        System.out.println(getEditOperations(word3, word4));

        // Test 3: Identical strings
        System.out.println("Test 3: Identical Strings");
        String word5 = "hello";
        String word6 = "hello";

        System.out.println("Word 1: " + word5);
        System.out.println("Word 2: " + word6);
        System.out.println("Edit distance: " + editDistance(word5, word6));
        System.out.println();

        // Test 4: Completely different
        System.out.println("Test 4: Completely Different");
        String word7 = "abc";
        String word8 = "def";

        System.out.println("Word 1: " + word7);
        System.out.println("Word 2: " + word8);
        System.out.println("Edit distance: " + editDistance(word7, word8));
        System.out.println();

        // Test 5: Spell checking simulation
        System.out.println("Test 5: Spell Checking");
        String typed = "recieve";
        String correct = "receive";

        System.out.println("Typed: " + typed);
        System.out.println("Correct: " + correct);
        System.out.println("Edit distance: " + editDistance(typed, correct));
        System.out.println("Suggested correction (distance <= 2): " +
            (editDistance(typed, correct) <= 2 ? "YES" : "NO"));
        System.out.println();

        // Test 6: DNA sequence alignment
        System.out.println("Test 6: DNA Sequence Alignment");
        String dna1 = "AGGTAB";
        String dna2 = "GXTXAYB";

        System.out.println("DNA 1: " + dna1);
        System.out.println("DNA 2: " + dna2);
        System.out.println("Edit distance: " + editDistance(dna1, dna2));
        System.out.println();

        // Test 7: Edge cases
        System.out.println("Test 7: Edge Cases");
        System.out.println("Empty to 'abc': " + editDistance("", "abc"));
        System.out.println("'abc' to empty: " + editDistance("abc", ""));
        System.out.println("Both empty: " + editDistance("", ""));
        System.out.println("Single char same: " + editDistance("a", "a"));
        System.out.println("Single char diff: " + editDistance("a", "b"));
        System.out.println();

        // Test 8: Compare regular vs optimized
        System.out.println("Test 8: Regular vs Optimized");
        String w1 = "kitten";
        String w2 = "sitting";

        int result1 = editDistance(w1, w2);
        int result2 = editDistanceOptimized(w1, w2);

        System.out.println("Word 1: " + w1);
        System.out.println("Word 2: " + w2);
        System.out.println("Regular result: " + result1);
        System.out.println("Optimized result: " + result2);
        System.out.println("Results match: " + (result1 == result2));
        System.out.println();

        // Test 9: Similar words
        System.out.println("Test 9: Word Similarity");
        String[] words = {"cat", "cut", "cot", "bat", "hat"};
        String target = "cat";

        System.out.println("Target word: " + target);
        System.out.println("Similar words (distance <= 1):");

        for (String word : words) {
            int dist = editDistance(target, word);
            if (dist <= 1 && !word.equals(target)) {
                System.out.println("  " + word + " (distance: " + dist + ")");
            }
        }
    }
}
