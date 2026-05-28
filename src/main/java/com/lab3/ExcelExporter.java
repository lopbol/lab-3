package com.lab3;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

public class ExcelExporter {

    public static void exportAndDisplay(DatabaseManager dbManager, String tableName) {
        String fileName = tableName + "_export.xlsx";
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet(tableName);
            ResultSet rs = dbManager.getTableData(tableName);
            if (rs == null) {
                System.out.println("Таблица пуста.");
                return;
            }
            ResultSetMetaData meta = rs.getMetaData();
            int cols = meta.getColumnCount();

            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            Row headerRow = sheet.createRow(0);
            for (int i = 1; i <= cols; i++) {
                Cell cell = headerRow.createCell(i - 1);
                cell.setCellValue(meta.getColumnName(i));
                cell.setCellStyle(headerStyle);
            }

            int rowNum = 1;
            while (rs.next()) {
                Row row = sheet.createRow(rowNum++);
                for (int i = 1; i <= cols; i++) {
                    String val = rs.getString(i);
                    if (val != null) row.createCell(i - 1).setCellValue(val);
                }
            }

            for (int i = 0; i < cols; i++) sheet.autoSizeColumn(i);

            try (FileOutputStream fos = new FileOutputStream(fileName)) {
                workbook.write(fos);
            }
            System.out.println("Экспортировано в " + fileName);

            // Вывод в консоль
            displayTable(dbManager, tableName);

        } catch (IOException | SQLException e) {
            System.err.println("Ошибка экспорта: " + e.getMessage());
        }
    }

    private static void displayTable(DatabaseManager dbManager, String tableName) {
        System.out.println("\n=== Содержимое таблицы " + tableName + " ===");
        try {
            ResultSet rs = dbManager.getTableData(tableName);
            if (rs == null) return;
            ResultSetMetaData meta = rs.getMetaData();
            int cols = meta.getColumnCount();

            for (int i = 1; i <= cols; i++) {
                System.out.printf("%-25s", meta.getColumnName(i));
            }
            System.out.println();
            System.out.println("-".repeat(cols * 25));

            int count = 0;
            while (rs.next() && count < 20) {
                for (int i = 1; i <= cols; i++) {
                    String val = rs.getString(i);
                    if (val != null && val.length() > 22) val = val.substring(0, 20) + "...";
                    System.out.printf("%-25s", val != null ? val : "NULL");
                }
                System.out.println();
                count++;
            }
        } catch (SQLException e) {
            System.err.println("Ошибка вывода: " + e.getMessage());
        }
    }
}