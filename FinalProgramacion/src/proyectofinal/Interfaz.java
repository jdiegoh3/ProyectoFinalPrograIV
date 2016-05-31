/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Santoescu
 */
public class Interfaz extends javax.swing.JFrame implements KeyListener {

    private Cliente cl;
    private Almacenamiento alma;
    DefaultTableModel dtmC, dtmP, dtmPV, dtmPVH;
    private Producto pr;
    private ArrayList<Producto> pro;

    private Factura fac;
    private Credito credi;
    private Contado cont;

    /**
     * Creates new form Interfaz
     */
    public Interfaz() {
        initComponents();
        alma = new Almacenamiento();
        dtmC = (DefaultTableModel) tablaCliente.getModel();
        dtmP = (DefaultTableModel) tablaProductos.getModel();
        dtmPV = (DefaultTableModel) tablaProductosVentas.getModel();
        dtmPVH=(DefaultTableModel) tablaVentasHechas.getModel();
        obtenerInfoCliente();
        obtenerInfoProducto();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/YYYY");
        Date fechaActual = new Date();
        String fechaConFormato = sdf.format(fechaActual);
        lblfecha.setText(String.valueOf(fechaConFormato));
        lblCueotas.setText(" ");
        txtCredito.setVisible(false);
        lblNumVenta.setText(String.valueOf(alma.leerContado().size() + alma.leerVentaCredito().size() + 1));
        pro = new ArrayList<>();
        llenarComboCliente();
        llenarComboprodcuto();
        txtNombreCliente.addKeyListener(this);
        txtTelefonoCliente.addKeyListener(this);
        txtCedulaCliente.addKeyListener(this);
        tablaCliente.setEnabled(false);
        tablaProductosVentas.setEnabled(false);
        tablaProductos.setEnabled(false);

    }

    public void modificarClienteSalario(String cedula, Double deuda) {
        int a = 0;
        ArrayList<Cliente> emple = alma.leerCliente();
        for (int i = 0; i < emple.size(); i++) {
            if (emple.get(i).getCedula().equals(cedula)) {
                emple.get(i).setDeuda(deuda);
            }

            alma.agregarModificacionCliente(emple.get(i), a);
            a++;

        }

    }

    public void validacionNumeros(JTextField cajaDeTexto) {
        String cadena = cajaDeTexto.getText();

        if (!cajaDeTexto.getText().matches("[0-9]*")) {
            cadena = cadena.substring(0, cadena.length() - 1);
            cajaDeTexto.setText(cadena);
            JOptionPane.showMessageDialog(null, "No se pueden ingresar letras.");

        }
    }

    public void validacionLetras(JTextField cajaDeTexto) {
        String cadena = cajaDeTexto.getText();

        if (!cajaDeTexto.getText().matches("[a-z]*")) {
            cadena = cadena.substring(0, cadena.length() - 1);
            cajaDeTexto.setText(cadena);
            JOptionPane.showMessageDialog(null, "No se pueden ingresar numeros.");

        }
    }

    public void llenarComboCliente() {
        jComboBox1.removeAllItems();
        jComboBox1.addItem("Seleccionar");
        for (int i = 0; i < alma.leerCliente().size(); i++) {
            jComboBox1.addItem(alma.leerCliente().get(i).getNombre());

        }
    }

    public void llenarComboprodcuto() {
        jComboBox2.removeAllItems();

        jComboBox2.addItem("Seleccionar");
        for (int i = 0; i < alma.leerProducto().size(); i++) {
            jComboBox2.addItem(alma.leerProducto().get(i).getNombre());

        }
    }

    public void agregarProductosMenosDe5(String id) {
        int a;
        ArrayList<Producto> empre = alma.leerProducto();
        for (int i = 0; i < empre.size(); i++) {
            if (empre.get(i).getCodigo().equals(id)) {
                if (empre.get(i).getCantidad() <= 5) {
                    a = JOptionPane.showConfirmDialog(null, "Probablemente, la cantidad de este producto es menor a 5; desea agregar nueva cantidad");
                    if (a == 0) {
                        int can = Integer.parseInt(JOptionPane.showInputDialog("cuanta cantidad desea agregar"));
                        modificarCantidadProductos(id, empre.get(i).getCantidad() + can);

                    }
                }
            }

        }

    }

