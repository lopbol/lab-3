package com.lab3.part2;

import java.util.ArrayList;
import java.util.List;

public final class StudentList extends Student {
    private static List<Student> students = new ArrayList<>();

    public StudentList(String studentId, String fullName, String direction, String groupName) {
        super(studentId, fullName, direction, groupName);
    }

    public static void addStudent(Student s) {
        students.add(s);
    }

    public static List<Student> getAll() {
        return students;
    }

    public static void printTable() {
        System.out.println("\n" + "=".repeat(85));
        System.out.println("| ID       | ФИО                       | Направление                   | Группа       |");
        System.out.println("=".repeat(85));
        for (Student s : students) {
            System.out.println(s.display());
        }
        System.out.println("=".repeat(85));
        System.out.println("Всего студентов: " + students.size());
    }
}