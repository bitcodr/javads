package org.algorithm_datastructure;

public class search_binary {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int target = 8;
        int found = search(nums, 0, nums.length, target);
        System.out.println(found);
    }

    private static int search(int[] nums, int low, int high, int target) {
        int middle = low + (high - low) / 2;

        if (nums[middle] == target) {
            return middle;
        }

        if (nums[middle] < target) {
            return search(nums, middle + 1, high, target);
        }

        return search(nums, low, middle - 1, target);
    }
}
