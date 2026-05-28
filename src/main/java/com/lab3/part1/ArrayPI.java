package com.lab3.part1;

public class ArrayPI {
    protected int[][] matrix;
    protected int rows;
    protected int cols;

    public ArrayPI(int[][] matrix) {
        this.matrix = matrix;
        this.rows = matrix.length;
        this.cols = matrix[0].length;
    }

    public void printMatrix(String title) {
        System.out.println("\n" + title + ":");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("%8d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    public String matrixToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append(matrix[i][j]);
                if (j < cols - 1) sb.append(" ");
            }
            if (i < rows - 1) sb.append("\n");
        }
        return sb.toString();
    }
}