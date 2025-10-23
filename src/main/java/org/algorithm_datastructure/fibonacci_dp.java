package org.algorithm_datastructure;

import java.util.*;

/**
 * Fibonacci using Dynamic Programming
 *
 * Calculates the nth Fibonacci number using various approaches.
 *
 * Fibonacci sequence: 0, 1, 1, 2, 3, 5, 8, 13, 21, ...
 * F(n) = F(n-1) + F(n-2), where F(0) = 0, F(1) = 1
 *
 * Approaches:
 * 1. Naive recursion: O(2^n) time, O(n) space
 * 2. Memoization (Top-down DP): O(n) time, O(n) space
 * 3. Tabulation (Bottom-up DP): O(n) time, O(n) space
 * 4. Space optimized: O(n) time, O(1) space
 *
 * Applications:
 * - Introduction to dynamic programming
 * - Algorithm optimization demonstration
 * - Mathematical sequences
 */
public class fibonacci_dp {

    /**
     * Naive recursive approach (exponential time)
     * Time Complexity: O(2^n)
     * Space Complexity: O(n) for recursion stack
     * @param n The nth Fibonacci number to calculate
     * @return nth Fibonacci number
     */
    public static long fibonacciRecursive(int n) {
        if (n <= 1) return n;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    /**
     * Memoization approach (top-down DP)
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param n The nth Fibonacci number to calculate
     * @return nth Fibonacci number
     */
    public static long fibonacciMemoization(int n) {
        Map<Integer, Long> memo = new HashMap<>();
        return fibMemoHelper(n, memo);
    }

    private static long fibMemoHelper(int n, Map<Integer, Long> memo) {
        if (n <= 1) return n;

        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        long result = fibMemoHelper(n - 1, memo) + fibMemoHelper(n - 2, memo);
        memo.put(n, result);
        return result;
    }

    /**
     * Tabulation approach (bottom-up DP)
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param n The nth Fibonacci number to calculate
     * @return nth Fibonacci number
     */
    public static long fibonacciTabulation(int n) {
        if (n <= 1) return n;

        long[] dp = new long[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    /**
     * Space optimized approach
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * @param n The nth Fibonacci number to calculate
     * @return nth Fibonacci number
     */
    public static long fibonacciOptimized(int n) {
        if (n <= 1) return n;

        long prev2 = 0;
        long prev1 = 1;
        long current = 0;

        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }

        return current;
    }

    /**
     * Generates first n Fibonacci numbers
     * Time Complexity: O(n)
     * @param n Number of Fibonacci numbers to generate
     * @return Array of first n Fibonacci numbers
     */
    public static long[] fibonacciSequence(int n) {
        long[] sequence = new long[n];
        if (n >= 1) sequence[0] = 0;
        if (n >= 2) sequence[1] = 1;

        for (int i = 2; i < n; i++) {
            sequence[i] = sequence[i - 1] + sequence[i - 2];
        }

        return sequence;
    }

    public static void main(String[] args) {
        System.out.println("Fibonacci Dynamic Programming Demo\n");

        // Test 1: Compare different approaches (small n)
        System.out.println("Test 1: Calculate F(10) using different methods");
        int n = 10;

        System.out.println("Recursive: " + fibonacciRecursive(n));
        System.out.println("Memoization: " + fibonacciMemoization(n));
        System.out.println("Tabulation: " + fibonacciTabulation(n));
        System.out.println("Optimized: " + fibonacciOptimized(n));
        System.out.println();

        // Test 2: First n Fibonacci numbers
        System.out.println("Test 2: First 15 Fibonacci Numbers");
        long[] sequence = fibonacciSequence(15);
        System.out.println(Arrays.toString(sequence));
        System.out.println();

        // Test 3: Edge cases
        System.out.println("Test 3: Edge Cases");
        System.out.println("F(0) = " + fibonacciOptimized(0));
        System.out.println("F(1) = " + fibonacciOptimized(1));
        System.out.println("F(2) = " + fibonacciOptimized(2));
        System.out.println();

        // Test 4: Larger numbers
        System.out.println("Test 4: Larger Fibonacci Numbers");
        System.out.println("F(20) = " + fibonacciOptimized(20));
        System.out.println("F(30) = " + fibonacciOptimized(30));
        System.out.println("F(40) = " + fibonacciOptimized(40));
        System.out.println("F(50) = " + fibonacciOptimized(50));
        System.out.println();

        // Test 5: Performance comparison
        System.out.println("Test 5: Performance Comparison");
        int testN = 40;

        System.out.println("Calculating F(" + testN + ")...");

        long startTime = System.nanoTime();
        long resultMemo = fibonacciMemoization(testN);
        long memoTime = System.nanoTime() - startTime;
        System.out.println("Memoization: " + resultMemo +
            " (" + memoTime / 1_000_000.0 + " ms)");

        startTime = System.nanoTime();
        long resultTab = fibonacciTabulation(testN);
        long tabTime = System.nanoTime() - startTime;
        System.out.println("Tabulation: " + resultTab +
            " (" + tabTime / 1_000_000.0 + " ms)");

        startTime = System.nanoTime();
        long resultOpt = fibonacciOptimized(testN);
        long optTime = System.nanoTime() - startTime;
        System.out.println("Optimized: " + resultOpt +
            " (" + optTime / 1_000_000.0 + " ms)");

        System.out.println("\nNote: Recursive approach with F(40) would take several seconds");
        System.out.println("Exponential time vs Linear time difference!\n");

        // Test 6: Time comparison with recursive (small n)
        System.out.println("Test 6: Recursive vs DP (F(35))");
        int smallN = 35;

        startTime = System.nanoTime();
        long recResult = fibonacciRecursive(smallN);
        long recTime = System.nanoTime() - startTime;
        System.out.println("Recursive: " + recResult +
            " (" + recTime / 1_000_000.0 + " ms)");

        startTime = System.nanoTime();
        long dpResult = fibonacciOptimized(smallN);
        long dpTime = System.nanoTime() - startTime;
        System.out.println("Optimized DP: " + dpResult +
            " (" + dpTime / 1_000_000.0 + " ms)");

        System.out.println("Speedup: " + (double) recTime / dpTime + "x faster");
    }
}
