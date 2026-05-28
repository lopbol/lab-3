package com.lab3.part2;

import com.lab3.Main;
import java.util.Scanner;

public class StudentByIdAction {
    public void execute() {
        Scanner sc = Main.scanner;
        System.out.print("\nВведите ID студента для поиска: ");
        String id = sc.nextLine();
        String result = Main.dbManager.getStudentById(id);
        if (result != null) {
            System.out.println("\nНайден студент:");
            System.out.println(result);
        } else {
            System.out.println("Студент с ID " + id + " не найден.");
        }
    }
}