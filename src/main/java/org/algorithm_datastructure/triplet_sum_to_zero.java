package org.algorithm_datastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Given an array of unsorted numbers, find all unique triplets in it that add up to zero.
public class triplet_sum_to_zero {
    public static void main(String[] args) {
        int[] arr = {-1, 0, 1, 2, -1, -4};
        System.out.println(triplet(arr));
    }

    private static List<List<Integer>> triplet(int[] arr) {
        Arrays.sort(arr);

        List<List<Integer>> res = new ArrayList<>();

        for(int i = 0; i < arr.length; i++) {
            if (i > 0 && arr[i] == arr[i-1]) {
                continue;
            }

            int left = i+1;
            int right = arr.length - 1;

            while(left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                if (sum == 0){
                    List<Integer> ar = new ArrayList<>(Arrays.asList(arr[i], arr[left], arr[right]));

                    res.add(ar);

                    left++;
                    right--;
                }


                if (sum > 0) {
                    right--;
                } else if(sum < 0) {
                    left++;
                }
            }
        }

        return res;
        
    }
}
