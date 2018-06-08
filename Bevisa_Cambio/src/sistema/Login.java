
package sistema;

import datos.Conexion;
import datos.Login_DB;
import java.awt.Color;
import negocio.Usuario;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;


public class Login extends javax.swing.JFrame {

    Connection con;
    List<Usuario> user ;
    
    public Login() {
        initComponents();
        try {
            con = Conexion.getConnection();
            Login_DB login = new Login_DB(con);
            user =login.select();
            //Ventana en medio 
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error  de conexion "+ ex);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error :"+ex);
        }
        
    }

    @Override
    public Image getIconImage() {
       Image retValue = Toolkit.getDefaultToolkit().
             getImage(ClassLoader.getSystemResource("img/icono.png"));


       return retValue;
    }
    
    public void camcolor(JButton jp)
    {
        jp.setBackground(new java.awt.Color(240,168,7));
    }
    
    public void resetcolor(JButton jp)
    {
        jp.setBackground(new java.awt.Color(240,200,115));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btncancelar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        txtuser = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        txtpass = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(getIconImage());
        setIconImages(getIconImages());
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(162, 127, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(162, 127, 51)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bevisalogo.png"))); // NOI18N
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 207, 113));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btncancelar.setBackground(new java.awt.Color(240, 200, 115));
        btncancelar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btncancelar.setText("Cancelar");
        btncancelar.setBorderPainted(false);
        btncancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btncancelarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btncancelarMouseExited(evt);
            }
        });
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel13.setText("Usuario :");

        jLabel15.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel15.setText("Contraseña :");

        btnAceptar.setBackground(new java.awt.Color(240, 200, 115));
        btnAceptar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.setBorderPainted(false);
        btnAceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAceptarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAceptarMouseExited(evt);
            }
        });
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        txtuser.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtuser.setBorder(null);
        txtuser.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtuser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtuskey(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pass.png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user.png"))); // NOI18N

        jSeparator1.setForeground(new java.awt.Color(162, 127, 51));

        jSeparator2.setForeground(new java.awt.Color(162, 127, 51));

        txtpass.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtpass.setBorder(null);
        txtpass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtpassKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btncancelar)
                        .addGap(47, 47, 47)
                        .addComponent(btnAceptar))
                    .addComponent(jLabel15)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtpass, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtuser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(0, 39, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel13)
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(txtuser, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(txtpass, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btncancelar)
                    .addComponent(btnAceptar))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 320, 230));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btncancelarActionPerformed

    public void entrar()
    {
        
        try{
            
            if (validacion_vacio()) {
                boolean correcto=false;
                Usuario user=null;
                for (int i = 0; i < this.user.size(); i++) {
                    if (this.txtuser.getText().compareTo(this.user.get(i).getUser())== 0) {
                        if (this.txtpass.getText().compareTo(this.user.get(i).getPass())== 0){
                            correcto=true; 
                            user=this.user.get(i);
                        }
                    }
                }

                if (correcto) {
                    JOptionPane.showMessageDialog(null, "Bienvenido");
                    Menu_Principal mp = new Menu_Principal(user,this.con);
                    mp.setVisible(true);
                    this.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Datos incorrectos");
                    this.txtpass.setText("");
                }
            }

        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Error :"+ex);
        } 
    }

    public boolean validacion_vacio(){
        boolean pasa=true;
        String error="";
        if (this.txtuser.getText().isEmpty()) {
            this.txtuser.setBackground(Color.decode("#FFCCCC"));
            pasa=false;
            error+="El usuario esta vacio\n";
        }
        
        if (this.txtpass.getText().isEmpty()) {
            this.txtpass.setBackground(Color.decode("#FFCCCC"));
            pasa=false;
            error+="El password esta vacio\n";
        }
        
        if (!pasa) {
            JOptionPane.showMessageDialog(null, "Error : Campo vacio\n"+error);
        }
        
        return pasa;
    }
    
    
    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
     entrar();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void txtpassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpassKeyPressed
         if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            entrar();
         }
         else
         {
             this.txtuser.setBackground(Color.white);
             this.txtpass.setBackground(Color.white);
         }
    }//GEN-LAST:event_txtpassKeyPressed

    private void btnAceptarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAceptarMouseEntered
       this.camcolor(btnAceptar);
    }//GEN-LAST:event_btnAceptarMouseEntered

    private void btnAceptarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAceptarMouseExited
       this.resetcolor(btnAceptar);
    }//GEN-LAST:event_btnAceptarMouseExited

    private void btncancelarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncancelarMouseEntered
       this.camcolor(btncancelar);
    }//GEN-LAST:event_btncancelarMouseEntered

    private void btncancelarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncancelarMouseExited
        this.resetcolor(btnAceptar);
    }//GEN-LAST:event_btncancelarMouseExited

    private void txtuskey(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtuskey
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            entrar();
         }
         else
         {
             this.txtuser.setBackground(Color.white);
         }
    }//GEN-LAST:event_txtuskey

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPasswordField txtpass;
    private javax.swing.JTextField txtuser;
    // End of variables declaration//GEN-END:variables
}
