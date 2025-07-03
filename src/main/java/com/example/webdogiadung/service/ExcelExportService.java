package com.example.webdogiadung.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

@Service
public class ExcelExportService {

    public <T> byte[] exportToExcel(List<T> data, Class<T> clazz) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Không có dữ liệu để export.");
        }

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet(clazz.getSimpleName());

            Field[] fields = clazz.getDeclaredFields();

            // Ghi tiêu đề
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                headerRow.createCell(i).setCellValue(fields[i].getName());
            }

            // Ghi dữ liệu
            for (int rowIndex = 0; rowIndex < data.size(); rowIndex++) {
                Row row = sheet.createRow(rowIndex + 1);
                T item = data.get(rowIndex);

                for (int col = 0; col < fields.length; col++) {
                    Object value;
                    try {
                        value = fields[col].get(item);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Lỗi khi truy cập dữ liệu", e);
                    }
                    row.createCell(col).setCellValue(value != null ? value.toString() : "");
                }
            }

            workbook.write(out);
            return out.toByteArray(); // Sau khi đã đảm bảo dữ liệu đã ghi xong
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi ghi file Excel", e);
        }
    }

}
