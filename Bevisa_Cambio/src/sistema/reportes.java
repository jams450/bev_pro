
package sistema;

import com.toedter.calendar.JTextFieldDateEditor;
import datos.Clientes_DB;
import datos.Conexion;
import datos.DBcontrolador;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import negocio.Clientes;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author JAMS
 */
public class reportes extends javax.swing.JFrame {
    private Connection con;
    private DBcontrolador dbc;
    private Menu_Principal mp;
    
    private String s;
    
    
     public String fechadividir(String  jt, int i ){
        String fi ="";
        if (i == 0) {
            //bajada
            String[] s = jt.split("-");
            fi = s[2]+"/"+s[1]+"/"+s[0];       
        }
        else
        {
            //subida
            String[] s = jt.split("/");
            fi = s[2]+"-"+s[1]+"-"+s[0]; 
        }
        return fi;
    }
    
    public reportes(Menu_Principal mp, Connection con) throws SQLException {
        initComponents();
        try {
            this.con=con;
            this.mp=mp;
            this.dbc=new DBcontrolador();
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
            String query="select * from delegacion;";
            List <String> op; 
            Clientes_DB db = new Clientes_DB(this.con);
            
            op=db.combo_dele();
            for (int i = 0; i < op.size(); i++) {
                this.cbdele.addItem(op.get(i)); 
            }
            
            Path c = Paths.get("");
            s = c.toAbsolutePath().toString();
            //s+="\\src\\reportes\\";
            //final
            s+="\\Reportes\\";
            
            Date date= new Date();
            this.jdate_ventas_sem1.setDateFormatString("dd/MM/yyyy");
            this.jdate_ventas_sem2.setDateFormatString("dd/MM/yyyy");
            this.jdate_ventas_sem1.setDate(date);
            this.jdate_ventas_sem2.setDate(date);
            
            JTextFieldDateEditor fecha = (JTextFieldDateEditor) this.jdate_ventas_sem1.getDateEditor();
            fecha.setEditable(false);
            JTextFieldDateEditor fecha1 = (JTextFieldDateEditor) this.jdate_ventas_sem2.getDateEditor();
            fecha1.setEditable(false);
            
            permisos();
        } 
        
        catch (Exception ex) {
            Logger.getLogger(reportes.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error de Conexion "+ex);
            this.con=Conexion.getConnection();
        }
        
    }
    
    public void permisos()
    {
        switch(this.mp.user.getPerfil())
        {
            //ALMACEN    
            case 2:
                this.tabpanel.setEnabledAt(0, false);
                this.tabpanel.setEnabledAt(1, false);
                this.tabpanel.setEnabledAt(2, false);
                this.tabpanel.setEnabledAt(3, true);
                this.tabpanel.setEnabledAt(4, true);
                this.tabpanel.setEnabledAt(5, true);
                break;
            //ALMACEN    
            case 3:
                this.tabpanel.setEnabledAt(0, false);
                this.tabpanel.setEnabledAt(1, true);
                this.tabpanel.setEnabledAt(2, false);
                this.tabpanel.setEnabledAt(3, false);
                this.tabpanel.setEnabledAt(4, false);
                this.tabpanel.setEnabledAt(5, false);
                this.tabpanel.setSelectedIndex(1);
                break;
            //PRODUCC    
            case 4:
                this.tabpanel.setEnabledAt(0, false);
                this.tabpanel.setEnabledAt(1, false);
                this.tabpanel.setEnabledAt(2, true);
                this.tabpanel.setEnabledAt(3, false);
                this.tabpanel.setEnabledAt(4, false);
                this.tabpanel.setEnabledAt(5, false);
                this.tabpanel.setSelectedIndex(2);
                break;
        }
    }
    
    @Override
    public Image getIconImage() {
       Image retValue = Toolkit.getDefaultToolkit().
             getImage(ClassLoader.getSystemResource("img/icono.png"));


       return retValue;
    }
    
    public void camcolor(JPanel jp)
    {
        jp.setBackground(new java.awt.Color(240,168,7));
    }
    
    public void resetcolor(JPanel jp)
    {
        jp.setBackground(new java.awt.Color(240,200,115));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tabpanel = new javax.swing.JTabbedPane();
        Productos = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        productostodos = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        productosterminados = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        productosmp = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        pruebas = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        ingredientes = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        Inventario = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        inventario_entradas_ano = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        inventario_entradas_mes = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        inventario_final_mes = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        inventario_final_ano = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        cbtipoinventario = new javax.swing.JComboBox<>();
        inventario_dinero_ano = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        inventario_dinero_mes = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jano_inventario = new com.toedter.calendar.JYearChooser();
        jmes_inventario = new com.toedter.calendar.JMonthChooser();
        Produccion = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        produccion_ano = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        produccion_mes = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        produccion_odp_nombre = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        txtodp = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jmes_prodruccion = new com.toedter.calendar.JMonthChooser();
        jano_prodruccion = new com.toedter.calendar.JYearChooser();
        jLabel80 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        vgpano = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        vgpmes = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        vgvmes = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        vgvano = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        cbtipoventa = new javax.swing.JComboBox<>();
        jLabel55 = new javax.swing.JLabel();
        jano_ventas = new com.toedter.calendar.JYearChooser();
        jmes_ventas = new com.toedter.calendar.JMonthChooser();
        jPanel9 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        vs = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        dventa = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        cbtipoventa1 = new javax.swing.JComboBox<>();
        jLabel71 = new javax.swing.JLabel();
        jdate_ventas_sem2 = new com.toedter.calendar.JDateChooser();
        jdate_ventas_sem1 = new com.toedter.calendar.JDateChooser();
        jano_detventa = new com.toedter.calendar.JYearChooser();
        jmes_detventa = new com.toedter.calendar.JMonthChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        vendedorestodos = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        vendedoresdele = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cbdele = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        clientes = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        rbAceptados = new javax.swing.JRadioButton();
        rbProspectos = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reportes");
        setIconImage(getIconImage());
        setIconImages(getIconImages());
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(162, 127, 51));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel1.setText("Reportes");
        jPanel7.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 30, -1, 39));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bevisalogo_p.png"))); // NOI18N
        jPanel7.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 110, 80));

        tabpanel.setBackground(new java.awt.Color(255, 255, 255));

        Productos.setBackground(new java.awt.Color(255, 255, 255));
        Productos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel2.setText("Productos");
        Productos.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, -1, 39));

        productostodos.setBackground(new java.awt.Color(240, 200, 115));
        productostodos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productostodosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                productostodosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                productostodosMouseExited(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(162, 127, 51));
        jLabel5.setText("Todos");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/caja.png"))); // NOI18N

        javax.swing.GroupLayout productostodosLayout = new javax.swing.GroupLayout(productostodos);
        productostodos.setLayout(productostodosLayout);
        productostodosLayout.setHorizontalGroup(
            productostodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productostodosLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(productostodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addGap(39, 39, 39))
        );
        productostodosLayout.setVerticalGroup(
            productostodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productostodosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(23, 23, 23))
        );

        Productos.add(productostodos, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 130, 92));

        productosterminados.setBackground(new java.awt.Color(240, 200, 115));
        productosterminados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productosterminadosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                productosterminadosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                productosterminadosMouseExited(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(162, 127, 51));
        jLabel8.setText("P. Terminados");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/botella.png"))); // NOI18N

        javax.swing.GroupLayout productosterminadosLayout = new javax.swing.GroupLayout(productosterminados);
        productosterminados.setLayout(productosterminadosLayout);
        productosterminadosLayout.setHorizontalGroup(
            productosterminadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productosterminadosLayout.createSequentialGroup()
                .addGroup(productosterminadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(productosterminadosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8))
                    .addGroup(productosterminadosLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel9)))
                .addContainerGap())
        );
        productosterminadosLayout.setVerticalGroup(
            productosterminadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productosterminadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(23, 23, 23))
        );

        Productos.add(productosterminados, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, -1, 92));

        productosmp.setBackground(new java.awt.Color(240, 200, 115));
        productosmp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productosmpMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                productosmpMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                productosmpMouseExited(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(162, 127, 51));
        jLabel6.setText("M. Prima");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/producto.png"))); // NOI18N

        javax.swing.GroupLayout productosmpLayout = new javax.swing.GroupLayout(productosmp);
        productosmp.setLayout(productosmpLayout);
        productosmpLayout.setHorizontalGroup(
            productosmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productosmpLayout.createSequentialGroup()
                .addGroup(productosmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(productosmpLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel7))
                    .addGroup(productosmpLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel6)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        productosmpLayout.setVerticalGroup(
            productosmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productosmpLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(23, 23, 23))
        );

        Productos.add(productosmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 130, 92));

        jLabel53.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel53.setText("Ingredientes / Pruebas");
        Productos.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, -1, 39));

        pruebas.setBackground(new java.awt.Color(240, 200, 115));
        pruebas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pruebasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pruebasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pruebasMouseExited(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(162, 127, 51));
        jLabel18.setText("Pruebas");

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pruebas.png"))); // NOI18N

        javax.swing.GroupLayout pruebasLayout = new javax.swing.GroupLayout(pruebas);
        pruebas.setLayout(pruebasLayout);
        pruebasLayout.setHorizontalGroup(
            pruebasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pruebasLayout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(pruebasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel18))
                .addGap(27, 27, 27))
        );
        pruebasLayout.setVerticalGroup(
            pruebasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pruebasLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18)
                .addContainerGap())
        );

        Productos.add(pruebas, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 260, 130, -1));

        ingredientes.setBackground(new java.awt.Color(240, 200, 115));
        ingredientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ingredientesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ingredientesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ingredientesMouseExited(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(162, 127, 51));
        jLabel12.setText("Ingredientes");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ingrediente.png"))); // NOI18N

        javax.swing.GroupLayout ingredientesLayout = new javax.swing.GroupLayout(ingredientes);
        ingredientes.setLayout(ingredientesLayout);
        ingredientesLayout.setHorizontalGroup(
            ingredientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ingredientesLayout.createSequentialGroup()
                .addGroup(ingredientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ingredientesLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12))
                    .addGroup(ingredientesLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel13)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ingredientesLayout.setVerticalGroup(
            ingredientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ingredientesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addGap(23, 23, 23))
        );

        Productos.add(ingredientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, -1, -1));

        tabpanel.addTab("Productos ", Productos);

        Inventario.setBackground(new java.awt.Color(255, 255, 255));
        Inventario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel20.setText("Tipo");
        Inventario.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, -1, -1));

        inventario_entradas_ano.setBackground(new java.awt.Color(240, 200, 115));
        inventario_entradas_ano.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inventario_entradas_anoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                inventario_entradas_anoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                inventario_entradas_anoMouseExited(evt);
            }
        });

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/entrada.png"))); // NOI18N

        jLabel21.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(162, 127, 51));
        jLabel21.setText("Año");

        javax.swing.GroupLayout inventario_entradas_anoLayout = new javax.swing.GroupLayout(inventario_entradas_ano);
        inventario_entradas_ano.setLayout(inventario_entradas_anoLayout);
        inventario_entradas_anoLayout.setHorizontalGroup(
            inventario_entradas_anoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventario_entradas_anoLayout.createSequentialGroup()
                .addGroup(inventario_entradas_anoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inventario_entradas_anoLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel21))
                    .addGroup(inventario_entradas_anoLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel22)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        inventario_entradas_anoLayout.setVerticalGroup(
            inventario_entradas_anoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventario_entradas_anoLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Inventario.add(inventario_entradas_ano, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 120, 90));

        inventario_entradas_mes.setBackground(new java.awt.Color(240, 200, 115));
        inventario_entradas_mes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inventario_entradas_mesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                inventario_entradas_mesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                inventario_entradas_mesMouseExited(evt);
            }
        });

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/entrada.png"))); // NOI18N

        jLabel23.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(162, 127, 51));
        jLabel23.setText("Mes");

        javax.swing.GroupLayout inventario_entradas_mesLayout = new javax.swing.GroupLayout(inventario_entradas_mes);
        inventario_entradas_mes.setLayout(inventario_entradas_mesLayout);
        inventario_entradas_mesLayout.setHorizontalGroup(
            inventario_entradas_mesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventario_entradas_mesLayout.createSequentialGroup()
                .addGroup(inventario_entradas_mesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inventario_entradas_mesLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel23))
                    .addGroup(inventario_entradas_mesLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel24)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        inventario_entradas_mesLayout.setVerticalGroup(
            inventario_entradas_mesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventario_entradas_mesLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Inventario.add(inventario_entradas_mes, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, 130, 90));

        jLabel26.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel26.setText("No. Mes");
        Inventario.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, -1, -1));

        jLabel28.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel28.setText("Entradas");
        Inventario.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, -1, 39));

        jLabel29.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel29.setText("Final");
        Inventario.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 140, -1, 39));

        inventario_final_mes.setBackground(new java.awt.Color(240, 200, 115));
        inventario_final_mes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inventario_final_mesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                inventario_final_mesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                inventario_final_mesMouseExited(evt);
            }
        });

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/paquete.png"))); // NOI18N

        jLabel31.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(162, 127, 51));
        jLabel31.setText("Mes");

        javax.swing.GroupLayout inventario_final_mesLayout = new javax.swing.GroupLayout(inventario_final_mes);
        inventario_final_mes.setLayout(inventario_final_mesLayout);
        inventario_final_mesLayout.setHorizontalGroup(
            inventario_final_mesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventario_final_mesLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel30)
                .addContainerGap(34, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inventario_final_mesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel31)
                .addGap(46, 46, 46))
        );
        inventario_final_mesLayout.setVerticalGroup(
            inventario_final_mesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventario_final_mesLayout.createSequentialGroup()
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Inventario.add(inventario_final_mes, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 180, 130, 90));

        inventario_final_ano.setBackground(new java.awt.Color(240, 200, 115));
        inventario_final_ano.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inventario_final_anoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                inventario_final_anoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                inventario_final_anoMouseExited(evt);
            }
        });

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/paquete.png"))); // NOI18N

        jLabel33.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(162, 127, 51));
        jLabel33.setText("Año");

        javax.swing.GroupLayout inventario_final_anoLayout = new javax.swing.GroupLayout(inventario_final_ano);
        inventario_final_ano.setLayout(inventario_final_anoLayout);
        inventario_final_anoLayout.setHorizontalGroup(
            inventario_final_anoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventario_final_anoLayout.createSequentialGroup()
                .addGroup(inventario_final_anoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inventario_final_anoLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel33))
                    .addGroup(inventario_final_anoLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel32)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        inventario_final_anoLayout.setVerticalGroup(
            inventario_final_anoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventario_final_anoLayout.createSequentialGroup()
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel33)
                .addContainerGap())
        );

        Inventario.add(inventario_final_ano, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, 120, 90));

        jLabel54.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel54.setText("Año");
        Inventario.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, -1, -1));

        cbtipoinventario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "M. Prima", "P. Final" }));
        Inventario.add(cbtipoinventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 120, -1));

        inventario_dinero_ano.setBackground(new java.awt.Color(240, 200, 115));
        inventario_dinero_ano.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inventario_dinero_anoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                inventario_dinero_anoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                inventario_dinero_anoMouseExited(evt);
            }
        });

        jLabel70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dinero.png"))); // NOI18N

        jLabel72.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(162, 127, 51));
        jLabel72.setText("Año");

        javax.swing.GroupLayout inventario_dinero_anoLayout = new javax.swing.GroupLayout(inventario_dinero_ano);
        inventario_dinero_ano.setLayout(inventario_dinero_anoLayout);
        inventario_dinero_anoLayout.setHorizontalGroup(
            inventario_dinero_anoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventario_dinero_anoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(inventario_dinero_anoLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel72)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        inventario_dinero_anoLayout.setVerticalGroup(
            inventario_dinero_anoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventario_dinero_anoLayout.createSequentialGroup()
                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel72)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Inventario.add(inventario_dinero_ano, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 320, 120, 110));

        jLabel73.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel73.setText("Dinero (Final)");
        Inventario.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 280, -1, 39));

        inventario_dinero_mes.setBackground(new java.awt.Color(240, 200, 115));
        inventario_dinero_mes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inventario_dinero_mesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                inventario_dinero_mesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                inventario_dinero_mesMouseExited(evt);
            }
        });

        jLabel74.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dinero.png"))); // NOI18N

        jLabel75.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(162, 127, 51));
        jLabel75.setText("Mes");

        javax.swing.GroupLayout inventario_dinero_mesLayout = new javax.swing.GroupLayout(inventario_dinero_mes);
        inventario_dinero_mes.setLayout(inventario_dinero_mesLayout);
        inventario_dinero_mesLayout.setHorizontalGroup(
            inventario_dinero_mesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventario_dinero_mesLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(inventario_dinero_mesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inventario_dinero_mesLayout.createSequentialGroup()
                        .addComponent(jLabel74)
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inventario_dinero_mesLayout.createSequentialGroup()
                        .addComponent(jLabel75)
                        .addGap(44, 44, 44))))
        );
        inventario_dinero_mesLayout.setVerticalGroup(
            inventario_dinero_mesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventario_dinero_mesLayout.createSequentialGroup()
                .addComponent(jLabel74)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel75)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Inventario.add(inventario_dinero_mes, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 320, 130, 110));
        Inventario.add(jano_inventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 120, -1));
        Inventario.add(jmes_inventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 120, 20));

        tabpanel.addTab("Inventario", Inventario);

        Produccion.setBackground(new java.awt.Color(255, 255, 255));
        Produccion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel34.setText("Año");
        Produccion.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, -1, -1));

        produccion_ano.setBackground(new java.awt.Color(240, 200, 115));
        produccion_ano.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                produccion_anoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                produccion_anoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                produccion_anoMouseExited(evt);
            }
        });

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fabrica.png"))); // NOI18N

        jLabel36.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(162, 127, 51));
        jLabel36.setText("Año");

        javax.swing.GroupLayout produccion_anoLayout = new javax.swing.GroupLayout(produccion_ano);
        produccion_ano.setLayout(produccion_anoLayout);
        produccion_anoLayout.setHorizontalGroup(
            produccion_anoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(produccion_anoLayout.createSequentialGroup()
                .addGroup(produccion_anoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(produccion_anoLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel36))
                    .addGroup(produccion_anoLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel35)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        produccion_anoLayout.setVerticalGroup(
            produccion_anoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(produccion_anoLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel36)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Produccion.add(produccion_ano, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 120, 90));

        produccion_mes.setBackground(new java.awt.Color(240, 200, 115));
        produccion_mes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                produccion_mesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                produccion_mesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                produccion_mesMouseExited(evt);
            }
        });

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fabrica.png"))); // NOI18N

        jLabel38.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(162, 127, 51));
        jLabel38.setText("Mes");

        javax.swing.GroupLayout produccion_mesLayout = new javax.swing.GroupLayout(produccion_mes);
        produccion_mes.setLayout(produccion_mesLayout);
        produccion_mesLayout.setHorizontalGroup(
            produccion_mesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(produccion_mesLayout.createSequentialGroup()
                .addGroup(produccion_mesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(produccion_mesLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel38))
                    .addGroup(produccion_mesLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel37)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        produccion_mesLayout.setVerticalGroup(
            produccion_mesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(produccion_mesLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel38)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Produccion.add(produccion_mes, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, 130, 90));

        jLabel39.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel39.setText("No. Mes");
        Produccion.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, -1, -1));

        produccion_odp_nombre.setBackground(new java.awt.Color(240, 200, 115));
        produccion_odp_nombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                produccion_odp_nombreMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                produccion_odp_nombreMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                produccion_odp_nombreMouseExited(evt);
            }
        });

        jLabel56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/trabajador.png"))); // NOI18N

        jLabel58.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(162, 127, 51));
        jLabel58.setText("Sin Nombre");

        javax.swing.GroupLayout produccion_odp_nombreLayout = new javax.swing.GroupLayout(produccion_odp_nombre);
        produccion_odp_nombre.setLayout(produccion_odp_nombreLayout);
        produccion_odp_nombreLayout.setHorizontalGroup(
            produccion_odp_nombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(produccion_odp_nombreLayout.createSequentialGroup()
                .addGroup(produccion_odp_nombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(produccion_odp_nombreLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel56))
                    .addGroup(produccion_odp_nombreLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel58)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        produccion_odp_nombreLayout.setVerticalGroup(
            produccion_odp_nombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(produccion_odp_nombreLayout.createSequentialGroup()
                .addComponent(jLabel56)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel58)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Produccion.add(produccion_odp_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 120, 130, 90));

        jLabel59.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel59.setText("Ordenes De Producción");
        Produccion.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, 39));
        Produccion.add(txtodp, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, 120, -1));

        jLabel61.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel61.setText("Lote");
        Produccion.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 70, -1, -1));
        Produccion.add(jmes_prodruccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 130, -1));
        Produccion.add(jano_prodruccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 120, -1));

        jLabel80.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel80.setText("ODP");
        Produccion.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, 60, 39));

        tabpanel.addTab("Produccion", Produccion);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel40.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel40.setText("Año");
        jPanel8.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, -1, -1));

        vgpano.setBackground(new java.awt.Color(240, 200, 115));
        vgpano.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vgpanoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                vgpanoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                vgpanoMouseExited(evt);
            }
        });

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ventas1.png"))); // NOI18N

        jLabel42.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(162, 127, 51));
        jLabel42.setText("Año");

        javax.swing.GroupLayout vgpanoLayout = new javax.swing.GroupLayout(vgpano);
        vgpano.setLayout(vgpanoLayout);
        vgpanoLayout.setHorizontalGroup(
            vgpanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vgpanoLayout.createSequentialGroup()
                .addGroup(vgpanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vgpanoLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel42))
                    .addGroup(vgpanoLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel41)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        vgpanoLayout.setVerticalGroup(
            vgpanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vgpanoLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel42)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.add(vgpano, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 120, 90));

        vgpmes.setBackground(new java.awt.Color(240, 200, 115));
        vgpmes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vgpmesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                vgpmesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                vgpmesMouseExited(evt);
            }
        });

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ventas1.png"))); // NOI18N

        jLabel44.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(162, 127, 51));
        jLabel44.setText("Mes");

        javax.swing.GroupLayout vgpmesLayout = new javax.swing.GroupLayout(vgpmes);
        vgpmes.setLayout(vgpmesLayout);
        vgpmesLayout.setHorizontalGroup(
            vgpmesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vgpmesLayout.createSequentialGroup()
                .addGroup(vgpmesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vgpmesLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel44))
                    .addGroup(vgpmesLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel43)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        vgpmesLayout.setVerticalGroup(
            vgpmesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vgpmesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel44)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.add(vgpmes, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, 130, 90));

        jLabel45.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel45.setText("No. Mes");
        jPanel8.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, -1, -1));

        jLabel46.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel46.setText("Vendedor");
        jPanel8.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, -1, 39));

        jLabel47.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel47.setText("Producto");
        jPanel8.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, -1, 39));

        vgvmes.setBackground(new java.awt.Color(240, 200, 115));
        vgvmes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vgvmesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                vgvmesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                vgvmesMouseExited(evt);
            }
        });

        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/vendedor.png"))); // NOI18N

        jLabel49.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(162, 127, 51));
        jLabel49.setText("Mes");

        javax.swing.GroupLayout vgvmesLayout = new javax.swing.GroupLayout(vgvmes);
        vgvmes.setLayout(vgvmesLayout);
        vgvmesLayout.setHorizontalGroup(
            vgvmesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vgvmesLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(vgvmesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel48)
                    .addGroup(vgvmesLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel49)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        vgvmesLayout.setVerticalGroup(
            vgvmesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vgvmesLayout.createSequentialGroup()
                .addComponent(jLabel48)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel49)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.add(vgvmes, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 260, 130, 90));

        vgvano.setBackground(new java.awt.Color(240, 200, 115));
        vgvano.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vgvanoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                vgvanoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                vgvanoMouseExited(evt);
            }
        });

        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/vendedor.png"))); // NOI18N

        jLabel51.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(162, 127, 51));
        jLabel51.setText("Año");

        javax.swing.GroupLayout vgvanoLayout = new javax.swing.GroupLayout(vgvano);
        vgvano.setLayout(vgvanoLayout);
        vgvanoLayout.setHorizontalGroup(
            vgvanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vgvanoLayout.createSequentialGroup()
                .addGroup(vgvanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vgvanoLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel51))
                    .addGroup(vgvanoLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel50)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        vgvanoLayout.setVerticalGroup(
            vgvanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vgvanoLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel51)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.add(vgvano, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 260, -1, 90));

        cbtipoventa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Industrial", "Galeria", "Otro" }));
        jPanel8.add(cbtipoventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 120, -1));

        jLabel55.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel55.setText("Tipo");
        jPanel8.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, -1, -1));
        jPanel8.add(jano_ventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 120, -1));
        jPanel8.add(jmes_ventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 290, 120, -1));

        tabpanel.addTab("Ventas ", jPanel8);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel52.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel52.setText("Inicio");
        jPanel9.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, -1, -1));

        jLabel57.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel57.setText("Fin");
        jPanel9.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, -1, -1));

        vs.setBackground(new java.awt.Color(240, 200, 115));
        vs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                vsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                vsMouseExited(evt);
            }
        });

        jLabel62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/monedas.png"))); // NOI18N

        jLabel63.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(162, 127, 51));
        jLabel63.setText("Semanal");

        javax.swing.GroupLayout vsLayout = new javax.swing.GroupLayout(vs);
        vs.setLayout(vsLayout);
        vsLayout.setHorizontalGroup(
            vsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vsLayout.createSequentialGroup()
                .addGroup(vsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vsLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel62))
                    .addGroup(vsLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel63)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        vsLayout.setVerticalGroup(
            vsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vsLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel62)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel63)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.add(vs, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, -1, 90));

        jLabel64.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel64.setText("Detalle de las Ventas");
        jPanel9.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, -1, 39));

        jLabel65.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel65.setText("Semanal");
        jPanel9.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, -1, 39));

        jLabel66.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel66.setText("Año");
        jPanel9.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, -1, -1));

        jLabel67.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel67.setText("No. Mes");
        jPanel9.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 190, -1, -1));

        dventa.setBackground(new java.awt.Color(240, 200, 115));
        dventa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dventaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dventaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dventaMouseExited(evt);
            }
        });

        jLabel68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cuenta.png"))); // NOI18N

        jLabel69.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(162, 127, 51));
        jLabel69.setText("Detalle");

        javax.swing.GroupLayout dventaLayout = new javax.swing.GroupLayout(dventa);
        dventa.setLayout(dventaLayout);
        dventaLayout.setHorizontalGroup(
            dventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dventaLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(dventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel68)
                    .addComponent(jLabel69))
                .addGap(27, 27, 27))
        );
        dventaLayout.setVerticalGroup(
            dventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dventaLayout.createSequentialGroup()
                .addComponent(jLabel68)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel69)
                .addContainerGap())
        );

        jPanel9.add(dventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 250, 120, 90));

        cbtipoventa1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Industrial", "Galeria", "Otro" }));
        jPanel9.add(cbtipoventa1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 120, -1));

        jLabel71.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel71.setText("Tipo");
        jPanel9.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, -1, -1));
        jPanel9.add(jdate_ventas_sem2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 120, -1));
        jPanel9.add(jdate_ventas_sem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 120, -1));
        jPanel9.add(jano_detventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 160, 120, -1));
        jPanel9.add(jmes_detventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(459, 220, 120, -1));

        tabpanel.addTab("Ventas Semanales", jPanel9);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel14.setText("Vendedores");

        vendedorestodos.setBackground(new java.awt.Color(240, 200, 115));
        vendedorestodos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vendedorestodosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                vendedorestodosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                vendedorestodosMouseExited(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(162, 127, 51));
        jLabel10.setText("Todos");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/vendedor.png"))); // NOI18N

        javax.swing.GroupLayout vendedorestodosLayout = new javax.swing.GroupLayout(vendedorestodos);
        vendedorestodos.setLayout(vendedorestodosLayout);
        vendedorestodosLayout.setHorizontalGroup(
            vendedorestodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vendedorestodosLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(vendedorestodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        vendedorestodosLayout.setVerticalGroup(
            vendedorestodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vendedorestodosLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        vendedoresdele.setBackground(new java.awt.Color(240, 200, 115));
        vendedoresdele.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vendedoresdeleMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                vendedoresdeleMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                vendedoresdeleMouseExited(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(162, 127, 51));
        jLabel16.setText("Delegacion");

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mexico.png"))); // NOI18N

        javax.swing.GroupLayout vendedoresdeleLayout = new javax.swing.GroupLayout(vendedoresdele);
        vendedoresdele.setLayout(vendedoresdeleLayout);
        vendedoresdeleLayout.setHorizontalGroup(
            vendedoresdeleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vendedoresdeleLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel17)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vendedoresdeleLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addGap(23, 23, 23))
        );
        vendedoresdeleLayout.setVerticalGroup(
            vendedoresdeleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vendedoresdeleLayout.createSequentialGroup()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel25.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel25.setText("Clientes");

        clientes.setBackground(new java.awt.Color(240, 200, 115));
        clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clientesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                clientesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                clientesMouseExited(evt);
            }
        });

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/vendedor.png"))); // NOI18N

        javax.swing.GroupLayout clientesLayout = new javax.swing.GroupLayout(clientes);
        clientes.setLayout(clientesLayout);
        clientesLayout.setHorizontalGroup(
            clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientesLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel27)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        clientesLayout.setVerticalGroup(
            clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientesLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        buttonGroup1.add(rbAceptados);
        rbAceptados.setSelected(true);
        rbAceptados.setText("Aceptados");

        buttonGroup1.add(rbProspectos);
        rbProspectos.setText("Prospectos");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(179, 461, Short.MAX_VALUE)
                .addComponent(jLabel25)
                .addGap(137, 137, 137))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(vendedoresdele, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbdele, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel14)
                        .addComponent(vendedorestodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbProspectos)
                            .addComponent(rbAceptados))
                        .addGap(137, 137, 137))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(clientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(113, 113, 113))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(vendedorestodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(cbdele, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(vendedoresdele, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(rbAceptados)
                        .addGap(18, 18, 18)
                        .addComponent(rbProspectos))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(37, 37, 37)
                .addComponent(clientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabpanel.addTab("Vendedores/ Clientes", jPanel2);

        jPanel7.add(tabpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 690, 470));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.mp.setVisible(true);
    }//GEN-LAST:event_formWindowClosing
    
    
    //<editor-fold defaultstate="collapsed" desc="INVENTARIO">
    
    
    private void inventario_entradas_mesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventario_entradas_mesMouseExited
        this.resetcolor(this.inventario_entradas_mes);
    }//GEN-LAST:event_inventario_entradas_mesMouseExited

    private void inventario_entradas_mesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventario_entradas_mesMouseEntered
        this.camcolor(this.inventario_entradas_mes);
    }//GEN-LAST:event_inventario_entradas_mesMouseEntered

    private void inventario_entradas_mesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventario_entradas_mesMouseClicked
        JasperReport reporte; //Creo el objeto reporte
        // Ubicacion del Reporte
        String path = s+"Inventario_Meses.jasper";
        try {
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path); //Cargo el reporte al objeto
            Map c = new HashMap();
            c.put("mes", this.jmes_inventario.getMonth()+1+"");
            c.put("ano", this.jano_inventario.getYear()+"");
             switch(this.cbtipoinventario.getSelectedIndex())
            {
                case 0:
                    c.put("ca1", "1");
                    c.put("ca2", "3");
                    break;
                case 1:
                    c.put("ca1", "2");
                    c.put("ca2", "4");
                    break;
            }
            JasperPrint jprint = JasperFillManager.fillReport(path, c, this.con); //Llenado del Reporte con Tres parametros ubicacion,parametros,conexion a la base de datos
            JasperViewer viewer = new JasperViewer(jprint,false); //Creamos la vista del Reporte
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Le agregamos que se cierre solo el reporte cuando lo cierre el usuario
            viewer.setVisible(true); //Inicializamos la vista del Reporte
        }
        catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error en los valores de entrada");
            try {
                this.con = Conexion.getConnection();
            } catch (SQLException ex1) {
                Logger.getLogger(reportes.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        }
    }//GEN-LAST:event_inventario_entradas_mesMouseClicked

    private void inventario_entradas_anoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventario_entradas_anoMouseExited
        this.resetcolor(this.inventario_entradas_ano);
    }//GEN-LAST:event_inventario_entradas_anoMouseExited

    private void inventario_entradas_anoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventario_entradas_anoMouseEntered
        this.camcolor(this.inventario_entradas_ano);
    }//GEN-LAST:event_inventario_entradas_anoMouseEntered

    private void inventario_entradas_anoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventario_entradas_anoMouseClicked
        JasperReport reporte; //Creo el objeto reporte
        // Ubicacion del Reporte
        String path = s+"Inventario_ano.jasper";
        try {
            
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path); //Cargo el reporte al objeto
            Map c = new HashMap();
            c.put("ano", this.jano_inventario.getYear()+"");
            switch(this.cbtipoinventario.getSelectedIndex())
            {
                case 0:
                    c.put("ca1", "1");
                    c.put("ca2", "3");
                    break;
                case 1:
                    c.put("ca1", "2");
                    c.put("ca2", "4");
                    break;
            }
            JasperPrint jprint = JasperFillManager.fillReport(path, c, this.con); //Llenado del Reporte con Tres parametros ubicacion,parametros,conexion a la base de datos
            JasperViewer viewer = new JasperViewer(jprint,false); //Creamos la vista del Reporte
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Le agregamos que se cierre solo el reporte cuando lo cierre el usuario
            viewer.setVisible(true); //Inicializamos la vista del Reporte
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error en los valores de entrada");
            try {
                this.con= Conexion.getConnection();
            } catch (SQLException ex1) {
                Logger.getLogger(reportes.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        }
    }//GEN-LAST:event_inventario_entradas_anoMouseClicked
    
    
    
    private void inventario_final_anoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventario_final_anoMouseExited
        this.resetcolor(this.inventario_final_ano);
    }//GEN-LAST:event_inventario_final_anoMouseExited

    private void inventario_final_anoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventario_final_anoMouseEntered
        this.camcolor(this.inventario_final_ano);
    }//GEN-LAST:event_inventario_final_anoMouseEntered

    private void inventario_final_anoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventario_final_anoMouseClicked
        JasperReport reporte; //Creo el objeto reporte
        // Ubicacion del Reporte
        String path = s+"InventarioFinal_ano.jasper";
        try {
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path); //Cargo el reporte al objeto
            Map c = new HashMap();
            c.put("ano", this.jano_inventario.getYear()+"");
             switch(this.cbtipoinventario.getSelectedIndex())
            {
                case 0:
                    c.put("ca1", "1");
                    c.put("ca2", "3");
                    break;
                case 1:
                    c.put("ca1", "2");
                    c.put("ca2", "4");
                    break;
            }
            JasperPrint jprint = JasperFillManager.fillReport(path, c, this.con); //Llenado del Reporte con Tres parametros ubicacion,parametros,conexion a la base de datos
            JasperViewer viewer = new JasperViewer(jprint,false); //Creamos la vista del Reporte
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Le agregamos que se cierre solo el reporte cuando lo cierre el usuario
            viewer.setVisible(true); //Inicializamos la vista del Reporte
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error en los valores de entrada");
            try {
                this.con = Conexion.getConnection();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }//GEN-LAST:event_inventario_final_anoMouseClicked

    private void inventario_final_mesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventario_final_mesMouseExited
        this.resetcolor(this.inventario_final_mes);
    }//GEN-LAST:event_inventario_final_mesMouseExited

    private void inventario_final_mesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventario_final_mesMouseEntered
        this.camcolor(this.inventario_final_mes);
    }//GEN-LAST:event_inventario_final_mesMouseEntered

    private void inventario_final_mesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventario_final_mesMouseClicked
        JasperReport reporte; //Creo el objeto reporte
        // Ubicacion del Reporte
        String path = s+"InventarioFinal_Meses.jasper";
        try {
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path); //Cargo el reporte al objeto
            Map c = new HashMap();
            c.put("mes", this.jmes_inventario.getMonth()+1+"");
            c.put("ano", this.jano_inventario.getYear()+"");
             switch(this.cbtipoinventario.getSelectedIndex())
            {
                case 0:
                    c.put("ca1", "1");
                    c.put("ca2", "3");
                    break;
                case 1:
                    c.put("ca1", "2");
                    c.put("ca2", "4");
                    break;
            }
            JasperPrint jprint = JasperFillManager.fillReport(path, c, this.con); //Llenado del Reporte con Tres parametros ubicacion,parametros,conexion a la base de datos
            JasperViewer viewer = new JasperViewer(jprint,false); //Creamos la vista del Reporte
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Le agregamos que se cierre solo el reporte cuando lo cierre el usuario
            viewer.setVisible(true); //Inicializamos la vista del Reporte
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error en los valores de entrada");
            try {
                this.con = Conexion.getConnection();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }//GEN-LAST:event_inventario_final_mesMouseClicked
    
    
    
    private void inventario_dinero_anoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventario_dinero_anoMouseClicked
        JasperReport reporte; //Creo el objeto reporte
        // Ubicacion del Reporte
        String path = s+"InventarioFinalD_ano.jasper";
        try {
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path); //Cargo el reporte al objeto
            Map c = new HashMap();
            String respuesta = JOptionPane.showInputDialog(this, "Factor (Conversion de dolar)");
            c.put("ano", this.jano_inventario.getYear()+"");
            c.put("factor", Double.parseDouble(respuesta));
             switch(this.cbtipoinventario.getSelectedIndex())
            {
                case 0:
                    c.put("ca1", "1");
                    c.put("ca2", "3");
                    break;
                case 1:
                    c.put("ca1", "2");
                    c.put("ca2", "4");
                    break;
            }
            JasperPrint jprint = JasperFillManager.fillReport(path, c, this.con); //Llenado del Reporte con Tres parametros ubicacion,parametros,conexion a la base de datos
            JasperViewer viewer = new JasperViewer(jprint,false); //Creamos la vista del Reporte
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Le agregamos que se cierre solo el reporte cuando lo cierre el usuario
            viewer.setVisible(true); //Inicializamos la vista del Reporte
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error en los valores de entrada");
            try {
                this.con = Conexion.getConnection();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }//GEN-LAST:event_inventario_dinero_anoMouseClicked

    private void inventario_dinero_anoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventario_dinero_anoMouseEntered
        this.camcolor(this.inventario_dinero_ano);
    }//GEN-LAST:event_inventario_dinero_anoMouseEntered

    private void inventario_dinero_anoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventario_dinero_anoMouseExited
        this.resetcolor(this.inventario_dinero_ano);
    }//GEN-LAST:event_inventario_dinero_anoMouseExited

    private void inventario_dinero_mesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventario_dinero_mesMouseClicked
        JasperReport reporte; //Creo el objeto reporte
        // Ubicacion del Reporte
        String path = s+"InventarioFinalD_Meses.jasper";
        try {
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path); //Cargo el reporte al objeto
            Map c = new HashMap();
            String respuesta = JOptionPane.showInputDialog(this, "Factor (Conversion de dolar)");
            c.put("factor", Double.parseDouble(respuesta) );
            c.put("mes", this.jmes_inventario.getMonth()+1+"");
            c.put("ano", this.jano_inventario.getYear()+"");
             switch(this.cbtipoinventario.getSelectedIndex())
            {
                case 0:
                    c.put("ca1", "1");
                    c.put("ca2", "3");
                    break;
                case 1:
                    c.put("ca1", "2");
                    c.put("ca2", "4");
                    break;
            }
            JasperPrint jprint = JasperFillManager.fillReport(path, c, this.con); //Llenado del Reporte con Tres parametros ubicacion,parametros,conexion a la base de datos
            JasperViewer viewer = new JasperViewer(jprint,false); //Creamos la vista del Reporte
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Le agregamos que se cierre solo el reporte cuando lo cierre el usuario
            viewer.setVisible(true); //Inicializamos la vista del Reporte
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error en los valores de entrada");
            try {
                this.con= Conexion.getConnection();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }//GEN-LAST:event_inventario_dinero_mesMouseClicked

    private void inventario_dinero_mesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventario_dinero_mesMouseEntered
        this.camcolor(this.inventario_dinero_mes);
    }//GEN-LAST:event_inventario_dinero_mesMouseEntered

    private void inventario_dinero_mesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventario_dinero_mesMouseExited
        this.resetcolor(this.inventario_dinero_ano);
    }//GEN-LAST:event_inventario_dinero_mesMouseExited

    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="PRODUCCION">
    
    private void produccion_mesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produccion_mesMouseExited
        this.resetcolor(this.produccion_mes);
    }//GEN-LAST:event_produccion_mesMouseExited

    private void produccion_mesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produccion_mesMouseEntered
        this.camcolor(this.produccion_mes);
    }//GEN-LAST:event_produccion_mesMouseEntered

    private void produccion_mesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produccion_mesMouseClicked
        JasperReport reporte; //Creo el objeto reporte
        // Ubicacion del Reporte
        String path = s+"Produccion_Mes.jasper";
        try {
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path); //Cargo el reporte al objeto
            Map c = new HashMap();
            String respuesta = JOptionPane.showInputDialog(this, "Factor (Conversion de dolar)");
            c.put("mes", this.jmes_prodruccion.getMonth()+1+"");
            c.put("ano", this.jano_prodruccion.getYear()+"");
            c.put("factor", Double.parseDouble(respuesta));
            JasperPrint jprint = JasperFillManager.fillReport(path, c, this.dbc.getCnx()); //Llenado del Reporte con Tres parametros ubicacion,parametros,conexion a la base de datos
            JasperViewer viewer = new JasperViewer(jprint,false); //Creamos la vista del Reporte
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Le agregamos que se cierre solo el reporte cuando lo cierre el usuario
            viewer.setVisible(true); //Inicializamos la vista del Reporte
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error en los valores de entrada");
            try {
                this.dbc = new DBcontrolador ();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }//GEN-LAST:event_produccion_mesMouseClicked

    private void produccion_anoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produccion_anoMouseExited
        this.resetcolor(this.produccion_ano);
    }//GEN-LAST:event_produccion_anoMouseExited

    private void produccion_anoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produccion_anoMouseEntered
        this.camcolor(this.produccion_ano);
    }//GEN-LAST:event_produccion_anoMouseEntered

    private void produccion_anoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produccion_anoMouseClicked
        JasperReport reporte; 
        // Ubicacion del Reporte
        String path = s+"Produccion_Ano.jasper";
        try {
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path); //Cargo el reporte al objeto
            Map c = new HashMap();
            String respuesta = JOptionPane.showInputDialog(this, "Factor (Conversion de dolar)");
            c.put("ano",this.jano_prodruccion.getYear()+"");
            c.put("factor",Double.parseDouble(respuesta));
            JasperPrint jprint = JasperFillManager.fillReport(path, c, this.dbc.getCnx()); //Llenado del Reporte con Tres parametros ubicacion,parametros,conexion a la base de datos
            JasperViewer viewer = new JasperViewer(jprint,false); //Creamos la vista del Reporte
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Le agregamos que se cierre solo el reporte cuando lo cierre el usuario
            viewer.setVisible(true); //Inicializamos la vista del Reporte
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error en los valores de entrada");
            try {
                this.dbc = new DBcontrolador ();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }//GEN-LAST:event_produccion_anoMouseClicked
    
    private void produccion_odp_nombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produccion_odp_nombreMouseClicked
        JasperReport reporte; //Creo el objeto reporte
        // Ubicacion del Reporte
        String path = s+"ODP2.jasper";
        try {
            Map c = new HashMap();
            c.put("ID_ODP", this.txtodp.getText());

            reporte = (JasperReport) JRLoader.loadObjectFromFile(path); //Cargo el reporte al objeto
            JasperPrint jprint = JasperFillManager.fillReport(path, c, this.dbc.getCnx()); //Llenado del Reporte con Tres parametros ubicacion,parametros,conexion a la base de datos
            JasperViewer viewer = new JasperViewer(jprint,false); //Creamos la vista del Reporte
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Le agregamos que se cierre solo el reporte cuando lo cierre el usuario
            viewer.setVisible(true); //Inicializamos la vista del Reporte
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            try {
                this.dbc = new DBcontrolador ();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }//GEN-LAST:event_produccion_odp_nombreMouseClicked

    private void produccion_odp_nombreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produccion_odp_nombreMouseEntered
        camcolor(this.produccion_odp_nombre);
    }//GEN-LAST:event_produccion_odp_nombreMouseEntered

    private void produccion_odp_nombreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produccion_odp_nombreMouseExited
        resetcolor(this.produccion_odp_nombre);
    }//GEN-LAST:event_produccion_odp_nombreMouseExited
        
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="VENTAS">
    //</editor-fold>
    
    
    private void vsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vsMouseExited
        this.resetcolor(vs);
    }//GEN-LAST:event_vsMouseExited

    private void vsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vsMouseEntered
        this.camcolor(vs);
    }//GEN-LAST:event_vsMouseEntered

    private void vsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vsMouseClicked
        JasperReport reporte; //Creo el objeto reporte
        // Ubicacion del Reporte
        String path = s+"Ventas_Semanal.jasper";
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path); //Cargo el reporte al objeto
            Map c = new HashMap();
            c.put("ini", this.fechadividir(formatter.format(this.jdate_ventas_sem1.getDate()), 2) );
            c.put("fin", this.fechadividir(formatter.format(this.jdate_ventas_sem2.getDate()), 2));
            JasperPrint jprint = JasperFillManager.fillReport(path, c, this.dbc.getCnx()); //Llenado del Reporte con Tres parametros ubicacion,parametros,conexion a la base de datos
            JasperViewer viewer = new JasperViewer(jprint,false); //Creamos la vista del Reporte
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Le agregamos que se cierre solo el reporte cuando lo cierre el usuario
            viewer.setVisible(true); //Inicializamos la vista del Reporte
        }
        
        catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error en los valores de entrada");
            try {
                this.dbc = new DBcontrolador ();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        }
       
    }//GEN-LAST:event_vsMouseClicked

    
    private void vgvanoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vgvanoMouseExited
        this.resetcolor(this.vgvano);
    }//GEN-LAST:event_vgvanoMouseExited

    private void vgvanoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vgvanoMouseEntered
        this.camcolor(this.vgvano);
    }//GEN-LAST:event_vgvanoMouseEntered

    private void vgvanoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vgvanoMouseClicked
        JasperReport reporte; //Creo el objeto reporte
        // Ubicacion del Reporte
        String path = s+"Ventas_AVendedor.jasper";
        try {
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path); //Cargo el reporte al objeto
            Map c = new HashMap();
            c.put("ano", this.jano_ventas.getYear()+"");
            JasperPrint jprint = JasperFillManager.fillReport(path, c, this.dbc.getCnx()); //Llenado del Reporte con Tres parametros ubicacion,parametros,conexion a la base de datos
            JasperViewer viewer = new JasperViewer(jprint,false); //Creamos la vista del Reporte
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Le agregamos que se cierre solo el reporte cuando lo cierre el usuario
            viewer.setVisible(true); //Inicializamos la vista del Reporte
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error en los valores de entrada");
             try {
                this.dbc = new DBcontrolador ();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
            this.con=this.dbc.getCnx();
        }
    }//GEN-LAST:event_vgvanoMouseClicked

    private void vgvmesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vgvmesMouseExited
        this.resetcolor(this.vgvmes);
    }//GEN-LAST:event_vgvmesMouseExited

    private void vgvmesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vgvmesMouseEntered
        this.camcolor(this.vgvmes);
    }//GEN-LAST:event_vgvmesMouseEntered

    private void vgvmesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vgvmesMouseClicked
        JasperReport reporte; //Creo el objeto reporte
        // Ubicacion del Reporte
        String path = s+"Ventas_MVendedor.jasper";
        try {
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path); //Cargo el reporte al objeto
            Map c = new HashMap();
            c.put("mes", this.jmes_ventas.getMonth()+1+"");
            c.put("ano", this.jano_ventas.getYear()+"");
            JasperPrint jprint = JasperFillManager.fillReport(path, c, this.dbc.getCnx()); //Llenado del Reporte con Tres parametros ubicacion,parametros,conexion a la base de datos
            JasperViewer viewer = new JasperViewer(jprint,false); //Creamos la vista del Reporte
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Le agregamos que se cierre solo el reporte cuando lo cierre el usuario
            viewer.setVisible(true); //Inicializamos la vista del Reporte
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error en los valores de entrada");
            try {
                this.dbc = new DBcontrolador ();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }//GEN-LAST:event_vgvmesMouseClicked

    private void vgpmesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vgpmesMouseExited
        this.resetcolor(this.vgpmes);
    }//GEN-LAST:event_vgpmesMouseExited

    private void vgpmesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vgpmesMouseEntered
        this.camcolor(this.vgpmes);
    }//GEN-LAST:event_vgpmesMouseEntered

    private void vgpmesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vgpmesMouseClicked
        JasperReport reporte; //Creo el objeto reporte
        // Ubicacion del Reporte
        String path = s+"Ventas_MProducto.jasper";
        try {
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path); //Cargo el reporte al objeto
            Map c = new HashMap();
            c.put("mes", this.jmes_ventas.getMonth()+1+"");
            c.put("ano", this.jano_ventas.getYear()+"");
            switch(this.cbtipoventa.getSelectedIndex())
            {
                case 0:
                    c.put("tipo", "1");
                    break;
                case 1:
                    c.put("tipo", "2");
                    break;
                case 2:
                    c.put("tipo", "3");
                    break;
            }
            JasperPrint jprint = JasperFillManager.fillReport(path, c, this.dbc.getCnx()); //Llenado del Reporte con Tres parametros ubicacion,parametros,conexion a la base de datos
            JasperViewer viewer = new JasperViewer(jprint,false); //Creamos la vista del Reporte
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Le agregamos que se cierre solo el reporte cuando lo cierre el usuario
            viewer.setVisible(true); //Inicializamos la vista del Reporte
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error en los valores de entrada");
            try {
                this.dbc = new DBcontrolador ();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }//GEN-LAST:event_vgpmesMouseClicked

    private void vgpanoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vgpanoMouseExited
        this.resetcolor(this.vgpano);
    }//GEN-LAST:event_vgpanoMouseExited

    private void vgpanoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vgpanoMouseEntered
        this.camcolor(this.vgpano);
    }//GEN-LAST:event_vgpanoMouseEntered

    private void vgpanoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vgpanoMouseClicked
        JasperReport reporte; //Creo el objeto reporte
        // Ubicacion del Reporte
        String path = s+"Ventas_AProducto.jasper";
        try {
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path); //Cargo el reporte al objeto
            Map c = new HashMap();
            c.put("ano", this.jano_ventas.getYear()+"");
            switch(this.cbtipoventa.getSelectedIndex())
            {
                case 0:
                    c.put("tipo", "1");
                    break;
                case 1:
                    c.put("tipo", "2");
                    break;
                case 2:
                    c.put("tipo", "3");
                    break;
            }
            JasperPrint jprint = JasperFillManager.fillReport(path, c, this.dbc.getCnx()); //Llenado del Reporte con Tres parametros ubicacion,parametros,conexion a la base de datos
            JasperViewer viewer = new JasperViewer(jprint,false); //Creamos la vista del Reporte
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Le agregamos que se cierre solo el reporte cuando lo cierre el usuario
            viewer.setVisible(true); //Inicializamos la vista del Reporte
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error en los valores de entrada");
            try {
                this.dbc = new DBcontrolador ();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }//GEN-LAST:event_vgpanoMouseClicked


    

    private void clientesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientesMouseExited
        resetcolor(this.clientes);
    }//GEN-LAST:event_clientesMouseExited

    private void clientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientesMouseEntered
        camcolor(this.clientes);
    }//GEN-LAST:event_clientesMouseEntered

    private void clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientesMouseClicked
        // Ubicacion del Reporte
        String path = s+"Clientes_C.jasper";
        try {
            Map c = new HashMap();
            if (this.rbAceptados.isSelected()) {
                c.put("ID_C", 2);
            }
            if (this.rbProspectos.isSelected()) {
                c.put("ID_C", 1);
            }

            JasperPrint jprint = JasperFillManager.fillReport(path, c,this.dbc.getCnx()); //Llenado del Reporte con Tres parametros ubicacion,parametros,conexion a la base de datos
            JasperViewer viewer = new JasperViewer(jprint,false); //Creamos la vista del Reporte
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Le agregamos que se cierre solo el reporte cuando lo cierre el usuario
            viewer.setVisible(true); //Inicializamos la vista del Reporte
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Esta mal algo "+ex);
            try {
                this.dbc = new DBcontrolador ();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }//GEN-LAST:event_clientesMouseClicked

    private void vendedoresdeleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendedoresdeleMouseExited
        resetcolor(this.vendedoresdele);
    }//GEN-LAST:event_vendedoresdeleMouseExited

    private void vendedoresdeleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendedoresdeleMouseEntered
        camcolor(this.vendedoresdele);
    }//GEN-LAST:event_vendedoresdeleMouseEntered

    private void vendedoresdeleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendedoresdeleMouseClicked
        JasperReport reporte; //Creo el objeto reporte
        // Ubicacion del Reporte
        String path = s+"Vendedores_Delegacion.jasper";
        try {
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path); //Cargo el reporte al objeto
            Map iddele = new HashMap();
            iddele.put("iddele", this.cbdele.getSelectedIndex()+1);
            JasperPrint jprint = JasperFillManager.fillReport(path, iddele, this.dbc.getCnx()); //Llenado del Reporte con Tres parametros ubicacion,parametros,conexion a la base de datos
            JasperViewer viewer = new JasperViewer(jprint,false); //Creamos la vista del Reporte
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Le agregamos que se cierre solo el reporte cuando lo cierre el usuario
            viewer.setVisible(true); //Inicializamos la vista del Reporte
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            try {
                this.dbc = new DBcontrolador ();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }//GEN-LAST:event_vendedoresdeleMouseClicked

    private void vendedorestodosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendedorestodosMouseExited
        resetcolor(this.vendedorestodos);
    }//GEN-LAST:event_vendedorestodosMouseExited

    private void vendedorestodosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendedorestodosMouseEntered
        camcolor(this.vendedorestodos);
    }//GEN-LAST:event_vendedorestodosMouseEntered

    private void vendedorestodosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendedorestodosMouseClicked
        JasperReport reporte; //Creo el objeto reporte
        // Ubicacion del Reporte
        String path = s+"Vendedores_todos.jasper";
        try {
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path); //Cargo el reporte al objeto
            JasperPrint jprint = JasperFillManager.fillReport(path, null, this.dbc.getCnx()); //Llenado del Reporte con Tres parametros ubicacion,parametros,conexion a la base de datos
            JasperViewer viewer = new JasperViewer(jprint,false); //Creamos la vista del Reporte
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Le agregamos que se cierre solo el reporte cuando lo cierre el usuario
            viewer.setVisible(true); //Inicializamos la vista del Reporte
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            try {
                this.dbc = new DBcontrolador ();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }//GEN-LAST:event_vendedorestodosMouseClicked

    private void productosmpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productosmpMouseExited
        resetcolor(this.productosmp);
    }//GEN-LAST:event_productosmpMouseExited

    private void productosmpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productosmpMouseEntered
        camcolor(this.productosmp);
    }//GEN-LAST:event_productosmpMouseEntered

    private void productosmpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productosmpMouseClicked
        JasperReport reporte; //Creo el objeto reporte
        // Ubicacion del Reporte
        String path = s+"productos_categoria.jasper";
        try {
            Map categoria = new HashMap();
            categoria.put("categoria", 1);
            categoria.put("categoria2", 3);
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path); //Cargo el reporte al objeto
            JasperPrint jprint = JasperFillManager.fillReport(path, categoria, this.dbc.getCnx()); //Llenado del Reporte con Tres parametros ubicacion,parametros,conexion a la base de datos
            JasperViewer viewer = new JasperViewer(jprint,false); //Creamos la vista del Reporte
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Le agregamos que se cierre solo el reporte cuando lo cierre el usuario
            viewer.setVisible(true); //Inicializamos la vista del Reporte
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            try {
                this.dbc = new DBcontrolador ();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }//GEN-LAST:event_productosmpMouseClicked

    private void productosterminadosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productosterminadosMouseExited
        resetcolor(this.productosterminados);
    }//GEN-LAST:event_productosterminadosMouseExited

    private void productosterminadosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productosterminadosMouseEntered
        camcolor(this.productosterminados);
    }//GEN-LAST:event_productosterminadosMouseEntered

    private void productosterminadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productosterminadosMouseClicked
        JasperReport reporte; //Creo el objeto reporte
        // Ubicacion del Reporte
        String path = s+"productos_categoria.jasper";
        try {
            Map categoria = new HashMap();
            categoria.put("categoria", 2);
            categoria.put("categoria2", 4);
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path); //Cargo el reporte al objeto
            JasperPrint jprint = JasperFillManager.fillReport(path, categoria, this.dbc.getCnx()); //Llenado del Reporte con Tres parametros ubicacion,parametros,conexion a la base de datos
            JasperViewer viewer = new JasperViewer(jprint,false); //Creamos la vista del Reporte
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Le agregamos que se cierre solo el reporte cuando lo cierre el usuario
            viewer.setVisible(true); //Inicializamos la vista del Reporte
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            try {
                this.dbc = new DBcontrolador ();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }//GEN-LAST:event_productosterminadosMouseClicked

    private void productostodosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productostodosMouseExited
        resetcolor(this.productostodos);
    }//GEN-LAST:event_productostodosMouseExited

    private void productostodosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productostodosMouseEntered
        camcolor(this.productostodos);
    }//GEN-LAST:event_productostodosMouseEntered

    private void productostodosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productostodosMouseClicked

        // Ubicacion del Reporte
        String path = s+"productos_general.jasper";
        try {
            Map c = new HashMap();
            c.put("ID_Venta", 1);

            JasperPrint jprint = JasperFillManager.fillReport(path, c,this.dbc.getCnx()); //Llenado del Reporte con Tres parametros ubicacion,parametros,conexion a la base de datos
            JasperViewer viewer = new JasperViewer(jprint,false); //Creamos la vista del Reporte
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Le agregamos que se cierre solo el reporte cuando lo cierre el usuario
            viewer.setVisible(true); //Inicializamos la vista del Reporte
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Esta mal algo "+ex);
            try {
                this.dbc = new DBcontrolador ();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }//GEN-LAST:event_productostodosMouseClicked

    private void pruebasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pruebasMouseExited
        resetcolor(this.pruebas);
    }//GEN-LAST:event_pruebasMouseExited

    private void pruebasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pruebasMouseEntered
        camcolor(this.pruebas);
    }//GEN-LAST:event_pruebasMouseEntered

    private void pruebasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pruebasMouseClicked
        JasperReport reporte; //Creo el objeto reporte
        // Ubicacion del Reporte
        String path = s+"Pruebas.jasper";
        try {
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path); //Cargo el reporte al objeto

            JasperPrint jprint = JasperFillManager.fillReport(path, null, this.dbc.getCnx()); //Llenado del Reporte con Tres parametros ubicacion,parametros,conexion a la base de datos
            JasperViewer viewer = new JasperViewer(jprint,false); //Creamos la vista del Reporte
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Le agregamos que se cierre solo el reporte cuando lo cierre el usuario
            viewer.setVisible(true); //Inicializamos la vista del Reporte
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            try {
                this.dbc = new DBcontrolador ();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }//GEN-LAST:event_pruebasMouseClicked

    private void ingredientesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ingredientesMouseExited
        resetcolor(this.ingredientes);
    }//GEN-LAST:event_ingredientesMouseExited

    private void ingredientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ingredientesMouseEntered
        camcolor(this.ingredientes);
    }//GEN-LAST:event_ingredientesMouseEntered

    private void ingredientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ingredientesMouseClicked
        JasperReport reporte; //Creo el objeto reporte
        // Ubicacion del Reporte
        String path = s+"Producto_Ingrediente.jasper";
        try {
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path); //Cargo el reporte al objeto
            JasperPrint jprint = JasperFillManager.fillReport(path, null, this.dbc.getCnx()); //Llenado del Reporte con Tres parametros ubicacion,parametros,conexion a la base de datos
            JasperViewer viewer = new JasperViewer(jprint,false); //Creamos la vista del Reporte
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Le agregamos que se cierre solo el reporte cuando lo cierre el usuario
            viewer.setVisible(true); //Inicializamos la vista del Reporte
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            try {
                this.dbc = new DBcontrolador ();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }//GEN-LAST:event_ingredientesMouseClicked

    

    private void dventaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dventaMouseClicked
        JasperReport reporte; //Creo el objeto reporte
        // Ubicacion del Reporte
        String path = s+"Ventas_Salida.jasper";
        try {
            Map c = new HashMap();
            c.put("mes", this.jmes_detventa.getMonth()+1+"");
            c.put("ano", this.jano_detventa.getYear()+"");
             switch(this.cbtipoventa1.getSelectedIndex())
            {
                case 0:
                    c.put("tipo", "1");
                    break;
                case 1:
                    c.put("tipo", "2");
                    break;
                case 2:
                    c.put("tipo", "3");
                    break;
            }
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path); //Cargo el reporte al objeto
            JasperPrint jprint = JasperFillManager.fillReport(path, c, this.dbc.getCnx()); //Llenado del Reporte con Tres parametros ubicacion,parametros,conexion a la base de datos
            JasperViewer viewer = new JasperViewer(jprint,false); //Creamos la vista del Reporte
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Le agregamos que se cierre solo el reporte cuando lo cierre el usuario
            viewer.setVisible(true); //Inicializamos la vista del Reporte
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            try {
                this.dbc = new DBcontrolador ();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }//GEN-LAST:event_dventaMouseClicked

    private void dventaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dventaMouseEntered
        camcolor(this.dventa);
    }//GEN-LAST:event_dventaMouseEntered

    private void dventaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dventaMouseExited
        resetcolor(this.dventa);
    }//GEN-LAST:event_dventaMouseExited

   

    
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
            java.util.logging.Logger.getLogger(reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new reportes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Inventario;
    private javax.swing.JPanel Produccion;
    private javax.swing.JPanel Productos;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbdele;
    private javax.swing.JComboBox<String> cbtipoinventario;
    private javax.swing.JComboBox<String> cbtipoventa;
    private javax.swing.JComboBox<String> cbtipoventa1;
    private javax.swing.JPanel clientes;
    private javax.swing.JPanel dventa;
    private javax.swing.JPanel ingredientes;
    private javax.swing.JPanel inventario_dinero_ano;
    private javax.swing.JPanel inventario_dinero_mes;
    private javax.swing.JPanel inventario_entradas_ano;
    private javax.swing.JPanel inventario_entradas_mes;
    private javax.swing.JPanel inventario_final_ano;
    private javax.swing.JPanel inventario_final_mes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private com.toedter.calendar.JYearChooser jano_detventa;
    private com.toedter.calendar.JYearChooser jano_inventario;
    private com.toedter.calendar.JYearChooser jano_prodruccion;
    private com.toedter.calendar.JYearChooser jano_ventas;
    private com.toedter.calendar.JDateChooser jdate_ventas_sem1;
    private com.toedter.calendar.JDateChooser jdate_ventas_sem2;
    private com.toedter.calendar.JMonthChooser jmes_detventa;
    private com.toedter.calendar.JMonthChooser jmes_inventario;
    private com.toedter.calendar.JMonthChooser jmes_prodruccion;
    private com.toedter.calendar.JMonthChooser jmes_ventas;
    private javax.swing.JPanel produccion_ano;
    private javax.swing.JPanel produccion_mes;
    private javax.swing.JPanel produccion_odp_nombre;
    private javax.swing.JPanel productosmp;
    private javax.swing.JPanel productosterminados;
    private javax.swing.JPanel productostodos;
    private javax.swing.JPanel pruebas;
    private javax.swing.JRadioButton rbAceptados;
    private javax.swing.JRadioButton rbProspectos;
    private javax.swing.JTabbedPane tabpanel;
    private javax.swing.JTextField txtodp;
    private javax.swing.JPanel vendedoresdele;
    private javax.swing.JPanel vendedorestodos;
    private javax.swing.JPanel vgpano;
    private javax.swing.JPanel vgpmes;
    private javax.swing.JPanel vgvano;
    private javax.swing.JPanel vgvmes;
    private javax.swing.JPanel vs;
    // End of variables declaration//GEN-END:variables
}
