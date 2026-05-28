package com.lab3.part1;

import com.lab3.ExcelExporter;
import com.lab3.Main;

public class Part1Menu {
    public static int[][] matrixA = null;
    public static int[][] matrixB = null;

    public static void show() {
        while (true) {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║    ЧАСТЬ 1 - Матрицы 7x7 (ООП)           ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║ 1. Вывести все таблицы из PostgreSQL     ║");
            System.out.println("║ 2. Создать таблицу в PostgreSQL          ║");
            System.out.println("║ 3. Ввести две матрицы 7x7               ║");
            System.out.println("║ 4. Операции с матрицами                  ║");
            System.out.println("║ 5. Сохранить данные в Excel              ║");
            System.out.println("║ 0. Назад в главное меню                  ║");
            System.out.println("╚══════════════════════════════════════════╝");

            int choice = Main.readInt("Выберите действие: ");
            switch (choice) {
                case 0 -> { return; }
                case 1 -> displayTables();
                case 2 -> Main.dbManager.createAllTables();
                case 3 -> new InputMatrixAction().execute();
                case 4 -> matrixOperationsMenu();
                case 5 -> ExcelExporter.exportAndDisplay(Main.dbManager, "matrices");
                default -> System.out.println("Неверный выбор!");
            }
        }
    }

    private static void displayTables() {
        System.out.println("\nТаблицы в БД:");
        for (String t : Main.dbManager.getAllTables()) {
            System.out.println(" - " + t);
        }
    }

    private static void matrixOperationsMenu() {
        if (matrixA == null || matrixB == null) {
            System.out.println("\nОшибка! Сначала введите матрицы (пункт 3).");
            return;
        }
        while (true) {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║     ОПЕРАЦИИ С МАТРИЦАМИ                 ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║ 1. Умножение матриц                      ║");
            System.out.println("║ 2. Сложение матриц                       ║");
            System.out.println("║ 3. Вычитание матриц                      ║");
            System.out.println("║ 4. Возведение матрицы в степень          ║");
            System.out.println("║ 0. Назад                                 ║");
            System.out.println("╚══════════════════════════════════════════╝");

            int choice = Main.readInt("Выберите операцию: ");
            switch (choice) {
                case 0 -> { return; }
                case 1 -> new MatrixMultiply(matrixA, matrixB).execute();
                case 2 -> new MatrixAdd(matrixA, matrixB).execute();
                case 3 -> new MatrixSubtract(matrixA, matrixB).execute();
                case 4 -> new MatrixPower(matrixA, matrixB).execute();
                default -> System.out.println("Неверный выбор!");
            }
        }
    }
}