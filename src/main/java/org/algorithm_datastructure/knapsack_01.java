package org.algorithm_datastructure;

import java.util.Arrays;

/**
 * 0/1 Knapsack Problem using Dynamic Programming
 *
 * Given weights and values of n items, put these items in a knapsack of
 * capacity W to get the maximum total value. Each item can be taken at most once.
 *
 * Problem: Given n items with weights w[] and values v[], and a knapsack
 * capacity W, find the maximum value that can be obtained.
 *
 * Approaches:
 * 1. Recursive: O(2^n) time
 * 2. Memoization: O(n * W) time, O(n * W) space
 * 3. Tabulation: O(n * W) time, O(n * W) space
 * 4. Space optimized: O(n * W) time, O(W) space
 *
 * Time Complexity: O(n * W) where n is items, W is capacity
 * Space Complexity: O(n * W) or O(W) optimized
 *
 * Applications:
 * - Resource allocation
 * - Budget management
 * - Cargo loading
 * - Investment selection
 */
public class knapsack_01 {

    /**
     * Solves 0/1 knapsack using tabulation (bottom-up DP)
     * Time Complexity: O(n * W)
     * Space Complexity: O(n * W)
     * @param values Array of item values
     * @param weights Array of item weights
     * @param capacity Knapsack capacity
     * @return Maximum value achievable
     */
    public static int knapsackTabulation(int[] values, int[] weights, int capacity) {
        int n = values.length;
        int[][] dp = new int[n + 1][capacity + 1];

        // Build table bottom-up
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {
                // Item weight
                int itemWeight = weights[i - 1];
                int itemValue = values[i - 1];

                if (itemWeight <= w) {
                    // Max of: (1) include item, (2) exclude item
                    dp[i][w] = Math.max(
                        itemValue + dp[i - 1][w - itemWeight],  // Include
                        dp[i - 1][w]                             // Exclude
                    );
                } else {
                    // Can't include item (too heavy)
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][capacity];
    }

    /**
     * Solves 0/1 knapsack with space optimization
     * Time Complexity: O(n * W)
     * Space Complexity: O(W)
     * @param values Array of item values
     * @param weights Array of item weights
     * @param capacity Knapsack capacity
     * @return Maximum value achievable
     */
    public static int knapsackOptimized(int[] values, int[] weights, int capacity) {
        int n = values.length;
        int[] dp = new int[capacity + 1];

        for (int i = 0; i < n; i++) {
            // Traverse from right to left to avoid using updated values
            for (int w = capacity; w >= weights[i]; w--) {
                dp[w] = Math.max(dp[w], values[i] + dp[w - weights[i]]);
            }
        }

        return dp[capacity];
    }

    /**
     * Solves 0/1 knapsack and returns selected items
     * Time Complexity: O(n * W)
     * Space Complexity: O(n * W)
     * @param values Array of item values
     * @param weights Array of item weights
     * @param capacity Knapsack capacity
     * @return KnapsackResult containing max value and selected items
     */
    public static KnapsackResult knapsackWithItems(
            int[] values, int[] weights, int capacity) {

        int n = values.length;
        int[][] dp = new int[n + 1][capacity + 1];

        // Build DP table
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {
                int itemWeight = weights[i - 1];
                int itemValue = values[i - 1];

                if (itemWeight <= w) {
                    dp[i][w] = Math.max(
                        itemValue + dp[i - 1][w - itemWeight],
                        dp[i - 1][w]
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Backtrack to find selected items
        boolean[] selected = new boolean[n];
        int w = capacity;
        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                selected[i - 1] = true;
                w -= weights[i - 1];
            }
        }

        return new KnapsackResult(dp[n][capacity], selected);
    }

    /**
     * Result class containing max value and selected items
     */
    static class KnapsackResult {
        int maxValue;
        boolean[] selectedItems;

        KnapsackResult(int maxValue, boolean[] selectedItems) {
            this.maxValue = maxValue;
            this.selectedItems = selectedItems;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Max Value: ").append(maxValue).append("\n");
            sb.append("Selected items: ");
            for (int i = 0; i < selectedItems.length; i++) {
                if (selectedItems[i]) {
                    sb.append(i).append(" ");
                }
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println("0/1 Knapsack Problem Demo\n");

        // Test 1: Basic example
        System.out.println("Test 1: Basic Knapsack");
        int[] values1 = {60, 100, 120};
        int[] weights1 = {10, 20, 30};
        int capacity1 = 50;

        System.out.println("Items:");
        for (int i = 0; i < values1.length; i++) {
            System.out.println("  Item " + i + ": value=" + values1[i] +
                ", weight=" + weights1[i]);
        }
        System.out.println("Capacity: " + capacity1);

        int maxValue = knapsackTabulation(values1, weights1, capacity1);
        System.out.println("Maximum value: " + maxValue);
        System.out.println();

        // Test 2: With item selection
        System.out.println("Test 2: Knapsack with Item Selection");
        KnapsackResult result = knapsackWithItems(values1, weights1, capacity1);
        System.out.println(result);

        int totalWeight = 0;
        int totalValue = 0;
        for (int i = 0; i < values1.length; i++) {
            if (result.selectedItems[i]) {
                System.out.println("  Item " + i + ": value=" + values1[i] +
                    ", weight=" + weights1[i]);
                totalWeight += weights1[i];
                totalValue += values1[i];
            }
        }
        System.out.println("Total weight: " + totalWeight);
        System.out.println("Total value: " + totalValue);
        System.out.println();

        // Test 3: Compare approaches
        System.out.println("Test 3: Compare Tabulation vs Optimized");
        int[] values2 = {10, 40, 50, 70};
        int[] weights2 = {1, 3, 4, 5};
        int capacity2 = 8;

        int result1 = knapsackTabulation(values2, weights2, capacity2);
        int result2 = knapsackOptimized(values2, weights2, capacity2);

        System.out.println("Tabulation result: " + result1);
        System.out.println("Optimized result: " + result2);
        System.out.println("Results match: " + (result1 == result2));
        System.out.println();

        // Test 4: Edge cases
        System.out.println("Test 4: Edge Cases");

        // Empty knapsack
        System.out.println("Capacity 0: " +
            knapsackOptimized(values1, weights1, 0));

        // Single item
        int[] singleValue = {50};
        int[] singleWeight = {10};
        System.out.println("Single item (fits): " +
            knapsackOptimized(singleValue, singleWeight, 15));
        System.out.println("Single item (doesn't fit): " +
            knapsackOptimized(singleValue, singleWeight, 5));
        System.out.println();

        // Test 5: Larger example
        System.out.println("Test 5: Larger Knapsack Problem");
        int[] values3 = {10, 20, 30, 40, 50, 60};
        int[] weights3 = {5, 10, 15, 20, 25, 30};
        int capacity3 = 50;

        System.out.println("Items: " + values3.length);
        System.out.println("Capacity: " + capacity3);

        result = knapsackWithItems(values3, weights3, capacity3);
        System.out.println("Maximum value: " + result.maxValue);
        System.out.print("Selected items: ");
        for (int i = 0; i < values3.length; i++) {
            if (result.selectedItems[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println("\n");

        // Test 6: Real-world example (robbery)
        System.out.println("Test 6: Treasure Heist Example");
        String[] items = {"Gold Bar", "Diamond", "Silver", "Emerald", "Ruby"};
        int[] values4 = {500, 300, 100, 250, 400};
        int[] weights4 = {10, 5, 2, 8, 7};
        int capacity4 = 20;

        System.out.println("Available treasures:");
        for (int i = 0; i < items.length; i++) {
            System.out.println("  " + items[i] + ": $" + values4[i] +
                " (" + weights4[i] + " kg)");
        }
        System.out.println("Bag capacity: " + capacity4 + " kg");

        result = knapsackWithItems(values4, weights4, capacity4);
        System.out.println("\nOptimal selection:");
        System.out.println("Maximum value: $" + result.maxValue);
        System.out.println("Items to take:");
        for (int i = 0; i < items.length; i++) {
            if (result.selectedItems[i]) {
                System.out.println("  - " + items[i] + ": $" + values4[i] +
                    " (" + weights4[i] + " kg)");
            }
        }
    }
}
