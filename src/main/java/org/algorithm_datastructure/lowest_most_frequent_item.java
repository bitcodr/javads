package org.algorithm_datastructure;

import java.util.*;

public class lowest_most_frequent_item {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 4, 3, 2, 1, 3, 4));
        System.out.println(migratoryBirds(list));
    }

    // Space = O (N)
    // Time = O (M+N)
    public static int migratoryBirds(List<Integer> arr) {

        Map<Integer, Integer> preserve = new HashMap<>();

        for (Integer item : arr) {
            preserve.put(item, preserve.getOrDefault(item, 1) + 1);
        }

        int mostFrequent = 0;
        int repeated = 0;

        for (Map.Entry<Integer, Integer> item : preserve.entrySet()) {
            if (item.getValue() > repeated) {
                mostFrequent = item.getKey();
                repeated = item.getValue();
            }
        }

        return mostFrequent;
    }

}
