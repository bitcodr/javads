package org.algorithm_datastructure;

import java.util.ArrayList;
import java.util.List;

public class matrix_diagonals_difference {

    public static void main(String[] args) {
        List<List<Integer>> arr = new ArrayList<>();

        List<Integer> i1 = new ArrayList<>();
        i1.add(11);
        i1.add(2);
        i1.add(4);

        arr.add(i1);

        List<Integer> i2 = new ArrayList<>();
        i2.add(4);
        i2.add(5);
        i2.add(6);

        arr.add(i2);

        List<Integer> i3 = new ArrayList<>();
        i3.add(10);
        i3.add(8);
        i3.add(-12);

        arr.add(i3);

        System.out.println(difference(arr));
    }

    private static int difference(List<List<Integer>> arr) {
        int leftToRightRow = 0;
        int leftToRightCol = 0;
        int leftToRightResult = 0;

        while (leftToRightRow < arr.size()) {
            leftToRightResult += arr.get(leftToRightRow).get(leftToRightCol);

            leftToRightRow++;
            leftToRightCol++;
        }


        int rightToLeftRow = 0;
        int rightToleftCol = arr.size() - 1;
        int rightToleftResult = 0;

        while (rightToleftCol >= 0) {
            rightToleftResult += arr.get(rightToLeftRow).get(rightToleftCol);

            rightToLeftRow++;
            rightToleftCol--;
        }

        // O(n+n)
        // O(n)

        return Math.abs(leftToRightResult - rightToleftResult);
    }
}
