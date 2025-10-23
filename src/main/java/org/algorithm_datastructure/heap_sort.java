package org.algorithm_datastructure;

import java.util.Arrays;

/**
 * Heap Sort Algorithm
 *
 * A comparison-based sorting algorithm that uses a binary heap data structure.
 * It first builds a max heap from the input, then repeatedly extracts the maximum
 * element and rebuilds the heap.
 *
 * Algorithm:
 * 1. Build a max heap from input array
 * 2. Swap root (maximum) with last element
 * 3. Reduce heap size by 1
 * 4. Heapify root
 * 5. Repeat steps 2-4 until heap size is 1
 *
 * Time Complexity: O(n log n) in all cases
 * Space Complexity: O(1) - sorts in place
 *
 * Characteristics:
 * - Not stable (relative order of equal elements may change)
 * - In-place sorting
 * - Not adaptive (same time complexity for all inputs)
 *
 * Applications:
 * - When consistent O(n log n) performance is needed
 * - When space is limited (in-place sorting)
 * - Priority queue implementation
 */
public class heap_sort {

    /**
     * Sorts array using heap sort algorithm
     * Time Complexity: O(n log n)
     * Space Complexity: O(1)
     * @param arr Array to sort
     */
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Build max heap (rearrange array)
        // Start from last non-leaf node and heapify each node
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Extract elements from heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Move current root (maximum) to end
            swap(arr, 0, i);

            // Heapify the reduced heap
            heapify(arr, i, 0);
        }
    }

    /**
     * Maintains max heap property for a subtree
     * Time Complexity: O(log n)
     * @param arr Array representing heap
     * @param heapSize Size of heap
     * @param rootIndex Root index of subtree
     */
    private static void heapify(int[] arr, int heapSize, int rootIndex) {
        int largest = rootIndex;
        int left = 2 * rootIndex + 1;
        int right = 2 * rootIndex + 2;

        // If left child is larger than root
        if (left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        }

        // If right child is larger than largest so far
        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }

        // If largest is not root
        if (largest != rootIndex) {
            swap(arr, rootIndex, largest);

            // Recursively heapify the affected subtree
            heapify(arr, heapSize, largest);
        }
    }

    /**
     * Swaps two elements in array
     * @param arr Array
     * @param i First index
     * @param j Second index
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Heap sort for generic comparable types
     * @param arr Array to sort
     * @param <T> Type that implements Comparable
     */
    public static <T extends Comparable<T>> void heapSort(T[] arr) {
        int n = arr.length;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Extract elements from heap
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    private static <T extends Comparable<T>> void heapify(T[] arr, int heapSize, int rootIndex) {
        int largest = rootIndex;
        int left = 2 * rootIndex + 1;
        int right = 2 * rootIndex + 2;

        if (left < heapSize && arr[left].compareTo(arr[largest]) > 0) {
            largest = left;
        }

        if (right < heapSize && arr[right].compareTo(arr[largest]) > 0) {
            largest = right;
        }

        if (largest != rootIndex) {
            swap(arr, rootIndex, largest);
            heapify(arr, heapSize, largest);
        }
    }

    private static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println("Heap Sort Implementation Demo\n");

        // Test 1: Basic sorting
        System.out.println("Test 1: Basic Sorting");
        int[] arr1 = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original: " + Arrays.toString(arr1));
        heapSort(arr1);
        System.out.println("Sorted:   " + Arrays.toString(arr1));
        System.out.println();

        // Test 2: Already sorted
        System.out.println("Test 2: Already Sorted Array");
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("Original: " + Arrays.toString(arr2));
        heapSort(arr2);
        System.out.println("Sorted:   " + Arrays.toString(arr2));
        System.out.println();

        // Test 3: Reverse sorted
        System.out.println("Test 3: Reverse Sorted Array");
        int[] arr3 = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println("Original: " + Arrays.toString(arr3));
        heapSort(arr3);
        System.out.println("Sorted:   " + Arrays.toString(arr3));
        System.out.println();

        // Test 4: Array with duplicates
        System.out.println("Test 4: Array with Duplicates");
        int[] arr4 = {5, 2, 8, 2, 9, 1, 5, 5};
        System.out.println("Original: " + Arrays.toString(arr4));
        heapSort(arr4);
        System.out.println("Sorted:   " + Arrays.toString(arr4));
        System.out.println();

        // Test 5: Single element
        System.out.println("Test 5: Single Element");
        int[] arr5 = {42};
        System.out.println("Original: " + Arrays.toString(arr5));
        heapSort(arr5);
        System.out.println("Sorted:   " + Arrays.toString(arr5));
        System.out.println();

        // Test 6: Two elements
        System.out.println("Test 6: Two Elements");
        int[] arr6 = {5, 2};
        System.out.println("Original: " + Arrays.toString(arr6));
        heapSort(arr6);
        System.out.println("Sorted:   " + Arrays.toString(arr6));
        System.out.println();

        // Test 7: Negative numbers
        System.out.println("Test 7: Negative Numbers");
        int[] arr7 = {-5, 3, -2, 8, -9, 1, 0};
        System.out.println("Original: " + Arrays.toString(arr7));
        heapSort(arr7);
        System.out.println("Sorted:   " + Arrays.toString(arr7));
        System.out.println();

        // Test 8: String sorting
        System.out.println("Test 8: String Array");
        String[] arr8 = {"grape", "apple", "orange", "banana", "kiwi"};
        System.out.println("Original: " + Arrays.toString(arr8));
        heapSort(arr8);
        System.out.println("Sorted:   " + Arrays.toString(arr8));
        System.out.println();

        // Test 9: Large array
        System.out.println("Test 9: Large Array");
        int[] arr9 = new int[20];
        for (int i = 0; i < arr9.length; i++) {
            arr9[i] = (int) (Math.random() * 100);
        }
        System.out.println("Original: " + Arrays.toString(arr9));
        heapSort(arr9);
        System.out.println("Sorted:   " + Arrays.toString(arr9));
        System.out.println();

        // Test 10: Performance comparison
        System.out.println("Test 10: Performance Test");
        int[] large = new int[10000];
        for (int i = 0; i < large.length; i++) {
            large[i] = (int) (Math.random() * 10000);
        }

        long startTime = System.nanoTime();
        heapSort(large);
        long endTime = System.nanoTime();

        System.out.println("Sorted " + large.length + " elements");
        System.out.println("Time taken: " + (endTime - startTime) / 1_000_000.0 + " ms");
        System.out.println("Verification: " + isSorted(large));
    }

    /**
     * Helper method to verify if array is sorted
     * @param arr Array to check
     * @return true if sorted, false otherwise
     */
    private static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
