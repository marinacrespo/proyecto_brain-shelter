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


public class ExportarExcelVENTAS {
    
    ConectaBD bd;

    public void export() {

        String excelFilePath = "C:\\xampp\\tomcat\\webapps\\excel\\listadoventas.xlsx";

        try {
            bd = new ConectaBD();
            bd.conectar();
            PreparedStatement ps;
            String sql = "SELECT dp.cod_ped, dp.cod_art, dp.fecha_ped, (dp.cantidad*dp.precio_arts) AS total, p.cod_cli, c.nombre, c.apellidos FROM detallespedido dp RIGHT JOIN pedidos p ON dp.cod_ped=p.cod_ped RIGHT JOIN clientes c ON p.cod_cli=c.cod_cli WHERE dp.cod_ped IS NOT NULL";

            ps = bd.getConexion().prepareStatement(sql);

            ResultSet result = ps.executeQuery();

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("listadoventas");

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
        headerCell.setCellValue("dp.cod_ped");

        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("dp.cod_art");
        
        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("dp.fecha_ped");

        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("total");

        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("p.cod_cli");

        headerCell = headerRow.createCell(5);
        headerCell.setCellValue("c.nombre");
        
        headerCell = headerRow.createCell(6);
        headerCell.setCellValue("c.apellidos");
    }

    private void writeDataLines(ResultSet result, XSSFWorkbook workbook,
            XSSFSheet sheet) throws SQLException {
        int rowCount = 1;

        while (result.next()) {
            int id_pedido = result.getInt("dp.cod_ped");
            int id_producto = result.getInt("dp.cod_art");
            String fecha_ped = result.getString("dp.fecha_ped");
            Double total = result.getDouble("total");
            int cod_cli = result.getInt("p.cod_cli");
            String nombre = result.getString("c.nombre");
            String apellidos = result.getString("c.apellidos");

            Row row = (Row) sheet.createRow(rowCount++);

            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(id_pedido);

            cell = row.createCell(columnCount++);
            cell.setCellValue(id_producto);
            
            cell = row.createCell(columnCount++);
            cell.setCellValue(fecha_ped);

            cell = row.createCell(columnCount++);
            cell.setCellValue(total);

            cell = row.createCell(columnCount++);
            cell.setCellValue(cod_cli);

            cell = row.createCell(columnCount++);
            cell.setCellValue(nombre);
            
            cell = row.createCell(columnCount);
            cell.setCellValue(apellidos);

        }
    }
 
    
}
