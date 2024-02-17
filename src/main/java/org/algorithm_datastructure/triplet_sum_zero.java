package org.algorithm_datastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Given an array of unsorted numbers, find all unique triplets in it that add up to zero.
public class triplet_sum_zero {
	public static void main(String[] args) {
		int[] arr = {4, 5, 2, 6, 8, 4, 2, 3, 9, 1, 0, 6};
		System.out.println(sum(arr));
	}

	private static List<List<Integer>> sum(int[] arr) {
		Arrays.sort(arr);

		List<List<Integer>> res = new ArrayList<>();

		// 0, 1, 2, 2, 3, 4, 4, 5, 6, 8, 9
		for (int i = 0; i < arr.length; i++) {

			int left = i + 1;
			int right = arr.length - 1;

			if (arr[i] == arr[left]) {
				continue;
			}

			while (left < right) {
				int total = arr[i] + arr[left] + arr[right];

				if (total == 0) {
					res.add(Arrays.asList(arr[i], arr[left], arr[right]));
				} else if (total < 0) {
					left++;
				} else if (total > 0) {
					right--;
				} else {
					left++;
					right--;
				}
			}
		}

		return res;
	}
}