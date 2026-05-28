package com.lab3.part2;

import com.lab3.Main;
import java.util.Scanner;

public class DeleteStudentAction {
    public void execute() {
        Scanner sc = Main.scanner;
        System.out.print("\nВведите ID студента для удаления: ");
        String id = sc.nextLine();
        boolean deleted = Main.dbManager.deleteStudent(id);
        if (deleted) {
            System.out.println("Студент с ID " + id + " удалён.");
        } else {
            System.out.println("Студент с ID " + id + " не найден.");
        }
    }
}