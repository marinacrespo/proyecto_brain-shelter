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
public class Ventas {
    
    int codped;
    int codart;
    String fecha_ped;
    Float total;
    int codcli;
    String nombre;
    String apellidos;
    
    public Ventas(int codped, int codart, String fecha_ped, Float total, int codcli, String nombre, String apellidos) {
        this.codped = codped;
        this.codart = codart;
        this.fecha_ped = fecha_ped;
        this.total = total;
        this.codcli = codcli;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public String getFecha_ped() {
        return fecha_ped;
    }

    public void setFecha_ped(String fecha_ped) {
        this.fecha_ped = fecha_ped;
    }

    public int getCodped() {
        return codped;
    }

    public void setCodped(int codped) {
        this.codped = codped;
    }

    public int getCodart() {
        return codart;
    }

    public void setCodart(int codart) {
        this.codart = codart;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public int getCodcli() {
        return codcli;
    }

    public void setCodcli(int codcli) {
        this.codcli = codcli;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    
    
    
}
