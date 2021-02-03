/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import ConectaBD.ConectaBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Alumno
 */
public class Articulos {

    

    //ATRIBUTOS
    String titulo;
    String descripcion;
    Float precio;
    int unidades;
    int idarticulo;
    String foto;

    
    

   


    public Articulos(String titulo, String descripcion, Float precio, int unidades, String foto) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.unidades = unidades;
        this.foto = foto;
    }

    
   

    //GETTER & SETTER
    public void setIdarticulo(int idarticulo) {
        this.idarticulo = idarticulo;
    }
    
     public int getIdarticulo() {
        return idarticulo;
    }
     
     
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }
    
    
    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

 

}
