package org.algorithm_datastructure;

import java.util.Arrays;

public class squaring_number {

    public static void main(String[] args) {
        int[] arr = {-2, -1, 0, 2, 3};
        System.out.println(Arrays.toString(squaring(arr)));

        int[] arr1 = {-3, -1, 0, 1, 2};
        System.out.println(Arrays.toString(squaring(arr1)));
    }

    private static int[] squaring(int[] arr) {
        int n = arr.length;
        int[] squares = new int[n];

        int left = 0, right = n - 1, sIndex = n - 1;

        while (left < right) {
            int leftSquare = arr[left] * arr[left];
            int rightSquare = arr[right] * arr[right];

            if (rightSquare > leftSquare) {
                squares[sIndex] = rightSquare;
                sIndex--;
                right--;
            } else {
                squares[sIndex] = leftSquare;
                sIndex--;
                left++;
            }
        }

        return squares;
    }

}