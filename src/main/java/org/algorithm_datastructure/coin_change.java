package org.algorithm_datastructure;

import java.util.Arrays;

/**
 * Coin Change Problem using Dynamic Programming
 *
 * Given an array of coin denominations and a target amount, find:
 * 1. Minimum number of coins needed to make the amount
 * 2. Number of ways to make the amount
 *
 * Time Complexity: O(n * amount) where n is number of coins
 * Space Complexity: O(amount)
 *
 * Applications:
 * - Currency exchange
 * - Vending machines
 * - Financial calculations
 * - Resource optimization
 */
public class coin_change {

    /**
     * Finds minimum number of coins to make amount
     * Time Complexity: O(n * amount)
     * Space Complexity: O(amount)
     * @param coins Array of coin denominations
     * @param amount Target amount
     * @return Minimum coins needed, or -1 if not possible
     */
    public static int coinChangeMinCoins(int[] coins, int amount) {
        if (amount == 0) return 0;

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);  // Initialize with max value
        dp[0] = 0;

        // For each amount from 1 to target
        for (int i = 1; i <= amount; i++) {
            // Try each coin
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * Counts number of ways to make amount
     * Time Complexity: O(n * amount)
     * Space Complexity: O(amount)
     * @param coins Array of coin denominations
     * @param amount Target amount
     * @return Number of combinations
     */
    public static int coinChangeWays(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;  // One way to make 0: use no coins

        // For each coin
        for (int coin : coins) {
            // Update all amounts that can be formed
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[amount];
    }

    /**
     * Finds minimum coins with coin selection
     * @param coins Array of coin denominations
     * @param amount Target amount
     * @return Array showing which coins to use, or null if not possible
     */
    public static int[] coinChangeWithSelection(int[] coins, int amount) {
        if (amount == 0) return new int[0];

        int[] dp = new int[amount + 1];
        int[] coinUsed = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i && dp[i - coin] + 1 < dp[i]) {
                    dp[i] = dp[i - coin] + 1;
                    coinUsed[i] = coin;
                }
            }
        }

        if (dp[amount] > amount) return null;

        // Reconstruct solution
        int[] result = new int[dp[amount]];
        int idx = 0;
        int remaining = amount;

        while (remaining > 0) {
            result[idx++] = coinUsed[remaining];
            remaining -= coinUsed[remaining];
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("Coin Change Problem Demo\n");

        // Test 1: Minimum coins
        System.out.println("Test 1: Minimum Coins");
        int[] coins1 = {1, 2, 5};
        int amount1 = 11;

        System.out.println("Coins: " + Arrays.toString(coins1));
        System.out.println("Amount: " + amount1);

        int minCoins = coinChangeMinCoins(coins1, amount1);
        System.out.println("Minimum coins needed: " + minCoins);

        int[] selection = coinChangeWithSelection(coins1, amount1);
        if (selection != null) {
            System.out.println("Coins to use: " + Arrays.toString(selection));
        }
        System.out.println();

        // Test 2: Number of ways
        System.out.println("Test 2: Number of Ways");
        int[] coins2 = {1, 2, 5};
        int amount2 = 5;

        System.out.println("Coins: " + Arrays.toString(coins2));
        System.out.println("Amount: " + amount2);
        System.out.println("Number of ways: " + coinChangeWays(coins2, amount2));
        System.out.println();

        // Test 3: No solution
        System.out.println("Test 3: No Solution");
        int[] coins3 = {2, 5};
        int amount3 = 3;

        System.out.println("Coins: " + Arrays.toString(coins3));
        System.out.println("Amount: " + amount3);
        System.out.println("Minimum coins: " + coinChangeMinCoins(coins3, amount3));
        System.out.println();

        // Test 4: Multiple examples
        System.out.println("Test 4: Various Amounts");
        int[] coins4 = {1, 5, 10, 25};
        int[] amounts = {1, 6, 11, 30, 41, 99};

        System.out.println("Coins: " + Arrays.toString(coins4));
        for (int amt : amounts) {
            int min = coinChangeMinCoins(coins4, amt);
            int[] sel = coinChangeWithSelection(coins4, amt);
            System.out.println("Amount " + amt + ": " + min + " coins " +
                (sel != null ? Arrays.toString(sel) : ""));
        }
        System.out.println();

        // Test 5: Edge cases
        System.out.println("Test 5: Edge Cases");
        System.out.println("Amount 0: " + coinChangeMinCoins(coins1, 0));
        System.out.println("Amount 1 with coin [1]: " +
            coinChangeMinCoins(new int[]{1}, 1));
        System.out.println();

        // Test 6: Large denomination
        System.out.println("Test 6: Large Denominations");
        int[] coins6 = {1, 5, 10, 25, 50, 100};
        int amount6 = 187;

        System.out.println("Coins: " + Arrays.toString(coins6));
        System.out.println("Amount: $" + (amount6 / 100.0));

        int[] solution = coinChangeWithSelection(coins6, amount6);
        if (solution != null) {
            System.out.println("Coins needed: " + solution.length);
            System.out.println("Coins: " + Arrays.toString(solution));

            // Count each denomination
            int[] count = new int[101];
            for (int coin : solution) {
                count[coin]++;
            }

            System.out.println("\nBreakdown:");
            if (count[100] > 0) System.out.println("  $1.00 x " + count[100]);
            if (count[50] > 0) System.out.println("  $0.50 x " + count[50]);
            if (count[25] > 0) System.out.println("  $0.25 x " + count[25]);
            if (count[10] > 0) System.out.println("  $0.10 x " + count[10]);
            if (count[5] > 0) System.out.println("  $0.05 x " + count[5]);
            if (count[1] > 0) System.out.println("  $0.01 x " + count[1]);
        }
        System.out.println();

        // Test 7: Ways comparison
        System.out.println("Test 7: Count Ways for Different Amounts");
        int[] coins7 = {1, 2, 5};

        System.out.println("Coins: " + Arrays.toString(coins7));
        for (int amt = 1; amt <= 10; amt++) {
            System.out.println("Amount " + amt + ": " +
                coinChangeWays(coins7, amt) + " ways");
        }
    }
}
