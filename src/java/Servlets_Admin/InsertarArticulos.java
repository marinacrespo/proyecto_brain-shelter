/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets_Admin;

import ConectaBD.ConectaBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
@WebServlet(name = "InsertarArticulos", urlPatterns = {"/admin/InsertarArticulos"}) //ESTO ES UNA WEB-ANOTATIONS
public class InsertarArticulos extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */

            // CODIGO DE CONEXION CON LA BBDD 
            ConectaBD bd = new ConectaBD();
            bd.conectar();

            //PARAMETROS DE FORMULARIO ALTAARTICULOS
            String Atitulo = request.getParameter("titulo");
            String Adescripcion = request.getParameter("desc_art");
            String Aprecio = request.getParameter("precio");
            String Aunidades = request.getParameter("unidades");
            String Afoto = request.getParameter("foto");

            try {
                

                PreparedStatement ps;
                ResultSet rs;

                String sql1 = "select titulo from articulos where titulo ='" + Atitulo + "'";

                String sql2 = "insert into articulos (titulo,desc_art, precio, unidades, foto) VALUES (?, ?, ?, ?, ?)";

                ps = (bd.getConexion()).prepareStatement(sql1);
                rs = ps.executeQuery(sql1);
                
                if (rs.next()) {

                    response.sendRedirect("errorarticulo.jsp");
                } else {

                    try {
                        PreparedStatement ps2;
                        
                        ps2 = (bd.getConexion()).prepareStatement(sql2);
                        ps2.setString(1, Atitulo);
                        ps2.setString(2, Adescripcion);
                        ps2.setString(3, Aprecio);
                        ps2.setString(4, Aunidades);
                        ps2.setString(5, Afoto);
                        ps2.executeUpdate();

                        response.sendRedirect("menuadmin.jsp");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                bd.desconectar();
            } catch (Exception e) {
                e.printStackTrace();
            }

            out.println("<h1>ARTICULO NO INSERTADO, NO VA AL JSP, SE QUEDA EN EL SERVLET at " + request.getContextPath() + "</h1>");
        } finally {
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
