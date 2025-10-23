package org.algorithm_datastructure;

/**
 * Climbing Stairs Problem using Dynamic Programming
 *
 * You are climbing a staircase with n steps. Each time you can climb
 * 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * This is essentially a Fibonacci problem:
 * ways(n) = ways(n-1) + ways(n-2)
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1) optimized
 *
 * Applications:
 * - Counting problems
 * - Path counting
 * - Introduction to DP
 */
public class climbing_stairs {

    /**
     * Counts ways to climb n stairs (space optimized)
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * @param n Number of stairs
     * @return Number of ways
     */
    public static int climbStairs(int n) {
        if (n <= 2) return n;

        int prev2 = 1;  // ways(1)
        int prev1 = 2;  // ways(2)
        int current = 0;

        for (int i = 3; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }

        return current;
    }

    /**
     * Counts ways with k steps allowed
     * Time Complexity: O(n * k)
     * Space Complexity: O(n)
     * @param n Number of stairs
     * @param k Max steps per climb
     * @return Number of ways
     */
    public static int climbStairsKSteps(int n, int k) {
        if (n <= 1) return 1;

        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k && j <= i; j++) {
                dp[i] += dp[i - j];
            }
        }

        return dp[n];
    }

    /**
     * Minimum cost to reach top (each step has a cost)
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * @param cost Array of step costs
     * @return Minimum cost to reach top
     */
    public static int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        if (n == 0) return 0;
        if (n == 1) return cost[0];

        int prev2 = cost[0];
        int prev1 = cost[1];

        for (int i = 2; i < n; i++) {
            int current = cost[i] + Math.min(prev1, prev2);
            prev2 = prev1;
            prev1 = current;
        }

        return Math.min(prev1, prev2);
    }

    public static void main(String[] args) {
        System.out.println("Climbing Stairs Problem Demo\n");

        // Test 1: Basic examples
        System.out.println("Test 1: Basic Examples");
        for (int n = 1; n <= 10; n++) {
            System.out.println("n = " + n + ": " + climbStairs(n) + " ways");
        }
        System.out.println();

        // Test 2: With k steps
        System.out.println("Test 2: Climbing with Different Step Sizes");
        int n = 5;
        System.out.println("Stairs: " + n);
        System.out.println("1-2 steps: " + climbStairs(n) + " ways");
        System.out.println("1-3 steps: " + climbStairsKSteps(n, 3) + " ways");
        System.out.println("1-4 steps: " + climbStairsKSteps(n, 4) + " ways");
        System.out.println();

        // Test 3: Minimum cost
        System.out.println("Test 3: Minimum Cost Climbing");
        int[] cost1 = {10, 15, 20};
        System.out.println("Costs: [10, 15, 20]");
        System.out.println("Minimum cost: " + minCostClimbingStairs(cost1));

        int[] cost2 = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println("\nCosts: [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]");
        System.out.println("Minimum cost: " + minCostClimbingStairs(cost2));
    }
}
