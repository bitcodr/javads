package org.algorithm_datastructure;

import java.util.Arrays;

public class pair_targetsum {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int target = 12;
        System.out.println(Arrays.toString(target(arr, target)));
    }


    private static int[] target(int[] arr, int targetsum) {
        int left = 0, right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == targetsum) {
                return new int[]{left, right};
            }

            if (sum < targetsum) {
                left++;
            } else {
                right--;
            }
        }

        return new int[]{-1, -1};
    }
}