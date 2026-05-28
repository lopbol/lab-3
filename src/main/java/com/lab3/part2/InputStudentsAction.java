package com.lab3.part2;

import com.lab3.Main;
import java.util.Scanner;

public class InputStudentsAction {
    public void execute() {
        Scanner sc = Main.scanner;
        System.out.print("\nВведите количество студентов (минимум 5): ");
        int count = Main.readInt("");
        while (count < 5) {
            System.out.print("Минимум 5! Введите ещё раз: ");
            count = Main.readInt("");
        }

        for (int i = 0; i < count; i++) {
            System.out.println("\n--- Студент " + (i + 1) + " ---");
            System.out.print("ID студента: ");
            String id = sc.nextLine();
            System.out.print("ФИО: ");
            String name = sc.nextLine();
            System.out.print("Направление подготовки: ");
            String direction = sc.nextLine();
            System.out.print("Группа: ");
            String group = sc.nextLine();

            Student student = new Student(id, name, direction, group);
            StudentList.addStudent(student);
            Main.dbManager.saveStudent(id, name, direction, group);
        }

        StudentList.printTable();
        System.out.println("Данные сохранены в БД.");
    }
}