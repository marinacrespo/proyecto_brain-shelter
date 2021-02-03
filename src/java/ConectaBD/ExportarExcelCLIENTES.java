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

public class ExportarExcelCLIENTES {

    ConectaBD bd;

    public void export() {

        String excelFilePath = "C:\\xampp\\tomcat\\webapps\\excel\\listadoclientes.xlsx";

        try {
            bd = new ConectaBD();
            bd.conectar();
            PreparedStatement ps;
            String sql = "SELECT u.cod_user, u.login, u.password, c.cod_cli, c.nombre, c.apellidos, c.telefono, c.email, c.direccion FROM usuarios u JOIN clientes c WHERE u.cod_user=c.cod_user";

            ps = bd.getConexion().prepareStatement(sql);

            ResultSet result = ps.executeQuery();

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("listadoclientes");

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
        headerCell.setCellValue("u.cod_user");

        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("u.login");

        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("u.password");

        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("c.cod_cli");

        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("c.nombre");

        headerCell = headerRow.createCell(5);
        headerCell.setCellValue("c.apellidos");

        headerCell = headerRow.createCell(6);
        headerCell.setCellValue("c.telefono");

        headerCell = headerRow.createCell(7);
        headerCell.setCellValue("c.email");

        headerCell = headerRow.createCell(8);
        headerCell.setCellValue("c.direccion");
    }

    private void writeDataLines(ResultSet result, XSSFWorkbook workbook,
            XSSFSheet sheet) throws SQLException {
        int rowCount = 1;

        while (result.next()) {
            int cod_user = result.getInt("u.cod_user");
            String u_login = result.getString("u.login");
            String password = result.getString("u.password");
            int cod_cliente = result.getInt("c.cod_cli");
            String nombre = result.getString("c.nombre");
            String apellidos = result.getString("c.apellidos");
            String telefono = result.getString("c.telefono");
            String email = result.getString("c.email");
            String direccion = result.getString("c.direccion");
            
            
            
            Row row = (Row) sheet.createRow(rowCount++);

            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(cod_user);

            cell = row.createCell(columnCount++);
            cell.setCellValue(u_login);

            cell = row.createCell(columnCount++);

            /*CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper creationHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
            cell.setCellStyle(cellStyle);*/
            cell.setCellValue(password);

            cell = row.createCell(columnCount++);
            cell.setCellValue(cod_cliente);

            cell = row.createCell(columnCount++);
            cell.setCellValue(nombre);
            
            cell = row.createCell(columnCount++);
            cell.setCellValue(apellidos);
            
            cell = row.createCell(columnCount++);
            cell.setCellValue(telefono);
            
            cell = row.createCell(columnCount++);
            cell.setCellValue(email);
            
            cell = row.createCell(columnCount);
            cell.setCellValue(direccion);

        }
    }


}
