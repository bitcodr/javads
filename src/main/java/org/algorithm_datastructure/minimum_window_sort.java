package org.algorithm_datastructure;

// Given an array, find the length of the smallest subarray in it which when sorted will sort the whole array.
public class minimum_window_sort {
    public static void main(String[] args) {
        // int[] arr1 = { 1, 2, 5, 3, 7, 10, 9, 12 }; // 5
        // System.out.println(subArrayItem(arr1));

        int[] arr2 = { 1, 3, 2, 0, -1, 7, 10 }; // 5
        System.out.println(subArrayItem(arr2));

        int[] arr3 = { 1, 2, 3 };
        System.out.println(subArrayItem(arr3)); // 0

        int[] arr4 = { 3, 2, 1 };
        System.out.println(subArrayItem(arr4)); // 3
    }

    // Time -> O(n)
    // Space -> O(1)
    private static int subArrayItem(int[] arr) {

        int lower = Integer.MAX_VALUE;
        int lowerIndex = 0;

        int lowerLeft = 0;
        int lowerRight = arr.length - 1;

        while (lowerLeft < lowerRight) {
            if (arr[lowerLeft] < lower && lowerLeft != 0) {
                lower = arr[lowerLeft];
                lowerIndex = lowerLeft;
                lowerLeft++;
            } else if (arr[lowerRight] < lower) {
                lower = arr[lowerRight];
                lowerIndex = lowerRight;
                lowerRight--;
            } else {
                lowerLeft++;
                lowerRight--;
            }
        }

        int higher = Integer.MIN_VALUE;
        int higherIndex = arr.length - 1;

        int higherLeft = 0;
        int higherRight = arr.length - 1;

        while (higherLeft < higherRight) {
            if (arr[higherLeft] > higher) {
                higher = arr[higherLeft];
                higherIndex = higherLeft;
                higherLeft++;
            } else if (arr[higherRight] > higher && higherRight != arr.length - 1) {
                higher = arr[higherRight];
                higherIndex = higherRight;
                higherRight--;
            } else {
                higherLeft++;
                higherRight--;
            }
        }

        return (higherIndex - lowerIndex) + 1;
    }
}
