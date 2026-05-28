package com.lab3.part2;

public class Student {
    private String studentId;
    private String fullName;
    private String direction;
    private String groupName;

    public Student(String studentId, String fullName, String direction, String groupName) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.direction = direction;
        this.groupName = groupName;
    }

    public String getStudentId() { return studentId; }
    public String getFullName() { return fullName; }
    public String getDirection() { return direction; }
    public String getGroupName() { return groupName; }

    public String display() {
        return String.format("| %-8s | %-25s | %-30s | %-12s |",
                studentId, fullName, direction, groupName);
    }
}