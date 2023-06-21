package org.algorithm_datastructure;

import java.util.Arrays;

// Given an array arr of unsorted numbers and a target sum,
// count all triplets in it such that arr[i] + arr[j] + arr[k] < target where i, j, and k are three different indices.
// Write a function to return the count of such triplets.
public class triplet_smaller_sum {
    public static void main(String[] args ) {
        int[] arr = {-1, 0, 2, 3};
        int target = 3;
        System.out.println(tripletSum(arr, target)); // 2

        int[] arr1 = {-1, 4, 2, 1, 3};
        int target1 = 5;
        System.out.println(tripletSum(arr1, target1)); // 4
    }


    private static int tripletSum(int[] arr, int target) {
        Arrays.sort(arr);

        int result = 0;

        // using arr.length - 2 to prevent iterating through an array whose length is less than 3
        // because we are finding triplets, minimum arr length should be 3
        for(int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;

            while (left < right) {
                    int sum = arr[left] + arr[i] + arr[right];
                    if (sum < target) {
                        // found the triplet
                        // since arr[right] >= arr[left], therefore, we can replace arr[right] by any
                        // number between left and right to get a sum less than the target sum

                        // We are using an optimization here.
                        // Since arr[right] >= arr[left], therefore,
                        // we can replace arr[right] by any number
                        // between left and right to get a sum less than the target sum; therefore,
                        // all numbers from left to right will give us the required triplet.
                        // That is why we counted them all using result += right - left;.
                        // The other option is only count once and then have another loop
                        // starting from left to find other triplets. This will increase the time complexity
                        // by the algorithm by the order of O(N), giving total complexity of O(N^3),
                        // which is not optimal.
                        result += right - left;
                        left++;
                    } else {
                        right--;  // we need a pair with a smaller sum
                    }
                }
        }



        return result;
    }
}