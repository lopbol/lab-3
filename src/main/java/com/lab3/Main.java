package com.lab3;

import com.lab3.part1.Part1Menu;
import com.lab3.part2.Part2Menu;
import com.lab3.part3.Part3Menu;
import java.util.Scanner;

public class Main {
    public static final DatabaseManager dbManager = new DatabaseManager();
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        dbManager.connect();
        dbManager.createAllTables();

        while (true) {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║        ГЛАВНОЕ МЕНЮ ЛАБОРАТОРНОЙ         ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║ 1. Часть 1 - Матрицы (ООП)               ║");
            System.out.println("║ 2. Часть 2 - Студенты (ООП)              ║");
            System.out.println("║ 3. Часть 3 - Треугольник и Факториал     ║");
            System.out.println("║ 4. Выход                                 ║");
            System.out.println("╚══════════════════════════════════════════╝");

            int choice = readInt("Выберите действие: ");
            switch (choice) {
                case 1 -> Part1Menu.show();
                case 2 -> Part2Menu.show();
                case 3 -> Part3Menu.show();
                case 4 -> {
                    System.out.println("Выход...");
                    dbManager.disconnect();
                    scanner.close();
                    return;
                }
                default -> System.out.println("Неверный выбор!");
            }
        }
    }

    public static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число!");
            }
        }
    }

    public static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число!");
            }
        }
    }
}