/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets_Uusarios;

import Clases.Articulos;
import ConectaBD.ConectaBD;
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
@WebServlet(name = "sSeleccionar", urlPatterns = {"/sSeleccionar"})
public class sSeleccionar extends HttpServlet {

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

            ArrayList listaarticulos = new ArrayList(); //CREO EL ARRAYLIST

            PreparedStatement ps;
            ResultSet rs;

            String sql1 = "select cod_art, titulo, desc_art, precio, unidades, foto from articulos where cod_art IS NOT NULL ";

            ps = (bd.getConexion()).prepareStatement(sql1);
            rs = ps.executeQuery(sql1);

            while (rs.next()) {
                int Acod_art = rs.getInt("cod_art");
                String Atitulo = rs.getString("titulo");
                String Adescripcion = rs.getString("desc_art");
                Float Aprecio = rs.getFloat("precio");
                int Aunidades = rs.getInt("unidades");
                String Afoto = rs.getString("foto");

                Articulos art_tmp = new Articulos(Atitulo, Adescripcion, Aprecio, Aunidades, Afoto); //COMO METO LOS RESULTSET EN EL OBJETO??
                art_tmp.setIdarticulo(Acod_art);// AÑADIR ID
                listaarticulos.add(art_tmp);

            }
            request.getSession().setAttribute("listaART", listaarticulos);
            response.sendRedirect("seleccionarARTS.jsp"); 
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
