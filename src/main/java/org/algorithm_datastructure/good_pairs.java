package org.algorithm_datastructure;

import java.util.HashMap;

// Given an array of integers nums, return the number of good pairs.
// A pair (i, j) is called good if nums[i] == nums[j] and i < j.
public class good_pairs {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1, 1, 3};
        System.out.println(goodPairs(nums));

        int[] nums1 = {1, 1, 1, 1};
        System.out.println(goodPairs(nums1));


        int[] nums2 = {1, 2, 3};
        System.out.println(goodPairs(nums2));
    }

    public static int goodPairs(int[] nums) {
        int pairs = 0;

        HashMap<Integer, Integer> n = new HashMap<>();

        for (int number : nums) {
			n.put(number, n.getOrDefault(number, 0) + 1);
			
            pairs += n.get(number) - 1;
        }

        return pairs;
    }
}