package org.algorithm_datastructure;

// Square root
// Given a non-negative integer x, return the square root of x rounded down to the nearest integer.
// The returned integer should be non-negative as well.
public class sqr {
    public static void main(String[] args) {
        int target = 8;
        int low = 2, high = target / 2;
        int found = sqroot(target, low, high);
        System.out.println(found);
    }

    private static int sqroot(int target, int low, int high) {
        if (target < 2) {
            return target; // 0 or 1
        }

        if (low >= high) {
            return high;
        }

        int middle = low + (high - low) / 2;

        long sq = (long) middle * middle;

        if (sq == target) {
            return middle;
        }

        if (sq > target) {
            return sqroot(target, low, middle - 1);
        }

        return sqroot(target, middle + 1, high);
    }
}
