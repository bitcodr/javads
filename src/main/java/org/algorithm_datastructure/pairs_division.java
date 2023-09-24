package org.algorithm_datastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class pairs_division {

    public static void main(String[] args) {
         List<Integer> ar =  new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
         int k = 5;
         int n = 3;
         System.out.println(divisibleSumPairs(n, k, ar));
    }

    public static int divisibleSumPairs(int n, int k, List<Integer> ar) {

        int result = 0;

        for( int i = 0; i < ar.size(); i++) {
            int left = i + 1;
            int right = ar.size() - 1;

            while(left <= right) {
                if ((ar.get(i) + ar.get(left)) % k == 0) {
                    result++;
                    left++;
                } else if ((ar.get(i) + ar.get(right)) % k == 0) {
                    result++;
                    right--;
                } else {
                    left++;
                    right--;
                }
            }
        }

        return result;
    }
}
