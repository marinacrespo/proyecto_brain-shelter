/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets_Uusarios;

import Clases.Seleccion;
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
@WebServlet(name = "ComprarArts", urlPatterns = {"/ComprarArts"})
public class ComprarArts extends HttpServlet {

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
            String nombreusuario = (String) sesion.getAttribute("login");

            int numArt = Integer.parseInt(request.getParameter("pedido"));

            String checkART = "";
            String CANTIDADES = "";

            ArrayList<Seleccion> seleccion = new ArrayList<Seleccion>();

            try {
                for (int i = 1; i <= numArt; i++) {

                    String par1 = "articulo" + i;
                    String par2 = "cantidad" + i;
                    checkART = request.getParameter(par1);
                    CANTIDADES = request.getParameter(par2);
                    out.println(checkART);
                    out.println(CANTIDADES);

                    Seleccion sel_temp = new Seleccion(checkART, CANTIDADES);

                    seleccion.add(sel_temp);
                }

                for (int j = 0; j < seleccion.size(); j++) {
                    String ART = seleccion.get(j).getArticulo();
                    String CANT = seleccion.get(j).getCantidad();

                    if (ART != null) { //AQUI METER TODAS LAS CONSULTAS
                        try {
                            PreparedStatement ps;
                            ResultSet rs;

                            int stockrestante = 0;

                            String sql = "SELECT (unidades-" + CANT + ") AS restante FROM articulos WHERE cod_art='" + ART + "'";
                            ps = (bd.getConexion()).prepareStatement(sql);
                            rs = ps.executeQuery(sql);

                            if (rs.next()) {

                                stockrestante = rs.getInt("restante");

                                if (stockrestante > 0) {

                                    PreparedStatement ps1;
                                    ResultSet rs1;
                                    //CONSIGO EL CODIGO DE CLIENTE DEL LOGIN (SESION) DEL JOIN DE USUARIOS CON CLIENTES
                                    String sql1 = "SELECT c.cod_cli, u.login FROM clientes c JOIN usuarios u ON u.cod_user=c.cod_user WHERE u.login='" + nombreusuario + "'";
                                    ps1 = (bd.getConexion()).prepareStatement(sql1);
                                    rs1 = ps1.executeQuery(sql1);
                                    int COD_CLI;
                                    while (rs1.next()) {
                                        COD_CLI = rs1.getInt("c.cod_cli");

                                        // AQUÍ SALTA
                                        PreparedStatement ps2;
                                        ResultSet rs2;
                                        // REVISAR ESTA CONSULTA
                                        String sql2 = "SELECT precio FROM articulos WHERE cod_art=" + ART + ""; // AQUI SOLO ME COGE DE UN PEDIDO --- PQ!!??
                                        ps2 = (bd.getConexion()).prepareStatement(sql2);
                                        rs2 = ps2.executeQuery(sql2);

                                        while (rs2.next()) {

                                            // PASO 1 --->    INSERTO EL PEDIDO EN DETALLEPEDIDO de la BD
                                            Float precioART = rs2.getFloat("precio");
                                            /*HAGO UN PARSEO DE CHECKART QUE ES STRING------- */
                                            int CODART = Integer.parseInt(ART);

                                            String sql3 = "INSERT INTO detallespedido VALUES (null, ?, NOW(), ?, ?) "; // AL INSERTAR EN DETALLESPEDIDO EL COD_ART, EL COD_USER SE GENERA SOLO EN LA BD PORQUE ES AUTOINCREMENT EN MYSQL

                                            PreparedStatement ps3;

                                            ps3 = (bd.getConexion()).prepareStatement(sql3, PreparedStatement.RETURN_GENERATED_KEYS);
                                            ps3.setInt(1, CODART);
                                            //ps3.setString(2, "NOW()");
                                            ps3.setString(2, CANT);
                                            ps3.setFloat(3, precioART);
                                            ps3.executeUpdate();

                                            ResultSet rs3 = ps3.getGeneratedKeys();
                                            while (rs3.next()) {
                                                ps3.getGeneratedKeys();
                                                int claveGenerada = rs3.getInt(1);
                                                out.println("Clave generada = " + claveGenerada);

                                                //------------------------------------------------------
                                                String sql4 = "INSERT INTO pedidos VALUES (?,?) "; // AL INSERTAR EN DETALLESPEDIDO EL COD_ART, EL COD_USER SE GENERA SOLO EN LA BD PORQUE ES AUTOINCREMENT EN MYSQL
                                                PreparedStatement ps4;

                                                ps4 = (bd.getConexion()).prepareStatement(sql4);
                                                ps4.setInt(1, claveGenerada);
                                                ps4.setInt(2, COD_CLI);
                                                ps4.executeUpdate();

                                                /*
                                                // SELECCIONAR EL CODIGO DE PEDIDO QUE COINCIDA CON LA FECHA ACTUAL Y EL CLIENTE
                                                //INSERTAR EN PEDIDOS EL CODIGO DE PEDIDO GENERADO, Y QUE COINCIDA CON EL CLIENTE
                                                //---------------------------------------------------------------------
                                                // PASO 2 --->    RESTO LO COGIDO DE LA TABLA DE ARTICULOS Y ACTUALIZO SU STOCK
                                                 */
                                                // PASO 2 --->    RESTO LO COGIDO DE LA TABLA DE ARTICULOS Y ACTUALIZO SU STOCK
                                                try {
                                                    int CODIGO = claveGenerada;

                                                    PreparedStatement ps5;
                                                    ResultSet rs5;

                                                    //RESTO (LO ESTOY HACIENDO TODOS LOS ARTICULOS CON JOIN DETALLESPEDIDO)
                                                    // LA CONSULTA FUNCIONA EN MYSQL
                                                    String sql5 = "SELECT (a.unidades-dp.cantidad) AS resta, dp.cod_ped, dp.cod_art FROM articulos a RIGHT JOIN detallespedido dp ON a.cod_art=dp.cod_art WHERE dp.cod_ped=" + CODIGO + "";
                                                    ps5 = (bd.getConexion()).prepareStatement(sql5);
                                                    //ps5.setInt(1, CODIGO);// DE ESTA MANERA NO FUNCIONABA
                                                    rs5 = ps5.executeQuery(sql5);

                                                    while (rs5.next()) { //SE LA PEGA AQUI
                                                        int resta = rs5.getInt("resta");
                                                        int dpcod_art = rs5.getInt("dp.cod_art");

                                                        // ACTUALIZO TABLA ARTICULOS (ASI ACTUALIZO TODOS LOS ARTICULOS A LA VEZ)
                                                        String sql6 = "UPDATE articulos SET unidades=? WHERE cod_art=?";
                                                        PreparedStatement ps6;
                                                        ps6 = (bd.getConexion()).prepareStatement(sql6);
                                                        ps6.setInt(1, resta);
                                                        ps6.setInt(2, dpcod_art);
                                                        ps6.executeUpdate();

                                                    }// WHILE rs6.next()

                                                } catch (Exception c) {
                                                    c.printStackTrace();
                                                }//catch
                                            }//if rs3.next sql3
                                        }//if rs2.next sql2
                                    }//if rs1.next sql1
                                }
                                /*else {
                                    response.sendRedirect("errorpedido.jsp");*/
                            }//if stockrestante

                            //if rs.next - sql
                            bd.desconectar();
                        } catch (Exception c) {
                            c.printStackTrace();
                        }

                    }
                    

                }

                response.sendRedirect("sacarFactura");

                // GENERA 2 NUMEROS DE PEDIDO POR CADA ARTICULO SELECCIONADO
                // COMO LO CAMBIO??
                out.println("<h1>ALGO VA MAL SE QUEDA EN EL SERVLET at " + request.getContextPath() + "</h1>");

            } catch (Exception c) {
                c.printStackTrace();
            }
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
