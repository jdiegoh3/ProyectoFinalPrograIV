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
public class Contado extends Venta implements Serializable{

    public Contado(String numventa, Cliente c, String fecha, ArrayList<Producto> pro) {
        super(numventa, c, fecha, pro);
    }
    
}
