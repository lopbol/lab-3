package com.lab3.part3;

import com.lab3.Main;

public class TaskSolver {
    public void execute() {
        System.out.println("\n=== ПОДЗАДАЧА 1: Треугольник ===");
        System.out.println("Введите стороны треугольника:");
        double a = Main.readDouble("Сторона a: ");
        double b = Main.readDouble("Сторона b: ");
        double c = Main.readDouble("Сторона c: ");

        Triangle triangle = new Triangle(a, b, c);
        RightTriangle rightTriangle = new RightTriangle(a, b, c);

        String result1 = triangle.getInfo() + "\n" + rightTriangle.getInfo();
        System.out.println(result1);
        Main.dbManager.saveCalculation("Треугольник",
                "a=" + a + ", b=" + b + ", c=" + c, result1);

        System.out.println("\n=== ПОДЗАДАЧА 2: Факториалы ===");
        int n = Main.readInt("Введите число N: ");

        FactorialCalculator factCalc = new FactorialCalculator(n);
        String result2 = factCalc.getInfo();
        System.out.println(result2);
        Main.dbManager.saveCalculation("Факториалы", "N=" + n, result2);

        System.out.println("\nРезультаты сохранены в БД.");
    }
}