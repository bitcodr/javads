package org.algorithm_datastructure;

import java.util.Arrays;

/**
 * Radix Sort Algorithm
 *
 * A non-comparison based sorting algorithm that sorts integers by processing
 * individual digits. It uses counting sort as a subroutine to sort digits.
 *
 * Algorithm:
 * 1. Find the maximum number to determine number of digits
 * 2. Sort by each digit from least significant to most significant
 * 3. Use counting sort for each digit position
 *
 * Time Complexity: O(d × (n + k)) where d is number of digits, k is range
 * Space Complexity: O(n + k)
 *
 * Characteristics:
 * - Stable sort (preserves relative order of equal elements)
 * - Not in-place (requires extra space)
 * - Works well for integers with limited digit count
 * - Linear time when d is constant
 *
 * Applications:
 * - Sorting integers
 * - Sorting strings of same length
 * - Card sorting
 * - When range of numbers is not significantly larger than n
 */
public class radix_sort {

    /**
     * Sorts array using radix sort (LSD - Least Significant Digit)
     * Time Complexity: O(d × (n + 10)) where d is max digits
     * Space Complexity: O(n)
     * @param arr Array to sort (non-negative integers)
     */
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        // Find the maximum number to know number of digits
        int max = getMax(arr);

        // Do counting sort for every digit
        // exp is 10^i where i is current digit number
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }

    /**
     * Counting sort based on digit represented by exp
     * Time Complexity: O(n + 10)
     * @param arr Array to sort
     * @param exp Exponent representing current digit (1, 10, 100, ...)
     */
    private static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10]; // Digits 0-9

        // Store count of occurrences
        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / exp) % 10;
            count[digit]++;
        }

        // Change count[i] to actual position in output array
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build output array (traverse from right to maintain stability)
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        // Copy output array to arr
        System.arraycopy(output, 0, arr, 0, n);
    }

    /**
     * Radix sort that handles negative numbers
     * Time Complexity: O(d × (n + k))
     * @param arr Array to sort (can include negative numbers)
     */
    public static void radixSortWithNegatives(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        // Separate positive and negative numbers
        int negCount = 0;
        for (int num : arr) {
            if (num < 0) negCount++;
        }

        int[] negatives = new int[negCount];
        int[] positives = new int[arr.length - negCount];

        int negIndex = 0, posIndex = 0;
        for (int num : arr) {
            if (num < 0) {
                negatives[negIndex++] = -num; // Convert to positive
            } else {
                positives[posIndex++] = num;
            }
        }

        // Sort both arrays
        if (negatives.length > 0) {
            radixSort(negatives);
        }
        if (positives.length > 0) {
            radixSort(positives);
        }

        // Merge: negatives in reverse (and negate), then positives
        int index = 0;
        for (int i = negatives.length - 1; i >= 0; i--) {
            arr[index++] = -negatives[i];
        }
        for (int num : positives) {
            arr[index++] = num;
        }
    }

    /**
     * Gets the maximum value in array
     * @param arr Array
     * @return Maximum value
     */
    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    /**
     * Radix sort for strings of same length
     * Time Complexity: O(d × n) where d is string length
     * @param arr Array of strings (all same length)
     */
    public static void radixSortStrings(String[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int n = arr.length;
        int maxLen = arr[0].length();

        // Sort by each character from right to left
        for (int pos = maxLen - 1; pos >= 0; pos--) {
            countingSortByChar(arr, pos);
        }
    }

    private static void countingSortByChar(String[] arr, int pos) {
        int n = arr.length;
        String[] output = new String[n];
        int[] count = new int[256]; // ASCII characters

        // Count occurrences
        for (int i = 0; i < n; i++) {
            char ch = arr[i].charAt(pos);
            count[ch]++;
        }

        // Cumulative count
        for (int i = 1; i < 256; i++) {
            count[i] += count[i - 1];
        }

        // Build output array
        for (int i = n - 1; i >= 0; i--) {
            char ch = arr[i].charAt(pos);
            output[count[ch] - 1] = arr[i];
            count[ch]--;
        }

        // Copy back
        System.arraycopy(output, 0, arr, 0, n);
    }

    public static void main(String[] args) {
        System.out.println("Radix Sort Implementation Demo\n");

        // Test 1: Basic sorting
        System.out.println("Test 1: Basic Sorting");
        int[] arr1 = {170, 45, 75, 90, 802, 24, 2, 66};
        System.out.println("Original: " + Arrays.toString(arr1));
        radixSort(arr1);
        System.out.println("Sorted:   " + Arrays.toString(arr1));
        System.out.println();

        // Test 2: Same digits
        System.out.println("Test 2: Numbers with Same Digit Count");
        int[] arr2 = {329, 457, 657, 839, 436, 720, 355};
        System.out.println("Original: " + Arrays.toString(arr2));
        radixSort(arr2);
        System.out.println("Sorted:   " + Arrays.toString(arr2));
        System.out.println();

        // Test 3: Different digit counts
        System.out.println("Test 3: Mixed Digit Counts");
        int[] arr3 = {1, 200, 45, 3000, 67, 8, 999};
        System.out.println("Original: " + Arrays.toString(arr3));
        radixSort(arr3);
        System.out.println("Sorted:   " + Arrays.toString(arr3));
        System.out.println();

        // Test 4: With duplicates
        System.out.println("Test 4: Array with Duplicates");
        int[] arr4 = {5, 2, 8, 2, 9, 1, 5, 5};
        System.out.println("Original: " + Arrays.toString(arr4));
        radixSort(arr4);
        System.out.println("Sorted:   " + Arrays.toString(arr4));
        System.out.println();

        // Test 5: Already sorted
        System.out.println("Test 5: Already Sorted");
        int[] arr5 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println("Original: " + Arrays.toString(arr5));
        radixSort(arr5);
        System.out.println("Sorted:   " + Arrays.toString(arr5));
        System.out.println();

        // Test 6: With negative numbers
        System.out.println("Test 6: With Negative Numbers");
        int[] arr6 = {-5, 3, -2, 8, -9, 1, 0, 7};
        System.out.println("Original: " + Arrays.toString(arr6));
        radixSortWithNegatives(arr6);
        System.out.println("Sorted:   " + Arrays.toString(arr6));
        System.out.println();

        // Test 7: String sorting
        System.out.println("Test 7: String Radix Sort (same length)");
        String[] arr7 = {"abc", "xyz", "def", "aaa", "zzz", "bcd"};
        System.out.println("Original: " + Arrays.toString(arr7));
        radixSortStrings(arr7);
        System.out.println("Sorted:   " + Arrays.toString(arr7));
        System.out.println();

        // Test 8: Large numbers
        System.out.println("Test 8: Large Numbers");
        int[] arr8 = {123456, 987654, 456789, 234567, 789012, 345678};
        System.out.println("Original: " + Arrays.toString(arr8));
        radixSort(arr8);
        System.out.println("Sorted:   " + Arrays.toString(arr8));
        System.out.println();

        // Test 9: Performance test
        System.out.println("Test 9: Performance Test");
        int[] large = new int[10000];
        for (int i = 0; i < large.length; i++) {
            large[i] = (int) (Math.random() * 100000);
        }

        long startTime = System.nanoTime();
        radixSort(large);
        long endTime = System.nanoTime();

        System.out.println("Sorted " + large.length + " elements");
        System.out.println("Time taken: " + (endTime - startTime) / 1_000_000.0 + " ms");
        System.out.println("Verification: " + isSorted(large));
        System.out.println();

        // Test 10: Comparison with Arrays.sort()
        System.out.println("Test 10: Radix Sort vs Arrays.sort()");
        int[] test1 = new int[50000];
        int[] test2 = new int[50000];
        for (int i = 0; i < test1.length; i++) {
            test1[i] = test2[i] = (int) (Math.random() * 1000000);
        }

        startTime = System.nanoTime();
        radixSort(test1);
        long radixTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        Arrays.sort(test2);
        long javaTime = System.nanoTime() - startTime;

        System.out.println("Array size: " + test1.length);
        System.out.println("Radix Sort time: " + radixTime / 1_000_000.0 + " ms");
        System.out.println("Arrays.sort time: " + javaTime / 1_000_000.0 + " ms");
        System.out.println("Speedup: " + (double) javaTime / radixTime + "x");
    }

    private static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
