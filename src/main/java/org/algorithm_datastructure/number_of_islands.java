package org.algorithm_datastructure;

// DFS algorithm will look like. We will update the input matrix to mark cells visited.
public class number_of_islands {

    public static void main(String[] args) {
        int[][] matrix = {
                { 0, 1, 1, 1, 0 },
                { 0, 0, 0, 1, 1 },
                { 0, 1, 1, 1, 0 },
                { 0, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0 }
        };
        System.out.println(countIslands(matrix));
    }

    // 0, 1, 1, 1, 0
    // 0, 0, 0, 1, 1
    // 0, 1, 1, 1, 0
    // 0, 1, 1, 0, 0
    // 0, 0, 0, 0, 0

    // T -> O(M * N)
    // S -> O(M * N)
    private static int countIslands(int[][] matrix) {
        int totalIslands = 0;

        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1) { // only if the cell is a land
                    totalIslands++; // we have found an island
                    visitIslandDFS(matrix, i, j);
                }
            }
        }

        return totalIslands;
    }

    // DFS
    private static void visitIslandDFS(int[][] matrix, int i, int j) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[i].length) {
            return; // return, if it is not a valid cell
        }

        if (matrix[i][j] == 0) {
            return; // return, if it is a water cell
        }

        matrix[i][j] = 0; // mark the cell visited by making it a water cell

        // recursively visit all neighboring cells (horizontally & vertically)
        visitIslandDFS(matrix, i + 1, j);
        visitIslandDFS(matrix, i - 1, j);
        visitIslandDFS(matrix, i, j + 1);
        visitIslandDFS(matrix, i, j - 1);
    }

    // BFS
    private static void visitIslandsBFS(int[][] matrix, int i, int j) {

    }

    // BFS with visited matrix
    private static void visitIslandsBFSMatrix(int[][] matrix, int i, int j) {

    }


}
