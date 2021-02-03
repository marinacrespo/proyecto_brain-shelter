/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Alumno
 */
public class Seleccion {
    
    String articulo;
    String cantidad;



    public Seleccion(String articulo, String cantidad) {
        this.articulo = articulo;
        this.cantidad = cantidad;
        
        
}
    
    
    

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
}
