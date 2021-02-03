/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConectaBD;


 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;




public class ConectaBD {

    // otras VARIABLES GLOBALES
    Connection conexion = null;
    Statement statement = null;

    // CONSTRUCTOR
    public ConectaBD() throws IOException {

    }

    // METODOS AUXILIARES
    public void conectar() throws FileNotFoundException, IOException {

        String driver = "jdbc:mysql";
        String servidor = "//localhost";
        String puerto = "3306";
        String bd = "brain_shelter";
        String usuario = "root";
        String contrasena = "";

        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de MySQL: " + ex);
            }

            conexion = DriverManager.getConnection(
                    (driver + ":" + servidor + ":" + puerto + "/" + bd),
                    usuario, contrasena);

        } catch (Exception e) {
            System.out.println("Error NO MANEJADO.");
            e.printStackTrace();
        }

    }

    public Connection getConexion() {

        return conexion;
    }

    public int desconectar() {

        try {
            statement.close();
            conexion.close();
            return 1;

        } catch (SQLException ex) {
            Logger.getLogger(ConectaBD.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } catch (Exception e1) {
            System.out.println("HAY UN ERROR NO CONTROLADO");
            e1.printStackTrace();
            return -2;
        }

    }

    public int ejecutaSQL(String consulta) {

        int result = -1; //-1 poq es el codigo de error si no sale que le doy
        try {
            result = statement.executeUpdate(consulta);
            System.out.println("Ejecución realizada");
        } catch (SQLException e) {
            System.out.println("Error al crear el Statement.");
            e.printStackTrace();
        }
        return result;

    }

    public ResultSet ejecutaSelectStatement(String consulta) {
        ResultSet result = null;
        Statement statement = null;// 
        try {

            result = statement.executeQuery(consulta);
            System.out.println("Consulta realizada");
        } catch (SQLException e) {

            System.out.println("Error al crear el Statement.");
            e.printStackTrace();
        }
        return result;
    }

    public String autentificacion(String user, String pass) throws SQLException {

        PreparedStatement p;
        ResultSet rs;
        String estado = "";

        try {

            String sql = "SELECT estado FROM usuarios WHERE login=? AND password=?";

            p = conexion.prepareStatement(sql);

            p.setString(1, user);
            p.setString(2, pass);

            rs = p.executeQuery();

            if (rs.next()) {

                estado = rs.getString("estado");

            }

        } catch (SQLException e) {

            System.out.println("Hay un error" + e);

        }

        return estado;
    }

    
    // ESTE METODO NO APLICA AQUI PORQUE ERA DE CENTRALITA Y NI SIQUIERA LLEGASTE A PROBARLO
    public int insertarEmpleado(String Enombre, String Eestado, String Elogin, String Epassword) {

        int resultado = 0;

        try {
            PreparedStatement ps;
            ResultSet rs;

            String sql1 = "select login, password from empleados where login ='" + Elogin + "' OR password='" + Epassword + "'";

            String sql2 = "insert into empleados (nombre_emp,estado, login, password) VALUES (?, ?, ?, ?)";

            ps = conexion.prepareStatement(sql1);
            rs = ps.executeQuery(sql1);

            if (rs.next()) {

                resultado = -1;
            } else {

                try {
                    PreparedStatement ps2;

                    ps2 = conexion.prepareStatement(sql2);
                    ps2.setString(1, Enombre);
                    ps2.setString(2, Eestado);
                    ps2.setString(3, Elogin);
                    ps2.setString(4, Epassword);
                    ps2.executeUpdate();

                    resultado = 1;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;

    }
}

