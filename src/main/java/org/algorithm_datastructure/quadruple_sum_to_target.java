package org.algorithm_datastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Given an array of unsorted numbers and a target number,
// find all unique quadruplets in it, whose sum is equal to the target number.
public class quadruple_sum_to_target {

    public static void main(String[] args) {
        // Output: [-3, -1, 1, 4], [-3, 1, 1, 2]
        System.out.println(searchQuadruplets(new int[]{4, 1, 2, -1, 1, -3}, 1));

        // Output: [-2, 1, 1, 2], [-1, 0, 1, 2]
        System.out.println(searchQuadruplets(new int[]{2, 0, -1, 1, -2, 2}, 2));
    }

    private static List<List<Integer>> searchQuadruplets(int[] arr, int target) {
        Arrays.sort(arr);

        List<List<Integer>> quadruplets = new ArrayList<>();


        for (int i = 0; i < arr.length - 3; i++) {
            int left = i + 1;
            int right = arr.length - 1;
            int middle = arr.length / 2;

            while (left < right) {
                int sum = arr[i] + arr[left] + arr[middle] + arr[right];
                if (sum == target) {
                    quadruplets.add(Arrays.asList(arr[i], arr[left], arr[middle], arr[right]));
                    right--;
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        return quadruplets;
    }
}
