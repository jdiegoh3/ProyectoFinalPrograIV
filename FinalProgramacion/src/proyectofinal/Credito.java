/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import java.io.Serializable;
import java.util.ArrayList;

public class Credito extends Venta implements Serializable {
    private int cuotas;

    public Credito(String numventa, Cliente c, String fecha, ArrayList<Producto> pro, int cuotas) {
        super(numventa, c, fecha, pro);
        this.cuotas=cuotas;
        
    }

    public int getCuotas() {
        return cuotas;
    }
      public double interes(){
       double inter=total();
       if(inter>=10000000){
           return inter*0.05;
           
       }
       if( inter>=1000000){
            return inter*0.07;
            
       }
       
           return inter*0.10;
       
   }
   public double totalFinal(){
       return interes()+total();
   }
    
   
   
}
