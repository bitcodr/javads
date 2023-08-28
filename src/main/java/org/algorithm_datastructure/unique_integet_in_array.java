package org.algorithm_datastructure;

import java.util.*;

public class unique_integet_in_array {

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        arr.add(1);
        arr.add(2);
        arr.add(3);

        System.out.println(findUnique(arr));
    }

    private static int findUnique(List<Integer> a) {
        Collections.sort(a);

        // { 1, 1, 2, 2, 3, 3, 4 }
        int left = 0;
        int right = left + 1;

        while(right <= a.size()) {
            if (right != a.size() && a.get(left).equals(a.get(right))) {
                left += 2;
                right += 2;
            } else {
                return a.get(left);
            }
        }

        return 0;
    }
}
