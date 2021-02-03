/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets_Uusarios;

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
@WebServlet(name = "InsertarUsuario", urlPatterns = {"/InsertarUsuario"})
public class InsertarUsuario extends HttpServlet {

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

            //PARAMETROS DE FORMULARIO ALTAUSUARIO-CLIENTE(REGISTRARSE)
            String Cnombre = request.getParameter("nombre");
            String Capellidos = request.getParameter("apellidos");
            String Ctelefono = request.getParameter("telefono");
            String Cemail = request.getParameter("email");
            String Cdireccion = request.getParameter("direccion");
            String Ulogin = request.getParameter("login");
            String Upassword = request.getParameter("password");

            try {
               

                PreparedStatement ps;
                ResultSet rs;

                String sql1 = "select login, password from usuarios where login ='" + Ulogin + "' OR password='" + Upassword + "'";

                ps = (bd.getConexion()).prepareStatement(sql1);
                rs = ps.executeQuery(sql1);

                if (rs.next()) {

                    response.sendRedirect("errordatos.jsp"); // FUNCIONA, AUNQUE AUN ESE JSP NO ESTA CREADO
                } else {

                    try {
                        String sql2 = "insert into usuarios (estado, login, password) VALUES (?, ?, ?)"; //FUNCIONA
                        
                        PreparedStatement ps2;

                        ps2 = (bd.getConexion()).prepareStatement(sql2);
                        ps2.setString(1, "U");
                        ps2.setString(2, Ulogin);
                        ps2.setString(3, Upassword);
                        ps2.executeUpdate();
                        
                        
                        ////////////// FALLA DESDE AQUI!!!

                        try {
                            
                            PreparedStatement ps3;
                            ResultSet rs3;
                            
                            
                            
                            String sql3 = "select cod_user from usuarios where login = '" + Ulogin + "'"; //METER EN UNA VARIABLE PARA EL ULTIMO INSERT INTO
                                                        
                            ps3 = (bd.getConexion()).prepareStatement(sql3);
                            rs3= ps3.executeQuery(sql3);
                            
                            if (rs3.next()) {
                                 int cod_user = rs3.getInt("cod_user");
                                 

                                try {
                                    String sql4 = "insert into clientes (nombre, apellidos, telefono, email, direccion, cod_user) VALUES (?, ?, ?, ?, ?, ?)";

                                    PreparedStatement ps4;

                                    ps4 = (bd.getConexion()).prepareStatement(sql4);
                                    ps4.setString(1, Cnombre);
                                    ps4.setString(2, Capellidos);
                                    ps4.setString(3, Ctelefono);
                                    ps4.setString(4, Cemail);
                                    ps4.setString(5, Cdireccion);
                                    ps4.setInt(6, cod_user);
                                    ps4.executeUpdate();
                                    
                                    
                                    response.sendRedirect("index.jsp"); //HACE FALTA, VUELVE AL INDEX PARA LOGUEARSE

                                } catch (Exception a) {
                                    a.printStackTrace();
                                }

                            }

                        } catch (Exception b) {
                            b.printStackTrace();
                        }

                        
                    } catch (Exception c) {
                        c.printStackTrace();
                    }
                }
                bd.desconectar();
            } catch (Exception d) {
                d.printStackTrace();
            }
            
            out.println("<h1>CLIENTE NO INSERTADO, NO VA AL JSP, SE QUEDA EN EL SERVLET at " + request.getContextPath() + "</h1>");
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
