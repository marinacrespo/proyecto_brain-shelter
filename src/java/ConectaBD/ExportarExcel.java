/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConectaBD;

/**
 *
 * @author Alumno
 */
import java.io.*;
import java.sql.*;
import java.sql.PreparedStatement;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ExportarExcel {

    ConectaBD bd;

    public void export() {

        String excelFilePath = "C:\\xampp\\tomcat\\webapps\\excel\\listado.xlsx";

        try {
            bd = new ConectaBD();
            bd.conectar();
            PreparedStatement ps;
            String sql = "SELECT * FROM articulos";

            ps = bd.getConexion().prepareStatement(sql);

            ResultSet result = ps.executeQuery();

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("listado");

            writeHeaderLine(sheet);

            writeDataLines(result, workbook, sheet);

            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();

            ps.close();

        } catch (SQLException e) {
            System.out.println("Datababse error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }
    }

    private void writeHeaderLine(XSSFSheet sheet) {

        Row headerRow = (Row) sheet.createRow(0);

        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("cod_art");

        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("titulo");

        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("desc_art");

        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("precio");

        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("unidades");
    }

    private void writeDataLines(ResultSet result, XSSFWorkbook workbook,
            XSSFSheet sheet) throws SQLException {
        int rowCount = 1;

        while (result.next()) {
            int id_producto = result.getInt("cod_art");
            String titulo = result.getString("titulo");
            String descripcion = result.getString("desc_art");
            Float precio = result.getFloat("precio");
            int unidades = result.getInt("unidades");

            Row row = (Row) sheet.createRow(rowCount++);

            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(id_producto);

            cell = row.createCell(columnCount++);
            cell.setCellValue(titulo);

            cell = row.createCell(columnCount++);

            /*CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper creationHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
            cell.setCellStyle(cellStyle);*/
            cell.setCellValue(descripcion);

            cell = row.createCell(columnCount++);
            cell.setCellValue(precio);

            cell = row.createCell(columnCount);
            cell.setCellValue(unidades);

        }
    }

    

}
