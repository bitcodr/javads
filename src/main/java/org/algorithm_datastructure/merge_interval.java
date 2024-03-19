package org.algorithm_datastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class merge_interval {

    public static void main(String[] args) {
        int[][] arr = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.println(Arrays.deepToString(intervals(arr)));
    }

    private static int[][] intervals(int[][] arr) {
        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));

        List<int[]> result = new ArrayList<>();
        int[] current = arr[0];

        for(int i = 1; i < arr.length; i++) {
            int[] next = arr[i];

            if(current[1] >= next[0]) {
                current[1] = Math.max(current[1], next[1]);
            } else {
                result.add(current);
                current = next;
            }
        }


        // Add the last interval if not merged
        if (result.isEmpty() || result.getLast()[1] < current[1]) {
            result.add(current);
        }

        return result.toArray(new int[result.size()][]);
    }
}
