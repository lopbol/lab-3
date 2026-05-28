package com.lab3.part2;

import com.lab3.ExcelExporter;
import com.lab3.Main;

public class Part2Menu {
    public static void show() {
        while (true) {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║    ЧАСТЬ 2 - Студенты (ООП)              ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║ 1. Вывести все таблицы из PostgreSQL     ║");
            System.out.println("║ 2. Создать таблицу в PostgreSQL          ║");
            System.out.println("║ 3. Ввести данные о студентах             ║");
            System.out.println("║ 4. Найти студента по ID                  ║");
            System.out.println("║ 5. Удалить студента по ID                ║");
            System.out.println("║ 6. Сохранить в Excel и вывести           ║");
            System.out.println("║ 0. Назад в главное меню                  ║");
            System.out.println("╚══════════════════════════════════════════╝");

            int choice = Main.readInt("Выберите действие: ");
            switch (choice) {
                case 0 -> { return; }
                case 1 -> displayTables();
                case 2 -> Main.dbManager.createAllTables();
                case 3 -> new InputStudentsAction().execute();
                case 4 -> new StudentByIdAction().execute();
                case 5 -> new DeleteStudentAction().execute();
                case 6 -> ExcelExporter.exportAndDisplay(Main.dbManager, "students");
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
}