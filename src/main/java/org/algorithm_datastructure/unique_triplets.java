package org.algorithm_datastructure;

import java.util.*;

// Given an array of unsorted numbers, find all unique triplets in it that add up to zero.
public class unique_triplets {
    public static void main(String[] args) {

        System.out.println(searchTriplets(new int[]{-3, 0, 1, 2, -1, 1, -2})); // Output: [[-3, 1, 2], [-2, 0, 2], [-2, 1, 1], [-1, 0, 1]]

        System.out.println(searchTriplets(new int[]{-5, 2, -1, -2, 3})); // Output: [[-5, 2, 3], [-2, -1, 3]]
    }

    private static List<List<Integer>> searchTriplets(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> triplets = new ArrayList<>();


        // target -> triplets sum to 0;
        // unique triplets

        // triplets
        // unique elements
        for (int i = 0; i < arr.length; i++) {
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue;
            }

            int left = i + 1, right = arr.length - 1;

            int target = -arr[i];

            while (left < right) {
                int sum = arr[left] + arr[right];
                if (sum == target) { // found the triplet
                    triplets.add(Arrays.asList(arr[i], arr[left], arr[right]));
                    left++;
                    right--;

                    while (left < right && arr[left] == arr[left - 1]) {
                        left++;  // skip same element to avoid duplicate triplets
                    }

                    while (left < right && arr[right] == arr[right + 1]) {
                        right--; // skip same element to avoid duplicate triplets
                    }

                } else if (target > sum) {
                    left++; // we need a pair with a bigger sum
                } else {
                    right--; // we need a pair with a smaller sum
                }

            }
        }

        return triplets;
    }
}