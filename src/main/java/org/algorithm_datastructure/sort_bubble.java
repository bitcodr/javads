package org.algorithm_datastructure;

import java.util.Arrays;

public class sort_bubble {

    public static void main(String[] args) {
        int[] nums1 = {6, 1, 4, 3, 5, 2};
        System.out.println("Bubble sort-> " + Arrays.toString(sort(nums1)));
    }


    private static int[] sort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j+1];
                    nums[j+1] = nums[j];
                    nums[j] = temp;
                }
            }
        }


        return nums;
    }
}
