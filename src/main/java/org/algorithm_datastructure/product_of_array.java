package org.algorithm_datastructure;

import java.util.Arrays;

// Product Of Array Except Self
// Given an array of integers, return a new array where each element at index i of the new array
// is the product of all the numbers in the original array except the one at I .
// Medium You must solve this problem without using division.
public class product_of_array {
	public static void main(String[] args) {
		int[] arr = {3, 5, 7, 4, 8};
		System.out.println(Arrays.toString(productOfArray(arr)));
	}

	private static int[] productOfArray(int[] arr) {

		int[] ar = new int[arr.length];

		for (int i = 0; i < arr.length; i++) {
			int number = 0;
			for (int j = 0; j < arr.length; j++) {
				if (i != j) {
					if (number == 0) {
						number += arr[j];
						continue;
					}

					number *= arr[j];
				}
			}

			ar[i] = number;
		}

		return ar;
	}

}