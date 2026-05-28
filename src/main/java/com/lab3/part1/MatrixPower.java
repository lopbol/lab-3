package com.lab3.part1;

import com.lab3.Main;

public final class MatrixPower extends ArrayPI {
    private int[][] matrixB;

    public MatrixPower(int[][] matrixA, int[][] matrixB) {
        super(matrixA);
        this.matrixB = matrixB;
    }

    public void execute() {
        int power = Main.readInt("Введите степень (2-5): ");
        if (power < 2 || power > 5) {
            System.out.println("Степень должна быть от 2 до 5!");
            return;
        }

        System.out.println("1. Возвести в степень матрицу A");
        System.out.println("2. Возвести в степень матрицу B");
        int choice = Main.readInt("Выбор: ");

        int[][] selected = (choice == 1) ? matrix : matrixB;
        int[][] result = powerMatrix(selected, power);

        String name = (choice == 1) ? "A^" + power : "B^" + power;
        ArrayPI resultMatrix = new ArrayPI(result);
        resultMatrix.printMatrix("Результат " + name);
        Main.dbManager.saveMatrix(name, resultMatrix.matrixToString(), "возведение в степень");
        System.out.println("Результат сохранён в БД.");
    }

    private int[][] powerMatrix(int[][] m, int power) {
        int n = m.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            result[i][i] = 1; // единичная матрица
        }

        for (int p = 0; p < power; p++) {
            int[][] temp = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        temp[i][j] += result[i][k] * m[k][j];
                    }
                }
            }
            result = temp;
        }
        return result;
    }
}