    public void modificarCantidadProductos(String id, int cantidad) {
        ArrayList<Producto> empre = alma.leerProducto();

        int a = 0;
        for (int i = 0; i < empre.size(); i++) {
            if (empre.get(i).getCodigo().equals(id)) {
                empre.get(i).setCantidad(cantidad);

            }

            alma.agregarModificacionProducto(empre.get(i), a);
            a++;

        }
    }

    public void obtenerInfoCliente() {
        dtmC.setRowCount(0);

        for (int i = 0; i < alma.leerCliente().size(); i++) {
            dtmC.addRow(new Object[]{alma.leerCliente().get(i).getCedula(),
                alma.leerCliente().get(i).getNombre(), alma.leerCliente().get(i).getTelefono(),
                alma.leerCliente().get(i).getDeuda()});

        }

    }

    public void obtenerInfoProducto() {
        dtmP.setRowCount(0);

        for (int i = 0; i < alma.leerProducto().size(); i++) {
            dtmP.addRow(new Object[]{alma.leerProducto().get(i).getNombre(),
                alma.leerProducto().get(i).getCodigo(), alma.leerProducto().get(i).getValor(),
                alma.leerProducto().get(i).getCantidad()});

        }

    }
    public void obtenerInfoProductoVentaHecha(ArrayList<Producto> es) {
        dtmPVH.setRowCount(0);

        for (int i = 0; i < es.size(); i++) {
            dtmPVH.addRow(new Object[]{es.get(i).getNombre(),
                es.get(i).getCodigo(), es.get(i).getValor(),
                es.get(i).getCantidad()});

        }

    }

    public boolean validarClietnte(String cedula) {

        for (int i = 0; i < alma.leerCliente().size(); i++) {
            if (!alma.leerCliente().get(i).equals(null)) {
                if (alma.leerCliente().get(i).getCedula().equals(cedula)) {
                    JOptionPane.showMessageDialog(null, "Ya existe el cliente con esta cedula");
                    return true;

                }
            }

        }
        return false;
    }

    public boolean validarProducto(String codigo) {

        for (int i = 0; i < alma.leerProducto().size(); i++) {
            if (!alma.leerProducto().get(i).equals(null)) {
                if (alma.leerProducto().get(i).getCodigo().equals(codigo)) {
                    JOptionPane.showMessageDialog(null, "Ya existe el prodcuto con este codigo");
                    return true;

                }
            }

        }
        return false;
    }

    public Cliente getcliente(String nombre) {
        for (int i = 0; i < alma.leerCliente().size(); i++) {
            if (alma.leerCliente().get(i).getNombre().equals(nombre)) {
                return alma.leerCliente().get(i);
            }

        }
        return null;
    }

    public Producto getproducto(String nombre) {
        for (int i = 0; i < alma.leerProducto().size(); i++) {
            if (alma.leerProducto().get(i).getNombre().equals(nombre)) {
                return alma.leerProducto().get(i);
            }

        }
        return null;
    }

