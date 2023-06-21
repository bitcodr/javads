package org.algorithm_datastructure;

import java.util.Arrays;

public class sort_merge {


    // Break
    // Arrange
    // Merge

    public static void main(String[] args) {
        int[] nums = {6, 4, 1, 3, 5, 2, 9, 8, 7};
        sort(nums, nums.length);
        System.out.println("Merge sort-> " + Arrays.toString(nums));
    }


    private static void sort(int[] nums, int len) {
        if (len < 2) {
            return;
        }

        int middle = len / 2;

        int[] left = new int[middle];
        int[] right = new int[len - middle];

        for (int i = 0; i < middle; i++) {
            left[i] = nums[i];
        }

        for (int j = middle; j < len; j++) {
            right[j - middle] = nums[j];
        }

        sort(left, middle);
        sort(right, len - middle);

        merge(nums, left, right);
    }


    private static void merge(int[] nums, int[] left, int[] right) {
        int l = 0, r = 0, k = 0;

        while (l < left.length && r < right.length) {
            if (left[l] < right[r]) {
                nums[k] = left[l];
                l++;
            } else {
                nums[k] = right[r];
                r++;
            }

            k++;
        }

        while (l < left.length) {
            nums[k] = left[l];
            k++;
            l++;
        }

        while (r < right.length) {
            nums[k] = right[r];
            k++;
            r++;
        }
    }

}
