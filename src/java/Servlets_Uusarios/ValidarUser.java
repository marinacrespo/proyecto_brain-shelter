/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets_Uusarios;

import ConectaBD.ConectaBD;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alumno
 */
@WebServlet(name = "ValidarUser", urlPatterns = {"/ValidarUser"})
public class ValidarUser extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");PrintWriter out = response.getWriter();

        try {

            //CODIGO DE PREGUNTAR SESION + CODIGO DE CONEXION CON LA BBDD
            //String usuario = (String) sesion.getAttribute("login"); 
            //String pass = (String) sesion.getAttribute("pwd");
            String Vestado = "";
            // CODIGO DE CONEXION CON LA BBDD 
            ConectaBD bd = new ConectaBD();
            bd.conectar();

            String Vlogin = request.getParameter("login");
            out.println(Vlogin);
            String Vpassword = request.getParameter("pwd");
            out.println(Vpassword);
	 	

            try {
                
                Vestado = bd.autentificacion(Vlogin, Vpassword);
                
                
                    request.getSession().setAttribute("login", Vlogin); // EN LOS SERVLETS ES ASI!!!! ÑAPA1 PORQUE INTRODUCES VALOR
                    request.getSession().setAttribute("pwd", Vpassword);
                    if(Vestado.equalsIgnoreCase("U")){
                        response.sendRedirect("menuusuario.jsp");
                    }else{
                        response.sendRedirect("listillo.jsp");
                }
                
                
                

                bd.desconectar();
                
                
            } catch (Exception e) {
                e.printStackTrace();
            }

            out.println("<h1>USUARIO NO VALIDADO, NO ENTRA EN EL BUCLE, NO VA AL JSP, SE QUEDA EN EL SERVLET at " + request.getContextPath() + "</h1>");

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
