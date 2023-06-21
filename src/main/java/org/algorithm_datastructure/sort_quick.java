package org.algorithm_datastructure;

import java.util.Arrays;

public class sort_quick {

    // pivot
    // replace pivot with last item
    // higher and less element from pivot and replace
    // if the less become greater replace it with pivot

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 2, 7, 9, 4, 8, 6};
        sort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }


    /* The main function that implements QuickSort
               arr[] --> Array to be sorted,
               low --> Starting index,
               high --> Ending index
      */
    private static void sort(int[] nums, int low, int high) {
        if (low < high) {

            // pi is partitioning index, arr[p]
            // is now at right place
            int pivot = partition(nums, low, high);

            // Separately sort elements before
            // partition and after partition
            sort(nums, low, pivot - 1);
            sort(nums, pivot + 1, high);
        }
    }


    /* This function takes last element as pivot, places
       the pivot element at its correct position in sorted
       array, and places all smaller (smaller than pivot)
       to left of pivot and all greater elements to right
       of pivot */
    private static int partition(int[] nums, int low, int high) {
        int pivot = nums[high];

        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        int pivotIndex = low - 1;

        for (int i = low; i < high; i++) {
            if (nums[i] < pivot) {
                pivotIndex++;

                swap(nums, pivotIndex, i);
            }
        }

        swap(nums, pivotIndex + 1, high);

        return pivotIndex + 1;
    }

    private static void swap(int[] nums, int low, int high) {
        int temp = nums[low];
        nums[low] = nums[high];
        nums[high] = temp;
    }

}
