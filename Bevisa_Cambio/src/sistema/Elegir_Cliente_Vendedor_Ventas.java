
package sistema;

import datos.Ventas_DB;
import java.awt.Dimension;
import static java.awt.Frame.NORMAL;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import negocio.Clientes;
import negocio.Vendedor;

/**
 *
 * @author JAMS
 */
public class Elegir_Cliente_Vendedor_Ventas extends javax.swing.JFrame {

    private DefaultTableModel tabla;
    private int columna;

    private Ventas mov;
    private int opc;
    
    private Connection con;
    /**
     * Creates new form Elegir_Producto
     */
    public Elegir_Cliente_Vendedor_Ventas(Ventas mov,int opc,Connection con) throws SQLException {
        
        this.con=con;  
        initComponents();
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.mov=mov;
        this.opc=opc;
        
        creaciontabla(this.opc);
    }
    
    @Override
    public Image getIconImage() {
       Image retValue = Toolkit.getDefaultToolkit().
             getImage(ClassLoader.getSystemResource("img/icono.png"));


       return retValue;
    }
    
     public void creaciontabla(int opc) throws SQLException
    {
        Ventas_DB db = new Ventas_DB(this.con);
        if(opc==1){
            this.tabla=(DefaultTableModel) this.tbdatos.getModel();
            this.tabla.setRowCount(0);
            List<Vendedor>vendedores=db.select_vendedor();
            for (int i = 0; i < vendedores.size(); i++) {
                Object[] obj = new Object[4];
                obj[0]=vendedores.get(i).getId();
                obj[1]=vendedores.get(i).getNombre();
                obj[2]=vendedores.get(i).getTelefono();
                obj[3]=vendedores.get(i).getCorreo();
                this.tabla.addRow(obj);
            }

        }
        else
        {
            this.tabla=(DefaultTableModel) this.tbdatos.getModel();
            this.tabla.setRowCount(0);
            
            List<Clientes>clientes=db.select_cliente();
            for (int i = 0; i < clientes.size(); i++) {
                Object[] obj = new Object[6];
                obj[0]=clientes.get(i).getId();
                obj[1]=clientes.get(i).getNombre();
                obj[2]=clientes.get(i).getTelefono();
                obj[3]=clientes.get(i).getCorreo();
                obj[4]=clientes.get(i).getNombref();
                obj[5]=clientes.get(i).getVendedor();

                this.tabla.addRow(obj);
            }
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtcorreo = new javax.swing.JTextField();
        txttelefono = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        txtid = new javax.swing.JTextField();
        txtbuscar = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(getIconImage());
        setIconImages(getIconImages());
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(162, 127, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setText("Vendedor / Clientes");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, -1, 39));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbdatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Telefono", "Correo", "ID vendedor", "Vendedor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbdatos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbdatos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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
            tbdatos.getColumnModel().getColumn(0).setPreferredWidth(35);
            tbdatos.getColumnModel().getColumn(1).setPreferredWidth(200);
            tbdatos.getColumnModel().getColumn(2).setPreferredWidth(100);
            tbdatos.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 11, 510, 240));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Buscar  :");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("ID  :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Nombre  :");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Telefono :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Correo :");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 180, -1, -1));

        txtcorreo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtcorreo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtcorreo.setEnabled(false);
        jPanel2.add(txtcorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 180, 260, -1));

        txttelefono.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txttelefono.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txttelefono.setEnabled(false);
        jPanel2.add(txttelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 140, 260, -1));

        txtnombre.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtnombre.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnombre.setEnabled(false);
        jPanel2.add(txtnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, 260, -1));

        txtid.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtid.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtid.setEnabled(false);
        jPanel2.add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 60, 152, -1));

        txtbuscar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
        });
        jPanel2.add(txtbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(612, 20, 220, -1));

        btnAceptar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.setEnabled(false);
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        jPanel2.add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 230, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 56, 880, 280));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        tr.setRowFilter(RowFilter.regexFilter(this.txtbuscar.getText().toUpperCase(),1));
    }//GEN-LAST:event_txtbuscarKeyReleased

    private void tbdatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbdatosMouseClicked
        try
        {
            this.columna=this.tbdatos.getSelectedRow();

            this.txtid.setText(this.tbdatos.getValueAt(this.columna, 0).toString());
            this.txtnombre.setText(this.tbdatos.getValueAt(this.columna, 1).toString());
            this.txttelefono.setText(this.tbdatos.getValueAt(this.columna, 2).toString());
            this.txtcorreo.setText(this.tbdatos.getValueAt(this.columna, 3).toString());
            this.btnAceptar.setEnabled(true);
          
        }
        catch(Exception ex)
        {

        }
    }//GEN-LAST:event_tbdatosMouseClicked

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        try{
            if(this.opc==1){
            Vendedor vende = new Vendedor();
            vende.setId(Integer.parseInt(this.txtid.getText()));
            vende.setNombre(this.txtnombre.getText());
            this.mov.colocarvendedor(vende);
            }
            else
            {
                Clientes clien = new Clientes();
                clien.setId(Integer.parseInt(this.txtid.getText()));
                clien.setNombre(this.txtnombre.getText());
                this.mov.colocarcliente(clien);
              
            }
            this.mov.setEnabled(true);
            this.mov.setState(NORMAL);
            this.dispose();
        
        } 
        catch (Exception ex) {
                Logger.getLogger(Elegir_Cliente_Vendedor_Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
         this.mov.setEnabled(true);
         this.mov.setState(NORMAL);
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(Elegir_Cliente_Vendedor_Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Elegir_Cliente_Vendedor_Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Elegir_Cliente_Vendedor_Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Elegir_Cliente_Vendedor_Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbdatos;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtcorreo;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