    public int sabercantidad(String nombre) {

        for (int i = 0; i < alma.leerProducto().size(); i++) {
            if (alma.leerProducto().get(i).getNombre().equals(nombre)) {
                return alma.leerProducto().get(i).getCantidad();

            }
        }

        return 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        principal = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCliente = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCedulaCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTelefonoCliente = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtNombreProducto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCodigoProducto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtValorProducto = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCantidadProducto = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaProductosVentas = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        lblNumVenta = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblfecha = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        txtCantidadSeleccionado = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jrbContado = new javax.swing.JRadioButton();
        jrbCredito = new javax.swing.JRadioButton();
        txtCredito = new javax.swing.JTextField();
        lblCueotas = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaVentasHechas = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        txtConsultarVenta = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        lblNombreCliente = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblCedulaCliente = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblTelefonoCliente = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblNumeroVentaHecha = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblsubTotalVentaHecha = new javax.swing.JLabel();
        lblTotalVentaHecha = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblcoutastexto = new javax.swing.JLabel();
        lblCuotasventas = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lblFechaVenta = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Almacen");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        principal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                principalMouseClicked(evt);
            }
        });

        jButton12.setText("Clientes");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setText("Hacer venta");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("Productos");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setText("Ventas Hechas");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 232, Short.MAX_VALUE)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 328, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        principal.addTab("Inicio", jPanel1);

        tablaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cedula", "Nombre", "Telefono", "Deuda"
            }
        ));
        jScrollPane1.setViewportView(tablaCliente);

        jButton1.setText("Agregar cliente");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Consultar cliente");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Modificar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre: ");

        jLabel2.setText("Cedula: ");

        jLabel3.setText("Telefono: ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNombreCliente)
                                    .addComponent(txtCedulaCliente)
                                    .addComponent(txtTelefonoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCedulaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        principal.addTab("Clientes", jPanel2);

        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Codigo", "Valor unitario", "Cantidad"
            }
        ));
        jScrollPane2.setViewportView(tablaProductos);

        jLabel4.setText("Nombre: ");

        txtNombreProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreProductoActionPerformed(evt);
            }
        });

        jLabel5.setText("Codigo: ");

        jLabel6.setText("Valor Unitario: ");

        jLabel7.setText("Cantidad: ");

        jButton4.setText("Agregar producto");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Consultar producto");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Modificar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(62, 62, 62)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombreProducto)
                            .addComponent(txtCodigoProducto)
                            .addComponent(txtValorProducto)
                            .addComponent(txtCantidadProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtValorProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCantidadProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        principal.addTab("Productos", jPanel3);

        tablaProductosVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Prodcuto", "Codigo", "Cantidad", "Valor unitario", "Subtotal"
            }
        ));
        jScrollPane3.setViewportView(tablaProductosVentas);

        jLabel8.setText("No. de venta: ");

        lblNumVenta.setText(" ");

        jLabel9.setText("Fecha: ");

        jLabel10.setText("Cliente: ");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));

        jLabel11.setText("Detalles de la venta: __________________________________________________");

        jLabel12.setText("Producto: ");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));

        jLabel13.setText("Cantidad: ");

        jButton7.setText("Agregar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jrbContado);
        jrbContado.setText("Contado");
        jrbContado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbContadoActionPerformed(evt);
            }
        });

        buttonGroup1.add(jrbCredito);
        jrbCredito.setText("Credito");
        jrbCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbCreditoActionPerformed(evt);
            }
        });

        lblCueotas.setText("Cuotas a meses: ");

        jButton10.setText("Hacer Venta");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel14.setText("Total: ");

        lblTotal.setText("0");

        jLabel15.setText("Forma de pago: ______________________________________________________");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton10)
                        .addGap(192, 192, 192)
                        .addComponent(jLabel14)
                        .addGap(12, 12, 12)
                        .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblNumVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jrbContado)
                                        .addGap(59, 59, 59)
                                        .addComponent(jrbCredito))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblCueotas)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(txtCantidadSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7)
                        .addGap(17, 17, 17))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(lblNumVenta)
                        .addComponent(jLabel9)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbContado)
                    .addComponent(jrbCredito)
                    .addComponent(lblCueotas)
                    .addComponent(txtCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jLabel12)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtCantidadSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10)
                    .addComponent(jLabel14)
                    .addComponent(lblTotal))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        principal.addTab("Hacer Venta", jPanel4);

        tablaVentasHechas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Condigo", "Cantidad", "Valor unitario", "Subtotal"
            }
        ));
        jScrollPane4.setViewportView(tablaVentasHechas);

        jLabel16.setText("Ingrese el numero de venta:");

        jButton11.setText("Consultar");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel17.setText("Cliente: ");

        lblNombreCliente.setText(" ");

        jLabel19.setText("Cedula: ");

        lblCedulaCliente.setText(" ");

        jLabel21.setText("Telefono: ");

        lblTelefonoCliente.setText(" ");

        jLabel18.setText("Venta No. ");

        lblNumeroVentaHecha.setText(" ");

        jLabel20.setText("Subtotal: ");

        jLabel22.setText("Total: ");

        lblsubTotalVentaHecha.setText(" ");

        lblTotalVentaHecha.setText(" ");

        jLabel23.setText("Informacion de la venta: _______________________________________________");

        lblcoutastexto.setText("Cuotas: ");

        lblCuotasventas.setText(" ");

        jLabel25.setText("Fecha: ");

        lblFechaVenta.setText(" ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane4)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(txtConsultarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton11)
                        .addGap(58, 58, 58))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(lblNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(18, 18, 18)
                                .addComponent(lblNumeroVentaHecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(59, 59, 59)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(18, 18, 18)
                                .addComponent(lblsubTotalVentaHecha, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(lblcoutastexto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblCuotasventas, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(18, 18, 18)
                                .addComponent(lblCedulaCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(12, 12, 12)))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(18, 18, 18)
                                .addComponent(lblTotalVentaHecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addGap(18, 18, 18)
                                .addComponent(lblFechaVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(18, 18, 18)
                                .addComponent(lblTelefonoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtConsultarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(lblNombreCliente)
                    .addComponent(lblCedulaCliente)
                    .addComponent(jLabel19)
                    .addComponent(jLabel21)
                    .addComponent(lblTelefonoCliente))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCuotasventas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(lblNumeroVentaHecha)
                        .addComponent(lblcoutastexto)
                        .addComponent(jLabel25)
                        .addComponent(lblFechaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel22)
                    .addComponent(lblTotalVentaHecha)
                    .addComponent(lblsubTotalVentaHecha))
                .addGap(18, 18, 18)
                .addComponent(jLabel23)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                .addContainerGap())
        );

        principal.addTab("Ventas Hechas", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(principal)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(principal))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!validarClietnte(txtCedulaCliente.getText())) {
            cl = new Cliente(txtNombreCliente.getText(), txtCedulaCliente.getText(), txtTelefonoCliente.getText());
            alma.agregarCliente(cl);
            obtenerInfoCliente();
            txtCedulaCliente.setText("");
            txtNombreCliente.setText("");
            txtTelefonoCliente.setText("");
            llenarComboCliente();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtNombreProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreProductoActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (!validarProducto(txtCodigoProducto.getText())) {
            try {
                pr = new Producto(txtNombreProducto.getText(), txtCodigoProducto.getText(), Double.parseDouble(txtValorProducto.getText()), Integer.parseInt(txtCantidadProducto.getText()));
                alma.agregarProducto(pr);
                obtenerInfoProducto();
                txtNombreProducto.setText("");
                txtCodigoProducto.setText("");
                txtCantidadProducto.setText("");
                txtValorProducto.setText("");
                llenarComboprodcuto();
            } catch (NumberFormatException ja) {
                JOptionPane.showMessageDialog(null, "Ingrese la cantidad y/o el valor correcto");
            }

        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int ops;
        try {
            String codigo = JOptionPane.showInputDialog("Ingrese la cedula del cliente que desea modificar");
            ops = Integer.parseInt(JOptionPane.showInputDialog("Menu:\n\n1.Modificar nombre.\n2.Modificar cedula.\n3.Modificar telefono.\n4.Modificar deuda o abonar.\n5.Eliminar."));
            switch (ops) {
                case 1:
                    String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre");
                    int a = 0;
                    ArrayList<Cliente> emple = alma.leerCliente();
                    for (int i = 0; i < emple.size(); i++) {
                        if (emple.get(i).getCedula().equals(codigo)) {
                            emple.get(i).setNombre(nombre);
                        }

                        alma.agregarModificacionCliente(emple.get(i), a);
                        a++;

                    }
                    obtenerInfoCliente();
                    llenarComboCliente();
                    break;
                case 2:
                    String codigo2 = JOptionPane.showInputDialog("Ingrese la nueva cedula");
                    int b = 0;
                    ArrayList<Cliente> em = alma.leerCliente();
                    for (int i = 0; i < em.size(); i++) {
                        if (em.get(i).getCedula().equals(codigo)) {
                            em.get(i).setCedula(codigo2);
                        }

                        alma.agregarModificacionCliente(em.get(i), b);
                        b++;

                    }
                    obtenerInfoCliente();
                    llenarComboCliente();
                    break;

                case 3:
                    String cantidad = (JOptionPane.showInputDialog("Ingrese el nuevo telefono"));
                    int c = 0;
                    ArrayList<Cliente> emp = alma.leerCliente();
                    for (int i = 0; i < emp.size(); i++) {
                        if (emp.get(i).getCedula().equals(codigo)) {
                            emp.get(i).setTelefono(cantidad);
                        }

                        alma.agregarModificacionCliente(emp.get(i), c);
                        c++;

                    }
                    obtenerInfoCliente();
                    llenarComboCliente();
                    break;
                case 4:
                    double valor = Double.parseDouble(JOptionPane.showInputDialog("ingrese el abono"));
                    int d = 0;
                    ArrayList<Cliente> empl = alma.leerCliente();
                    for (int i = 0; i < empl.size(); i++) {
                        if (empl.get(i).getCedula().equals(codigo)) {
                            if (empl.get(i).getDeuda() >= valor && valor > 0) {
                                empl.get(i).setDeuda(empl.get(i).getDeuda() - valor);
                            } else {
                                JOptionPane.showMessageDialog(null, " Ingrese un abono no mayor al monto a pagar");
                            }
                        }

                        alma.agregarModificacionCliente(empl.get(i), d);
                        d++;

                    }
                    obtenerInfoCliente();
                    llenarComboCliente();
                    break;
                case 5:
                    int f = 0;
                    ArrayList<Cliente> empli = alma.leerCliente();
                    for (int i = 0; i < empli.size(); i++) {
                        if (empli.get(i).getCedula().equals(codigo)) {
                            empli.remove(i);
                        }

                        if (empli.get(i) != null) {
                            alma.agregarModificacionCliente(empli.get(i), f);
                            f++;
                        }

                    }
                    obtenerInfoCliente();
                    llenarComboCliente();
                    break;

                default:
                    obtenerInfoProducto();
                    llenarComboprodcuto();
                    break;

            }

        } catch (NumberFormatException io) {
            JOptionPane.showMessageDialog(null, "Ingrese datos validos");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jrbCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbCreditoActionPerformed
        lblCueotas.setText("Cuotas a meses:");
        txtCredito.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_jrbCreditoActionPerformed

    private void jrbContadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbContadoActionPerformed
        lblCueotas.setText("");
        txtCredito.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jrbContadoActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        if (jrbContado.isSelected()) {
            if (jComboBox1.getSelectedItem() != "Seleccionar" && pro.size() != 0) {
                cont = new Contado(String.valueOf(lblNumVenta.getText()), getcliente(String.valueOf(jComboBox1.getSelectedItem())), String.valueOf(lblfecha.getText()), pro);
                alma.agregarVentaContado(cont);
                lblNumVenta.setText(String.valueOf(alma.leerContado().size() + alma.leerVentaCredito().size() + 1));
                dtmPV.setRowCount(0);
                lblTotal.setText("0");
                buttonGroup1.clearSelection();
                pro = new ArrayList<>();
                llenarComboCliente();
                llenarComboprodcuto();
                fac = new Factura(cont);

            } else {
                if (jComboBox1.getSelectedItem() == "Seleccionar") {
                    JOptionPane.showMessageDialog(null, "Seleccione un cliente");

                }
                if (pro.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Agregue un producto");
                }

            }
        }
        if (jrbCredito.isSelected()) {
            try {
                if (jComboBox1.getSelectedItem() != "Seleccionar" && pro.size() != 0) {
                    credi = new Credito(String.valueOf(lblNumVenta.getText()), getcliente(String.valueOf(jComboBox1.getSelectedItem())), String.valueOf(lblfecha.getText()), pro, Integer.parseInt(txtCredito.getText()));
                    alma.agregarVentaCredito(credi);
                    modificarClienteSalario(getcliente(String.valueOf(jComboBox1.getSelectedItem())).getCedula(), getcliente(String.valueOf(jComboBox1.getSelectedItem())).getDeuda() + credi.totalFinal());
                    lblNumVenta.setText(String.valueOf(alma.leerContado().size() + alma.leerVentaCredito().size() + 1));
                    dtmPV.setRowCount(0);
                    buttonGroup1.clearSelection();
                    pro = new ArrayList<>();
                    llenarComboCliente();
                    llenarComboprodcuto();
                    txtCredito.setText("");
                    lblCueotas.setText("");
                    txtCredito.setVisible(false);
                    lblTotal.setText("0");
                    obtenerInfoCliente();
                    fac = new Factura(credi);

                } else {
                    if (jComboBox1.getSelectedItem() == "Seleccionar") {
                        JOptionPane.showMessageDialog(null, "Seleccione un cliente");

                    }
                    if (pro.size() == 0) {
                        JOptionPane.showMessageDialog(null, "Agregue un producto");
                    }

                }
            } catch (NumberFormatException io) {
                JOptionPane.showMessageDialog(null, "Ingrese el numero de coutas");
                txtCredito.setText("");

            }
        }

    }//GEN-LAST:event_jButton10ActionPerformed

    private void principalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_principalMouseClicked


    }//GEN-LAST:event_principalMouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            double total = Double.parseDouble(lblTotal.getText());
            if (txtCantidadSeleccionado.getText().isEmpty() || jComboBox2.getSelectedItem() == "Seleccionar") {
                JOptionPane.showMessageDialog(null, "Selecione un producto y agreguele su cantidad");
            } else {
                if (Integer.parseInt(txtCantidadSeleccionado.getText()) > 0) {
                    if (Integer.parseInt(txtCantidadSeleccionado.getText()) <= sabercantidad(String.valueOf(jComboBox2.getSelectedItem()))) {
                        pr = new Producto(getproducto(String.valueOf(jComboBox2.getSelectedItem())).getNombre(), getproducto(String.valueOf(jComboBox2.getSelectedItem())).getCodigo(), getproducto(String.valueOf(jComboBox2.getSelectedItem())).getValor(), Integer.parseInt(txtCantidadSeleccionado.getText()));
                        pro.add(pr);
                        dtmPV.addRow(new Object[]{getproducto(String.valueOf(jComboBox2.getSelectedItem())).getNombre(),
                            getproducto(String.valueOf(jComboBox2.getSelectedItem())).getCodigo(), txtCantidadSeleccionado.getText(),
                            getproducto(String.valueOf(jComboBox2.getSelectedItem())).getValor(), String.valueOf(Integer.parseInt(txtCantidadSeleccionado.getText()) * getproducto(String.valueOf(jComboBox2.getSelectedItem())).getValor())});
                        total += Double.parseDouble(txtCantidadSeleccionado.getText()) * (getproducto(String.valueOf(jComboBox2.getSelectedItem())).getValor());
                        lblTotal.setText(String.valueOf(total));
                        modificarCantidadProductos(getproducto(String.valueOf(jComboBox2.getSelectedItem())).getCodigo(), getproducto(String.valueOf(jComboBox2.getSelectedItem())).getCantidad() - Integer.parseInt(txtCantidadSeleccionado.getText()));
                        agregarProductosMenosDe5(getproducto(String.valueOf(jComboBox2.getSelectedItem())).getCodigo());
                        obtenerInfoProducto();
                        txtCantidadSeleccionado.setText("");

                    } else {
                        JOptionPane.showMessageDialog(null, "No hay esa cantidad de producto");
                    }

                }

            }
        } catch (NumberFormatException ie) {

            JOptionPane.showMessageDialog(null, "Ingrese datos validos");
            txtCantidadSeleccionado.setText("");

        }
    }//GEN-LAST:event_jButton7ActionPerformed


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (!consultarCliente(txtCedulaCliente.getText())) {
            JOptionPane.showMessageDialog(null, "No se encontro el cliente.");
            txtCedulaCliente.setText("");
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    public boolean consultarCliente(String cedula) {
        for (int i = 0; i < alma.leerCliente().size(); i++) {
            if (alma.leerCliente().get(i).getCedula().equals(cedula)) {
                JOptionPane.showMessageDialog(null, "Nombre: " + alma.leerCliente().get(i).getNombre() + "\nCedula: " + alma.leerCliente().get(i).getCedula() + "\nTelefono: " + alma.leerCliente().get(i).getTelefono() + "\nDeuda: " + alma.leerCliente().get(i).getDeuda());
                txtCedulaCliente.setText("");
                return true;
            }

        }
        return false;
    }

    public boolean consultarProducto(String codigo) {
        for (int i = 0; i < alma.leerProducto().size(); i++) {
            if (alma.leerProducto().get(i).getCodigo().equals(codigo)) {
                JOptionPane.showMessageDialog(null, "Nombre: " + alma.leerProducto().get(i).getNombre() + "\nCodigo: " + alma.leerProducto().get(i).getCodigo() + "\nCantidad: " + alma.leerProducto().get(i).getCantidad() + "\nValor unitario: " + alma.leerProducto().get(i).getValor());
                txtCodigoProducto.setText("");
                return true;
            }

        }
        return false;
    }

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (!consultarProducto(txtCodigoProducto.getText())) {
            JOptionPane.showMessageDialog(null, "No se encontro el producto.");
            txtCodigoProducto.setText("");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int ops;
        try {
            String codigo = JOptionPane.showInputDialog("Ingrese el codigo del producto que desea modificar");
            ops = Integer.parseInt(JOptionPane.showInputDialog("Menu:\n\n1.Modificar nombre.\n2.Modificar codigo.\n3.Modificar cantidad.\n4.Modificar valor.\n5.Eliminar."));
            switch (ops) {
                case 1:
                    String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre");
                    int a = 0;
                    ArrayList<Producto> emple = alma.leerProducto();
                    for (int i = 0; i < emple.size(); i++) {
                        if (emple.get(i).getCodigo().equals(codigo)) {
                            emple.get(i).setNombre(nombre);
                        }

                        alma.agregarModificacionProducto(emple.get(i), a);
                        a++;

                    }
                    obtenerInfoProducto();
                    llenarComboprodcuto();
                    break;
                case 2:
                    String codigo2 = JOptionPane.showInputDialog("Ingrese el nuevo codigo");
                    int b = 0;
                    ArrayList<Producto> em = alma.leerProducto();
                    for (int i = 0; i < em.size(); i++) {
                        if (em.get(i).getCodigo().equals(codigo)) {
                            em.get(i).setCodigo(codigo2);
                        }

                        alma.agregarModificacionProducto(em.get(i), b);
                        b++;

                    }
                    obtenerInfoProducto();
                    llenarComboprodcuto();
                    break;

                case 3:
                    int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad"));
                    int c = 0;
                    ArrayList<Producto> emp = alma.leerProducto();
                    for (int i = 0; i < emp.size(); i++) {
                        if (emp.get(i).getCodigo().equals(codigo)) {
                            emp.get(i).setCantidad(cantidad);
                        }

                        alma.agregarModificacionProducto(emp.get(i), c);
                        c++;

                    }
                    obtenerInfoProducto();
                    llenarComboprodcuto();
                    break;
                case 4:
                    double valor = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la nueva cantidad"));
                    int d = 0;
                    ArrayList<Producto> empl = alma.leerProducto();
                    for (int i = 0; i < empl.size(); i++) {
                        if (empl.get(i).getCodigo().equals(codigo)) {
                            empl.get(i).setValor(valor);
                        }

                        alma.agregarModificacionProducto(empl.get(i), d);
                        d++;

                    }
                    obtenerInfoProducto();
                    llenarComboprodcuto();
                    break;
                case 5:
                    int f = 0;
                    ArrayList<Producto> empli = alma.leerProducto();
                    for (int i = 0; i < empli.size(); i++) {
                        if (empli.get(i).getCodigo().equals(codigo)) {
                            empli.remove(i);
                        }

                        if (empli.get(i) != null) {
                            alma.agregarModificacionProducto(empli.get(i), f);
                            f++;
                        }

                    }
                    obtenerInfoProducto();
                    llenarComboprodcuto();
                    break;

                default:
                    obtenerInfoProducto();
                    llenarComboprodcuto();
                    break;

            }

        } catch (NumberFormatException io) {
            JOptionPane.showMessageDialog(null, "Ingrese datos validos");
        }
    }//GEN-LAST:event_jButton6ActionPerformed
    public int consultarVenta(String num) {
        int a=0;
        
        for (int i = 0; i < alma.leerContado().size(); i++) {
            if (alma.leerContado().get(i).getnumventa().equals(num)) {
              

              a=1;

            }

        }
        for (int i = 0; i < alma.leerVentaCredito().size(); i++) {
            if (alma.leerVentaCredito().get(i).getnumventa().equals(num)) {
                

                a=2;
            }

        }
        System.out.println(a);
        return a;

    }
    public Contado retorContado(String num){
        for (int i = 0; i < alma.leerContado().size(); i++) {
            if (alma.leerContado().get(i).getnumventa().equals(num)) {

              return alma.leerContado().get(i);

            }

        }
        return null;
        
    }
    public Credito retorCredito(String num){
        for (int i = 0; i < alma.leerVentaCredito().size(); i++) {
            if (alma.leerVentaCredito().get(i).getnumventa().equals(num)) {

              return alma.leerVentaCredito().get(i);

            }

        }
        return null;
        
    }


    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
       if(consultarVenta(txtConsultarVenta.getText())==1){
           Contado cont= retorContado(txtConsultarVenta.getText());
           lblCedulaCliente.setText(cont.getCliente().getCedula());
           lblNombreCliente.setText(cont.getCliente().getNombre());
           lblTelefonoCliente.setText(cont.getCliente().getTelefono());
           lblNumVenta.setText(cont.getnumventa());
           lblCuotasventas.setText("");
           lblcoutastexto.setText("");
           lblNumeroVentaHecha.setText(cont.getnumventa());
           lblFechaVenta.setText(cont.getfecha());
           lblsubTotalVentaHecha.setText(String.valueOf(cont.total()));
           lblTotalVentaHecha.setText(String.valueOf(cont.total()));
           obtenerInfoProductoVentaHecha(cont.getPro());
           txtConsultarVenta.setText("");
          
           
           
       }
       else if(consultarVenta(txtConsultarVenta.getText())==2){
          Credito cont= retorCredito(txtConsultarVenta.getText());
          lblCedulaCliente.setText(cont.getCliente().getCedula());
           lblNombreCliente.setText(cont.getCliente().getNombre());
           lblTelefonoCliente.setText(cont.getCliente().getTelefono());
           lblNumVenta.setText(cont.getnumventa());
           lblCuotasventas.setText(String.valueOf(cont.getCuotas()));
           lblcoutastexto.setText("Cuotas: ");
           lblNumeroVentaHecha.setText(cont.getnumventa());
           lblFechaVenta.setText(cont.getfecha());
           lblsubTotalVentaHecha.setText(String.valueOf(cont.total()));
           lblTotalVentaHecha.setText(String.valueOf(cont.totalFinal()));
           obtenerInfoProductoVentaHecha(cont.getPro());
           txtConsultarVenta.setText("");
           
       } else{
           JOptionPane.showMessageDialog(null, "Ingrese un numero de venta valido");
       }


    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
principal.setSelectedIndex(1);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
     principal.setSelectedIndex(2);     // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
principal.setSelectedIndex(3);          // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
principal.setSelectedIndex(4);          // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JRadioButton jrbContado;
    private javax.swing.JRadioButton jrbCredito;
    private javax.swing.JLabel lblCedulaCliente;
    private javax.swing.JLabel lblCueotas;
    private javax.swing.JLabel lblCuotasventas;
    private javax.swing.JLabel lblFechaVenta;
    private javax.swing.JLabel lblNombreCliente;
    private javax.swing.JLabel lblNumVenta;
    private javax.swing.JLabel lblNumeroVentaHecha;
    private javax.swing.JLabel lblTelefonoCliente;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalVentaHecha;
    private javax.swing.JLabel lblcoutastexto;
    private javax.swing.JLabel lblfecha;
    private javax.swing.JLabel lblsubTotalVentaHecha;
    private javax.swing.JTabbedPane principal;
    private javax.swing.JTable tablaCliente;
    private javax.swing.JTable tablaProductos;
    private javax.swing.JTable tablaProductosVentas;
    private javax.swing.JTable tablaVentasHechas;
    private javax.swing.JTextField txtCantidadProducto;
    private javax.swing.JTextField txtCantidadSeleccionado;
    private javax.swing.JTextField txtCedulaCliente;
    private javax.swing.JTextField txtCodigoProducto;
    private javax.swing.JTextField txtConsultarVenta;
    private javax.swing.JTextField txtCredito;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreProducto;
    private javax.swing.JTextField txtTelefonoCliente;
    private javax.swing.JTextField txtValorProducto;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == txtNombreCliente) {
            validacionLetras(txtNombreCliente);

        }
        if (e.getSource() == txtCedulaCliente) {
            validacionNumeros(txtCedulaCliente);
        }
        if (e.getSource() == txtTelefonoCliente) {
            validacionNumeros(txtTelefonoCliente);
        }

    }
}
