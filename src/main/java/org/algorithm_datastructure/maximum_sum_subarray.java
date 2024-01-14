package org.algorithm_datastructure;

import java.util.Arrays;

// Given an array of positive numbers and a positive number 'k,' find the maximum sum of any contiguous subarray of size 'k'.
public class maximum_sum_subarray {
	public static void main(String[] args) {
		int[] arr = {2, 4, 5, 8, 1, 3};
		int k = 2;
		System.out.println(Arrays.toString(sum(arr, k)));

		int[] arr1 = {2, 4, 5, 8, 1, 3};
		int k1 = 3;
		System.out.println(Arrays.toString(sum(arr1, k1)));
	}

	private static int[] sum(int[] arr, int k) {
		int[] maxarr = new int[k];
		int max = 0;

		for (int i = 0; i < arr.length; i++) {
			if (i + k > arr.length - 1) {
				break;
			}

			int j = i;
			int temp = 0;
			int[] tempmaxarr = new int[k];
			int arrlengh = 0;
			while (j < i + k) {
				temp += arr[j];
				tempmaxarr[arrlengh] = arr[j];
				j++;
				arrlengh++;
			}

			if (temp > max) {
				max = temp;
				maxarr = tempmaxarr;
			}
		}

		return maxarr;
	}
}