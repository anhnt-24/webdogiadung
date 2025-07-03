package com.example.webdogiadung.service;

import com.example.webdogiadung.StatisticsDTO.RevenueDTO;
import com.example.webdogiadung.StatisticsDTO.TopCustomerDto;
import com.example.webdogiadung.StatisticsDTO.TopProductDto;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xddf.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.*;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsExportService {

    private final StatisticsService statisticsService;

    public void exportAllStatistics(HttpServletResponse response) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();

        exportTopCustomers(workbook);
        exportTopProducts(workbook);
        exportRevenueSheet(workbook, "Revenue10Days", statisticsService.getRecent10DaysRevenue());
        exportRevenueSheet(workbook, "Revenue10Weeks", statisticsService.getRecent10WeeksRevenue());
        exportRevenueSheet(workbook, "Revenue10Months", statisticsService.getRecent10MonthsRevenue());
        exportRevenueSheet(workbook, "Revenue10Years", statisticsService.getRecent10YearsRevenue());

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        String filename = "Thong_Ke_Tong_Hop_" + java.time.LocalDate.now() + ".xlsx";
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

        workbook.write(response.getOutputStream());
        workbook.close();
    }

    private void exportTopCustomers(XSSFWorkbook workbook) {
        List<TopCustomerDto> customers = statisticsService.getTop5Customers();
        Sheet sheet = workbook.createSheet("Top5Customers");
        Row header = sheet.createRow(0);
        String[] columns = {
                "Customer Key",
                "Client ID",
                "Name",
                "Email",
                "Phone",
                "Address",
                "Total Spent",
                "Number of Orders"
        };
        for (int i = 0; i < columns.length; i++) {
            header.createCell(i).setCellValue(columns[i]);
        }
        for (int i = 0; i < customers.size(); i++) {
            TopCustomerDto customer = customers.get(i);
            Row row = sheet.createRow(i + 1);

            row.createCell(0).setCellValue(customer.getCustomerKey());
            row.createCell(1).setCellValue(customer.getClientId());
            row.createCell(2).setCellValue(customer.getName());
            row.createCell(3).setCellValue(customer.getEmail());
            row.createCell(4).setCellValue(customer.getPhone());
            row.createCell(5).setCellValue(customer.getAddress());
            row.createCell(6).setCellValue(customer.getTotalSpent().doubleValue());
            row.createCell(7).setCellValue(customer.getNumOrder());
        }
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
        createBarChart(workbook, sheet, customers.size(), 2, 6);
    }

    private void exportTopProducts(XSSFWorkbook workbook) {
        List<TopProductDto> products = statisticsService.getTop5BestSellingProducts();
        Sheet sheet = workbook.createSheet("Top5Products");
        Row header = sheet.createRow(0);
        String[] columns = {
                "Product Key",
                "Product ID",
                "Name",
                "Brand",
                "Category",
                "Selling Price",
                "Sold Quantity",
                "Rating"
        };

        for (int i = 0; i < columns.length; i++) {
            header.createCell(i).setCellValue(columns[i]);
        }
        for (int i = 0; i < products.size(); i++) {
            TopProductDto product = products.get(i);
            Row row = sheet.createRow(i + 1);

            row.createCell(0).setCellValue(product.getProductKey());
            row.createCell(1).setCellValue(product.getProductId());
            row.createCell(2).setCellValue(product.getName());
            row.createCell(3).setCellValue(product.getBrand());
            row.createCell(4).setCellValue(product.getCategory());
            row.createCell(5).setCellValue(product.getSellingPrice().doubleValue());
            row.createCell(6).setCellValue(product.getSold());
            row.createCell(7).setCellValue(product.getRating().doubleValue());
        }
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
        createBarChart(workbook, sheet, products.size(), 2, 6);
    }

    private void exportRevenueSheet(XSSFWorkbook workbook, String sheetName, List<RevenueDTO> revenues) {
        Sheet sheet = workbook.createSheet(sheetName);

        // Create header row with all columns
        Row header = sheet.createRow(0);
        String[] columns = {
                "Date",
                "Day",
                "Week",
                "Month",
                "Year",
                "Revenue",
                "Profit"
        };

        for (int i = 0; i < columns.length; i++) {
            header.createCell(i).setCellValue(columns[i]);
        }

        // Populate data rows with all revenue information
        for (int i = 0; i < revenues.size(); i++) {
            RevenueDTO revenue = revenues.get(i);
            Row row = sheet.createRow(i + 1);

            row.createCell(0).setCellValue(revenue.getDate());
            row.createCell(1).setCellValue(revenue.getDay() != null ? revenue.getDay() : 0);
            row.createCell(2).setCellValue(revenue.getWeek() != null ? revenue.getWeek() : 0);
            row.createCell(3).setCellValue(revenue.getMonth() != null ? revenue.getMonth() : 0);
            row.createCell(4).setCellValue(revenue.getYear() != null ? revenue.getYear() : 0);
            row.createCell(5).setCellValue(revenue.getRevenue().doubleValue());
            row.createCell(6).setCellValue(revenue.getProfit().doubleValue());
        }

        // Auto-size columns for better visibility
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Create combination chart
        createCombinationChart(workbook, sheet, revenues.size(), 0, 5, 6); // Date (0), Revenue (5), Profit (6)
    }

    private void createCombinationChart(XSSFWorkbook workbook, Sheet sheet, int rowCount, int labelCol, int revenueCol, int profitCol) {
        XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 8, 1, 20, 16); // Adjusted position to avoid overlapping data

        XSSFChart chart = drawing.createChart(anchor);
        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.BOTTOM);

        // Create axes
        XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
        XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        // Create right axis for profit (line)
        XDDFValueAxis rightAxis = chart.createValueAxis(AxisPosition.RIGHT);
        rightAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        // Data sources
        XDDFDataSource<String> categories = XDDFDataSourcesFactory.fromStringCellRange(
                (XSSFSheet) sheet,
                new CellRangeAddress(1, rowCount, labelCol, labelCol)
        );

        XDDFNumericalDataSource<Double> revenueValues = XDDFDataSourcesFactory.fromNumericCellRange(
                (XSSFSheet) sheet,
                new CellRangeAddress(1, rowCount, revenueCol, revenueCol)
        );

        XDDFNumericalDataSource<Double> profitValues = XDDFDataSourcesFactory.fromNumericCellRange(
                (XSSFSheet) sheet,
                new CellRangeAddress(1, rowCount, profitCol, profitCol)
        );

        // Create combination chart data
        XDDFChartData data = chart.createData(ChartTypes.BAR, bottomAxis, leftAxis);
        XDDFBarChartData barData = (XDDFBarChartData) data;
        barData.setBarDirection(BarDirection.COL);

        // Add revenue as columns (primary axis)
        XDDFChartData.Series revenueSeries = data.addSeries(categories, revenueValues);
        revenueSeries.setTitle("Revenue", null);

        // Create line chart data for profit (secondary axis)
        XDDFLineChartData lineData = (XDDFLineChartData) chart.createData(ChartTypes.LINE, bottomAxis, rightAxis);
        XDDFChartData.Series profitSeries = lineData.addSeries(categories, profitValues);
        profitSeries.setTitle("Profit", null);

        chart.plot(data);
        chart.plot(lineData);

        if (profitSeries != null) {
            XDDFShapeProperties shapeProperties = profitSeries.getShapeProperties();
            if (shapeProperties == null) {
                shapeProperties = new XDDFShapeProperties();
            }

            XDDFLineProperties lineProperties = new XDDFLineProperties();
            lineProperties.setPresetDash(new XDDFPresetLineDash(PresetLineDash.SOLID));
            lineProperties.setWidth(2.5); // Độ rộng đường line

            XDDFSolidFillProperties fillProperties = new XDDFSolidFillProperties();
            XDDFColor color = XDDFColor.from(PresetColor.RED);
            fillProperties.setColor(color);

            lineProperties.setFillProperties(fillProperties);
            shapeProperties.setLineProperties(lineProperties);

            profitSeries.setShapeProperties(shapeProperties);

        }
    }

    private void createBarChart(XSSFWorkbook workbook, Sheet sheet, int rowCount, int labelCol, int valueCol) {
        XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 3, 1, 10, 16);

        XSSFChart chart = drawing.createChart(anchor);
        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.BOTTOM);

        XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
        XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        XDDFDataSource<String> categories = XDDFDataSourcesFactory.fromStringCellRange(
                (XSSFSheet) sheet,
                new CellRangeAddress(1, rowCount, labelCol, labelCol)
        );
        XDDFNumericalDataSource<Double> values = XDDFDataSourcesFactory.fromNumericCellRange(
                (XSSFSheet) sheet,
                new CellRangeAddress(1, rowCount, valueCol, valueCol)
        );

        XDDFChartData data = chart.createData(ChartTypes.BAR, bottomAxis, leftAxis);
        XDDFBarChartData bar = (XDDFBarChartData) data;
        bar.setBarDirection(BarDirection.COL);

        XDDFChartData.Series series = data.addSeries(categories, values);
        series.setTitle("Giá trị", null);
        chart.plot(data);
    }

    private void createLineChart(XSSFWorkbook workbook, Sheet sheet, int rowCount, int labelCol, int valueCol) {
        XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 3, 1, 10, 16);

        XSSFChart chart = drawing.createChart(anchor);
        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.BOTTOM);

        XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
        XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        XDDFDataSource<String> categories = XDDFDataSourcesFactory.fromStringCellRange(
                (XSSFSheet) sheet,
                new CellRangeAddress(1, rowCount, labelCol, labelCol)
        );
        XDDFNumericalDataSource<Double> values = XDDFDataSourcesFactory.fromNumericCellRange(
                (XSSFSheet) sheet,
                new CellRangeAddress(1, rowCount, valueCol, valueCol)
        );

        XDDFChartData data = chart.createData(ChartTypes.LINE, bottomAxis, leftAxis);
        XDDFLineChartData line = (XDDFLineChartData) data;

        XDDFChartData.Series series = data.addSeries(categories, values);
        series.setTitle("Doanh thu", null);
        chart.plot(data);
    }
}