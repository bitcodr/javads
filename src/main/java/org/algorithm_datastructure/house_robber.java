package org.algorithm_datastructure;

import java.util.Arrays;

/**
 * House Robber Problem using Dynamic Programming
 *
 * You are a robber planning to rob houses along a street. Each house has
 * a certain amount of money. Adjacent houses have security systems connected,
 * so you cannot rob two adjacent houses. Find maximum amount you can rob.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1) optimized
 *
 * Applications:
 * - Resource selection with constraints
 * - Scheduling problems
 * - Maximum independent set
 */
public class house_robber {

    /**
     * Maximum money that can be robbed (space optimized)
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * @param nums Array of house values
     * @return Maximum money
     */
    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int prev2 = 0;
        int prev1 = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int current = Math.max(prev1, nums[i] + prev2);
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    /**
     * House Robber II - houses arranged in a circle
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * @param nums Array of house values
     * @return Maximum money
     */
    public static int robCircular(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        // Either rob first house (can't rob last) or rob last (can't rob first)
        return Math.max(
            robRange(nums, 0, nums.length - 2),
            robRange(nums, 1, nums.length - 1)
        );
    }

    private static int robRange(int[] nums, int start, int end) {
        int prev2 = 0;
        int prev1 = 0;

        for (int i = start; i <= end; i++) {
            int current = Math.max(prev1, nums[i] + prev2);
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    /**
     * Returns which houses to rob
     * @param nums Array of house values
     * @return Array of house indices to rob
     */
    public static int[] robWithSelection(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];
        if (nums.length == 1) return new int[]{0};

        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }

        // Backtrack to find houses
        boolean[] selected = new boolean[n];
        int i = n - 1;

        while (i >= 0) {
            if (i == 0 || (i >= 2 && dp[i] == nums[i] + dp[i - 2])) {
                selected[i] = true;
                i -= 2;
            } else {
                i--;
            }
        }

        // Convert to indices array
        int count = 0;
        for (boolean s : selected) {
            if (s) count++;
        }

        int[] result = new int[count];
        int idx = 0;
        for (int j = 0; j < n; j++) {
            if (selected[j]) {
                result[idx++] = j;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("House Robber Problem Demo\n");

        // Test 1: Basic example
        System.out.println("Test 1: Basic House Robber");
        int[] houses1 = {1, 2, 3, 1};
        System.out.println("Houses: " + Arrays.toString(houses1));
        System.out.println("Maximum money: " + rob(houses1));

        int[] robbed = robWithSelection(houses1);
        System.out.println("Rob houses at indices: " + Arrays.toString(robbed));
        int total = 0;
        System.out.print("Values: ");
        for (int idx : robbed) {
            System.out.print(houses1[idx] + " ");
            total += houses1[idx];
        }
        System.out.println("\nTotal: " + total);
        System.out.println();

        // Test 2: Another example
        System.out.println("Test 2: Another Example");
        int[] houses2 = {2, 7, 9, 3, 1};
        System.out.println("Houses: " + Arrays.toString(houses2));
        System.out.println("Maximum money: " + rob(houses2));

        robbed = robWithSelection(houses2);
        System.out.println("Rob houses at indices: " + Arrays.toString(robbed));
        System.out.println();

        // Test 3: Circular arrangement
        System.out.println("Test 3: Circular Arrangement");
        int[] houses3 = {2, 3, 2};
        System.out.println("Houses (circular): " + Arrays.toString(houses3));
        System.out.println("Maximum money: " + robCircular(houses3));
        System.out.println();

        // Test 4: Larger example
        System.out.println("Test 4: Larger Example");
        int[] houses4 = {5, 3, 4, 11, 2, 1, 8, 6};
        System.out.println("Houses: " + Arrays.toString(houses4));
        System.out.println("Maximum money: " + rob(houses4));

        robbed = robWithSelection(houses4);
        System.out.print("Rob houses at indices: ");
        for (int idx : robbed) {
            System.out.print(idx + " ");
        }
        System.out.println();

        System.out.print("Values: ");
        total = 0;
        for (int idx : robbed) {
            System.out.print(houses4[idx] + " ");
            total += houses4[idx];
        }
        System.out.println("\nTotal: " + total);
        System.out.println();

        // Test 5: Edge cases
        System.out.println("Test 5: Edge Cases");
        System.out.println("Empty array: " + rob(new int[]{}));
        System.out.println("Single house [5]: " + rob(new int[]{5}));
        System.out.println("Two houses [2, 3]: " + rob(new int[]{2, 3}));
        System.out.println();

        // Test 6: All same values
        System.out.println("Test 6: All Same Values");
        int[] houses6 = {5, 5, 5, 5, 5};
        System.out.println("Houses: " + Arrays.toString(houses6));
        System.out.println("Maximum money: " + rob(houses6));
        System.out.println("(Rob indices 0, 2, 4 = 15)");
    }
}
