
package sistema;

import com.toedter.calendar.JTextFieldDateEditor;
import datos.Conexion;
import datos.DBcontrolador;
import datos.OrdenPedido_Provedores_DB;
import datos.Reproceso_DB;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import negocio.Producto;
import funciones.n2t;
import java.awt.Color;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import negocio.Clientes;
import negocio.Inventario;
import negocio.OrdenPedido_Provedores;
import negocio.Orden_de_Fabricacion;
import negocio.Proveedores;
import negocio.Reproceso;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.view.JasperViewer;


public class Movimientos extends javax.swing.JFrame {


    public int idproveedor;
    public String nombreproveedor;
    public String correo;
    
    public int columnaopp;
    
    public Producto pro ;
    public String moneda;
    public int idmoneda;
    public int idmoneda2;
    public int idcliente;
    public String nombrecliente;
    public int columnaopc;
    
    private  DBcontrolador dbc;
    private Connection con;
    
    
    ArrayList <Double> preciosopp = new ArrayList<>();
    
    //OPP
    private DefaultTableModel tabla_OPP;
    //Compra
    private DefaultTableModel tabla_Compra1;
    private DefaultTableModel tabla_Compra2;
    //OPC
    private DefaultTableModel tabla_OPC;
    //OPC
    private DefaultTableModel tabla_repro;
    
    
    //pruebas
    private DefaultTableModel sensoriales;
    
    private DefaultTableModel micro;
    
    private DefaultTableModel fisico;
    
    
    //array para guardar parametros
    private ArrayList <String[]> se = new ArrayList<>();
    private ArrayList <String[]> mi = new ArrayList<>();
    private ArrayList <String[]> fi = new ArrayList<>();
    
    private String s;
    
   
    private Menu_Principal mp ;
    
    /**
     * Creates new form Movimientos
     */
    public Movimientos(Menu_Principal mp, Connection con) throws SQLException {
        initComponents();
        this.mp=mp;
        this.con=con;
        this.dbc = new DBcontrolador();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.tabla_OPP=(DefaultTableModel) this.tbproductosopp.getModel();
        this.tabla_Compra1=(DefaultTableModel) this.tbproductosCOM.getModel();
        this.tabla_Compra2=(DefaultTableModel) this.tbproductosCOM2.getModel();
        this.tabla_OPC=(DefaultTableModel) this.tbopc.getModel();
        this.sensoriales=(DefaultTableModel) this.tbliberacionsensoriales.getModel();
        this.micro=(DefaultTableModel) this.tblieracionmicrobiologicas.getModel();
        this.fisico=(DefaultTableModel) this.tblieracionfisicoquimicas.getModel();
        this.tabla_repro=(DefaultTableModel) this.tb_repro.getModel();
        combo();
        
        JTextFieldDateEditor fecha1 = (JTextFieldDateEditor) this.jdfechaopp.getDateEditor();
        fecha1.setEditable(false);
        
        JTextFieldDateEditor fecha2 = (JTextFieldDateEditor) this.jdfecha_entradacompra.getDateEditor();
        fecha2.setEditable(false);
        JTextFieldDateEditor fecha3 = (JTextFieldDateEditor) this.jdfecha_caducidadcompra.getDateEditor();
        fecha3.setEditable(false);
        
        JTextFieldDateEditor fecha4 = (JTextFieldDateEditor) this.jDfechaopc.getDateEditor();
        fecha4.setEditable(false);
        
        JTextFieldDateEditor fecha5 = (JTextFieldDateEditor) this.jdfechaLIB.getDateEditor();
        fecha5.setEditable(false);
        
        JTextFieldDateEditor fecha6 = (JTextFieldDateEditor) this.jdreproceso.getDateEditor();
        fecha6.setEditable(false);
        
        permisos();
        Path c = Paths.get("");
        s = c.toAbsolutePath().toString();
    }
    
    @Override
    public Image getIconImage() {
       Image retValue = Toolkit.getDefaultToolkit().
             getImage(ClassLoader.getSystemResource("img/icono.png"));


       return retValue;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * W ARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        tabpanel = new javax.swing.JTabbedPane();
        OPP = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtidOPP = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtidproveedorOPP = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        txtnombreproveedorOPP = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        btnElegir_proveedorOPP = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbproductosopp = new javax.swing.JTable();
        btnAgregarproOPP = new javax.swing.JButton();
        btnquitarproOPP = new javax.swing.JButton();
        btnaceptaropp = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        cbcondicion_pagoOPP = new javax.swing.JComboBox<>();
        jLabel55 = new javax.swing.JLabel();
        jdfechaopp = new com.toedter.calendar.JDateChooser();
        Compra = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txtidproveedorCOM = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        txtnombreproveedorCOM = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbproductosCOM = new javax.swing.JTable();
        btnAgregarproCOM = new javax.swing.JButton();
        btnquitarproCOM = new javax.swing.JButton();
        txtfechapedidocompra = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        btnaceptarCOM = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbproductosCOM2 = new javax.swing.JTable();
        cboppCOM = new javax.swing.JComboBox<>();
        cbproductosCOM = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        txtcantidadcompraCOM = new javax.swing.JTextField();
        txtcapacidadcompraCOM = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txtlotecompra = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        txtfactnoCOM = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jdfecha_caducidadcompra = new com.toedter.calendar.JDateChooser();
        jdfecha_entradacompra = new com.toedter.calendar.JDateChooser();
        btncancelarCOM = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        OPC = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtidopc = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txtidclienteopc = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        txtnombreclienteOPC = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        btnElegirClienteOPC = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbopc = new javax.swing.JTable();
        btnAgregarproOPC = new javax.swing.JButton();
        btnquitarproOPC = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        btnaceptarOPC = new javax.swing.JButton();
        jDfechaopc = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        LOP = new javax.swing.JPanel();
        cbodpLIB = new javax.swing.JComboBox<>();
        jLabel44 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbliberacionsensoriales = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblieracionmicrobiologicas = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblieracionfisicoquimicas = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtcomentariosLIB = new javax.swing.JTextArea();
        btnaceptarLIB = new javax.swing.JButton();
        jLabel50 = new javax.swing.JLabel();
        txtproductoliberacion = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        txtproductoidliberacion = new javax.swing.JTextField();
        jdfechaLIB = new com.toedter.calendar.JDateChooser();
        REPROCESO = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtcantidad_repro = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        txtidodp_repro = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        txtnombre_repro = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        btnbuscar_repro = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tb_repro = new javax.swing.JTable();
        btnagregar_repro = new javax.swing.JButton();
        btnquitar_repro = new javax.swing.JButton();
        jLabel59 = new javax.swing.JLabel();
        btnaceptar_repro = new javax.swing.JButton();
        jdreproceso = new com.toedter.calendar.JDateChooser();
        jScrollPane10 = new javax.swing.JScrollPane();
        txtarea_repro = new javax.swing.JTextArea();
        jLabel23 = new javax.swing.JLabel();
        txtidpro_repro = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Movimientos");
        setIconImage(getIconImage());
        setIconImages(getIconImages());
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(162, 127, 51));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabpanel.setBackground(new java.awt.Color(255, 255, 255));

