package com.lab3.part1;

import com.lab3.Main;
import java.util.Scanner;

public class InputMatrixAction {
    public void execute() {
        Scanner sc = Main.scanner;
        Part1Menu.matrixA = inputMatrix(sc, "A");
        Part1Menu.matrixB = inputMatrix(sc, "B");

        new ArrayPI(Part1Menu.matrixA).printMatrix("Матрица A");
        new ArrayPI(Part1Menu.matrixB).printMatrix("Матрица B");

        Main.dbManager.saveMatrix("A", new ArrayPI(Part1Menu.matrixA).matrixToString(), "ввод");
        Main.dbManager.saveMatrix("B", new ArrayPI(Part1Menu.matrixB).matrixToString(), "ввод");
        System.out.println("Матрицы сохранены в БД.");
    }

    private int[][] inputMatrix(Scanner sc, String name) {
        System.out.println("\nВвод матрицы " + name + " (7x7):");
        int[][] matrix = new int[7][7];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.printf("Элемент [%d][%d]: ", i, j);
                matrix[i][j] = Main.readInt("");
            }
        }
        return matrix;
    }
}