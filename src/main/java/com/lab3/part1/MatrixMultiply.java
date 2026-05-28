package com.lab3.part1;

import com.lab3.Main;

public final class MatrixMultiply extends ArrayPI {
    private int[][] matrixB;

    public MatrixMultiply(int[][] matrixA, int[][] matrixB) {
        super(matrixA);
        this.matrixB = matrixB;
    }

    public void execute() {
        int[][] result = new int[rows][matrixB[0].length];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                for (int k = 0; k < cols; k++) {
                    result[i][j] += matrix[i][k] * matrixB[k][j];
                }
            }
        }
        ArrayPI resultMatrix = new ArrayPI(result);
        resultMatrix.printMatrix("Результат умножения A × B");
        Main.dbManager.saveMatrix("A_x_B", resultMatrix.matrixToString(), "умножение");
        System.out.println("Результат сохранён в БД.");
    }
}