        OPP.setBackground(new java.awt.Color(255, 255, 255));
        OPP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel14.setText("Orden de Pedido a Proveedores");
        OPP.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, -1, 39));

        txtidOPP.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtidOPP.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtidOPP.setEnabled(false);
        OPP.add(txtidOPP, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 45, 118, -1));

        jLabel31.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel31.setText("ID Orden :");
        OPP.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 48, -1, -1));

        txtidproveedorOPP.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtidproveedorOPP.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtidproveedorOPP.setEnabled(false);
        OPP.add(txtidproveedorOPP, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 72, 118, -1));

        jLabel47.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel47.setText("ID Proveedor :");
        OPP.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 75, -1, -1));

        txtnombreproveedorOPP.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtnombreproveedorOPP.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnombreproveedorOPP.setEnabled(false);
        OPP.add(txtnombreproveedorOPP, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 103, 450, -1));

        jLabel51.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel51.setText("Nombre :");
        OPP.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 106, -1, -1));

        btnElegir_proveedorOPP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnElegir_proveedorOPP.setText("Elegir");
        btnElegir_proveedorOPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnElegir_proveedorOPPActionPerformed(evt);
            }
        });
        OPP.add(btnElegir_proveedorOPP, new org.netbeans.lib.awtextra.AbsoluteConstraints(471, 72, 130, -1));

        tbproductosopp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Clave", "Nombre", "U. Medida", "Cantidad", "Moneda", "P. Unitario", "Subtotal", "Iva", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbproductosopp.getTableHeader().setReorderingAllowed(false);
        tbproductosopp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbproductosoppMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbproductosopp);
        if (tbproductosopp.getColumnModel().getColumnCount() > 0) {
            tbproductosopp.getColumnModel().getColumn(0).setMaxWidth(50);
            tbproductosopp.getColumnModel().getColumn(1).setMaxWidth(100);
            tbproductosopp.getColumnModel().getColumn(2).setMinWidth(400);
            tbproductosopp.getColumnModel().getColumn(3).setMinWidth(70);
            tbproductosopp.getColumnModel().getColumn(3).setMaxWidth(70);
            tbproductosopp.getColumnModel().getColumn(4).setMaxWidth(70);
            tbproductosopp.getColumnModel().getColumn(5).setMaxWidth(100);
        }

        OPP.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 1240, 230));

        btnAgregarproOPP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAgregarproOPP.setText("Agregar Producto");
        btnAgregarproOPP.setEnabled(false);
        btnAgregarproOPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarproOPPActionPerformed(evt);
            }
        });
        OPP.add(btnAgregarproOPP, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, -1, -1));

        btnquitarproOPP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnquitarproOPP.setText("Quitar Producto");
        btnquitarproOPP.setEnabled(false);
        btnquitarproOPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnquitarproOPPActionPerformed(evt);
            }
        });
        OPP.add(btnquitarproOPP, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 420, 160, -1));

        btnaceptaropp.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnaceptaropp.setText("Aceptar");
        btnaceptaropp.setEnabled(false);
        btnaceptaropp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptaroppActionPerformed(evt);
            }
        });
        OPP.add(btnaceptaropp, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 420, 210, -1));

        jLabel43.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel43.setText("Condicion de pago:");
        OPP.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 80, -1, -1));

        cbcondicion_pagoOPP.setEnabled(false);
        OPP.add(cbcondicion_pagoOPP, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 80, 130, 20));

        jLabel55.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel55.setText("Fecha:");
        OPP.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 40, -1, -1));

        jdfechaopp.setDateFormatString("dd/MM/yyyy");
        jdfechaopp.setEnabled(false);
        OPP.add(jdfechaopp, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 40, 130, -1));

        tabpanel.addTab("Orden de Pedido a Proveedores", OPP);

        Compra.setBackground(new java.awt.Color(255, 255, 255));
        Compra.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel15.setText("Registro de Compra");
        Compra.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, -1, 39));

        jLabel33.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel33.setText("ID Orden :");
        Compra.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, -1));

        txtidproveedorCOM.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtidproveedorCOM.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtidproveedorCOM.setEnabled(false);
        Compra.add(txtidproveedorCOM, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 118, -1));

        jLabel48.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel48.setText("ID Proveedor :");
        Compra.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, -1));

        txtnombreproveedorCOM.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtnombreproveedorCOM.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnombreproveedorCOM.setEnabled(false);
        Compra.add(txtnombreproveedorCOM, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 440, -1));

        jLabel52.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel52.setText("Nombre :");
        Compra.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, -1, -1));

        tbproductosCOM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Clave", "Cantidad", "P. Unitario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbproductosCOM.setEnabled(false);
        tbproductosCOM.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tbproductosCOM);
        if (tbproductosCOM.getColumnModel().getColumnCount() > 0) {
            tbproductosCOM.getColumnModel().getColumn(0).setPreferredWidth(30);
            tbproductosCOM.getColumnModel().getColumn(1).setPreferredWidth(230);
            tbproductosCOM.getColumnModel().getColumn(2).setPreferredWidth(50);
            tbproductosCOM.getColumnModel().getColumn(3).setPreferredWidth(50);
            tbproductosCOM.getColumnModel().getColumn(4).setPreferredWidth(100);
        }

        Compra.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 610, 230));

        btnAgregarproCOM.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAgregarproCOM.setText("Agregar Producto");
        btnAgregarproCOM.setEnabled(false);
        btnAgregarproCOM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarproCOMActionPerformed(evt);
            }
        });
        Compra.add(btnAgregarproCOM, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 410, -1, -1));

        btnquitarproCOM.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnquitarproCOM.setText("Quitar Producto");
        btnquitarproCOM.setEnabled(false);
        btnquitarproCOM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnquitarproCOMActionPerformed(evt);
            }
        });
        Compra.add(btnquitarproCOM, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 410, -1, -1));

        txtfechapedidocompra.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfechapedidocompra.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtfechapedidocompra.setEnabled(false);
        Compra.add(txtfechapedidocompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 150, -1));

        jLabel34.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel34.setText("Fecha Pedido:");
        Compra.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, -1, -1));

        btnaceptarCOM.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnaceptarCOM.setText("Aceptar");
        btnaceptarCOM.setEnabled(false);
        btnaceptarCOM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptarCOMActionPerformed(evt);
            }
        });
        Compra.add(btnaceptarCOM, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 450, 107, -1));

        tbproductosCOM2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Capacidad", "Lote", "Costo", "Caducidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbproductosCOM2.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tbproductosCOM2);
        if (tbproductosCOM2.getColumnModel().getColumnCount() > 0) {
            tbproductosCOM2.getColumnModel().getColumn(0).setPreferredWidth(30);
            tbproductosCOM2.getColumnModel().getColumn(1).setPreferredWidth(230);
            tbproductosCOM2.getColumnModel().getColumn(2).setPreferredWidth(100);
            tbproductosCOM2.getColumnModel().getColumn(3).setPreferredWidth(100);
            tbproductosCOM2.getColumnModel().getColumn(4).setPreferredWidth(100);
            tbproductosCOM2.getColumnModel().getColumn(5).setPreferredWidth(100);
        }

        Compra.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 150, 590, 230));

        cboppCOM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboppCOMActionPerformed(evt);
            }
        });
        Compra.add(cboppCOM, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 118, -1));

        Compra.add(cbproductosCOM, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 170, -1));

        jLabel35.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel35.setText("Producto :");
        Compra.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, -1, -1));

        jLabel36.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel36.setText("Cantidad :");
        Compra.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 390, -1, -1));

        jLabel37.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel37.setText("Capacidad:");
        Compra.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 390, -1, -1));

        txtcantidadcompraCOM.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtcantidadcompraCOM.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtcantidadcompraCOM.setEnabled(false);
        txtcantidadcompraCOM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcantidadcompraCOMKeyReleased(evt);
            }
        });
        Compra.add(txtcantidadcompraCOM, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 410, 121, -1));

        txtcapacidadcompraCOM.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtcapacidadcompraCOM.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtcapacidadcompraCOM.setEnabled(false);
        txtcapacidadcompraCOM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcapacidadcompraCOMKeyReleased(evt);
            }
        });
        Compra.add(txtcapacidadcompraCOM, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 410, 121, -1));

        jLabel38.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel38.setText("Lote :");
        Compra.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 60, -1, -1));

        jLabel39.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel39.setText("Fecha  Entrada:");
        Compra.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 30, -1, -1));

        txtlotecompra.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtlotecompra.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtlotecompra.setEnabled(false);
        txtlotecompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtlotecompraKeyReleased(evt);
            }
        });
        Compra.add(txtlotecompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 60, 160, -1));

        jLabel40.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel40.setText("No Factura :");
        Compra.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 90, -1, -1));

        txtfactnoCOM.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfactnoCOM.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtfactnoCOM.setEnabled(false);
        txtfactnoCOM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtfactnoCOMKeyReleased(evt);
            }
        });
        Compra.add(txtfactnoCOM, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 90, 160, -1));

        jLabel45.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel45.setText("Caducidad :");
        Compra.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 120, -1, -1));

        jdfecha_caducidadcompra.setEnabled(false);
        Compra.add(jdfecha_caducidadcompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 120, 160, -1));

        jdfecha_entradacompra.setEnabled(false);
        Compra.add(jdfecha_entradacompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 30, 160, -1));

        btncancelarCOM.setText("Cancelar Orden");
        btncancelarCOM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarCOMActionPerformed(evt);
            }
        });
        Compra.add(btncancelarCOM, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, 150, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel9.setText("Capacidad: Cantidad por numero de entrada");
        jLabel9.setFocusable(false);
        Compra.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 470, 220, 20));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel10.setText("*Nota: Todos los productos deben entrar al inventario.");
        jLabel10.setFocusable(false);
        Compra.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 430, 280, 20));

        jLabel11.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel11.setText("Cantidad: Numero de entradas al inventario");
        jLabel11.setFocusable(false);
        Compra.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 450, 220, 20));

        tabpanel.addTab("Registro de Compra", Compra);

        OPC.setBackground(new java.awt.Color(255, 255, 255));
        OPC.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel16.setText("Orden de Pedido a Clientes");
        OPC.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 0, -1, 39));

        txtidopc.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtidopc.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtidopc.setEnabled(false);
        OPC.add(txtidopc, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 45, 118, -1));

        jLabel41.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel41.setText("ID Orden :");
        OPC.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 48, -1, -1));

        txtidclienteopc.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtidclienteopc.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtidclienteopc.setEnabled(false);
        OPC.add(txtidclienteopc, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 72, 118, -1));

        jLabel49.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel49.setText("ID Cliente :");
        OPC.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 75, -1, -1));

        txtnombreclienteOPC.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtnombreclienteOPC.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnombreclienteOPC.setEnabled(false);
        OPC.add(txtnombreclienteOPC, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 103, 460, -1));

        jLabel53.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel53.setText("Nombre :");
        OPC.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 106, -1, -1));

        btnElegirClienteOPC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnElegirClienteOPC.setText("Elegir");
        btnElegirClienteOPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnElegirClienteOPCActionPerformed(evt);
            }
        });
        OPC.add(btnElegirClienteOPC, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 140, -1));

        tbopc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Clave", "Nombre", "U. Medida", "Cantidad", "No. Orden Cliente"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbopc.getTableHeader().setReorderingAllowed(false);
        tbopc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbopcMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbopc);
        if (tbopc.getColumnModel().getColumnCount() > 0) {
            tbopc.getColumnModel().getColumn(0).setPreferredWidth(30);
            tbopc.getColumnModel().getColumn(1).setPreferredWidth(70);
            tbopc.getColumnModel().getColumn(2).setPreferredWidth(250);
            tbopc.getColumnModel().getColumn(3).setPreferredWidth(50);
            tbopc.getColumnModel().getColumn(4).setPreferredWidth(100);
            tbopc.getColumnModel().getColumn(5).setPreferredWidth(150);
        }

        OPC.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 1230, 250));

        btnAgregarproOPC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAgregarproOPC.setText("Agregar Producto");
        btnAgregarproOPC.setEnabled(false);
        btnAgregarproOPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarproOPCActionPerformed(evt);
            }
        });
        OPC.add(btnAgregarproOPC, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, -1, -1));

        btnquitarproOPC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnquitarproOPC.setText("Quitar Producto");
        btnquitarproOPC.setEnabled(false);
        btnquitarproOPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnquitarproOPCActionPerformed(evt);
            }
        });
        OPC.add(btnquitarproOPC, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 430, 160, -1));

        jLabel42.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel42.setText("Fecha:");
        OPC.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 40, -1, -1));

        btnaceptarOPC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnaceptarOPC.setText("Aceptar");
        btnaceptarOPC.setEnabled(false);
        btnaceptarOPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptarOPCActionPerformed(evt);
            }
        });
        OPC.add(btnaceptarOPC, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 430, 107, -1));

        jDfechaopc.setEnabled(false);
        OPC.add(jDfechaopc, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 40, 170, -1));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel12.setText("*Nota: El No. de Orden de Cliente es obligatorio de llenar");
        jLabel12.setFocusable(false);
        OPC.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 430, 280, 20));

        tabpanel.addTab("Orden de Pedido a Clientes", OPC);

        LOP.setBackground(new java.awt.Color(255, 255, 255));
        LOP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbodpLIB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbodpLIBActionPerformed(evt);
            }
        });
        LOP.add(cbodpLIB, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 36, 190, -1));

        jLabel44.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel44.setText("No. Orden Produccion :");
        LOP.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 38, -1, -1));

        jLabel18.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel18.setText("Liberacion");
        LOP.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, -1, 39));

        jLabel19.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel19.setText("Fisicoquimicas");
        LOP.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 100, -1, -1));

        tbliberacionsensoriales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Determinacion", "Resultado", "Metodo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbliberacionsensoriales);

        LOP.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 131, 580, 150));

        jLabel20.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel20.setText("Sensoriales");
        LOP.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, -1, -1));

        tblieracionmicrobiologicas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Determinacion", "Resultado", "Metodo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tblieracionmicrobiologicas);

        LOP.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 590, 150));

        tblieracionfisicoquimicas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Determinacion", "Resultado", "Metodo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(tblieracionfisicoquimicas);

        LOP.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 130, 600, 150));

        jLabel21.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel21.setText("Comentarios");
        LOP.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 290, -1, -1));

        jLabel46.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel46.setText("Fecha :");
        LOP.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 69, -1, -1));

        jLabel22.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel22.setText("Microbiologicas");
        LOP.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 290, -1, -1));

        txtcomentariosLIB.setColumns(20);
        txtcomentariosLIB.setRows(5);
        jScrollPane8.setViewportView(txtcomentariosLIB);

        LOP.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 320, 600, 120));

        btnaceptarLIB.setText("Aceptar");
        btnaceptarLIB.setEnabled(false);
        btnaceptarLIB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptarLIBActionPerformed(evt);
            }
        });
        LOP.add(btnaceptarLIB, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 450, 130, -1));

        jLabel50.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel50.setText("Producto :");
        LOP.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 70, -1, -1));

        txtproductoliberacion.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtproductoliberacion.setEnabled(false);
        LOP.add(txtproductoliberacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 70, 480, -1));

        jLabel54.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel54.setText("ID Producto :");
        LOP.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 40, -1, -1));

        txtproductoidliberacion.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtproductoidliberacion.setEnabled(false);
        LOP.add(txtproductoidliberacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 40, 92, -1));

        jdfechaLIB.setEnabled(false);
        LOP.add(jdfechaLIB, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 180, -1));

        tabpanel.addTab("Liberacion O. Produccion", LOP);

        REPROCESO.setBackground(new java.awt.Color(255, 255, 255));
        REPROCESO.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel17.setText("Observaciones");
        REPROCESO.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 140, -1, 39));

        txtcantidad_repro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtcantidad_repro.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtcantidad_repro.setEnabled(false);
        REPROCESO.add(txtcantidad_repro, new org.netbeans.lib.awtextra.AbsoluteConstraints(1088, 70, 150, -1));

        jLabel56.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel56.setText("Cantidad :");
        REPROCESO.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 70, -1, -1));

        txtidodp_repro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtidodp_repro.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        REPROCESO.add(txtidodp_repro, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 118, -1));

        jLabel57.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel57.setText("Lote :");
        REPROCESO.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, -1));

        txtnombre_repro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtnombre_repro.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnombre_repro.setEnabled(false);
        REPROCESO.add(txtnombre_repro, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 460, -1));

        jLabel58.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel58.setText("Producto:");
        REPROCESO.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, -1));

        btnbuscar_repro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnbuscar_repro.setText("Buscar");
        btnbuscar_repro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscar_reproActionPerformed(evt);
            }
        });
        REPROCESO.add(btnbuscar_repro, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 100, -1));

        tb_repro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Clave", "Nombre", "U. Medida", "Cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_repro.getTableHeader().setReorderingAllowed(false);
        jScrollPane9.setViewportView(tb_repro);
        if (tb_repro.getColumnModel().getColumnCount() > 0) {
            tb_repro.getColumnModel().getColumn(0).setPreferredWidth(30);
            tb_repro.getColumnModel().getColumn(1).setPreferredWidth(70);
            tb_repro.getColumnModel().getColumn(2).setPreferredWidth(250);
            tb_repro.getColumnModel().getColumn(3).setPreferredWidth(50);
            tb_repro.getColumnModel().getColumn(4).setPreferredWidth(100);
        }

        REPROCESO.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 690, 250));

        btnagregar_repro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnagregar_repro.setText("Agregar Producto");
        btnagregar_repro.setEnabled(false);
        btnagregar_repro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregar_reproActionPerformed(evt);
            }
        });
        REPROCESO.add(btnagregar_repro, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 430, -1, -1));

        btnquitar_repro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnquitar_repro.setText("Quitar Producto");
        btnquitar_repro.setEnabled(false);
        btnquitar_repro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnquitar_reproActionPerformed(evt);
            }
        });
        REPROCESO.add(btnquitar_repro, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 430, 160, -1));

        jLabel59.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel59.setText("Fecha:");
        REPROCESO.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 100, -1, -1));

        btnaceptar_repro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnaceptar_repro.setText("Aceptar");
        btnaceptar_repro.setEnabled(false);
        btnaceptar_repro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptar_reproActionPerformed(evt);
            }
        });
        REPROCESO.add(btnaceptar_repro, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 430, 107, -1));

        jdreproceso.setEnabled(false);
        REPROCESO.add(jdreproceso, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 100, 150, -1));

        txtarea_repro.setColumns(20);
        txtarea_repro.setRows(5);
        jScrollPane10.setViewportView(txtarea_repro);

        REPROCESO.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 180, 480, 230));

        jLabel23.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel23.setText("Reproceso");
        REPROCESO.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 0, -1, 39));

        txtidpro_repro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtidpro_repro.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtidpro_repro.setEnabled(false);
        REPROCESO.add(txtidpro_repro, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 118, -1));

        jLabel60.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel60.setText("ID :");
        REPROCESO.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, -1, -1));

        tabpanel.addTab("Reproceso", REPROCESO);

        jPanel4.add(tabpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 56, 1290, 520));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel13.setText("Movimientos");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, -1, 39));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public void permisos()
    {
        switch(this.mp.user.getPerfil())
        {
            //ALMACEN    
            case 3:
                this.tabpanel.setEnabledAt(0, true);
                this.tabpanel.setEnabledAt(1, true);
                this.tabpanel.setEnabledAt(2, false);
                this.tabpanel.setEnabledAt(3, false);
                this.tabpanel.setEnabledAt(4, false);
                break;
            //PRODUCC    
            case 4:
                this.tabpanel.setEnabledAt(0, false);
                this.tabpanel.setEnabledAt(1, false);
                this.tabpanel.setEnabledAt(2, true);
                this.tabpanel.setEnabledAt(3, true);
                this.tabpanel.setEnabledAt(4, true);
                this.tabpanel.setSelectedIndex(2);
                break;
        }
    }
    
    //cambiar formato de fecha
    public String fechadividir(JTextField  jt, int i ){
        String fi ="";
        if (i == 0) {
            String[] s = jt.getText().split("-");
            fi = s[2]+"/"+s[1]+"/"+s[0];       
        }
        else
        {
            String[] s = jt.getText().split("/");
            fi = s[2]+"-"+s[1]+"-"+s[0]; 
        }
        return fi;
    }
    
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
    
    //METODO PARA QUITAR COLOR ROJO 
    public void regresar_color(JTextField jx)
    {
        jx.setBackground(Color.WHITE);
    }
    
    public void combo() throws SQLException
    {
        OrdenPedido_Provedores_DB oppdb = new OrdenPedido_Provedores_DB(this.con);
        
        this.cboppCOM.removeAllItems();
        List <String[]> op = oppdb.select_opp();
        for (int i = 0; i < op.size(); i++) {
            this.cboppCOM.addItem(op.get(i)[0]+ " " + op.get(i)[1]);
        }
        
        this.cbodpLIB.removeAllItems();
        String query="SELECT id FROM ordenes_prod where estatus = 1";
        op.clear();
        op=this.dbc.seleccionar(query);
        for (int i = 0; i < op.size(); i++) {
            this.cbodpLIB.addItem(op.get(i)[0]);
        }
        
        //CONDICION PAGO
        this.cbcondicion_pagoOPP.removeAllItems();
        List <String> pago = oppdb.select_pago();
        for (int i = 0; i < pago.size(); i++) {
            this.cbcondicion_pagoOPP.addItem(pago.get(i));
        }
    }
    

    //cierra
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       this.setVisible(false);
       this.mp.notificaciones();
       this.mp.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_formWindowClosing

    
    //<editor-fold defaultstate="collapsed" desc="LIB">
    //LIB
    private void cbodpLIBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbodpLIBActionPerformed
        comboliberacion();
    }//GEN-LAST:event_cbodpLIBActionPerformed
    
    //LIB
    public void comboliberacion()
    {
        try{
            this.sensoriales.setRowCount(0);
            this.micro.setRowCount(0);
            this.fisico.setRowCount(0);

            String idodp= this.cbodpLIB.getSelectedItem().toString();
            String query = "select ordenes_prod.id, ordenes_prod.idproduc, productos.nombre, ordenes_prod.fecha from ordenes_prod \n" +
                           "join productos on ordenes_prod.idproduc = productos.id  where  ordenes_prod.id = "+ idodp;
            ArrayList <String[]> op = new ArrayList<>();
            op= this.dbc.seleccionar(query);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            this.jdfechaLIB.setDateFormatString("dd/MM/yyyy");
            Date date = formatter.parse(fechadividir(op.get(0)[3],0));
            this.jdfechaLIB.setDate(date);
            this.txtproductoidliberacion.setText(op.get(0)[1]);
            this.txtproductoliberacion.setText(op.get(0)[2]);

            query="select * from pruebas where idproducto =  "+this.txtproductoidliberacion.getText();
            op.clear();
            this.se.clear();
            this.mi.clear();
            this.fi.clear();

            op=this.dbc.seleccionar(query);
            for (int i = 0; i < op.size(); i++) {
                if (op.get(i)[2].compareTo("1")==0) {
                    String[] s = new String[3];
                    s[0]=op.get(i)[3];
                    s[1]="";
                    s[2]=op.get(i)[5];
                    this.se.add(op.get(i));
                    this.sensoriales.addRow(s);
                }
                else
                {
                    if (op.get(i)[2].compareTo("3")==0) {
                        String[] s = new String[3];
                        s[0]=op.get(i)[3];
                        s[1]="";
                        s[2]=op.get(i)[5];
                        this.micro.addRow(s);
                        this.mi.add(op.get(i));
                    }
                    else
                    {
                        if (op.get(i)[2].compareTo("2")==0) {
                            String[] s = new String[3];
                            s[0]=op.get(i)[3];
                            s[1]="";
                            s[2]=op.get(i)[5];
                            this.fisico.addRow(s);
                            this.fi.add(op.get(i));
                        }

                    }
                }
            }
            
            this.jdfechaLIB.setEnabled(true);
            this.btnaceptarLIB.setEnabled(true);
        }
        catch(Exception ex )
        {
            System.out.println("Error combo "+ ex);
            
        }
        
    }
    
    
    //LIB
    private void btnaceptarLIBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarLIBActionPerformed
        try{
            boolean se=true;
            boolean fi=true;
            boolean mi=true;

            for (int i = 0; i < this.tbliberacionsensoriales.getRowCount(); i++) {
                String res=this.tbliberacionsensoriales.getValueAt(i, 1).toString().toUpperCase();
                if (res.isEmpty()) {
                    se=false;
                }
            }

            for (int i = 0; i < this.tblieracionmicrobiologicas.getRowCount(); i++) {
                String res=this.tblieracionmicrobiologicas.getValueAt(i, 1).toString().toUpperCase();
                if (res.isEmpty()) {
                    mi=false;
                }
                /*
                if (this.mi.get(i)[4].matches("^[0-9]+(.[0-9]+)?-[0-9]+(.[0-9]+)?$")) {
                    String[] c = this.mi.get(i)[4].split("-");
                    double n1=Double.parseDouble(c[0]);
                    double n2=Double.parseDouble(c[1]);
                    double res=Double.parseDouble(this.tblieracionmicrobiologicas.getValueAt(i, 1).toString());
                    if (res < n1|| res > n2) {
                        mi=false;
                    }
                }
                else
                {
                    if (this.mi.get(i)[4].matches("^>=?[0-9]+(.[0-9]+)?|<=?[0-9]+(.[0-9]+)?$")) {
                        String[] c = this.mi.get(i)[4].split("=");

                        double res=Double.parseDouble(this.tblieracionmicrobiologicas.getValueAt(i, 1).toString());
                        double n2=Double.parseDouble(c[1]);
                        if (c[0].compareTo("<")== 0) {
                            if (res > n2) {
                                mi=false;
                            }
                        }
                        else
                        {
                            if (res < n2) {
                                mi=false;
                            }
                        }
                    }
                    else
                    {
                        String res=this.tblieracionmicrobiologicas.getValueAt(i, 1).toString().toUpperCase();
                        if (res.compareTo(this.mi.get(i)[4].toUpperCase()) != 0) {
                            mi=false;
                        }
                    }
                }*/
            }

            for (int i = 0; i < this.tblieracionfisicoquimicas.getRowCount(); i++) {
                String res=this.tblieracionfisicoquimicas.getValueAt(i, 1).toString().toUpperCase();
                if (res.isEmpty()) {
                    mi=false;
                }
                /*
                if (this.fi.get(i)[4].matches("^[0-9]+(.[0-9]+)?-[0-9]+(.[0-9]+)?$")) {
                    String[] c = this.fi.get(i)[4].split("-");
                    double n1=Double.parseDouble(c[0]);
                    double n2=Double.parseDouble(c[1]);
                    double res=Double.parseDouble(this.tblieracionfisicoquimicas.getValueAt(i, 1).toString());
                    if (res < n1|| res > n2) {
                        fi=false;
                    }
                }
                else
                {
                    if (this.fi.get(i)[4].matches("^>=?[0-9]+(.[0-9]+)?|<=?[0-9]+(.[0-9]+)?$")) {
                        String[] c = this.fi.get(i)[4].split("=");

                        double res=Double.parseDouble(this.tblieracionfisicoquimicas.getValueAt(i, 1).toString());
                        double n2=Double.parseDouble(c[1]);
                        if (c[0].compareTo("<")== 0) {
                            if (res > n2) {
                                fi=false;
                            }
                        }
                        else
                        {
                            if (res < n2) {
                                fi=false;
                            }
                        }
                    }
                    else
                    {
                        String res=this.tblieracionfisicoquimicas.getValueAt(i, 1).toString().toUpperCase();
                        if (res.compareTo(this.fi.get(i)[4].toUpperCase()) != 0) {
                            fi=false;
                        }
                    }
                }*/
            }

            if (se) {
                if (mi) {
                    if (fi) {
                        int n = JOptionPane.showConfirmDialog(null,"¿Seguro que dar de alta los resultados?","Datos Correctos",JOptionPane.YES_NO_OPTION);
                        if (n== JOptionPane.YES_OPTION) {
                            String query="update ordenes_prod set estatus = 2, observaciones = '"+this.txtcomentariosLIB.getText()+"' where id = "+ this.cbodpLIB.getSelectedItem().toString();
                            this.dbc.operacion(query);

                            query="insert into resultadosp_odp (idodp, idprueba, resultado) values (?,?,?)";
                            for (int i = 0; i < this.se.size(); i++) {
                                PreparedStatement ps= this.dbc.getCnx().prepareStatement(query); 

                                ps.setString(1, this.cbodpLIB.getSelectedItem().toString());
                                ps.setString(2, this.se.get(i)[0]);
                                ps.setString(3, this.sensoriales.getValueAt(i, 1).toString());

                                ps.executeUpdate();
                                ps.close();
                            }

                            for (int i = 0; i < this.mi.size(); i++) {
                                PreparedStatement ps= this.dbc.getCnx().prepareStatement(query); 

                                ps.setString(1, this.cbodpLIB.getSelectedItem().toString());
                                ps.setString(2, this.mi.get(i)[0]);
                                ps.setString(3, this.micro.getValueAt(i, 1).toString());

                                ps.executeUpdate();
                                ps.close();
                            }

                            for (int i = 0; i < this.fi.size(); i++) {
                                PreparedStatement ps= this.dbc.getCnx().prepareStatement(query); 

                                ps.setString(1, this.cbodpLIB.getSelectedItem().toString());
                                ps.setString(2, this.fi.get(i)[0]);
                                ps.setString(3, this.fisico.getValueAt(i, 1).toString());

                                ps.executeUpdate();
                                ps.close();
                            }
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                            //insertar pt en inventario
                            query="insert into inventario(idproducto, fechaentrada, cantidadactual,lote, idopp,facturano, cantidad,costo,fechacaducidad) values (?,?,?,?,?,?,?,?,?)";
                            PreparedStatement ps= this.dbc.getCnx().prepareStatement(query); 
                            ps.setString(1, this.txtproductoidliberacion.getText());
                            ps.setString(2, this.fechadividir(formatter.format(this.jdfechaLIB.getDate()), 1) );
                            String q2 =this.dbc.seleccionarid("select cantidad from ordenes_prod where id = "+this.cbodpLIB.getSelectedItem().toString());
                            ps.setString(3, q2);
                            ps.setString(4, this.cbodpLIB.getSelectedItem().toString());
                            ps.setString(5, this.cbodpLIB.getSelectedItem().toString());
                            ps.setString(6, this.cbodpLIB.getSelectedItem().toString());
                            ps.setString(7, q2);
                            String q3 =this.dbc.seleccionarid("select sum(costo) from mp_odp where idodp = "+this.cbodpLIB.getSelectedItem().toString());
                            ps.setString(8, q3);
                            String q4 =this.dbc.seleccionarid("select mesescaducidad from productos where id = "+this.txtproductoidliberacion.getText());
                            LocalDate dt =  LocalDate.parse(this.fechadividir(formatter.format(this.jdfechaLIB.getDate()), 1)).plusMonths(Integer.parseInt(q4));
                            ps.setString(9, dt.getYear()+"-"+dt.getMonthValue()+"-"+dt.getDayOfMonth());
                            ps.executeUpdate();
                            ps.close();

                            this.sensoriales.setRowCount(0);
                            this.micro.setRowCount(0);
                            this.fisico.setRowCount(0);

                            this.txtcomentariosLIB.setText("");
                            this.txtproductoidliberacion.setText("");
                            this.txtproductoliberacion.setText("");

                            this.btnaceptarLIB.setEnabled(false);

                            JasperReport reporte; //Creo el objeto reporte
                            // Ubicacion del Reporte
                            String path = s+"\\Reportes\\Liberacion_ODP.jasper";
                            try {
                                reporte = (JasperReport) JRLoader.loadObjectFromFile(path); //Cargo el reporte al objeto
                                Map id = new HashMap();
                                id.put("ID_Inven", this.dbc.seleccionarid("select max(id) from inventario"));
                                JasperPrint jprint = JasperFillManager.fillReport(path, id, this.dbc.getCnx()); //Llenado del Reporte con Tres parametros ubicacion,parametros,conexion a la base de datos
                                JasperExportManager.exportReportToPdfFile(jprint, s+"\\Liberacion\\Liberacion-"+this.cbodpLIB.getSelectedItem().toString()+".pdf");
                                
                                JRDocxExporter exporter = new JRDocxExporter();
                                exporter.setExporterInput(new SimpleExporterInput(jprint));      
                                File exportReportFile = new File(s+"\\Liberacion\\Liberacion-"+this.cbodpLIB.getSelectedItem().toString() + ".docx");
                                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(exportReportFile));
                                exporter.exportReport();
                                
                                
                                
                                JasperViewer viewer = new JasperViewer(jprint,false); //Creamos la vista del Reporte
                                viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Le agregamos que se cierre solo el reporte cuando lo cierre el usuario
                                viewer.setVisible(true); //Inicializamos la vista del Reporte

                            } catch (Exception ex) {
                                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            combo();
                            comboliberacion();
                        }
                        
             
                        
                        
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Valores incorrectos en pruebas Fisicoquimicas");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Valores incorrectos en pruebas Microbiologicas");
                }

            }
            else
            {
                JOptionPane.showMessageDialog(null, "Valores incorrectos en pruebas Sensoriales");
            }
        
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexion, intente otra vez");
            try {
                this.dbc = new DBcontrolador ();
            } catch (SQLException ex1) {
                Logger.getLogger(Movimientos.class.getName()).log(Level.SEVERE, null, ex1);
            }
           this.con=this.dbc.getCnx();
           
        }
        
        
    }//GEN-LAST:event_btnaceptarLIBActionPerformed

    
    //</editor-fold>
       
    //<editor-fold defaultstate="collapsed" desc="ODProduccion">
    public void colocarcliente(Clientes cl)
    {
        
        this.txtidclienteopc.setText(cl.getId() + "");
        this.txtnombreclienteOPC.setText(cl.getNombre());
        
        Date date = new Date();
        this.jDfechaopc.setDateFormatString("dd/MM/yyyy");
        this.jDfechaopc.setDate(date);
        
        this.jDfechaopc.setEnabled(true);
        
        
        String query="Select MAX(id) from opclientes";
        int maxid;
        if(this.dbc.seleccionarid(query)==null)
        maxid=1;
        else
        maxid=Integer.parseInt(this.dbc.seleccionarid(query)+1);
        
        this.txtidopc.setText(maxid + "");
        this.btnAgregarproOPC.setEnabled(true);
        
    }
    
     public void colocarproductoopc()
    {
        
        String[] s = new String[5]; 
        s[1]=this.pro.getClave();
        s[0]=this.pro.getId()+"";
        s[2]=this.pro.getNombre();
        s[3]=this.pro.getMedida();
        s[4]=this.pro.getStockmin()+"";
        
        boolean x=true;
        for (int i = 0; i < this.tabla_OPC.getRowCount(); i++) {
            int id = Integer.parseInt(this.tabla_OPC.getValueAt(i, 0).toString());
            if (id == Integer.parseInt(s[0])) {
                x=false;
            }            
        }
         
        if (x) {
            this.tabla_OPC.addRow(s);
            this.btnquitarproOPC.setEnabled(true);
            this.btnaceptarOPC.setEnabled(true);

        }
        else
        {
            JOptionPane.showMessageDialog(null, "El producto ya esta seleccionado");
        }
        
    }
    

    private void btnaceptarOPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarOPCActionPerformed
        try{
            if (this.tbopc.getRowCount() > 0) {
                String query;
                String ingrefaltante="";
                ArrayList <String[]> op = new ArrayList<>();
                ArrayList <String[]> op2 = new ArrayList<>();
                
                boolean sipasa=true;
                boolean existe=true;
                
                //validacion
                
                //hacer lista con todos los ingredientes
                for (int i = 0; i < this.tabla_OPC.getRowCount(); i++) {
                    //Ingredientes de un producto
                    query="select idproducto, cantidad from ingredientes where idproductofinal= " + this.tabla_OPC.getValueAt(i, 0).toString();
                    op= this.dbc.seleccionar(query);
                    for (int j = 0; j < op.size(); j++) {
                        //multiplicar por la cantidad
                        op.get(j)[1]= Double.parseDouble(op.get(j)[1]) *  Double.parseDouble(this.tabla_OPC.getValueAt(i, 4).toString())+"";
                        //Para saber si ya esta seleccionado (dentro de op2)
                        existe=true;
                        for (int k = 0; k < op2.size(); k++) {
                            if (op.get(j)[0].equals(op2.get(k)[0])) {
                                double cantidad = Double.parseDouble(op.get(j)[1]);
                                op2.get(k)[1]=(cantidad+Double.parseDouble(op2.get(k)[1]))+"";
                                existe=false;
                            }
                        }
                        if(existe)
                        op2.add(op.get(j));
                    }
                    op.clear();
                }
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                //Comparar con el inventario
                for (int i = 0; i < op2.size(); i++) {
                    query="select sum(inventario.cantidadactual), productos.nombre from inventario join productos on productos.id = inventario.idproducto where fechacaducidad >= '"+this.fechadividir(formatter.format(this.jDfechaopc.getDate()), 2) +"' and idproducto = "+ op2.get(i)[0];
                    op=this.dbc.seleccionar(query);
                    
                    if (op.get(0)[0]== null) {
                        ingrefaltante += "Requiere "+Double.parseDouble(op2.get(i)[1])  +" de "+op.get(0)[1] +"\n";
                        sipasa=false;
                        
                    }
                    else
                    {
                        if (Double.parseDouble(op.get(0)[0]) < Double.parseDouble(op2.get(i)[1])) {
                        sipasa=false;
                        double falta = Double.parseDouble(op2.get(i)[1])- Double.parseDouble(op.get(0)[0]);
                        ingrefaltante += "Requiere "+falta+" de "+op.get(0)[1] +"\n";
                        }
                    }
                }
                
                
                
                boolean ordencliente=true;
                for (int i = 0; i < this.tabla_OPC.getRowCount(); i++) {
                    if (this.tabla_OPC.getValueAt(i, 5) == null) {
                        ordencliente=false;
                    }
                }

                if (sipasa) {
                    if (ordencliente) {
                        
                        String[] opciones_orden = {"Hacer Orden de Produccion", "Imprimir Pre-Orden", "Cancelar"};
                        int seleccion = JOptionPane.showOptionDialog(null, "Ingredientes Correctos \n ¿Qué desea hacer?", "Orden de Fabricacion", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,null,opciones_orden,opciones_orden[0]);
                        
                        if (seleccion==0){
                            double preciototal=0;
                            for (int i = 0; i < this.tabla_OPC.getRowCount(); i++) {
                                System.out.println(i);
                                query="Select pventa from productos where id = "+ this.tabla_OPC.getValueAt(i, 0).toString();
                                op=this.dbc.seleccionar(query);
                                preciototal+=Double.parseDouble(op.get(0)[0])* Double.parseDouble(this.tabla_OPC.getValueAt(i, 4).toString());
                                op.clear();
                            }

                            query="Insert into opclientes(idcliente,ptotal, fecha) values (?,?,?)";
                            PreparedStatement ps= this.dbc.getCnx().prepareStatement(query);

                            ps.setInt(1, Integer.parseInt(this.txtidclienteopc.getText()) );

                            ps.setDouble(2, preciototal );

                            ps.setString(3, this.fechadividir(formatter.format(this.jDfechaopc.getDate()), 2));

                            ps.executeUpdate();
                            ps.close();

                            query = "select MAX(id) from opclientes";

                            int index = Integer.parseInt(this.dbc.seleccionarid(query) );

                            query ="Insert into pedidos_opc(idopc,idproducto,cantidad,noordencliente) values (?,?,?,?)";

                            for (int i = 0; i < this.tabla_OPC.getRowCount(); i++) {

                                ps= this.dbc.getCnx().prepareStatement(query);
                                ps.setDouble(1, index);
                                ps.setInt(2, Integer.parseInt(this.tabla_OPC.getValueAt(i, 0).toString()));
                                ps.setDouble(3, Double.parseDouble(this.tabla_OPC.getValueAt(i, 4).toString()));
                                ps.setString(4, this.tabla_OPC.getValueAt(i, 5).toString());
                                ps.executeUpdate();
                                ps.close();
                            }

                            //crearodernproduccion
                            for (int i = 0; i < this.tabla_OPC.getRowCount(); i++) {
                                query ="Insert into ordenes_prod(idopc,idproduc,fecha,cantidad) values (?,?,?,?)";
                                ps = this.dbc.getCnx().prepareStatement(query);
                                ps.setDouble(1, index);
                                ps.setInt(2, Integer.parseInt(this.tabla_OPC.getValueAt(i, 0).toString()));
                                ps.setString(3, this.fechadividir(formatter.format(this.jDfechaopc.getDate()), 2));
                                ps.setDouble(4, Double.parseDouble(this.tabla_OPC.getValueAt(i, 4).toString()));
                                ps.executeUpdate();
                                ps.close();
                                query = "select MAX(id) from ordenes_prod";

                                int indexodp = Integer.parseInt(this.dbc.seleccionarid(query) );

                                //inventario e ingredientes

                                double cantidad=Double.parseDouble(this.tabla_OPC.getValueAt(i, 4).toString());
                                ArrayList <String[]> op1 = new ArrayList<>();
                                query="select * from ingredientes where idproductofinal = "+ this.tabla_OPC.getValueAt(i, 0).toString();
                                op=this.dbc.seleccionar(query);
                                for (int j = 0; j < op.size(); j++) {

                                    query="select * from inventario where idproducto = "+op.get(j)[2] +" and cantidadactual > 0 and fechacaducidad >= '"+this.fechadividir(formatter.format(this.jDfechaopc.getDate()), 2)+"'";
                                    op1=this.dbc.seleccionar(query);
                                    double cantidad2=cantidad * Double.parseDouble(op.get(j)[3]);
                                    for (int k = 0; k < op1.size(); k++) {

                                        if (cantidad2 != 0) {

                                            double aux =Double.parseDouble(op1.get(k)[3]) - cantidad2;
                                            if (aux >= 0) {
                                                query="insert into mp_odp (idodp,idinven,cantidad,costo) values (?,?,?,?)";
                                                ps= this.dbc.getCnx().prepareStatement(query);
                                                ps.setDouble(1, indexodp);
                                                ps.setInt(2, Integer.parseInt(op1.get(k)[0]));
                                                ps.setDouble(3, cantidad2);
                                                double costo =Double.parseDouble(op1.get(k)[8])/Double.parseDouble(op1.get(k)[7]) * cantidad2;
                                                ps.setDouble(4, costo);
                                                ps.executeUpdate();
                                                ps.close();

                                                query="update inventario set cantidadactual = ? where id = ? ";
                                                ps= this.dbc.getCnx().prepareStatement(query);
                                                ps.setDouble(1, aux);
                                                ps.setInt(2, Integer.parseInt(op1.get(k)[0]));
                                                ps.executeUpdate();
                                                ps.close();

                                                cantidad2=0;
                                            }
                                            else
                                            {
                                                query="insert into mp_odp (idodp,idinven,cantidad,costo) values (?,?,?,?)";
                                                ps= this.dbc.getCnx().prepareStatement(query);
                                                ps.setDouble(1, indexodp);
                                                ps.setInt(2, Integer.parseInt(op1.get(k)[0]));
                                                ps.setDouble(3, Double.parseDouble(op1.get(k)[3]));
                                                double costo =Double.parseDouble(op1.get(k)[8])/Double.parseDouble(op1.get(k)[7]) * Double.parseDouble(op1.get(k)[3]);
                                                ps.setDouble(4, costo);
                                                ps.executeUpdate();
                                                ps.close();

                                                query="update inventario set cantidadactual = ? where id = ?";
                                                ps= this.dbc.getCnx().prepareStatement(query);
                                                ps.setDouble(1, 0);
                                                ps.setInt(2, Integer.parseInt(op1.get(k)[0]));
                                                ps.executeUpdate();
                                                ps.close();

                                                cantidad2=cantidad2-Double.parseDouble(op1.get(k)[3]);
                                            }

                                        }

                                    }

                                    op1.clear();

                                }
                                //reporte 
                                JasperReport reporte; //Creo el objeto reporte
                                // Ubicacion del Reporte
                                String path = s+"\\Reportes\\ODP.jasper";
                           try {
                               
                               reporte = (JasperReport) JRLoader.loadObjectFromFile(path); //Cargo el reporte al objeto
                               Map idodp = new HashMap();
                               idodp.put("ID_ODP", indexodp);
                               JasperPrint jprint = JasperFillManager.fillReport(path, idodp, this.dbc.getCnx()); //Llenado del Reporte con Tres parametros ubicacion,parametros,conexion a la base de datos
                               JasperExportManager.exportReportToPdfFile(jprint, s+"\\ODP\\ODP-"+indexodp+".pdf");
                               JasperViewer viewer = new JasperViewer(jprint,false); //Creamos la vista del Reporte
                               viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Le agregamos que se cierre solo el reporte cuando lo cierre el usuario
                               viewer.setVisible(true); //Inicializamos la vista del Reporte
                               
                           } catch (Exception ex) {
                               Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                           }

                            }
                            this.btnAgregarproOPC.setEnabled(false);
                            this.btnquitarproOPC.setEnabled(false);
                            this.btnaceptarOPC.setEnabled(false);

                            this.txtidclienteopc.setText("");
                            
                            this.txtnombreclienteOPC.setText("");
                            this.txtidopc.setText("");

                            this.tabla_OPC.setRowCount(0);
                            combo();
                        }
                        else{
                            //Pre Orden para que Ale sepa que materias utilizar
                            if (seleccion==1) {
                                List <Inventario> ingre = new ArrayList<>();
                                
                                for (int i = 0; i < this.tabla_OPC.getRowCount(); i++) {                          
                                //inventario e ingredientes
                                double cantidad=Double.parseDouble(this.tabla_OPC.getValueAt(i, 4).toString());
                                ArrayList <String[]> op1 = new ArrayList<>();
                                query="select * from ingredientes where idproductofinal = "+ this.tabla_OPC.getValueAt(i, 0).toString();
                                op=this.dbc.seleccionar(query);
                                for (int j = 0; j < op.size(); j++) {
                                    query="select inventario.id,productos.nombre,lote,umedida.nombre,productos.clave,inventario.cantidadactual from inventario \n" +
                                    " join productos on productos.id=inventario.idproducto join umedida on umedida.id=productos.idmedida where idproducto = "+op.get(j)[2] +" and cantidadactual > 0";
                                    op1=this.dbc.seleccionar(query);
                                    double cantidad2=cantidad * Double.parseDouble(op.get(j)[3]);
                                    
                                    for (int k = 0; k < op1.size(); k++) {
                                        if (cantidad2 != 0) {
                                            double aux =Double.parseDouble(op1.get(k)[5]) - cantidad2;
                                            
                                            if (aux >= 0) {
                                                //USO de INVENTARIO como auxilri
                                                Inventario ingrediente = new Inventario();
                                                ingrediente.setId(Integer.parseInt(op1.get(k)[0]));
                                                //nombre
                                                ingrediente.setFecha_entrada(op1.get(k)[1]);
                                                ingrediente.setLote(op1.get(k)[2]);
                                                ingrediente.setCantidad(cantidad2);
                                                //umedida
                                                ingrediente.setFactura(op1.get(k)[3]);
                                                //clave
                                                ingrediente.setFecha_caducidad(op1.get(k)[4]);
                                                
                                                ingre.add(ingrediente);
                                                
                                                cantidad2=0;
                                            }
                                            else
                                            {  
                                                //USO de INVENTARIO como auxilri
                                                Inventario ingrediente = new Inventario();
                                                ingrediente.setId(Integer.parseInt(op1.get(k)[0]));
                                                //nombre
                                                ingrediente.setFecha_entrada(op1.get(k)[1]);
                                                ingrediente.setLote(op1.get(k)[2]);
                                                ingrediente.setCantidad(Double.parseDouble(op1.get(k)[5]));
                                                //umedida
                                                ingrediente.setFactura(op1.get(k)[3]);
                                                //clave
                                                ingrediente.setFecha_caducidad(op1.get(k)[4]);
                                                
                                                ingre.add(ingrediente);

                                                cantidad2=cantidad2-Double.parseDouble(op1.get(k)[5]);
                                                
                                            }

                                        }

                                    }

                                    op1.clear();

                                }
                                
                                //Reporte Pre Orden   
                                JRBeanCollectionDataSource ingres = new JRBeanCollectionDataSource(ingre);
                                
                                Map<String, Object> parametros = new HashMap<>();
                                
                                parametros.put("Clave", this.tabla_OPC.getValueAt(i, 1).toString());
                                parametros.put("Nombre", this.tabla_OPC.getValueAt(i, 2).toString());
                                parametros.put("Cantidad", this.tabla_OPC.getValueAt(i, 4).toString());
                                parametros.put("Data", ingres);
                                //reporte 
                                    JasperReport reporte; //Creo el objeto reporte
                                    // Ubicacion del Reporte
                                    String path = s+"\\Reportes\\PREODP.jasper";
                                try {

                                    reporte = (JasperReport) JRLoader.loadObjectFromFile(path); //Cargo el reporte al objeto
                                    JasperPrint jprint = JasperFillManager.fillReport(path, parametros, new JREmptyDataSource()); //Llenado del Reporte con Tres parametros ubicacion,parametros,conexion a la base de datos
                                    JasperViewer viewer = new JasperViewer(jprint,false); //Creamos la vista del Reporte
                                    viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Le agregamos que se cierre solo el reporte cuando lo cierre el usuario
                                    viewer.setVisible(true); //Inicializamos la vista del Reporte

                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(null, "No se encontro el archivo del reporte", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            
                            
                          }
                        }

                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Alguna Orden de cliente esta vacia", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, ingrefaltante, "Error: Faltan Ingredientes", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "No hay ningun producto en la tabla");
            }
            
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexion, intente otra vez "+ ex);
            try {
                this.dbc = new DBcontrolador ();
            } catch (SQLException ex1) {
                Logger.getLogger(Movimientos.class.getName()).log(Level.SEVERE, null, ex1);
            }
           this.con=this.dbc.getCnx();
           
        }
    }//GEN-LAST:event_btnaceptarOPCActionPerformed

    private void btnquitarproOPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnquitarproOPCActionPerformed
        try
        {
            this.tabla_OPC.removeRow(this.columnaopp);
            if (this.tabla_OPC.getRowCount()==0) {
                this.btnquitarproOPC.setEnabled(false);
                this.btnaceptarOPC.setEnabled(false);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_btnquitarproOPCActionPerformed

    private void btnAgregarproOPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarproOPCActionPerformed
        try
        {
            Elegir_ProductoOPP productos = new Elegir_ProductoOPP (this,2,this.con);
            this.setEnabled(false);
            productos.setVisible(true);

        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_btnAgregarproOPCActionPerformed

    private void tbopcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbopcMouseClicked
        try
        {
            this.columnaopc=this.tbopc.getSelectedRow();
        }
        catch(Exception ex)
        {

        }
    }//GEN-LAST:event_tbopcMouseClicked

    private void btnElegirClienteOPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElegirClienteOPCActionPerformed
        try {
            Elegir_ProveedorOPP pro = new Elegir_ProveedorOPP(this,2,this.con);
            pro.setVisible(true);
            this.setEnabled(false);

        } catch (Exception ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnElegirClienteOPCActionPerformed
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="OCompra">

    private void txtlotecompraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtlotecompraKeyReleased
        this.regresar_color(txtlotecompra);
    }//GEN-LAST:event_txtlotecompraKeyReleased

    private void txtfactnoCOMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfactnoCOMKeyReleased
        this.regresar_color(txtfactnoCOM);
    }//GEN-LAST:event_txtfactnoCOMKeyReleased

    private void txtcantidadcompraCOMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcantidadcompraCOMKeyReleased
        this.regresar_color(txtcantidadcompraCOM);
    }//GEN-LAST:event_txtcantidadcompraCOMKeyReleased

    private void txtcapacidadcompraCOMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcapacidadcompraCOMKeyReleased
        this.regresar_color(txtcapacidadcompraCOM);
    }//GEN-LAST:event_txtcapacidadcompraCOMKeyReleased
    
    private void btncancelarCOMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarCOMActionPerformed
        try {
            OrdenPedido_Provedores_DB opp = new OrdenPedido_Provedores_DB(this.con);
            String[] id = this.cboppCOM.getSelectedItem().toString().split(" ");
            if (JOptionPane.showConfirmDialog(null, "¿Desea Cancelar la Orden?", "Cancelacion", JOptionPane.YES_NO_OPTION)==0) {
                opp.delete_opp(id[0]);
                combo();
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
        
    }//GEN-LAST:event_btncancelarCOMActionPerformed
    
    private boolean valida_vacio_compra()
    {
        boolean valida=true;
        if (this.txtlotecompra.getText().isEmpty()) {
            valida = false;
            this.txtlotecompra.setBackground(Color.decode("#FFCCCC"));
        }
        if (this.txtfactnoCOM.getText().isEmpty()) {
            valida = false;
            this.txtfactnoCOM.setBackground(Color.decode("#FFCCCC"));
        }
        if (this.txtcantidadcompraCOM.getText().isEmpty()) {
            valida = false;
            this.txtcantidadcompraCOM.setBackground(Color.decode("#FFCCCC"));
        }
        if (this.txtcapacidadcompraCOM.getText().isEmpty()) {
            valida = false;
            this.txtcapacidadcompraCOM.setBackground(Color.decode("#FFCCCC"));
        }
     
        return valida;
    }
    
    private boolean valida_formato_compra()
    {
        String error="";
        boolean valida=true;
        if (!this.txtcantidadcompraCOM.getText().matches("^([0-9]+)(\\.[0-9]+)?$")) {
            valida = false;
            this.txtcantidadcompraCOM.setBackground(Color.decode("#FFCCCC"));
            error+="Cantidad : 3.65 ó 57";
        }
        if (!this.txtcapacidadcompraCOM.getText().matches("^([0-9]+)(\\.[0-9]+)?$")) {
            valida = false;
            this.txtcapacidadcompraCOM.setBackground(Color.decode("#FFCCCC"));
            error+="Capacidad : 3.65 ó 57";
        }
        if (!valida) {
            JOptionPane.showMessageDialog(null, "Formato Incorrecto:\n"+error);
        }
        return valida;
    }
    
    //compra
    private void cboppCOMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboppCOMActionPerformed
        try{
            this.cbproductosCOM.removeAllItems();
            String[] id = this.cboppCOM.getSelectedItem().toString().split(" ");
            OrdenPedido_Provedores_DB db = new OrdenPedido_Provedores_DB(this.con);
            List <Producto> op = db.select_detopp(id[0]);
            this.tabla_Compra1.setRowCount(0);
            for (int i = 0; i < op.size(); i++) {
                Object[] obj = new Object[5];
                obj[0]=op.get(i).getId();
                obj[1]=op.get(i).getNombre();
                obj[2]=op.get(i).getClave();
                obj[3]=op.get(i).getPeso();
                obj[4]=op.get(i).getPventa();
                
                this.tabla_Compra1.addRow(obj);
                this.cbproductosCOM.addItem(op.get(i).getNombre());

            }
            
            this.idmoneda2=db.select_idmoneda_opp(id[0]);

            Proveedores  pro = db.select_provedor(id[0]);
            
            this.txtidproveedorCOM.setText(pro.getId()+"");
            this.txtnombreproveedorCOM.setText(pro.getNombre());
            this.txtfechapedidocompra.setText(pro.getCalle());
            this.txtfechapedidocompra.setText(this.fechadividir(txtfechapedidocompra, 0));
            
            
            Date date = new Date();
            this.jdfecha_entradacompra.setDateFormatString("dd/MM/yyyy");
            this.jdfecha_entradacompra.setDate(date);

            this.jdfecha_entradacompra.setEnabled(true);

            this.txtlotecompra.setEnabled(true);
            this.txtfactnoCOM.setEnabled(true);

            this.cbproductosCOM.setEnabled(true);
            this.txtcantidadcompraCOM.setEnabled(true);
            this.txtcapacidadcompraCOM.setEnabled(true);
            
            this.jdfecha_caducidadcompra.setEnabled(true);
            this.jdfecha_caducidadcompra.setDateFormatString("dd/MM/yyyy");
            this.jdfecha_caducidadcompra.setDate(date);
            
            this.btnAgregarproCOM.setEnabled(true);
            this.btnquitarproCOM.setEnabled(false);
            this.btnaceptarCOM.setEnabled(false);
            
            this.txtlotecompra.setText("");
            this.txtfactnoCOM.setText("");
            this.txtcantidadcompraCOM.setText("");
            this.txtcapacidadcompraCOM.setText("");
            
            this.tabla_Compra2.setRowCount(0);
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
            
        } 

    }//GEN-LAST:event_cboppCOMActionPerformed
    
    //compra
    private void btnaceptarCOMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarCOMActionPerformed
        try{
            boolean escero=true; 
            
            String noentrada="";
            
            for (int i = 0; i < this.tabla_Compra1.getRowCount(); i++) {
                double total=Double.parseDouble(this.tabla_Compra1.getValueAt(i, 3).toString());
                if (total != 0 ) {
                    escero=false;
                }
            }

            if (escero) {
                String  query ="Insert into inventario(idproducto,fechaentrada,cantidadactual,lote,idopp,facturano,cantidad,costo,fechacaducidad,idmoneda) values (?,?,?,?,?,?,?,?,?,?)";
                for (int i = 0; i < this.tabla_Compra2.getRowCount(); i++) {
                    PreparedStatement ps= this.dbc.getCnx().prepareStatement(query);
                    ps.setInt(1, Integer.parseInt(this.tabla_Compra2.getValueAt(i, 0).toString()));

                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    
                    ps.setString(2, fechadividir(formatter.format(this.jdfecha_entradacompra.getDate()),2));
                    ps.setDouble(3, Double.parseDouble(this.tabla_Compra2.getValueAt(i, 2).toString()));
                    ps.setString(4, this.tabla_Compra2.getValueAt(i, 3).toString());

                    String[] id = this.cboppCOM.getSelectedItem().toString().split(" ");
                    ps.setInt(5, Integer.parseInt(id[0]));
                    ps.setString(6, this.txtfactnoCOM.getText());
                    ps.setDouble(7, Double.parseDouble(this.tabla_Compra2.getValueAt(i, 2).toString()));
                    ps.setDouble(8, Double.parseDouble(this.tabla_Compra2.getValueAt(i, 4).toString()));
                    ps.setString(9, fechadividir(this.tabla_Compra2.getValueAt(i, 5).toString(),2));
                    //dinero
                    ps.setInt(10,this.idmoneda2);
                    ps.executeUpdate();
                    ps.close();
                    
                    noentrada+="El producto "+this.tabla_Compra2.getValueAt(i, 1).toString()+ " tiene el numero de entrada "+this.dbc.seleccionarid("select max(id) from inventario")+"\n";
                    

                    //quitar datos habilitar y deshabilitar
                }
                
                JOptionPane.showMessageDialog(null, "No entrada: \n\n"+noentrada);
                String[] id = this.cboppCOM.getSelectedItem().toString().split(" ");
                query="update opproveedores set idestatus = 2 where id = "+id[0];
                PreparedStatement ps= this.dbc.getCnx().prepareStatement(query);
                ps.executeUpdate();
                ps.close();

                //limpiar
                this.txtlotecompra.setText("");
                this.txtfactnoCOM.setText("");
                this.txtcantidadcompraCOM.setText("");
                this.txtcapacidadcompraCOM.setText("");
                
                this.txtfechapedidocompra.setText("");
                this.txtidproveedorCOM.setText("");
                this.txtnombreproveedorCOM.setText("");
                this.cbproductosCOM.removeAllItems();
                this.tabla_Compra1.setRowCount(0);
                this.tabla_Compra2.setRowCount(0);
                
                //deshabilitar
                this.txtfechapedidocompra.setEnabled(false);
                this.txtlotecompra.setEnabled(false);
                this.txtfactnoCOM.setEnabled(false);
                this.txtcantidadcompraCOM.setEnabled(false);
                this.txtcapacidadcompraCOM.setEnabled(false);
                this.jdfecha_entradacompra.setEnabled(false);
                this.jdfecha_caducidadcompra.setEnabled(false);
                this.btnAgregarproCOM.setEnabled(false);
                this.btnquitarproCOM.setEnabled(false);
                this.btnaceptarCOM.setEnabled(false);
                this.tabla_Compra1.setRowCount(0);
                this.tabla_Compra2.setRowCount(0);
                
                        
                        
                this.combo();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Falta producto por agregar, revise la tabla de la derecha");
            }

        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexion, intente otra vez");
            try {
                this.dbc = new DBcontrolador ();
            } catch (SQLException ex1) {
                Logger.getLogger(Movimientos.class.getName()).log(Level.SEVERE, null, ex1);
            }
           this.con=this.dbc.getCnx();
           
        }
    }//GEN-LAST:event_btnaceptarCOMActionPerformed
   
    //compra
    private void btnquitarproCOMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnquitarproCOMActionPerformed
        int fila = this.tbproductosCOM2.getSelectedRow();
        if (fila >= 0) {
            String nombre = this.tabla_Compra2.getValueAt(fila, 1).toString();
            double cantidad =Double.parseDouble(this.tabla_Compra2.getValueAt(fila, 2).toString());

            for (int i = 0; i < this.tabla_Compra1.getRowCount(); i++) {
                if(this.tabla_Compra1.getValueAt(i, 1).toString().compareTo(nombre)== 0)
                {
                    double c= Double.parseDouble(this.tabla_Compra1.getValueAt(i, 3).toString())+cantidad;
                    this.tabla_Compra1.setValueAt(c, i, 3);
                }
            }

            this.tabla_Compra2.removeRow(fila);
            if (this.tabla_Compra2.getRowCount()==0) {
                this.btnquitarproCOM.setEnabled(false);
                this.btnaceptarCOM.setEnabled(false);
            }
        }

    }//GEN-LAST:event_btnquitarproCOMActionPerformed

    //compra
    private void btnAgregarproCOMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarproCOMActionPerformed

        if (valida_vacio_compra()) {
            
            if (valida_formato_compra())  {
                double cantidadtotal=0;
                double punitario=0;
                int idproducto=0;
                int index=0;
                int cantidad = Integer.parseInt(this.txtcantidadcompraCOM.getText());
                double capacidad = Double.parseDouble(this.txtcapacidadcompraCOM.getText());
                String producto = this.cbproductosCOM.getSelectedItem().toString();
                for (int i = 0; i < this.tabla_Compra1.getRowCount(); i++) {
                    if(this.tabla_Compra1.getValueAt(i, 1).toString().compareTo(producto)== 0)
                    {
                        idproducto=Integer.parseInt(this.tabla_Compra1.getValueAt(i, 0).toString());
                        cantidadtotal=Double.parseDouble(this.tabla_Compra1.getValueAt(i, 3).toString());
                        punitario=Double.parseDouble(this.tabla_Compra1.getValueAt(i, 4).toString());
                        index=i;

                    }
                }

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                if (cantidadtotal >=  cantidad*capacidad ) {
                    for (int i = 0; i < cantidad; i++) {
                        String[] c = new    String[6];
                        c[0]=idproducto+"";
                        c[1]=producto;
                        c[2]=capacidad+"";
                        c[3]=this.txtlotecompra.getText();
                        c[4]= (punitario * capacidad) +"";
                        c[5]= formatter.format(this.jdfecha_caducidadcompra.getDate());
                        this.tabla_Compra2.addRow(c);
                    }
                    this.tabla_Compra1.setValueAt(cantidadtotal -  cantidad*capacidad, index, 3);
                }

                this.btnaceptarCOM.setEnabled(true);
                this.btnquitarproCOM.setEnabled(true);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Campos vacios");
        }

    }//GEN-LAST:event_btnAgregarproCOMActionPerformed


//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="OPProveedores">
    
    public void colocarproveedor(Proveedores pr) 
    {   
        try{
            this.txtidproveedorOPP.setText(pr.getId() + "");
            this.txtnombreproveedorOPP.setText(pr.getNombre());
            this.correo=pr.getCorreo();
            this.jdfechaopp.setDateFormatString("dd/MM/yyyy");
            Date date =new Date();
            this.jdfechaopp.setDate(date);
            this.jdfechaopp.setEnabled(true);

            OrdenPedido_Provedores_DB db = new OrdenPedido_Provedores_DB(this.con);
            int maxid= db.select_max();
            if(maxid == 0){
               maxid=1;
            }
            this.txtidOPP.setText(maxid + "");

            this.btnAgregarproOPP.setEnabled(true);
            this.cbcondicion_pagoOPP.setEnabled(true);
            
            if (this.tabla_OPP.getRowCount()==0) {
                this.btnquitarproOPP.setEnabled(false);
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
    }
    
    public void colocarproducto(Producto pro)
    {
        
        String[] s = new String[10]; 
        s[1]=pro.getClave();
        s[0]=pro.getId()+"";
        s[2]=pro.getNombre();
        s[3]=pro.getMedida();
        s[4]=pro.getStockmin()+"";
        s[5]=pro.getMoneda();
        s[6]=pro.getPventa()+"";      
        s[7]=(Math.round( (pro.getPventa()*pro.getStockmin())* 100.0 ) / 100.0)+"";
        if (pro.getIva()==1) {
            s[8]=(Math.round( (pro.getPventa()*pro.getStockmin()*0.16)* 100.0 ) / 100.0)+"";
        }
        else
        {
            s[8]="0";
        }
        s[9]=(Math.round( (Double.parseDouble(s[7])+Double.parseDouble(s[8]))* 100.0 ) / 100.0)+"";
        
        boolean x=true;
        for (int i = 0; i < this.tabla_OPP.getRowCount(); i++) {
            int id = Integer.parseInt(this.tabla_OPP.getValueAt(i, 0).toString());
            if (id == Integer.parseInt(s[0])) {
                x=false;
            }            
        }
        if (x) {
            this.tabla_OPP.addRow(s);   
            this.btnquitarproOPP.setEnabled(true);
            this.btnaceptaropp.setEnabled(true);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "El producto ya esta seleccionado");
        }

    }
    
    private void btnaceptaroppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptaroppActionPerformed
        try{
            String respuesta = JOptionPane.showInputDialog(this, "Observaciones");
            if (this.tbproductosopp.getRowCount() > 0 && respuesta != null) {
                OrdenPedido_Provedores_DB db = new OrdenPedido_Provedores_DB(this.con);
                OrdenPedido_Provedores opp = new OrdenPedido_Provedores();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                
                double subtotal=0;
                double iva=0;
                double total=0;
                for (int i = 0; i < this.tabla_OPP.getRowCount(); i++) {
                    Producto pro = new Producto();
                    pro.setId(Integer.parseInt(this.tabla_OPP.getValueAt(i, 0).toString()));
                    pro.setPventa(Double.parseDouble(this.tabla_OPP.getValueAt(i, 6).toString()));
                    //PESO -> TOTAL
                    pro.setPeso(Double.parseDouble(this.tabla_OPP.getValueAt(i, 7).toString()));
                    //STOCK -> CANTIDAD
                    pro.setStockmin(Double.parseDouble(this.tabla_OPP.getValueAt(i, 4).toString()));
                    opp.getProductos().add(pro);
                    
                    subtotal+=Double.parseDouble(this.tabla_OPP.getValueAt(i, 7).toString());
                    iva+=Double.parseDouble(this.tabla_OPP.getValueAt(i, 8).toString());
                    total+=Double.parseDouble(this.tabla_OPP.getValueAt(i, 9).toString());
                }
 
                opp.setIdproveedor(Integer.parseInt(this.txtidproveedorOPP.getText()));
                opp.setIdmoneda(idmoneda);
                opp.setSubtotal(Math.round( subtotal * 100.0 ) / 100.0);
                opp.setIva(Math.round( iva * 100.0 ) / 100.0);
                opp.setPtotal(Math.round( total * 100.0 ) / 100.0);
                opp.setIdestatus(1);
                opp.setFecha(fechadividir(formatter.format(this.jdfechaopp.getDate()),1));
                opp.setIdcpago(this.cbcondicion_pagoOPP.getSelectedIndex()+1);
                opp.setObservaciones(respuesta);
                
                db.insert_opp(opp);

                //cantidad en letra
                String cantidad= (Math.round( (total) * 100.0 ) / 100.0)+"";
                System.out.println(cantidad);
                String[] divi =cantidad.split("\\.");
                n2t num = new n2t();
                System.out.println(divi[0]);
                String num_text =num.convertirLetras(Integer.parseInt(divi[0]));
                String moneda;
                if (this.idmoneda == 1) {
                    moneda="Moneda Nacional";
                }else
                {
                    moneda="USD";
                }
                num_text+=" "+divi[1]+"/100 "+moneda;
                
                int id=db.select_max();
                
                JasperReport reporte; //Creo el objeto reporte
                // Ubicacion del Reporte
                String path = s+"\\Reportes\\Orden_Compra.jasper";
                try {

                   reporte = (JasperReport) JRLoader.loadObjectFromFile(path); //Cargo el reporte al objeto
                   Map para = new HashMap();
                   para.put("id", id);
                   para.put("letra", num_text.toUpperCase());
                   JasperPrint jprint = JasperFillManager.fillReport(path, para, this.con); //Llenado del Reporte con Tres parametros ubicacion,parametros,conexion a la base de datos
                   JasperExportManager.exportReportToPdfFile(jprint, s+"\\ODC\\ODC-"+id+".pdf");
                   JasperViewer viewer = new JasperViewer(jprint,false); //Creamos la vista del Reporte
                   viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Le agregamos que se cierre solo el reporte cuando lo cierre el usuario
                   viewer.setVisible(true); //Inicializamos la vista del Reporte
                   
                    if (JOptionPane.showConfirmDialog(null, "¿Enviar correo?", "Correo", JOptionPane.YES_NO_OPTION)==0) {
                        Properties props = new Properties();
                        props.setProperty("mail.smtp.host", "smtp.gmail.com");
                        props.setProperty("mail.smtp.starttls.enable", "true");
                        props.setProperty("mail.smtp.port","587");
                        props.setProperty("mail.smtp.user", "bevisagaleria@gmail.com");
                        props.setProperty("mail.smtp.auth", "true");
                        Session session = Session.getDefaultInstance(props);
                        
                        BodyPart texto = new MimeBodyPart();
                        texto.setText("Mando orden de compra,favor de confirmar fecha indicada de entrega ");
                        BodyPart adjunto = new MimeBodyPart();
                        adjunto.setDataHandler(new DataHandler(new FileDataSource(s+"\\ODC\\ODC-"+id+".pdf")));
                        adjunto.setFileName("ODC.pdf");

                        
                        MimeMultipart multiParte = new MimeMultipart();
                        multiParte.addBodyPart(texto);
                        multiParte.addBodyPart(adjunto);

                        MimeMessage message = new MimeMessage(session);

                        message.setFrom(new InternetAddress("bevisagaleria@gmail.com"));
                        message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.correo));

                        message.setSubject("Orden de Compra");
                        message.setContent(multiParte);
                        //t.connect("bevisagaleria@gmail.com","bevisairlandesa");
                        Transport t = session.getTransport("smtp");
                        t.connect("bevisagaleria@gmail.com","bevisairlandesa");
                        t.sendMessage(message,message.getAllRecipients());
                        t.close();
                    }
                }   
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error cargando el reporte "+ex);
                    
                }
                this.tabla_OPP.setRowCount(0);
                this.btnAgregarproOPP.setEnabled(false);
                this.btnquitarproOPP.setEnabled(false);
                this.btnaceptaropp.setEnabled(false);

                this.txtidOPP.setText("");
                this.txtnombreproveedorOPP.setText("");
                this.txtidproveedorOPP.setText("");

                this.preciosopp.clear();
                this.combo();

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
    }//GEN-LAST:event_btnaceptaroppActionPerformed

    private void btnquitarproOPPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnquitarproOPPActionPerformed
        try
        {
            
            int col=this.tbproductosopp.getSelectedRow();
            this.tabla_OPP.removeRow(col);
            
            if (this.tbproductosopp.getRowCount() == 0) {
                this.btnaceptaropp.setEnabled(false);
                this.btnquitarproOPP.setEnabled(false);
            }

        }
        catch(Exception ex)
        {
            
        }

    }//GEN-LAST:event_btnquitarproOPPActionPerformed

    private void btnAgregarproOPPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarproOPPActionPerformed
        try
        {
            Elegir_ProductoOPP productos = new Elegir_ProductoOPP (this,1,this.con);
            this.setEnabled(false);
            productos.setVisible(true);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Error :"+ ex);
        }
    }//GEN-LAST:event_btnAgregarproOPPActionPerformed

    private void btnElegir_proveedorOPPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElegir_proveedorOPPActionPerformed
        try {
            Elegir_ProveedorOPP pro = new Elegir_ProveedorOPP(this,1,this.con);
            pro.setVisible(true);
            this.setEnabled(false);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error :"+ ex);
        }
    }//GEN-LAST:event_btnElegir_proveedorOPPActionPerformed

     private void tbproductosoppMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbproductosoppMouseClicked
        if (this.tabla_OPP.getRowCount()!=0) {
            this.btnquitarproOPP.setEnabled(true);
        }
    }//GEN-LAST:event_tbproductosoppMouseClicked

    //</editor-fold>         

    //<editor-fold defaultstate="collapsed" desc="REPRO">
    
     
     public void colocar_producto_repro(Producto pro)
    {
        Object[] obj = new Object[5];
        obj[0]=pro.getId();
        obj[1]=pro.getClave();
        obj[2]=pro.getNombre();
        obj[3]=pro.getMedida();
        obj[4]=pro.getStockmin();
        
        this.tabla_repro.addRow(obj);
        
        this.btnquitar_repro.setEnabled(true);
        this.btnaceptar_repro.setEnabled(true);
    }

     private void btnbuscar_reproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar_reproActionPerformed
         if (this.txtidodp_repro.getText().matches("^[0-9]+$")) {
            try
            {
                Reproceso_DB db = new Reproceso_DB(this.con);
                String[] dato_odp=db.select_odf(Integer.parseInt(this.txtidodp_repro.getText()));
                if (dato_odp!=null) {
                    this.txtnombre_repro.setText(dato_odp[1]);
                    this.txtcantidad_repro.setText(dato_odp[2]);
                    this.txtidpro_repro.setText(dato_odp[3]);
                            
                    this.btnagregar_repro.setEnabled(true); 
                    this.txtarea_repro.setEnabled(true);
                    
                    Date date = new Date();
                    this.jdreproceso.setDateFormatString("dd/MM/yyyy");
                    this.jdreproceso.setDate(date);
                    this.jdreproceso.setEnabled(true);
                    
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "El lote No existe");
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
             
            
         }
         else
         {
            JOptionPane.showMessageDialog(null, "Formato incorrecto en lote");
         }
         
         
    }//GEN-LAST:event_btnbuscar_reproActionPerformed

    private void btnagregar_reproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregar_reproActionPerformed
        try
        {
            Elegir_ProductoOPP productos = new Elegir_ProductoOPP (this,3,this.con);
            this.setEnabled(false);
            productos.setVisible(true);

        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_btnagregar_reproActionPerformed

    private void btnquitar_reproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnquitar_reproActionPerformed
        try
        {
            this.tabla_repro.removeRow(this.tb_repro.getSelectedRow());
            if (tabla_repro.getRowCount()==0) {
                this.btnquitar_repro.setEnabled(false);
                this.btnaceptar_repro.setEnabled(false);
            }
        }
        catch(Exception ex)
        {
            
        }
    }//GEN-LAST:event_btnquitar_reproActionPerformed

    private void btnaceptar_reproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptar_reproActionPerformed
        
        //validar inventario
        try  
        {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String faltante="";
            boolean sipasa=true;
            Reproceso_DB db = new Reproceso_DB(this.con);
            for (int i = 0; i < this.tabla_repro.getRowCount(); i++) {
                Producto pro=db.select_inventario(fechadividir(formatter.format(this.jdreproceso.getDate()),1), Integer.parseInt(this.tabla_repro.getValueAt(i, 0).toString()));
                
                if (pro== null) {
                        faltante += "Requiere "+this.tb_repro.getValueAt(i, 4).toString()+" de "+this.tb_repro.getValueAt(i, 2).toString()+"\n";
                        sipasa=false;
                        
                }
                else
                {
                    if (pro.getStockmin()< Double.parseDouble(this.tb_repro.getValueAt(i, 4).toString())) {
                    sipasa=false;
                    double falta = Double.parseDouble(this.tb_repro.getValueAt(i, 4).toString())- pro.getStockmin();
                    faltante += "Requiere "+falta+" de "+pro.getNombre() +"\n";
                    }
                }
                
            }
            
            if (sipasa) {
                

                //crearodernproduccion
                Orden_de_Fabricacion odf = new Orden_de_Fabricacion();
                odf.setId(0);
                odf.setIdproducto(Integer.parseInt(this.txtidpro_repro.getText()));
                odf.setFecha(fechadividir(formatter.format(this.jdreproceso.getDate()),1));
                odf.setCantidad(Double.parseDouble(this.txtcantidad_repro.getText()));
                for (int i = 0; i <this.tabla_repro.getRowCount(); i++) {
                    Producto pro = new Producto();
                    pro.setId(Integer.parseInt(this.tabla_repro.getValueAt(i, 0).toString()));
                    pro.setStockmin(Double.parseDouble(this.tabla_repro.getValueAt(i, 4).toString()));
                    odf.getProduc().add(pro);
                }
                
                Reproceso rp = new Reproceso();
                rp.setId_loteant(Integer.parseInt(this.txtidodp_repro.getText()));
                rp.setId_fecha(fechadividir(formatter.format(this.jdreproceso.getDate()),1));
                rp.setObservaciones(this.txtarea_repro.getText());
                System.out.println("aqui");
                int id=db.insert_opf(odf,rp);
                
                //REPORTE
                 JasperReport reporte; //Creo el objeto reporte
                    // Ubicacion del Reporte
                    String path = s+"\\Reportes\\Reproceso.jasper";
               try {

                   reporte = (JasperReport) JRLoader.loadObjectFromFile(path); //Cargo el reporte al objeto
                   Map idodp = new HashMap();
                   idodp.put("ID_RE", id);
                   JasperPrint jprint = JasperFillManager.fillReport(path, idodp, this.dbc.getCnx()); //Llenado del Reporte con Tres parametros ubicacion,parametros,conexion a la base de datos
                   JasperExportManager.exportReportToPdfFile(jprint, s+"\\ODP\\REPROCESO-"+id+".pdf");
                   JasperViewer viewer = new JasperViewer(jprint,false); //Creamos la vista del Reporte
                   viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Le agregamos que se cierre solo el reporte cuando lo cierre el usuario
                   viewer.setVisible(true); //Inicializamos la vista del Reporte

               } catch (Exception ex) {
                   Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
               }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Falta:\n"+faltante);
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
        
        

        
    }//GEN-LAST:event_btnaceptar_reproActionPerformed

    //</editor-fold>

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
            java.util.logging.Logger.getLogger(Movimientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Movimientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Movimientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Movimientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Compra;
    private javax.swing.JPanel LOP;
    private javax.swing.JPanel OPC;
    private javax.swing.JPanel OPP;
    private javax.swing.JPanel REPROCESO;
    private javax.swing.JButton btnAgregarproCOM;
    private javax.swing.JButton btnAgregarproOPC;
    private javax.swing.JButton btnAgregarproOPP;
    private javax.swing.JButton btnElegirClienteOPC;
    private javax.swing.JButton btnElegir_proveedorOPP;
    private javax.swing.JButton btnaceptarCOM;
    private javax.swing.JButton btnaceptarLIB;
    private javax.swing.JButton btnaceptarOPC;
    private javax.swing.JButton btnaceptar_repro;
    private javax.swing.JButton btnaceptaropp;
    private javax.swing.JButton btnagregar_repro;
    private javax.swing.JButton btnbuscar_repro;
    private javax.swing.JButton btncancelarCOM;
    private javax.swing.JButton btnquitar_repro;
    private javax.swing.JButton btnquitarproCOM;
    private javax.swing.JButton btnquitarproOPC;
    private javax.swing.JButton btnquitarproOPP;
    private javax.swing.JComboBox<String> cbcondicion_pagoOPP;
    private javax.swing.JComboBox<String> cbodpLIB;
    private javax.swing.JComboBox<String> cboppCOM;
    private javax.swing.JComboBox<String> cbproductosCOM;
    private com.toedter.calendar.JDateChooser jDfechaopc;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
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
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane2;
    private com.toedter.calendar.JDateChooser jdfechaLIB;
    private com.toedter.calendar.JDateChooser jdfecha_caducidadcompra;
    private com.toedter.calendar.JDateChooser jdfecha_entradacompra;
    private com.toedter.calendar.JDateChooser jdfechaopp;
    private com.toedter.calendar.JDateChooser jdreproceso;
    private javax.swing.JTabbedPane tabpanel;
    private javax.swing.JTable tb_repro;
    private javax.swing.JTable tbliberacionsensoriales;
    private javax.swing.JTable tblieracionfisicoquimicas;
    private javax.swing.JTable tblieracionmicrobiologicas;
    private javax.swing.JTable tbopc;
    private javax.swing.JTable tbproductosCOM;
    private javax.swing.JTable tbproductosCOM2;
    private javax.swing.JTable tbproductosopp;
    private javax.swing.JTextArea txtarea_repro;
    private javax.swing.JTextField txtcantidad_repro;
    private javax.swing.JTextField txtcantidadcompraCOM;
    private javax.swing.JTextField txtcapacidadcompraCOM;
    private javax.swing.JTextArea txtcomentariosLIB;
    private javax.swing.JTextField txtfactnoCOM;
    private javax.swing.JTextField txtfechapedidocompra;
    private javax.swing.JTextField txtidOPP;
    private javax.swing.JTextField txtidclienteopc;
    private javax.swing.JTextField txtidodp_repro;
    private javax.swing.JTextField txtidopc;
    private javax.swing.JTextField txtidpro_repro;
    private javax.swing.JTextField txtidproveedorCOM;
    private javax.swing.JTextField txtidproveedorOPP;
    private javax.swing.JTextField txtlotecompra;
    private javax.swing.JTextField txtnombre_repro;
    private javax.swing.JTextField txtnombreclienteOPC;
    private javax.swing.JTextField txtnombreproveedorCOM;
    private javax.swing.JTextField txtnombreproveedorOPP;
    private javax.swing.JTextField txtproductoidliberacion;
    private javax.swing.JTextField txtproductoliberacion;
    // End of variables declaration//GEN-END:variables
}
