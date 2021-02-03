/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets_Admin;


import Clases.Ventas;
import ConectaBD.ConectaBD;
import ConectaBD.ExportarExcel;
import ConectaBD.ExportarExcelVENTAS;
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
@WebServlet(name = "ListarVentas", urlPatterns = {"/ListarVentas"})
public class ListarVentas extends HttpServlet {

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
            
            //ExportarExcel exp_ex = new ExportarExcel();

            HttpSession sesion = request.getSession();

            ArrayList listaventas = new ArrayList(); //CREO EL ARRAYLIST

            PreparedStatement ps;
            ResultSet rs;

            String sql="SELECT dp.cod_ped, dp.cod_art, dp.fecha_ped, (dp.cantidad*dp.precio_arts) AS total, p.cod_cli, c.nombre, c.apellidos FROM detallespedido dp RIGHT JOIN pedidos p ON dp.cod_ped=p.cod_ped RIGHT JOIN clientes c ON p.cod_cli=c.cod_cli WHERE dp.cod_ped IS NOT NULL";
            
            
            //String sql1 = "select titulo, desc_art, precio, unidades from articulos where cod_art IS NOT NULL ";

            ps = (bd.getConexion()).prepareStatement(sql);
            rs = ps.executeQuery(sql);

            while (rs.next()) {

                int DPcod_ped = rs.getInt("dp.cod_ped");
                int DPcod_art = rs.getInt("dp.cod_art");
                String DPfecha_ped = rs.getString("dp.fecha_ped");
                Float Ttotal = rs.getFloat("total");
                int Pcod_cli = rs.getInt("p.cod_cli");
                String Cnombre = rs.getString("c.nombre");
                String Capellidos = rs.getString("c.apellidos");

                Ventas venta_tmp = new Ventas(DPcod_ped, DPcod_art, DPfecha_ped, Ttotal, Pcod_cli, Cnombre, Capellidos); //COMO METO LOS RESULTSET EN EL OBJETO??

                listaventas.add(venta_tmp);

            }
            
            //exp_ex.export();
            
            
            request.getSession().setAttribute("listaVENTAS", listaventas);
            ExportarExcelVENTAS exportar = new ExportarExcelVENTAS();
            exportar.export();
            response.sendRedirect("admin/mostrarventas.jsp");
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
