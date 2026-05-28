package com.lab3.part3;

import com.lab3.ExcelExporter;
import com.lab3.Main;

public class Part3Menu {
    public static void show() {
        while (true) {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║  ЧАСТЬ 3 - Треугольник и Факториал       ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║ 1. Вывести все таблицы из PostgreSQL     ║");
            System.out.println("║ 2. Создать таблицу в PostgreSQL          ║");
            System.out.println("║ 3. Решить задачи (треугольник + факториал)║");
            System.out.println("║ 4. Вывести данные по ID                  ║");
            System.out.println("║ 5. Сохранить в Excel и вывести           ║");
            System.out.println("║ 0. Назад в главное меню                  ║");
            System.out.println("╚══════════════════════════════════════════╝");

            int choice = Main.readInt("Выберите действие: ");
            switch (choice) {
                case 0 -> { return; }
                case 1 -> displayTables();
                case 2 -> Main.dbManager.createAllTables();
                case 3 -> new TaskSolver().execute();
                case 4 -> displayById();
                case 5 -> ExcelExporter.exportAndDisplay(Main.dbManager, "calculations");
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

    private static void displayById() {
        int id = Main.readInt("Введите ID строки: ");
        try {
            var rs = Main.dbManager.getTableData("calculations");
            if (rs == null) return;
            while (rs.next()) {
                if (rs.getInt("id") == id) {
                    System.out.println("\n=== Строка ID=" + id + " ===");
                    System.out.println("Задача: " + rs.getString("task_name"));
                    System.out.println("Входные данные: " + rs.getString("input_data"));
                    System.out.println("Результат: " + rs.getString("result"));
                    return;
                }
            }
            System.out.println("Строка с ID=" + id + " не найдена.");
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}