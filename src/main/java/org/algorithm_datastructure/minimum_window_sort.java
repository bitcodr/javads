package org.algorithm_datastructure;

// Given an array, find the length of the smallest subarray in it which when sorted will sort the whole array.
public class minimum_window_sort {
    public static void main(String[] args) {
        int[] arr0 = { 2, 6, 4, 8, 10, 9, 15 }; // 5
        System.out.println(subArrayItem(arr0));

        int[] arr1 = { 1, 2, 5, 3, 7, 10, 9, 12 }; // 5
        System.out.println(subArrayItem(arr1));

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

        // 2, 6, 4, 8, 10, 9, 15
        // 6, 4, 8, 10, 9, 15
        // 6, 4, 8, 10, 9
        // 4, 8, 10, 9
        // 4, 8, 10

        int lowerCorrectIndex = 0;
        int lowerLeftIndex = 0;
        int lowerRightIndex = arr.length - 1;

        while (lowerLeftIndex < lowerRightIndex) {
            if (arr[lowerLeftIndex] < arr[lowerRightIndex] && lowerLeftIndex == lowerCorrectIndex) {
                lowerLeftIndex++;
            } else {
                lowerCorrectIndex = lowerLeftIndex;
                lowerRightIndex--;
            }
        }

        int higherCorrectIndex = arr.length - 1;
        int higherLeft = 0;
        int higherRight = arr.length - 1;

        while (higherLeft < higherRight) {
            if (arr[higherLeft] < arr[higherRight] && higherCorrectIndex == higherRight) {
                higherCorrectIndex = higherRight;
                higherRight--;
            } else {
                higherLeft++;
            }
        }

        if (higherCorrectIndex + lowerCorrectIndex == arr.length) {
            return 0;
        }

        return ((higherCorrectIndex - 1) - (lowerCorrectIndex - 1)) + 1;
    }
}
