package org.algorithm_datastructure;

import java.util.Arrays;

public class dutch_national_flag_problem {

    public static void main(String[] args) {
        int[] arr1 = {1, 0, 2, 1, 0};
        sortDutch(arr1, 0, arr1.length - 1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = {2, 2, 0, 1, 2, 0};
        sortDutch(arr2, 0, arr2.length - 1);
        System.out.println(Arrays.toString(arr2));
    }

    private static void sortDutch(int[] arr, int low, int high) {
        // selection O(n^2)       in-place, brute force
        // bubble sort O(n^2)     in-place, brute force
        // merge sort O(n * logN) recursive ( break, arrange, merge )
        // quick sort O(n * logN) in-place recursive

        if (low < high) {

            int pivot = sort(arr, low, high);

            sortDutch(arr, low, pivot - 1);
            sortDutch(arr, pivot + 1, high);
        }
    }

    private static int sort(int[] arr, int low, int high) {
        int pivot = arr[high];

        int pivotIndex = low - 1;

        for (int i = low; i < high; i++) {
            if (arr[i] < pivot) {
                pivotIndex++;

                swap(arr, pivotIndex, i);
            }
        }

        swap(arr, pivotIndex + 1, high);

        return pivotIndex + 1;
    }


    private static void swap(int[] arr, int low, int high) {
        int temp = arr[low];
        arr[low] = arr[high];
        arr[high] = temp;
    }


}
