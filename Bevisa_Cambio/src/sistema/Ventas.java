/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import datos.Conexion;
import negocio.*;
import datos.DBcontrolador;
import datos.Ventas_DB;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.PrinterName;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author PC
 */
public class Ventas extends javax.swing.JFrame {

    /**
     * Creates new form Ventas
     */
    private  DBcontrolador dbc;
    private Connection con;
    
    private Menu_Principal mp ;
    
    private negocio.Ventas venta;
    
    private double importe;
    private double iva;
    private double total;
    
    private String s;
    //vendedores
    private DefaultTableModel ventas;
    
    public String fechadividir(String  jt, int i ){
        String fi ="";
        if (i == 0) {
            String[] s = jt.split("-");
            fi = s[2]+"/"+s[1]+"/"+s[0];       
        }
        else
        {
            String[] s = jt.split("/");
            fi = s[2]+"-"+s[1]+"-"+s[0]; 
        }
        return fi;
    }
    
    public Ventas(Menu_Principal mp ,Connection con) throws SQLException {
        
        initComponents();
        this.venta = new negocio.Ventas();
        this.con=con;
        this.mp=mp;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        Ventas_DB vt = new Ventas_DB(this.con);
        List<String> lista=vt.select();
        for (int i = 0; i < lista.size(); i++) {
            this.cbconcepto.addItem(lista.get(i));
        }
        this.cbconcepto.setSelectedIndex(1);
        
        Path c = Paths.get("");
        s = c.toAbsolutePath().toString();
        this.importe=0;
        this.iva=0;
        LocalDate dt = LocalDate.now();
        this.txtfecha.setText(dt.getDayOfMonth()+"/"+dt.getMonthValue()+"/"+dt.getYear());
        this.ventas = (DefaultTableModel) this.tbventas.getModel();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbconcepto = new javax.swing.JComboBox<>();
        txtnombrevendedor = new javax.swing.JTextField();
        txtidcliente = new javax.swing.JTextField();
        txtnombrecliente = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtfecha = new javax.swing.JTextField();
        btnvendedor = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbventas = new javax.swing.JTable();
        btnagregar = new javax.swing.JButton();
        btnquitar = new javax.swing.JButton();
        btnaceptar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtimporta = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtiva = new javax.swing.JTextField();
        txttotal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtidvendedor = new javax.swing.JTextField();
        btnelegircliente = new javax.swing.JButton();
        btncancelacion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(162, 127, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel13.setText("Ventas");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, -1, 39));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnelegirclienteKeyPressed(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Concepto :");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("ID vendedor :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 48, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Nombre Vendedor :");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 78, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("ID Cliente :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 110, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Nombre Cliente :");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 139, -1, -1));

        cbconcepto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnelegirclienteKeyPressed(evt);
            }
        });
        jPanel2.add(cbconcepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 10, 200, -1));

        txtnombrevendedor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtnombrevendedor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnombrevendedor.setEnabled(false);
        jPanel2.add(txtnombrevendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 75, 360, -1));

        txtidcliente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtidcliente.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtidcliente.setEnabled(false);
        jPanel2.add(txtidcliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 107, 140, -1));

        txtnombrecliente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtnombrecliente.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnombrecliente.setEnabled(false);
        jPanel2.add(txtnombrecliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 136, 360, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Fecha :");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 20, -1, -1));

        txtfecha.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtfecha.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtfecha.setEnabled(false);
        jPanel2.add(txtfecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 20, 155, -1));

        btnvendedor.setText("Elegir [F8]");
        btnvendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvendedorActionPerformed(evt);
            }
        });
        btnvendedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnelegirclienteKeyPressed(evt);
            }
        });
        jPanel2.add(btnvendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, 100, -1));

        tbventas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Clave", "Nombre", "P. Venta", "Cantidad", "% Descuento", "Imp. Descuento", "Importe", "Comision"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbventas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbventas.setCellSelectionEnabled(true);
        tbventas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbventas.getTableHeader().setReorderingAllowed(false);
        tbventas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnelegirclienteKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(tbventas);
        tbventas.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (tbventas.getColumnModel().getColumnCount() > 0) {
            tbventas.getColumnModel().getColumn(0).setPreferredWidth(35);
            tbventas.getColumnModel().getColumn(1).setPreferredWidth(60);
            tbventas.getColumnModel().getColumn(2).setPreferredWidth(300);
            tbventas.getColumnModel().getColumn(4).setPreferredWidth(100);
            tbventas.getColumnModel().getColumn(5).setPreferredWidth(100);
            tbventas.getColumnModel().getColumn(6).setPreferredWidth(100);
            tbventas.getColumnModel().getColumn(7).setPreferredWidth(100);
            tbventas.getColumnModel().getColumn(8).setPreferredWidth(100);
        }

        jPanel2.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 185, 1150, 250));

        btnagregar.setText("Agregar Producto  F[10]");
        btnagregar.setEnabled(false);
        btnagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarActionPerformed(evt);
            }
        });
        btnagregar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnelegirclienteKeyPressed(evt);
            }
        });
        jPanel2.add(btnagregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 223, -1));

        btnquitar.setText("Quitar Producto");
        btnquitar.setEnabled(false);
        btnquitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnquitarActionPerformed(evt);
            }
        });
        btnquitar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnelegirclienteKeyPressed(evt);
            }
        });
        jPanel2.add(btnquitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 450, 223, -1));

        btnaceptar.setText("Aceptar");
        btnaceptar.setEnabled(false);
        btnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptarActionPerformed(evt);
            }
        });
        btnaceptar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnelegirclienteKeyPressed(evt);
            }
        });
        jPanel2.add(btnaceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 460, 107, 20));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Importe :");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 60, -1, -1));

        txtimporta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtimporta.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtimporta.setEnabled(false);
        jPanel2.add(txtimporta, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 60, 155, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("IVA :");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 90, -1, -1));

        txtiva.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtiva.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtiva.setEnabled(false);
        jPanel2.add(txtiva, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 90, 155, -1));

        txttotal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txttotal.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txttotal.setEnabled(false);
        jPanel2.add(txttotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 120, 155, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Total :");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 120, -1, -1));

        txtidvendedor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtidvendedor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtidvendedor.setEnabled(false);
        jPanel2.add(txtidvendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 45, 140, -1));

        btnelegircliente.setText("Elegir [F9]");
        btnelegircliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnelegirclienteActionPerformed(evt);
            }
        });
        btnelegircliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnelegirclienteKeyPressed(evt);
            }
        });
        jPanel2.add(btnelegircliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 105, 100, -1));

        btncancelacion.setText("Cancelación de Ticket");
        btncancelacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelacionActionPerformed(evt);
            }
        });
        btncancelacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btncancelacionbtnelegirclienteKeyPressed(evt);
            }
        });
        jPanel2.add(btncancelacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 450, 190, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1210, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public void colocarvendedor(Vendedor vende)
    {
        this.txtidvendedor.setText(vende.getId()+"");
        this.txtnombrevendedor.setText(vende.getNombre()+"");
        this.venta.setIdvendedor(vende.getId()+"");
        
        this.btnagregar.setEnabled(true);
        this.btnquitar.setEnabled(true);
        
        this.txtfecha.setEnabled(true);
    }
    
    public void colocarcliente(Clientes clie)
    {
        this.txtidcliente.setText(clie.getId()+"");
        this.txtnombrecliente.setText(clie.getNombre()+"");
        this.venta.setIdcliente(clie.getId()+"");
        this.txtfecha.setEnabled(true);
        this.btnagregar.setEnabled(true);
        this.btnquitar.setEnabled(true);
        this.txtfecha.setEnabled(true);
    }
    
    
    public void colocarproducto(String[] c)
    {
       
        DetalleVenta dt = new DetalleVenta();
        
        String [] producto_tabla = new String[9];
        producto_tabla[0]=c[0];
        producto_tabla[1]=c[1];
        producto_tabla[2]=c[2];
        producto_tabla[3]=c[6];
        producto_tabla[4]=c[4];
        producto_tabla[5]=c[3];
        
        double ptotal = (Double.parseDouble(c[6]) * Double.parseDouble(c[4]));
        double des = ptotal * (Double.parseDouble(c[3])/100);
        producto_tabla[6]=des+"";
        
        if (!c[7].equals("0")) {
            this.iva+=ptotal*0.16;
            dt.setIva(ptotal*0.16);
        }else
        {
            dt.setIva(0.0);
        }
        producto_tabla[7]=(ptotal-des)+"";
        producto_tabla[8]=Double.parseDouble(c[5])*Double.parseDouble(c[4])+"";

        dt.setIdproducto(Integer.parseInt(c[0]));
        dt.setPventa(Double.parseDouble(c[6]));
        dt.setCantidad(Double.parseDouble(c[4]));
        dt.setImporte(ptotal);
        dt.setDescuento(Double.parseDouble(c[3]));
        dt.setIdescuento(des);
        
        dt.setTotal(ptotal-des);
        dt.setComision(Double.parseDouble(c[5])*Double.parseDouble(c[4]));
        
        this.venta.getDetalle().add(dt);
        
        this.ventas.addRow(producto_tabla);

        this.importe+=ptotal-des;
        this.total=importe+this.iva;
        
        this.txtimporta.setText(importe+"");
        this.txtiva.setText(this.iva+"");
        this.txttotal.setText(total+"");
        
        this.btnaceptar.setEnabled(true);
        
    }
    
    private void btnquitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnquitarActionPerformed
      try{
        int col = this.tbventas.getSelectedRow();

        if (this.venta.getDetalle().get(col).getIva().intValue()!=0) {
          
            this.iva-=Double.parseDouble(this.ventas.getValueAt(col, 7).toString())*0.16;
        }
        
        this.venta.getDetalle().remove(col);
        
        this.importe-=Double.parseDouble(this.ventas.getValueAt(col, 7).toString());
       
        this.total=importe+iva;
        
        this.txtimporta.setText(importe+"");
        this.txtiva.setText(iva+"");
        this.txttotal.setText(total+"");
        
        this.ventas.removeRow(col);
        
          if (this.tbventas.getRowCount() < 0) {
              this.btnaceptar.setEnabled(false);
          }
      }
      catch(Exception ex)
      {
          System.out.println(ex);
      }
      
    }//GEN-LAST:event_btnquitarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.mp.notificaciones();
        this.mp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void btnvendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvendedorActionPerformed
       try{        
       Elegir_Cliente_Vendedor_Ventas cv = new Elegir_Cliente_Vendedor_Ventas(this,1,this.con);
       cv.setVisible(true);
       }
       catch(Exception ex)
       {
           
       }
    }//GEN-LAST:event_btnvendedorActionPerformed

    private void btnelegirclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnelegirclienteActionPerformed
        try{
       this.setEnabled(false);
       Elegir_Cliente_Vendedor_Ventas cv = new Elegir_Cliente_Vendedor_Ventas(this,2,this.con);
       cv.setVisible(true);
       }
       catch(Exception ex)
       {
           
       }
    }//GEN-LAST:event_btnelegirclienteActionPerformed

    private void btnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarActionPerformed
       try{
       this.mp.setVisible(false);
       Elegir_ProductoVentas cv = new Elegir_ProductoVentas(this,this.con);
       cv.setVisible(true);
       }
       catch(Exception ex)
       {
           
       }
    }//GEN-LAST:event_btnagregarActionPerformed

    private void btnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarActionPerformed
        try{
            Ventas_DB db= new Ventas_DB(this.con);

            if (this.txtfecha.getText().matches("^[0-9]{1,2}\\/[0-9]{1,2}\\/[0-9]{4}$") && this.tbventas.getRowCount() > 0) {
                
                boolean hay = true;
                String falta="";
                for (int i = 0; i < this.tbventas.getRowCount(); i++) {
                     double cantidad=db.validacion_prodvtas(this.tbventas.getValueAt(i, 0).toString());
                     if (cantidad==0) {
                        falta+="Falta "+this.tbventas.getValueAt(i, 4).toString()+" del prodcuto "+ this.tbventas.getValueAt(i, 2).toString() +"\n";
                        hay = false;
                    }
                    else
                    {
                        if (cantidad < Double.parseDouble(this.tbventas.getValueAt(i, 4).toString() )) {
                           hay = false;
                           double aux=Double.parseDouble(this.tbventas.getValueAt(i, 4).toString())-cantidad;
                           falta+="Falta "+aux+" del prodcuto "+ this.tbventas.getValueAt(i, 2).toString() +"\n";
                       }
                    }
                    
                }
                if (hay) {
                    String respuesta = JOptionPane.showInputDialog(this, "Observaciones");
                    this.venta.setFecha(this.fechadividir(this.txtfecha.getText(), 1));
                    this.venta.setImporte(importe);
                    this.venta.setIva(iva);
                    this.venta.setTotal(importe);
                    this.venta.setTipo(this.cbconcepto.getSelectedIndex()+1+"");
                    this.venta.setOp(respuesta);
                    int id_venta=db.insert(venta);      
                    List <String[]> lotes = new ArrayList<>();
                    for (int i = 0; i < this.ventas.getRowCount(); i++) {
                        lotes=db.validacion_lotes(this.tbventas.getValueAt(i, 0).toString());
                        double cantidad = Double.parseDouble(this.ventas.getValueAt(i, 4).toString());
                        for (int j = 0; j < lotes.size(); j++) {

                            double cantidad_inventario = Double.parseDouble(lotes.get(j)[1]);

                            double aux = cantidad_inventario-cantidad;
                            if (cantidad != 0) {

                                if (aux >= 0) {
                                    this.venta.getDetalle().get(i).setIdventa(id_venta);
                                    this.venta.getDetalle().get(i).setIdinven(Integer.parseInt(lotes.get(j)[0]));
                                    db.insert_dv(this.venta.getDetalle().get(i));
                                    
                                    Inventario inven = new Inventario();
                                    inven.setId(Integer.parseInt(lotes.get(j)[0]));
                                    inven.setCantidad_actual(aux);
                                    db.update_inv(inven);
                                    cantidad=0;
                                }
                                else
                                {
                                    this.venta.getDetalle().get(i).setIdventa(id_venta);
                                    this.venta.getDetalle().get(i).setIdventa(Integer.parseInt(lotes.get(j)[0]));
                                    db.insert_dv(this.venta.getDetalle().get(i));
                                    
                                    Inventario inven = new Inventario();
                                    inven.setId(Integer.parseInt(lotes.get(j)[0]));
                                    inven.setCantidad_actual(0);
                                    db.update_inv(inven);
                                    cantidad-=cantidad_inventario;
                                }
                            }
                        }
                    }
                  
                    
                    //limpiar
                    this.ventas.setRowCount(0);
                    
                    this.txtidcliente.setText("");
                    this.txtnombrecliente.setText("");
                    this.txtidvendedor.setText("");
                    this.txtnombrevendedor.setText("");
                    this.total=0;
                    this.importe=0;
                    this.iva=0;
                    this.txtimporta.setText("");
                    this.txtiva.setText("");
                    this.txttotal.setText("");
                    
                    //deshabilitar
                    this.btnaceptar.setEnabled(false);
                    this.btnagregar.setEnabled(false);
                    this.btnquitar.setEnabled(false);
                    
                    
                    JasperReport reporte; //Creo el objeto reporte
                    // Ubicacion del Reporte
                   String path = s+"\\Reportes\\Ventas_Ticket.jasper";
                   try {
                       Map categoria = new HashMap();
                       categoria.put("ID_Venta", id_venta);
                       categoria.put("op",respuesta);
                       reporte = (JasperReport) JRLoader.loadObjectFromFile(path); //Cargo el reporte al objeto
                       JasperPrint jprint = JasperFillManager.fillReport(path, categoria,this.dbc.getCnx()); //Llenado del Reporte con Tres parametros ubicacion,parametros,conexion a la base de datos
                       JasperExportManager.exportReportToPdfFile(jprint, s+"\\Tickets\\Venta-"+id_venta+".pdf");

                       
                       if (this.cbconcepto.getSelectedIndex()==1)
                       {
                           for (int i = 0; i < 2; i++) {
                                PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();

                                printRequestAttributeSet.add(new Copies(1));
                                PrinterName printerName = new PrinterName("EPSON TM-T88V Receipt", null); //gets printer 
                                PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
                                printServiceAttributeSet.add(printerName);
                                JRPrintServiceExporter exporter = new JRPrintServiceExporter();
                                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jprint);
                                exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
                                exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printServiceAttributeSet);
                                exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
                                exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
                                exporter.exportReport();
                               }              
                       }

                       JasperViewer viewer = new JasperViewer(jprint,false); //Creamos la vista del Reporte
                       viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Le agregamos que se cierre solo el reporte cuando lo cierre el usuario
                       viewer.setVisible(true); //Inicializamos la vista del Reporte
                        
                        
                       
                   } 
                   catch(Exception ex)
                    {
                        JOptionPane.showMessageDialog(null, "Error  de conexion "+ ex);
                         try {
                            this.con=Conexion.getConnection();
                        } catch (SQLException ex1) {
                            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }
                    
                }
                else
                {
                    JOptionPane.showMessageDialog(null, falta);
                }

            }
            else
            {
                JOptionPane.showMessageDialog(null, "Error en los datos");
            }

        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error  de conexion "+ ex);
             try {
                this.con=Conexion.getConnection();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error :"+ex);
        }

    }//GEN-LAST:event_btnaceptarActionPerformed

    private void btnelegirclienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnelegirclienteKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_F10) {
            try{
                if (this.btnagregar.isEnabled()) {
                    this.mp.setVisible(false);
                    Elegir_ProductoVentas cv = new Elegir_ProductoVentas(this,this.con);
                    cv.setVisible(true);
                }
                
            }
            catch(Exception ex)
            {

            }
         }
        if(evt.getKeyCode() == KeyEvent.VK_F9) {
            try{
            this.setEnabled(false);
            Elegir_Cliente_Vendedor_Ventas cv = new Elegir_Cliente_Vendedor_Ventas(this,2,con);
            cv.setVisible(true);
            }
            catch(Exception ex)
            {

            }
         }
        if(evt.getKeyCode() == KeyEvent.VK_F8) {
            try{        
        
            Elegir_Cliente_Vendedor_Ventas cv = new Elegir_Cliente_Vendedor_Ventas(this,1,this.con);
            cv.setVisible(true);
            }
            catch(Exception ex)
            {

            }
         }

    }//GEN-LAST:event_btnelegirclienteKeyPressed

    private void btncancelacionbtnelegirclienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btncancelacionbtnelegirclienteKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btncancelacionbtnelegirclienteKeyPressed

    private void btncancelacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelacionActionPerformed
        try{
            Ventas_DB db = new Ventas_DB(this.con);
            String respuesta = JOptionPane.showInputDialog(null, "Escribe el Folio del Ticket");
            if (db.cancelacion(respuesta)) {
                JOptionPane.showMessageDialog(null, "Ticket Cancelado");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "No Existe el Ticket ");
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error  de conexion "+ ex);
             try {
                this.con=Conexion.getConnection();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error :"+ex);
        }     
    }//GEN-LAST:event_btncancelacionActionPerformed

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
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               // new Ventas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnaceptar;
    private javax.swing.JButton btnagregar;
    private javax.swing.JButton btncancelacion;
    private javax.swing.JButton btnelegircliente;
    private javax.swing.JButton btnquitar;
    private javax.swing.JButton btnvendedor;
    private javax.swing.JComboBox<String> cbconcepto;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tbventas;
    private javax.swing.JTextField txtfecha;
    private javax.swing.JTextField txtidcliente;
    private javax.swing.JTextField txtidvendedor;
    private javax.swing.JTextField txtimporta;
    private javax.swing.JTextField txtiva;
    private javax.swing.JTextField txtnombrecliente;
    private javax.swing.JTextField txtnombrevendedor;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
}
