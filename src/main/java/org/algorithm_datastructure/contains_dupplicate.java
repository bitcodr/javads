package org.algorithm_datastructure;

import java.util.Arrays;

// Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
public class contains_dupplicate {
	public static void main(String[] args) {
		int[] arrDupplicate = {5, 3, 7, 1, 4, 0, 3, 7};
		System.out.println(hasDupplicate(arrDupplicate));

		int[] arr = {5, 3, 7, 1, 4, 8, 2, 0};
		System.out.println(hasDupplicate(arr));
	}

	// Time Complexity: O(N)
	// Space Complixity: O(1)
	private static boolean hasDupplicate(int[] arr) {
		Arrays.sort(arr);

		int left = 0;
		int right = arr.length - 1;

		while (left < right) {
			if (arr[left] == arr[left + 1]) {
				return true;
			} else {
				left++;
			}
		}

		return false;
	}
}