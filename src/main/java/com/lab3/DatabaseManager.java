package com.lab3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private Connection connection;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/lab3_db";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "asdf";

    public void connect() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Подключение к PostgreSQL установлено.");
        } catch (SQLException e) {
            System.err.println("Ошибка подключения к БД: " + e.getMessage());
            System.exit(1);
        }
    }

    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Подключение закрыто.");
            }
        } catch (SQLException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    public void createAllTables() {
        String[] sqls = {
                // Часть 1 - матрицы
                "CREATE TABLE IF NOT EXISTS matrices (" +
                        "id SERIAL PRIMARY KEY, matrix_name VARCHAR(50), " +
                        "matrix_data TEXT, operation_type VARCHAR(50), " +
                        "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)",

                // Часть 2 - студенты
                "CREATE TABLE IF NOT EXISTS students (" +
                        "id SERIAL PRIMARY KEY, student_id VARCHAR(20) UNIQUE, " +
                        "full_name VARCHAR(200), direction VARCHAR(100), " +
                        "group_name VARCHAR(50), created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)",

                // Часть 3 - треугольники и факториалы
                "CREATE TABLE IF NOT EXISTS calculations (" +
                        "id SERIAL PRIMARY KEY, task_name VARCHAR(100), " +
                        "input_data TEXT, result TEXT, " +
                        "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)"
        };
        try (Statement stmt = connection.createStatement()) {
            for (String sql : sqls) stmt.execute(sql);
            System.out.println("Таблицы созданы.");
        } catch (SQLException e) {
            System.err.println("Ошибка создания таблиц: " + e.getMessage());
        }
    }

    // === Методы для матриц ===
    public void saveMatrix(String matrixName, String matrixData, String operationType) {
        String sql = "INSERT INTO matrices (matrix_name, matrix_data, operation_type) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, matrixName);
            ps.setString(2, matrixData);
            ps.setString(3, operationType);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ошибка сохранения матрицы: " + e.getMessage());
        }
    }

    // === Методы для студентов ===
    public void saveStudent(String studentId, String fullName, String direction, String groupName) {
        String sql = "INSERT INTO students (student_id, full_name, direction, group_name) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, studentId);
            ps.setString(2, fullName);
            ps.setString(3, direction);
            ps.setString(4, groupName);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ошибка сохранения студента: " + e.getMessage());
        }
    }

    public String getStudentById(String studentId) {
        String sql = "SELECT * FROM students WHERE student_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, studentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return String.format("ID: %s | ФИО: %s | Направление: %s | Группа: %s",
                        rs.getString("student_id"),
                        rs.getString("full_name"),
                        rs.getString("direction"),
                        rs.getString("group_name"));
            }
        } catch (SQLException e) {
            System.err.println("Ошибка поиска: " + e.getMessage());
        }
        return null;
    }

    public boolean deleteStudent(String studentId) {
        String sql = "DELETE FROM students WHERE student_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, studentId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Ошибка удаления: " + e.getMessage());
            return false;
        }
    }

    // === Методы для расчётов ===
    public void saveCalculation(String taskName, String inputData, String result) {
        String sql = "INSERT INTO calculations (task_name, input_data, result) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, taskName);
            ps.setString(2, inputData);
            ps.setString(3, result);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ошибка сохранения расчёта: " + e.getMessage());
        }
    }

    // === Общие методы ===
    public List<String> getAllTables() {
        List<String> tables = new ArrayList<>();
        String sql = "SELECT table_name FROM information_schema.tables WHERE table_schema = 'public'";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) tables.add(rs.getString("table_name"));
        } catch (SQLException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
        return tables;
    }

    public ResultSet getTableData(String tableName) {
        try {
            return connection.createStatement().executeQuery("SELECT * FROM " + tableName + " ORDER BY id");
        } catch (SQLException e) {
            System.err.println("Ошибка: " + e.getMessage());
            return null;
        }
    }
}