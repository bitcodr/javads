package org.algorithm_datastructure;

import java.util.*;

/**
 * N-Queens Problem using Backtracking
 *
 * Place N chess queens on an N×N chessboard so that no two queens
 * threaten each other. Queens can attack horizontally, vertically,
 * and diagonally.
 *
 * Algorithm: Backtracking
 * 1. Try placing queen in each column of current row
 * 2. Check if placement is safe (no conflicts)
 * 3. If safe, recursively try next row
 * 4. If solution found, save it
 * 5. Backtrack and try next position
 *
 * Time Complexity: O(N!) - explores all permutations
 * Space Complexity: O(N) for recursion stack
 *
 * Applications:
 * - Constraint satisfaction problems
 * - Game AI
 * - Resource allocation
 * - Algorithm design education
 */
public class n_queens {

    /**
     * Solves N-Queens and returns all solutions
     * Time Complexity: O(N!)
     * @param n Board size (N x N)
     * @return List of all solutions
     */
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        int[] queens = new int[n];  // queens[row] = column of queen in that row
        Arrays.fill(queens, -1);

        backtrack(0, n, queens, solutions);
        return solutions;
    }

    /**
     * Backtracking helper function
     * @param row Current row to place queen
     * @param n Board size
     * @param queens Array storing queen positions
     * @param solutions List to store all solutions
     */
    private static void backtrack(int row, int n, int[] queens,
                                   List<List<String>> solutions) {
        if (row == n) {
            // Found a solution
            solutions.add(createBoard(queens, n));
            return;
        }

        // Try placing queen in each column of current row
        for (int col = 0; col < n; col++) {
            if (isSafe(row, col, queens)) {
                queens[row] = col;
                backtrack(row + 1, n, queens, solutions);
                queens[row] = -1;  // Backtrack
            }
        }
    }

    /**
     * Checks if placing queen at (row, col) is safe
     * Time Complexity: O(N)
     * @param row Row to place queen
     * @param col Column to place queen
     * @param queens Current queen positions
     * @return true if safe, false otherwise
     */
    private static boolean isSafe(int row, int col, int[] queens) {
        // Check all previously placed queens
        for (int prevRow = 0; prevRow < row; prevRow++) {
            int prevCol = queens[prevRow];

            // Check same column
            if (prevCol == col) {
                return false;
            }

            // Check diagonal (difference in rows equals difference in columns)
            if (Math.abs(prevRow - row) == Math.abs(prevCol - col)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Creates board representation from queen positions
     * @param queens Queen positions
     * @param n Board size
     * @return Board as list of strings
     */
    private static List<String> createBoard(int[] queens, int n) {
        List<String> board = new ArrayList<>();

        for (int row = 0; row < n; row++) {
            StringBuilder sb = new StringBuilder();
            for (int col = 0; col < n; col++) {
                if (queens[row] == col) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            board.add(sb.toString());
        }

        return board;
    }

    /**
     * Finds just one solution for N-Queens
     * @param n Board size
     * @return First solution found, or empty list if no solution
     */
    public static List<String> solveNQueensOneSolution(int n) {
        int[] queens = new int[n];
        Arrays.fill(queens, -1);

        if (backtrackOneSolution(0, n, queens)) {
            return createBoard(queens, n);
        }

        return new ArrayList<>();
    }

    private static boolean backtrackOneSolution(int row, int n, int[] queens) {
        if (row == n) {
            return true;  // Found solution
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(row, col, queens)) {
                queens[row] = col;
                if (backtrackOneSolution(row + 1, n, queens)) {
                    return true;
                }
                queens[row] = -1;
            }
        }

        return false;  // No solution found
    }

    /**
     * Counts total number of solutions
     * @param n Board size
     * @return Number of solutions
     */
    public static int countNQueensSolutions(int n) {
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        return countSolutions(0, n, queens);
    }

    private static int countSolutions(int row, int n, int[] queens) {
        if (row == n) {
            return 1;
        }

        int count = 0;
        for (int col = 0; col < n; col++) {
            if (isSafe(row, col, queens)) {
                queens[row] = col;
                count += countSolutions(row + 1, n, queens);
                queens[row] = -1;
            }
        }

        return count;
    }

    /**
     * Prints a chess board
     * @param board Board representation
     */
    private static void printBoard(List<String> board) {
        for (String row : board) {
            System.out.println(row);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("N-Queens Problem using Backtracking\n");

        // Test 1: 4-Queens problem
        System.out.println("Test 1: 4-Queens Problem");
        int n1 = 4;
        List<List<String>> solutions1 = solveNQueens(n1);

        System.out.println("Number of solutions: " + solutions1.size());
        System.out.println("\nAll solutions for 4-Queens:");
        for (int i = 0; i < solutions1.size(); i++) {
            System.out.println("Solution " + (i + 1) + ":");
            printBoard(solutions1.get(i));
        }

        // Test 2: 8-Queens problem (classic)
        System.out.println("Test 2: 8-Queens Problem (Classic)");
        int n2 = 8;
        List<List<String>> solutions2 = solveNQueens(n2);
        System.out.println("Number of solutions: " + solutions2.size());

        System.out.println("\nFirst solution:");
        if (!solutions2.isEmpty()) {
            printBoard(solutions2.get(0));
        }

        // Test 3: Count solutions for different N
        System.out.println("Test 3: Count Solutions for Different N");
        for (int n = 1; n <= 10; n++) {
            int count = countNQueensSolutions(n);
            System.out.println("N = " + n + ": " + count + " solutions");
        }
        System.out.println();

        // Test 4: Edge cases
        System.out.println("Test 4: Edge Cases");

        // N = 1
        System.out.println("N = 1:");
        List<List<String>> sol1 = solveNQueens(1);
        if (!sol1.isEmpty()) {
            printBoard(sol1.get(0));
        }

        // N = 2 (no solution)
        System.out.println("N = 2 (no solution exists):");
        List<List<String>> sol2 = solveNQueens(2);
        System.out.println("Number of solutions: " + sol2.size());
        System.out.println();

        // N = 3 (no solution)
        System.out.println("N = 3 (no solution exists):");
        List<List<String>> sol3 = solveNQueens(3);
        System.out.println("Number of solutions: " + sol3.size());
        System.out.println();

        // Test 5: Find just one solution quickly
        System.out.println("Test 5: Find One Solution (Faster)");
        int n5 = 8;
        List<String> oneSolution = solveNQueensOneSolution(n5);

        if (!oneSolution.isEmpty()) {
            System.out.println("One solution for " + n5 + "-Queens:");
            printBoard(oneSolution);
        }

        // Test 6: Visualize 5-Queens
        System.out.println("Test 6: 5-Queens Problem");
        int n6 = 5;
        List<List<String>> solutions6 = solveNQueens(n6);

        System.out.println("Number of solutions: " + solutions6.size());
        System.out.println("\nFirst 2 solutions:");
        for (int i = 0; i < Math.min(2, solutions6.size()); i++) {
            System.out.println("Solution " + (i + 1) + ":");
            printBoard(solutions6.get(i));
        }

        // Test 7: Performance test
        System.out.println("Test 7: Performance Test");
        int n7 = 10;

        System.out.println("Solving " + n7 + "-Queens...");
        long startTime = System.nanoTime();
        int count = countNQueensSolutions(n7);
        long endTime = System.nanoTime();

        System.out.println("Solutions found: " + count);
        System.out.println("Time taken: " + (endTime - startTime) / 1_000_000.0 + " ms");
        System.out.println();

        // Test 8: Validate solution
        System.out.println("Test 8: Validate Solutions");
        int n8 = 6;
        List<List<String>> solutions8 = solveNQueens(n8);

        System.out.println(n8 + "-Queens problem:");
        System.out.println("Total solutions: " + solutions8.size());

        if (!solutions8.isEmpty()) {
            System.out.println("\nFirst solution (validated):");
            List<String> firstSol = solutions8.get(0);
            printBoard(firstSol);

            // Verify no two queens attack each other
            int[] positions = new int[n8];
            for (int row = 0; row < n8; row++) {
                positions[row] = firstSol.get(row).indexOf('Q');
            }

            boolean valid = true;
            for (int i = 0; i < n8; i++) {
                for (int j = i + 1; j < n8; j++) {
                    if (positions[i] == positions[j] ||
                        Math.abs(i - j) == Math.abs(positions[i] - positions[j])) {
                        valid = false;
                        break;
                    }
                }
            }

            System.out.println("Solution is valid: " + valid);
        }
    }
}
