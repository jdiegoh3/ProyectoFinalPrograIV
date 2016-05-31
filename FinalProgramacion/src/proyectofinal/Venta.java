/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Santoescu
 */
public class Venta  implements Serializable{
    private String numventa, fecha;
    private ArrayList<Producto> pro;
    private Cliente cliente;
    
   
    public Venta(String numventa,Cliente c,String fecha, ArrayList<Producto> pro ){
     this.numventa=numventa;
     this.cliente=c;
     this.fecha=fecha;
     this.pro=pro;
     
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<Producto> getPro() {
        return pro;
    }
   
    public String getnumventa(){
        return numventa;
    }
    
    
    public void setnumventa(String numventa){
        this.numventa=numventa;
    }
    
    
    
    public String getfecha(){
        return fecha;
    }
    
    
    public void setfecha(String fecha){
        this.fecha=fecha;
    }
    
     public double total(){
         double valorfinal=0;
         for (int i = 0; i < pro.size(); i++) {
            valorfinal+=pro.get(i).getValor()*pro.get(i).getCantidad();
             
         }
         return valorfinal;
     }
   
    
   
}
