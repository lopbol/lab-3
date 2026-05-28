package com.lab3.part1;

import com.lab3.Main;

public final class MatrixSubtract extends ArrayPI {
    private int[][] matrixB;

    public MatrixSubtract(int[][] matrixA, int[][] matrixB) {
        super(matrixA);
        this.matrixB = matrixB;
    }

    public void execute() {
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix[i][j] - matrixB[i][j];
            }
        }
        ArrayPI resultMatrix = new ArrayPI(result);
        resultMatrix.printMatrix("Результат вычитания A - B");
        Main.dbManager.saveMatrix("A_minus_B", resultMatrix.matrixToString(), "вычитание");
        System.out.println("Результат сохранён в БД.");
    }
}