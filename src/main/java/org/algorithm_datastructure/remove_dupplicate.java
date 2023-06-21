package org.algorithm_datastructure;

// As the input array is sorted, therefore, one way to do this is to shift the elements left whenever we encounter duplicates.
// In other words, we will keep one pointer for iterating the array and one pointer for placing the next non-duplicate number
public class remove_dupplicate {


    public static void main(String[] args) {
        int[] nums = {2, 2, 3, 3, 6, 9, 9}; // 2 9 3 3 6 9 3
        System.out.println(remove(nums));
    }


    private static int remove(int[] arr) {
        int nonDupplicate = 1;

        // Space complexity O(1)
        // Time complexity O(n)

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != arr[nonDupplicate - 1]) {
                arr[nonDupplicate] = arr[i];
                nonDupplicate++;
            }
        }

        return nonDupplicate;
    }


}