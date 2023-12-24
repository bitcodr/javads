package org.algorithm_datastructure;

// Best Time To Buy And Sell Stock Given an array prices where prices[i] is the price of a given stock on the ith day.
// Easy You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
// Return the maximum profit you can achieve from this transaction.
// If you cannot achieve any profit, return 0.
public class best_time_to_buy_sell {

    public static void main(String[] args) {
        int[] a = {43, 65, 33, 9, 10};
        System.out.println(maxProfit(a));
    }

    private static int maxProfit(int[] arr) {

        int smallest = arr[0];
        int indexSmallest = 0;

        for (int i = indexSmallest; i < arr.length; i++) {
            if (arr[i] < smallest) {
                smallest = arr[i];
                indexSmallest = i;
            }
        }

        int biggest = arr[indexSmallest];

        for (int j = indexSmallest; j < arr.length; j++) {
            if (arr[j] > biggest) {
                biggest = arr[j];
            }
        }

        return biggest - smallest;
    }

}
