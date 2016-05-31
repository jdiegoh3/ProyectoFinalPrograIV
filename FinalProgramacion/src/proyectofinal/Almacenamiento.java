/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import proyectofinal.Cliente;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Santoescu
 */
public class Almacenamiento {

    private File cliente, ventaCredito, producto, ventaContado;

    public Almacenamiento() {
        cliente = new File("Cliente.obj");
        producto = new File("Producto.obj");
        ventaCredito = new File("VentaCredito.obj");
        ventaContado = new File("VentaContado.obj");

    }

    public boolean agregarCliente(Cliente e) {
        ObjectOutputStream oos;
        FileOutputStream fos;

        try {

            fos = new FileOutputStream(cliente, true);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(e);
            oos.close();
            return true;

        } catch (FileNotFoundException fnfe) {

            fnfe.printStackTrace();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return false;
    }

    public ArrayList<Cliente> leerCliente() {

        ArrayList<Cliente> clientes = new ArrayList();
        Cliente e;
        if (cliente.exists()) {
            try {
                FileInputStream fis = new FileInputStream(cliente);
                while (true) {
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    e = (Cliente) ois.readObject();
                    clientes.add(e);

                }

            } catch (ClassNotFoundException cs) {

            } catch (FileNotFoundException fnfe) {

            } catch (IOException io) {

            } catch (NullPointerException aa) {

            }
        }
        return clientes;
    }

    public boolean agregarModificacionCliente(Cliente e, int a) {
        ObjectOutputStream oos;
        FileOutputStream fos;

        try {
            if (a == 0) {

                fos = new FileOutputStream(cliente);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(e);
                oos.close();
                return true;
            } else {
                fos = new FileOutputStream(cliente, true);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(e);
                oos.close();
                return true;
            }

        } catch (FileNotFoundException fnfe) {

            fnfe.printStackTrace();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return false;
    }

    public boolean agregarProducto(Producto e) {
        ObjectOutputStream oos;
        FileOutputStream fos;

        try {

            fos = new FileOutputStream(producto, true);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(e);
            oos.close();
            return true;

        } catch (FileNotFoundException fnfe) {

            fnfe.printStackTrace();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return false;
    }

    public ArrayList<Producto> leerProducto() {

        ArrayList<Producto> emple = new ArrayList();
        Producto e;
        if (producto.exists()) {
            try {
                FileInputStream fis = new FileInputStream(producto);
                while (true) {
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    e = (Producto) ois.readObject();
                    emple.add(e);

                }

            } catch (ClassNotFoundException cs) {

            } catch (FileNotFoundException fnfe) {

            } catch (IOException io) {

            } catch (NullPointerException aa) {

            }
        }
        return emple;
    }

    public boolean agregarModificacionProducto(Producto e, int a) {
        ObjectOutputStream oos;
        FileOutputStream fos;

        try {
            if (a == 0) {

                fos = new FileOutputStream(producto);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(e);
                oos.close();
                return true;
            } else {
                fos = new FileOutputStream(producto, true);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(e);
                oos.close();
                return true;
            }

        } catch (FileNotFoundException fnfe) {

            fnfe.printStackTrace();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return false;
    }

    public boolean agregarVentaCredito(Credito e) {
        ObjectOutputStream oos;
        FileOutputStream fos;

        try {

            fos = new FileOutputStream(ventaCredito, true);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(e);
            oos.close();
            return true;

        } catch (FileNotFoundException fnfe) {

            fnfe.printStackTrace();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return false;
    }

    public ArrayList<Credito> leerVentaCredito() {
      

        ArrayList<Credito> emple = new ArrayList();
        Credito c;
        if (ventaCredito.exists()) {
            try {
                FileInputStream fis = new FileInputStream(ventaCredito);
                while (true) {
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    c = (Credito) ois.readObject();
                    emple.add(c);

                }

            } catch (ClassNotFoundException cs) {

            } catch (FileNotFoundException fnfe) {

            } catch (IOException io) {

            } catch (NullPointerException aa) {

            }
        }
        return emple;
    }

    public boolean agregarModificacionVentaCredito(Credito e, int a) {
        ObjectOutputStream oos;
        FileOutputStream fos;

        try {
            if (a == 0) {

                fos = new FileOutputStream(ventaCredito);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(e);
                oos.close();
                return true;
            } else {
                fos = new FileOutputStream(ventaCredito, true);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(e);
                oos.close();
                return true;
            }

        } catch (FileNotFoundException fnfe) {

            fnfe.printStackTrace();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return false;
    }

    public boolean agregarVentaContado(Contado e) {

        ObjectOutputStream oos;
        FileOutputStream fos;

        try {

            fos = new FileOutputStream(ventaContado, true);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(e);
            oos.close();
            return true;

        } catch (FileNotFoundException fnfe) {

            fnfe.printStackTrace();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return false;
    }
    public ArrayList<Contado> leerContado(){
        ArrayList<Contado> con= new ArrayList<>();
        Contado c;
        FileInputStream fis;
        ObjectInputStream ois;
        if(ventaContado.exists()){
            try{
                fis= new FileInputStream(ventaContado);
                while(true){
                    ois= new ObjectInputStream(fis);
                    c=(Contado) ois.readObject();
                    con.add(c);
                }
            } catch (ClassNotFoundException cs) {

            } catch (FileNotFoundException fnfe) {

            } catch (IOException io) {

            } catch (NullPointerException aa) {
               
            }
        }
        return con;
    }

    
    public boolean agregarModificacionVentaContado(Contado e, int a) {
        ObjectOutputStream oos;
        FileOutputStream fos;

        try {
            if (a == 0) {

                fos = new FileOutputStream(ventaContado);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(e);
                oos.close();
                return true;
            } else {
                fos = new FileOutputStream(ventaContado, true);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(e);
                oos.close();
                return true;
            }

        } catch (FileNotFoundException fnfe) {

            fnfe.printStackTrace();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return false;
    }

}
