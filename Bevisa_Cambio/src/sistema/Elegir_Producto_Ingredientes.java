/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import datos.Conexion;
import datos.Ingredientes_DB;
import java.awt.Color;
import java.awt.Dimension;
import static java.awt.Frame.NORMAL;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import negocio.Ingrediente;
import negocio.Producto;

/**
 *
 * @author JAMS
 */
public class Elegir_Producto_Ingredientes extends javax.swing.JFrame {
    

    
    private DefaultTableModel tabla;
    private int columna;
    private int opc;
    private Connection con;
    
    
    private Datos vpro;
    /**
     * Creates new form Elegir_Producto
     */
    public Elegir_Producto_Ingredientes(Datos p, int opc,Connection con) throws SQLException {
        initComponents();
        this.con=con;
        if (this.con.isClosed() || this.con.isReadOnly()) {
            this.con.close();
            this.con=Conexion.getConnection();
        }
        this.opc=opc;
        this.tabla=(DefaultTableModel) this.tbdatos.getModel();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        creaciontabla(this.opc);
        this.vpro=p;       
    }
    
     public void creaciontabla(int opc)
    {
        try{
            if(opc==1  || opc ==3){
            
            Ingredientes_DB inge = new Ingredientes_DB(this.con);
            List <Producto> productos = inge.select_pt();
            this.tabla.setRowCount(0);

            for (int i = 0; i < productos.size(); i++) {    
                Object[] obj = new Object[4];
                obj[0]=productos.get(i).getId();
                obj[1]=productos.get(i).getClave();
                obj[2]=productos.get(i).getNombre();
                obj[3]=productos.get(i).getMedida();
                this.tabla.addRow(obj);
            } 
            
            this.lbcantidad.setVisible(false);
            this.lblnota.setVisible(false);
            this.txtcantidad.setVisible(false);
            }
            else
            {

               this.tabla.setRowCount(0);

               Ingredientes_DB inge = new Ingredientes_DB(this.con);
               List <Producto> productos = inge.select_ma();
               this.tabla.setRowCount(0);

               for (int i = 0; i < productos.size(); i++) {    
                   Object[] obj = new Object[4];
                   obj[0]=productos.get(i).getId();
                   obj[1]=productos.get(i).getClave();
                   obj[2]=productos.get(i).getNombre();
                   obj[3]=productos.get(i).getMedida();
                   this.tabla.addRow(obj);
               } 
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error  de conexion "+ ex);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error :"+ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbdatos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtbuscar = new javax.swing.JTextField();
        txtid = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtclave = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtmedida = new javax.swing.JTextField();
        lbcantidad = new javax.swing.JLabel();
        txtcantidad = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        lblnota = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(162, 127, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setText("Productos");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, -1, 39));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tbdatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Clave", "Nombre", "U Medida"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbdatos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbdatos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbdatos.setGridColor(new java.awt.Color(204, 204, 204));
        tbdatos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbdatos.setShowHorizontalLines(false);
        tbdatos.setShowVerticalLines(false);
        tbdatos.getTableHeader().setReorderingAllowed(false);
        tbdatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbdatosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbdatos);
        tbdatos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (tbdatos.getColumnModel().getColumnCount() > 0) {
            tbdatos.getColumnModel().getColumn(0).setPreferredWidth(30);
            tbdatos.getColumnModel().getColumn(1).setPreferredWidth(50);
            tbdatos.getColumnModel().getColumn(2).setPreferredWidth(200);
        }

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 24, 370, 200));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Buscar  :");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 30, -1, -1));

        txtbuscar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
        });
        jPanel2.add(txtbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, 260, -1));

        txtid.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtid.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtid.setEnabled(false);
        jPanel2.add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 70, 147, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("ID Producto :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Clave :");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, -1, -1));

        txtclave.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtclave.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtclave.setEnabled(false);
        jPanel2.add(txtclave, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, 147, -1));

        txtnombre.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtnombre.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnombre.setEnabled(false);
        jPanel2.add(txtnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 130, 260, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Nombre Producto :");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("U. Medida :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 160, -1, -1));

        txtmedida.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtmedida.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtmedida.setEnabled(false);
        jPanel2.add(txtmedida, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, 147, -1));

        lbcantidad.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbcantidad.setText("Cantidad :");
        lbcantidad.setToolTipText("");
        jPanel2.add(lbcantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 190, -1, -1));

        txtcantidad.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtcantidad.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtcantidad.setEnabled(false);
        txtcantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcantidadKeyReleased(evt);
            }
        });
        jPanel2.add(txtcantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 190, 147, -1));

        btnAceptar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.setEnabled(false);
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        jPanel2.add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 230, 150, -1));

        lblnota.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        lblnota.setText("*Nota: Para campos numericos se aceptan con o sin punto decimal");
        lblnota.setFocusable(false);
        jPanel2.add(lblnota, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 330, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 56, 780, 280));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(this.tabla);
        this.tbdatos.setRowSorter(tr);

        tr.setRowFilter(RowFilter.regexFilter(this.txtbuscar.getText().toUpperCase(),2));
    }//GEN-LAST:event_txtbuscarKeyReleased

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        try{
            if(this.opc==1){
            Producto pro = new Producto();    
            pro.setNombre(this.txtnombre.getText());
            pro.setClave(this.txtclave.getText());
            pro.setId(Integer.parseInt(this.txtid.getText()));  
            this.vpro.colocarproductoin(pro);
            this.vpro.setEnabled(true);
            this.vpro.setState(NORMAL);
            this.dispose();

            }
            else
            {
                if (this.opc==3) {              
                    Producto pro = new Producto();    
                    pro.setNombre(this.txtnombre.getText());
                    pro.setClave(this.txtclave.getText());
                    pro.setId(Integer.parseInt(this.txtid.getText()));  
                    this.vpro.insertarpro_prueba(pro);
                    this.vpro.setEnabled(true);
                    this.vpro.setState(NORMAL);
                    this.dispose();
                }
                else
                {
                    if (!this.txtcantidad.getText().isEmpty()) {
                        if(this.txtcantidad.getText().matches("([0-9]+)(\\.[0-9]+)?")){
                        Ingrediente ing = new Ingrediente();    

                        ing.setNombre(this.txtnombre.getText());
                        ing.setClave(this.txtclave.getText());
                        ing.setId(Integer.parseInt(this.txtid.getText())); 
                        ing.setCantidad(Double.parseDouble(this.txtcantidad.getText()));

                        this.vpro.insetaringre(ing);
                        this.vpro.setEnabled(true);
                        this.vpro.setState(NORMAL);
                        this.dispose();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Formato Incorrecto:\nEjemplo: 14.25 ó 5");
                            this.txtcantidad.setBackground(Color.decode("#FFCCCC"));
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Cantidad Vacia");
                        this.txtcantidad.setBackground(Color.decode("#FFCCCC"));
                    }
                    
                }
            }
        } 
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error  de conexion "+ ex);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error :"+ex);
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
         this.vpro.setEnabled(true);
         this.vpro.setState(NORMAL);
    }//GEN-LAST:event_formWindowClosing

    private void tbdatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbdatosMouseClicked
        try
        {
            this.columna=this.tbdatos.getSelectedRow();

            this.txtid.setText(this.tbdatos.getValueAt(this.columna, 0).toString());
            this.txtclave.setText(this.tbdatos.getValueAt(this.columna, 1).toString());
            this.txtnombre.setText(this.tbdatos.getValueAt(this.columna, 2).toString());
            this.txtmedida.setText(this.tbdatos.getValueAt(this.columna, 3).toString());
            this.btnAceptar.setEnabled(true);
            this.txtcantidad.setEnabled(true);
        }
        catch(Exception ex)
        {

        }
    }//GEN-LAST:event_tbdatosMouseClicked

    private void txtcantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcantidadKeyReleased
        this.txtcantidad.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtcantidadKeyReleased

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
            java.util.logging.Logger.getLogger(Elegir_Producto_Ingredientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Elegir_Producto_Ingredientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Elegir_Producto_Ingredientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Elegir_Producto_Ingredientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                   // new Elegir_Producto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbcantidad;
    private javax.swing.JLabel lblnota;
    private javax.swing.JTable tbdatos;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtcantidad;
    private javax.swing.JTextField txtclave;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtmedida;
    private javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables
}
