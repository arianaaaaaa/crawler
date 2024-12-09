package crawler;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class SaveToExcel {

    // Method to save the list of properties to an Excel file
    public static void saveToExcel(List<Property> properties, String filePath) {
        if (properties == null || properties.isEmpty()) {
            System.out.println("No data to write to Excel.");
            return;
        }

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Properties");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("City");
        headerRow.createCell(1).setCellValue("Postcode");
        headerRow.createCell(2).setCellValue("House Number");
        headerRow.createCell(3).setCellValue("Price");

        // Fill the sheet with property data
        int rowNum = 1;  // Start from row 1, since row 0 is for headers
        for (Property property : properties) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(property.getCity());
            row.createCell(1).setCellValue(property.getPostcode());
            row.createCell(2).setCellValue(property.getHouseNumber());
            row.createCell(3).setCellValue(property.getPrice());
        }

        // Write the data to the file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            System.out.println("Data saved to Excel file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
