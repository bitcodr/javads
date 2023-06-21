package org.algorithm_datastructure;

import java.util.*;

// Given an array with positive numbers and a positive target number,
// find all of its contiguous sub-arrays whose product is less than the target number.
public class subarray_less_than_target {
    public static void main(String[] args) {
        int[] arr = {2, 5, 3, 10};
        int target = 30;
        System.out.println(subArrayTarget(arr, target));

        int[] arr1 = {8, 2, 6, 5};
        int target1 = 50;
        System.out.println(subArrayTarget(arr1, target1));
    }


    // time complexity is: O(n)
    // space complexity is: O(n)
    private static Set<List<Integer>> subArrayTarget(int[] arr, int target) {
        Set<List<Integer>> result = new HashSet<>();

        int left = 0;
        int right = left + 1;

        while (right < arr.length) {

            int product = arr[right] * arr[left];
            if (product < target) {
                result.add(List.of(arr[left]));
                result.add(List.of(arr[right]));
                result.add(Arrays.asList(arr[left], arr[right]));
            } else {
                result.add(List.of(arr[right]));
            }

            left++;
            right++;
        }

        return result;
    }


}