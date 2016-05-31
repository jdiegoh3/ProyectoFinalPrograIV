/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import java.io.Serializable;

/**
 *
 * @author Santoescu
 */
public class Producto implements Serializable {
    private String nombre;
    private String codigo;
    private double valor;
    private int cantidad;
    
    public Producto(String nombre, String codigo, double valor, int cantidad){
        this.nombre=nombre;
        this.codigo=codigo;
        this.valor=valor;
        this.cantidad=cantidad;
        
    }
    
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
       this.nombre=nombre;
    }
    
    public String getCodigo(){
        return codigo;
    }
    public void setCodigo(String codigo){
       this.codigo=codigo;
    }
    
    public double getValor(){
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
   
    
    public int getCantidad(){
        return cantidad;
    }
    public void setCantidad(int cantidad){
       this.cantidad=cantidad;
    }
    
}
