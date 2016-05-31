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
public class Cliente implements Serializable{
    
    //declaracion de atributos
    private String nombre;
    private String cedula;
    private String telefono;
    private double deuda;
    
    //metodo constructor
    public Cliente(String nombre,String cedula,String telefono){
        
        this.nombre=nombre;
        this.cedula=cedula;
        this.telefono=telefono;
        deuda=0;
        
    }
    
    //metodos get y set

    public double getDeuda() {
        return deuda;
    }

    public void setDeuda(double deuda) {
        this.deuda = deuda;
    }
    
    
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public String getCedula(){
        return cedula;
    }
    public void setCedula(String cedula){
        this.cedula=cedula;
    }
    public String getTelefono(){
        return telefono;
    }
    public void setTelefono(String telefono){
        this.telefono=telefono;
    }
   
    
    
}
