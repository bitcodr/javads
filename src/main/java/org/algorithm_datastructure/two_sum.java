package org.algorithm_datastructure;

import java.util.*;

// Given an array of integers nums and an integer target.
// Find two distinct indices i and j such that the sum of nums[i] and nums[j] is equal to the target.
public class two_sum {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 4, 3, 2, 1, 3, 4));
		int target = 8;
		System.out.println(twoSum(list, target));
	}

	// Space = O (N)
	// Time = O (N)
	public static List<Integer> twoSum(List<Integer> arr, int target) {
		int left = 0;
		int right = arr.size() - 1;

		List<Integer> result = new ArrayList<>();

		while (left < right) {
			if (arr.get(left) + arr.get(right) == target) {
				result.add(left);
				result.add(right);
				
				return result;
			} else if (arr.get(left) > target) {
				left++;
			} else if (arr.get(right) > target) {
				right--;
			} else {
				left++;
				right--;
			}
		}

		return result;
	}

}
