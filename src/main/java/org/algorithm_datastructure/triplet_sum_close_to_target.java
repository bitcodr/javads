package org.algorithm_datastructure;

import java.util.Arrays;

// Given an array of unsorted numbers and a target number,
// find a triplet in the array whose sum is as close to the target number as possible, return the sum of the triplet.
// If there are more than one such triplet, return the sum of the triplet with the smallest sum.
public class triplet_sum_close_to_target {
    public static void main(String[] args) {
        int[] arr = {-1, 0, 2, 3};
        int target = 3;
        System.out.println(sumClose(arr, target)); // Output: 2

        int[] arr1 = {-3, -1, 1, 2};
        int target1 = 1;
        System.out.println(sumClose(arr1, target1)); // Output: 0

        int[] arr2 = {1, 0, 1, 1};
        int target2 = 100;
        System.out.println(sumClose(arr2, target2)); // Output: 3

        int[] arr3 = {0, 0, 1, 1, 2, 6};
        int target3 = 5;
        System.out.println(sumClose(arr3, target3)); // Output: 4
    }

    private static int sumClose(int[] arr, int targetSum) {
        if (arr == null || arr.length < 3)
            throw new IllegalArgumentException();

        Arrays.sort(arr);

        int smallestDifference = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length - 2; i++) {

            int left = i + 1, right = arr.length - 1;

            while (left < right) {
                // comparing the sum of three numbers to the 'targetSum' can cause overflow
                // so, we will try to find a target difference
                int targetDiff = targetSum - arr[i] - arr[left] - arr[right];

                if (targetDiff == 0) //  we've found a triplet with an exact sum
                    return targetSum; // return sum of all the numbers

                // the second part of the above 'if' is to handle the smallest sum when we have 
                // more than one solution
                if (Math.abs(targetDiff) < Math.abs(smallestDifference)
                        || (Math.abs(targetDiff) == Math.abs(smallestDifference)
                        && targetDiff > smallestDifference))
                    smallestDifference = targetDiff; // save the closest and the biggest difference

                if (targetDiff > 0)
                    left++; // we need a triplet with a bigger sum
                else
                    right--; // we need a triplet with a smaller sum
            }
        }

        return targetSum - smallestDifference;
    }
}