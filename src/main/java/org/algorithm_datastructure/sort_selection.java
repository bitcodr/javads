package org.algorithm_datastructure;


import java.util.Arrays;

public class sort_selection {
    public static void main(String[] args) {
        int[] nums1 = {6, 1, 4, 3, 5, 2};
        System.out.println("Selection sort-> " + Arrays.toString(sort(nums1)));
    }

    private static int[] sort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int smallIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[smallIndex]) {
                    smallIndex = j;
                }
            }

            int keep = nums[i];
            nums[i] = nums[smallIndex];
            nums[smallIndex] = keep;
        }

        return nums;
    }
}