/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets_Admin;


import Clases.Clientes;
import ConectaBD.ConectaBD;
import ConectaBD.ExportarExcelCLIENTES;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alumno
 */
@WebServlet(name = "ListarClientes", urlPatterns = {"/ListarClientes"})
public class ListarClientes extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            ConectaBD bd = new ConectaBD();
            bd.conectar();

            HttpSession sesion = request.getSession();

            ArrayList listaclientes = new ArrayList(); //CREO EL ARRAYLIST

            PreparedStatement ps;
            ResultSet rs;

            String sql="SELECT u.cod_user, u.login, u.password, c.cod_cli, c.nombre, c.apellidos, c.telefono, c.email, c.direccion FROM usuarios u JOIN clientes c WHERE u.cod_user=c.cod_user";
            
            
            //String sql1 = "select titulo, desc_art, precio, unidades from articulos where cod_art IS NOT NULL ";

            ps = (bd.getConexion()).prepareStatement(sql);
            rs = ps.executeQuery(sql);

            while (rs.next()) {

                int Ucod_user = rs.getInt("u.cod_user");
                String Ulogin = rs.getString("u.login");
                String Upassword = rs.getString("u.password");
                int Ccod_cli = rs.getInt("c.cod_cli");
                String Cnombre = rs.getString("c.nombre");
                String Capellidos = rs.getString("c.apellidos");
                String Ctelefono = rs.getString("c.telefono");
                String Cemail = rs.getString("c.email");
                String Cdireccion = rs.getString("c.direccion");

                Clientes cli_tmp = new Clientes(Ucod_user, Ulogin, Upassword, Ccod_cli, Cnombre, Capellidos, Ctelefono, Cemail, Cdireccion); //COMO METO LOS RESULTSET EN EL OBJETO??

                listaclientes.add(cli_tmp);

            }
            request.getSession().setAttribute("listaCLI", listaclientes);
            ExportarExcelCLIENTES exportar = new ExportarExcelCLIENTES();
            exportar.export();
            response.sendRedirect("admin/mostrarlistaclientes.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    

    
        finally {
            out.close();
    }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
