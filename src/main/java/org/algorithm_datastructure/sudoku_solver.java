package org.algorithm_datastructure;

/**
 * Sudoku Solver using Backtracking
 *
 * Solves a 9x9 Sudoku puzzle. Rules:
 * 1. Each row must contain digits 1-9 without repetition
 * 2. Each column must contain digits 1-9 without repetition
 * 3. Each 3x3 sub-box must contain digits 1-9 without repetition
 *
 * Time Complexity: O(9^m) where m is number of empty cells (worst case)
 * Space Complexity: O(1) additional space (modifies in-place)
 *
 * Applications:
 * - Puzzle solving
 * - Constraint satisfaction problems
 * - Game AI
 */
public class sudoku_solver {

    /**
     * Solves Sudoku puzzle using backtracking
     * @param board 9x9 Sudoku board (0 represents empty cell)
     * @return true if solved, false if no solution
     */
    public static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    // Try digits 1-9
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;

                            if (solveSudoku(board)) {
                                return true;
                            }

                            // Backtrack
                            board[row][col] = 0;
                        }
                    }
                    return false;  // No valid number found
                }
            }
        }
        return true;  // All cells filled
    }

    /**
     * Checks if placing num at (row, col) is valid
     * @param board Sudoku board
     * @param row Row index
     * @param col Column index
     * @param num Number to place (1-9)
     * @return true if valid, false otherwise
     */
    private static boolean isValid(int[][] board, int row, int col, int num) {
        // Check row
        for (int c = 0; c < 9; c++) {
            if (board[row][c] == num) {
                return false;
            }
        }

        // Check column
        for (int r = 0; r < 9; r++) {
            if (board[r][col] == num) {
                return false;
            }
        }

        // Check 3x3 sub-box
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;

        for (int r = boxRow; r < boxRow + 3; r++) {
            for (int c = boxCol; c < boxCol + 3; c++) {
                if (board[r][c] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Validates if Sudoku board is correct
     * @param board Sudoku board
     * @return true if valid, false otherwise
     */
    public static boolean isValidSudoku(int[][] board) {
        // Check rows and columns
        for (int i = 0; i < 9; i++) {
            boolean[] rowCheck = new boolean[10];
            boolean[] colCheck = new boolean[10];

            for (int j = 0; j < 9; j++) {
                // Check row
                if (board[i][j] != 0) {
                    if (rowCheck[board[i][j]]) return false;
                    rowCheck[board[i][j]] = true;
                }

                // Check column
                if (board[j][i] != 0) {
                    if (colCheck[board[j][i]]) return false;
                    colCheck[board[j][i]] = true;
                }
            }
        }

        // Check 3x3 boxes
        for (int boxRow = 0; boxRow < 9; boxRow += 3) {
            for (int boxCol = 0; boxCol < 9; boxCol += 3) {
                boolean[] boxCheck = new boolean[10];

                for (int r = boxRow; r < boxRow + 3; r++) {
                    for (int c = boxCol; c < boxCol + 3; c++) {
                        if (board[r][c] != 0) {
                            if (boxCheck[board[r][c]]) return false;
                            boxCheck[board[r][c]] = true;
                        }
                    }
                }
            }
        }

        return true;
    }

    /**
     * Prints Sudoku board
     * @param board Sudoku board
     */
    private static void printBoard(int[][] board) {
        for (int row = 0; row < 9; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("------+-------+------");
            }

            for (int col = 0; col < 9; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print("| ");
                }

                if (board[row][col] == 0) {
                    System.out.print(". ");
                } else {
                    System.out.print(board[row][col] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("Sudoku Solver using Backtracking\n");

        // Test 1: Easy Sudoku
        System.out.println("Test 1: Easy Sudoku");
        int[][] board1 = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        System.out.println("Original Board:");
        printBoard(board1);

        if (solveSudoku(board1)) {
            System.out.println("Solved Board:");
            printBoard(board1);
            System.out.println("Solution is valid: " + isValidSudoku(board1));
        } else {
            System.out.println("No solution exists");
        }

        // Test 2: Another puzzle
        System.out.println("Test 2: Another Puzzle");
        int[][] board2 = {
            {0, 0, 0, 2, 6, 0, 7, 0, 1},
            {6, 8, 0, 0, 7, 0, 0, 9, 0},
            {1, 9, 0, 0, 0, 4, 5, 0, 0},
            {8, 2, 0, 1, 0, 0, 0, 4, 0},
            {0, 0, 4, 6, 0, 2, 9, 0, 0},
            {0, 5, 0, 0, 0, 3, 0, 2, 8},
            {0, 0, 9, 3, 0, 0, 0, 7, 4},
            {0, 4, 0, 0, 5, 0, 0, 3, 6},
            {7, 0, 3, 0, 1, 8, 0, 0, 0}
        };

        System.out.println("Original Board:");
        printBoard(board2);

        if (solveSudoku(board2)) {
            System.out.println("Solved Board:");
            printBoard(board2);
            System.out.println("Solution is valid: " + isValidSudoku(board2));
        }

        // Test 3: Hard puzzle
        System.out.println("Test 3: Hard Puzzle");
        int[][] board3 = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 3, 0, 8, 5},
            {0, 0, 1, 0, 2, 0, 0, 0, 0},
            {0, 0, 0, 5, 0, 7, 0, 0, 0},
            {0, 0, 4, 0, 0, 0, 1, 0, 0},
            {0, 9, 0, 0, 0, 0, 0, 0, 0},
            {5, 0, 0, 0, 0, 0, 0, 7, 3},
            {0, 0, 2, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 4, 0, 0, 0, 9}
        };

        System.out.println("Original Board (Hard):");
        printBoard(board3);

        System.out.println("Solving...");
        long startTime = System.currentTimeMillis();
        boolean solved = solveSudoku(board3);
        long endTime = System.currentTimeMillis();

        if (solved) {
            System.out.println("Solved in " + (endTime - startTime) + " ms");
            System.out.println("Solved Board:");
            printBoard(board3);
            System.out.println("Solution is valid: " + isValidSudoku(board3));
        }

        // Test 4: Invalid puzzle (no solution)
        System.out.println("Test 4: Invalid Puzzle (No Solution)");
        int[][] board4 = {
            {1, 2, 3, 4, 5, 6, 7, 8, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 2},
            {0, 0, 0, 0, 0, 0, 0, 0, 3},
            {0, 0, 0, 0, 0, 0, 0, 0, 4},
            {0, 0, 0, 0, 0, 0, 0, 0, 5},
            {0, 0, 0, 0, 0, 0, 0, 0, 6},
            {0, 0, 0, 0, 0, 0, 0, 0, 7},
            {0, 0, 0, 0, 0, 0, 0, 0, 8},
            {0, 0, 0, 0, 0, 0, 0, 0, 9}
        };

        System.out.println("Invalid Board:");
        printBoard(board4);

        if (solveSudoku(board4)) {
            System.out.println("Solved (unexpected)");
        } else {
            System.out.println("No solution exists (expected)");
        }
    }
}
