
package sistema;

import datos.Conexion;
import negocio.Producto;
import datos.DBcontrolador;
import datos.Ingredientes_DB;
import datos.Productos_DB;
import datos.Pruebas_DB;
import java.awt.Dimension;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import negocio.Ingrediente;
import negocio.Prueba;

/**
 *
 * @author JAMS
 * 
 * 
 */
public class Datos extends javax.swing.JFrame {
    
    //produtos
    private DefaultTableModel tabla_productos;
    //ingredientes
    private DefaultTableModel tabla_ingredientes;
    //prueba
    private DefaultTableModel tabla_pruebas;  
    //vendedores
    private DefaultTableModel tabla_vendedores;
    
    //clientes
    private DefaultTableModel tabla4;
    //proveedores
    private DefaultTableModel tabla5;
    //inventario
    private DefaultTableModel tabla_inventario;
    
    

    private int columnaingrediente;
    private int columnavendedor;
    private int columnacliente;
    private int columnaproveedor;
    private int columnainventario;
    private int columnaprueba;
    
    //para ver si es agregar o modificar
    private int seleccionproducto;
    //para ver si es agregar o modificar
    private int seleccionvendedor;
    //para ver si es agregar o modificar
    private int seleccionclientes;
    //para ver si es agregar o modificar
    private int seleccionproveedor;

    private Menu_Principal mp;
    
    
    private  DBcontrolador dbc;
    private Connection con;

    /**
     *
     * @param mp 
     * @param con
     */
    public Datos(Menu_Principal mp, Connection con) {
        
        try{
            initComponents();
      
            this.mp=mp;
            this.con=con;
            if (this.con.isClosed() || this.con.isReadOnly()) {
                this.con.close();
                this.con=Conexion.getConnection();
            }
            
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
            this.dbc = new DBcontrolador();
            this.tablaproductos();
          
            creaciontablavendedores();
            creaciontablaclientes();
            creaciontablaproveedor(); 
            creaciontablainventario();  
            combobox();
            
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Error "+ ex );
        }
        
    }
    
    
    //tablas de cada parte
    //necesario separar debido a que se llaman despues por separado

    public void creaciontablavendedores()
    {

        this.tabla_vendedores=(DefaultTableModel) this.tbvendedores.getModel();
        
        this.tabla_vendedores.setRowCount(0);

        ArrayList <String[]> op = new ArrayList<>();
        
        String query="select vendedores.id , vendedores.nombre, vendedores.telefono, vendedores.correo,\n" +
        " delegacion.nombre, vendedores.colonia, vendedores.calle,vendedores.noint,vendedores.noext\n" +
        " from vendedores join delegacion on vendedores.iddelegacion = delegacion.id";
        op=this.dbc.seleccionar(query);
 
        for (int i = 0; i < op.size(); i++) {    
            
            this.tabla_vendedores.addRow(op.get(i));
        } 
        
    }
    
    public void creaciontablaclientes()
    {
        this.tabla4=(DefaultTableModel) this.tbclientes.getModel();
        
        this.tabla4.setRowCount(0);

        ArrayList <String[]> op = new ArrayList<>();
        
        String query="select clientes.id , clientes.nombre, clientes.telefono, clientes.celular, clientes.correo,\n" +
        " delegacion.nombre, clientes.colonia, clientes.calle, clientes.noint, clientes.noext, clientes.rfc, modo_pago.nombre ,"
        + " clientes.cuenta, clientes.contacto, clientes.estatus, clientes.nombrefact,clientes.coloniafact, clientes.callefact, clientes.nointfact, clientes.noextfact, clientes.delefact \n" +
        " from clientes join delegacion on clientes.iddelegacion = delegacion.id join modo_pago on clientes.idmpago = modo_pago.id";
        op=this.dbc.seleccionar(query);
 
        for (int i = 0; i < op.size(); i++) {    
            
            this.tabla4.addRow(op.get(i));
        } 
    }
    
    public void creaciontablaproveedor()
    {
        this.tabla5=(DefaultTableModel) this.tbproveedor.getModel();
        
        this.tabla5.setRowCount(0);

        ArrayList <String[]> op = new ArrayList<>();
        
        String query="select proveedores.id , proveedores.nombre, proveedores.telefono, proveedores.correo,\n" +
        " proveedores.cp, proveedores.colonia, proveedores.calle, proveedores.noint, proveedores.noext, proveedores.rfc, modo_pago.nombre ,"
        + " proveedores.cuenta, proveedores.contacto, proveedores.sistema_calidad \n" +
        " from proveedores join modo_pago on proveedores.idmpago = modo_pago.id";
        op=this.dbc.seleccionar(query);
 
        for (int i = 0; i < op.size(); i++) {    
            
            this.tabla5.addRow(op.get(i));
        } 
    }
    
    
   
    
    //llenado de combo (se junta mas de una pestaña)
    public void combobox()
    {
        String query="select * from categoria;";
        ArrayList <String[]> op = new ArrayList<>();
        op=this.dbc.seleccionar(query);
        for (int i = 0; i < op.size(); i++) {
            this.cbcategoriaproducto.addItem(op.get(i)[1]);
        }
        
        query="select * from umedida;";
        op.clear();
        op=this.dbc.seleccionar(query);
        for (int i = 0; i < op.size(); i++) {
            this.cbmedidaproducto.addItem(op.get(i)[1]);
        }

        query="select * from procesos;";
        op.clear();
        op=this.dbc.seleccionar(query);
        for (int i = 0; i < op.size(); i++) {
            this.cbprocesoproducto.addItem(op.get(i)[1]);
        }
        
        query="select * from delegacion;";
        op.clear();
        op=this.dbc.seleccionar(query);
        for (int i = 0; i < op.size(); i++) {
            this.cbdelegacion.addItem(op.get(i)[1]);
        }
        
        query="select * from delegacion;";
        op.clear();
        op=this.dbc.seleccionar(query);
        for (int i = 0; i < op.size(); i++) {
            this.cbdelegacion1.addItem(op.get(i)[1]);
            this.cbdelegacion2.addItem(op.get(i)[1]);  
        }
        op.clear();
        query="select * from modo_pago";
        op=this.dbc.seleccionar(query);
        for (int i = 0; i < op.size(); i++) {
            this.cbmodopago.addItem(op.get(i)[1]);
            this.cbmodopago1.addItem(op.get(i)[1]);
        }
        
        op.clear();
        query="select * from categoria_prueba";
        op=this.dbc.seleccionar(query);
        for (int i = 0; i < op.size(); i++) {
            this.cbcategoriaprueba.addItem(op.get(i)[1]);
        }
        
        op.clear();
        query="select id,nombre from vendedores";
        op=this.dbc.seleccionar(query);
        for (int i = 0; i < op.size(); i++) {
            this.cbvendedor.addItem(op.get(i)[1]);
        }
        
        
    }

      
      

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        Productos = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbproductos = new javax.swing.JTable();
        btnAgregar_producto = new javax.swing.JButton();
        btnCambiar_producto = new javax.swing.JButton();
        btnGuardar_producto = new javax.swing.JButton();
        btnCancelar_producto = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtidproducto = new javax.swing.JTextField();
        txtbuscar_producto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtclaveproducto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtnombreproducto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbcategoriaproducto = new javax.swing.JComboBox<>();
        cbmedidaproducto = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtsminproducto = new javax.swing.JTextField();
        lblsmin = new javax.swing.JLabel();
        lblpventa = new javax.swing.JLabel();
        txtpventaproducto = new javax.swing.JTextField();
        txtcaducidadproducto = new javax.swing.JTextField();
        lblcaducidad = new javax.swing.JLabel();
        lblproceso = new javax.swing.JLabel();
        cbprocesoproducto = new javax.swing.JComboBox<>();
        chivaproducto = new javax.swing.JCheckBox();
        lbliva = new javax.swing.JLabel();
        txtpeso_producto = new javax.swing.JTextField();
        lblpeso = new javax.swing.JLabel();
        Ingredientes = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtidingrediente = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtclaveingrediente = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtnombreingrediente = new javax.swing.JTextField();
        btnElegir_ingrediente = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbingredientes = new javax.swing.JTable();
        btnAgregaringre = new javax.swing.JButton();
        btnquitaringre = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        Pruebas = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        txtidprueba = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        txtclaveprueba = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        txtnombreprueba = new javax.swing.JTextField();
        btnElegirprueba = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbpruebas = new javax.swing.JTable();
        btnAgregarprueba = new javax.swing.JButton();
        btnquitarprueba = new javax.swing.JButton();
        jLabel63 = new javax.swing.JLabel();
        cbcategoriaprueba = new javax.swing.JComboBox<>();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        txtdeterminacion = new javax.swing.JTextField();
        txtparametro = new javax.swing.JTextField();
        txtmetodo = new javax.swing.JTextField();
        Inventario = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbinventario = new javax.swing.JTable();
        btnCambiarinventario = new javax.swing.JButton();
        btnGuardarinventario = new javax.swing.JButton();
        btnCancelarinventario = new javax.swing.JButton();
        jLabel81 = new javax.swing.JLabel();
        txtbuscarinventario = new javax.swing.JTextField();
        txtnoentradainventario = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        txtclaveinventario = new javax.swing.JTextField();
        txtnombreinventario = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        txtfechainventario = new javax.swing.JTextField();
        txtcantidadinventario = new javax.swing.JTextField();
        jLabel86 = new javax.swing.JLabel();
        txtloteinventario = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        txtidoppinventario = new javax.swing.JTextField();
        jLabel88 = new javax.swing.JLabel();
        txtfactinventario = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        txtcantidadtinventario = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        txtfechainventario1 = new javax.swing.JTextField();
        Vendedores = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbvendedores = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        txtbuscarvendedores = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtidvendedor = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txttelefonovendedor = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        cbdelegacion = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        txtnombrevendedor = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtcoloniavendedor = new javax.swing.JTextField();
        txtcorreovendedor = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtcallevendedor = new javax.swing.JTextField();
        txtnointvendedor = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        btnAgregarvendedor = new javax.swing.JButton();
        btnCambiarvendedor = new javax.swing.JButton();
        btnGuardarvendedor = new javax.swing.JButton();
        btnCancelarvendedor = new javax.swing.JButton();
        txtnoextvendedor = new javax.swing.JTextField();
        Proovedores = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbproveedor = new javax.swing.JTable();
        jLabel46 = new javax.swing.JLabel();
        txtbuscarproveedor = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        txtidproveedor = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        txttelefonoproveedor = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        txtnombreproveedor = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        txtcoloniaproveedor = new javax.swing.JTextField();
        txtcorreoproveedor = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        txtcalleproveedor = new javax.swing.JTextField();
        txtnointproveedor = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        btnAgregarproveedor = new javax.swing.JButton();
        btnCambiarproveedor = new javax.swing.JButton();
        btnGuardarproveedor = new javax.swing.JButton();
        btnCancelarproveedor = new javax.swing.JButton();
        txtnoextproveedor = new javax.swing.JTextField();
        txtrfcproveedor = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        cbmodopago1 = new javax.swing.JComboBox<>();
        txtcuentaproveedor = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        txtcontactoproveedor = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        txtsistemacalidad = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        txtcpproveedor = new javax.swing.JTextField();
        Clientes = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbclientes = new javax.swing.JTable();
        jLabel30 = new javax.swing.JLabel();
        txtbuscarclientes = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtidclientes = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txttelefonoclientes = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        cbdelegacion1 = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        txtnombreclientes = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtcoloniaclientes = new javax.swing.JTextField();
        txtcorreoclientes = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtcalleclientes = new javax.swing.JTextField();
        txtnointclientes = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        btnAgregarcliente = new javax.swing.JButton();
        btnCambiarcliente = new javax.swing.JButton();
        btnGuardarcliente = new javax.swing.JButton();
        btnCancelarcliente = new javax.swing.JButton();
        txtnoextclientes = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        cbmodopago = new javax.swing.JComboBox<>();
        txtcuentaclientes = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        txtcontactoclientes = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        chestatusclientes = new javax.swing.JCheckBox();
        jLabel67 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        txtnombreclientes1 = new javax.swing.JTextField();
        txtrfcclientes = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        txtcoloniaclientes1 = new javax.swing.JTextField();
        txtcalleclientes1 = new javax.swing.JTextField();
        txtnointclientes1 = new javax.swing.JTextField();
        txtnoextclientes1 = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        cbdelegacion2 = new javax.swing.JComboBox<>();
        jLabel40 = new javax.swing.JLabel();
        txtcelularclientes = new javax.swing.JTextField();
        cbvendedor = new javax.swing.JComboBox<>();
        jLabel77 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Datos");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(162, 127, 51));

        jTabbedPane2.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        Productos.setBackground(new java.awt.Color(255, 255, 255));
        Productos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setText("Productos");
        Productos.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, 39));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tbproductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Clave", "Nombre", "Categoria", "U. Medida", "P. Venta", "Stock min", "IVA", "Proceso", "Caducidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbproductos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbproductos.setColumnSelectionAllowed(true);
        tbproductos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbproductos.setShowHorizontalLines(false);
        tbproductos.setShowVerticalLines(false);
        tbproductos.getTableHeader().setReorderingAllowed(false);
        tbproductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbproductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbproductos);
        tbproductos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (tbproductos.getColumnModel().getColumnCount() > 0) {
            tbproductos.getColumnModel().getColumn(0).setPreferredWidth(25);
            tbproductos.getColumnModel().getColumn(1).setPreferredWidth(100);
            tbproductos.getColumnModel().getColumn(2).setPreferredWidth(250);
            tbproductos.getColumnModel().getColumn(3).setPreferredWidth(100);
            tbproductos.getColumnModel().getColumn(4).setPreferredWidth(50);
        }

        Productos.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 50, 600, 400));

        btnAgregar_producto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAgregar_producto.setText("Agregar");
        btnAgregar_producto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregar_productoMouseClicked(evt);
            }
        });
        Productos.add(btnAgregar_producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 470, -1, -1));

        btnCambiar_producto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCambiar_producto.setText("Cambiar");
        btnCambiar_producto.setEnabled(false);
        btnCambiar_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiar_productoActionPerformed(evt);
            }
        });
        Productos.add(btnCambiar_producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 470, -1, -1));

        btnGuardar_producto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnGuardar_producto.setText("Guardar");
        btnGuardar_producto.setEnabled(false);
        btnGuardar_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar_productoActionPerformed(evt);
            }
        });
        Productos.add(btnGuardar_producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 470, -1, -1));

        btnCancelar_producto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCancelar_producto.setText("Cancelar");
        btnCancelar_producto.setEnabled(false);
        btnCancelar_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar_productoActionPerformed(evt);
            }
        });
        Productos.add(btnCancelar_producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 470, -1, -1));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Buscar  :");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("ID Producto :");

        txtidproducto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtidproducto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtidproducto.setEnabled(false);
        txtidproducto.setNextFocusableComponent(txtclaveproducto);

        txtbuscar_producto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtbuscar_producto.setFocusCycleRoot(true);
        txtbuscar_producto.setNextFocusableComponent(txtidproducto);
        txtbuscar_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscar_productoKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Clave :");

        txtclaveproducto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtclaveproducto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtclaveproducto.setEnabled(false);
        txtclaveproducto.setNextFocusableComponent(txtnombreproducto);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Nombre Producto :");

        txtnombreproducto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtnombreproducto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnombreproducto.setEnabled(false);
        txtnombreproducto.setNextFocusableComponent(cbcategoriaproducto);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Categoria :");

        cbcategoriaproducto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbcategoriaproducto.setEnabled(false);
        cbcategoriaproducto.setNextFocusableComponent(cbmedidaproducto);
        cbcategoriaproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbcategoriaproductoActionPerformed(evt);
            }
        });

        cbmedidaproducto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbmedidaproducto.setEnabled(false);
        cbmedidaproducto.setNextFocusableComponent(txtsminproducto);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("U. Medida :");

        txtsminproducto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtsminproducto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtsminproducto.setEnabled(false);
        txtsminproducto.setNextFocusableComponent(txtpventaproducto);

        lblsmin.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblsmin.setText("Stock Min :");

        lblpventa.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblpventa.setText("Precio Venta :");

        txtpventaproducto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtpventaproducto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtpventaproducto.setEnabled(false);
        txtpventaproducto.setNextFocusableComponent(cbprocesoproducto);

        txtcaducidadproducto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtcaducidadproducto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtcaducidadproducto.setEnabled(false);
        txtcaducidadproducto.setNextFocusableComponent(chivaproducto);

        lblcaducidad.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblcaducidad.setText("Caducidad :");

        lblproceso.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblproceso.setText("Proceso :");

        cbprocesoproducto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbprocesoproducto.setEnabled(false);

        chivaproducto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        chivaproducto.setEnabled(false);

        lbliva.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbliva.setText("IVA :");
        lbliva.setToolTipText("");

        txtpeso_producto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtpeso_producto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtpeso_producto.setEnabled(false);

        lblpeso.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblpeso.setText("Peso:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblproceso)
                    .addComponent(lblcaducidad)
                    .addComponent(lblpventa)
                    .addComponent(lblsmin)
                    .addComponent(jLabel7)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3)
                    .addComponent(lblpeso)
                    .addComponent(lbliva))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chivaproducto)
                    .addComponent(txtidproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtclaveproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbcategoriaproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtbuscar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbmedidaproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsminproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpventaproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcaducidadproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbprocesoproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnombreproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpeso_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 32, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtbuscar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtidproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtclaveproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnombreproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbcategoriaproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbmedidaproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblsmin, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsminproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblpventa, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpventaproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblcaducidad, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcaducidadproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblproceso, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbprocesoproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtpeso_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblpeso, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chivaproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbliva))
                .addContainerGap(93, Short.MAX_VALUE))
        );

        txtbuscar_producto.getAccessibleContext().setAccessibleName("");

        Productos.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 30, 460, 420));

        jTabbedPane2.addTab("Productos", Productos);

        Ingredientes.setBackground(new java.awt.Color(255, 255, 255));
        Ingredientes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel15.setText("ID Producto :");
        Ingredientes.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 48, -1, -1));

        txtidingrediente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtidingrediente.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtidingrediente.setEnabled(false);
        Ingredientes.add(txtidingrediente, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 45, 211, -1));

        jLabel16.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel16.setText("Clave :");
        Ingredientes.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 77, -1, -1));

        txtclaveingrediente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtclaveingrediente.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtclaveingrediente.setEnabled(false);
        Ingredientes.add(txtclaveingrediente, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 74, 213, -1));

        jLabel17.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel17.setText("Nombre Producto :");
        Ingredientes.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 106, -1, -1));

        txtnombreingrediente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtnombreingrediente.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnombreingrediente.setEnabled(false);
        Ingredientes.add(txtnombreingrediente, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 103, 450, -1));

        btnElegir_ingrediente.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnElegir_ingrediente.setText("Elegir");
        btnElegir_ingrediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnElegir_ingredienteActionPerformed(evt);
            }
        });
        Ingredientes.add(btnElegir_ingrediente, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 40, 120, -1));

        tbingredientes.setModel(new javax.swing.table.DefaultTableModel(
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
        tbingredientes.getTableHeader().setReorderingAllowed(false);
        tbingredientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbingredientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbingredientes);
        if (tbingredientes.getColumnModel().getColumnCount() > 0) {
            tbingredientes.getColumnModel().getColumn(0).setPreferredWidth(35);
            tbingredientes.getColumnModel().getColumn(0).setMaxWidth(70);
            tbingredientes.getColumnModel().getColumn(1).setPreferredWidth(100);
            tbingredientes.getColumnModel().getColumn(1).setMaxWidth(150);
            tbingredientes.getColumnModel().getColumn(2).setPreferredWidth(250);
            tbingredientes.getColumnModel().getColumn(3).setPreferredWidth(100);
            tbingredientes.getColumnModel().getColumn(3).setMaxWidth(100);
            tbingredientes.getColumnModel().getColumn(4).setPreferredWidth(100);
            tbingredientes.getColumnModel().getColumn(4).setMaxWidth(150);
        }

        Ingredientes.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 1070, 290));

        btnAgregaringre.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAgregaringre.setText("Agregar ingrediente");
        btnAgregaringre.setEnabled(false);
        btnAgregaringre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregaringreActionPerformed(evt);
            }
        });
        Ingredientes.add(btnAgregaringre, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 460, 240, 40));

        btnquitaringre.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnquitaringre.setText("Quitar ingrediente");
        btnquitaringre.setEnabled(false);
        btnquitaringre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnquitaringreActionPerformed(evt);
            }
        });
        Ingredientes.add(btnquitaringre, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 460, 220, 40));

        jLabel14.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel14.setText("Ingredientes");
        Ingredientes.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, -1, 39));

        jTabbedPane2.addTab("Ingredientes", Ingredientes);

        Pruebas.setBackground(new java.awt.Color(255, 255, 255));
        Pruebas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel60.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel60.setText("ID Producto :");
        Pruebas.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 50, -1, -1));

        txtidprueba.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtidprueba.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtidprueba.setEnabled(false);
        Pruebas.add(txtidprueba, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 47, 211, -1));

        jLabel61.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel61.setText("Clave :");
        Pruebas.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 79, -1, -1));

        txtclaveprueba.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtclaveprueba.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtclaveprueba.setEnabled(false);
        Pruebas.add(txtclaveprueba, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 76, 213, -1));

        jLabel62.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel62.setText("Nombre Producto :");
        Pruebas.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 106, -1, -1));

        txtnombreprueba.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtnombreprueba.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnombreprueba.setEnabled(false);
        Pruebas.add(txtnombreprueba, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 103, 700, -1));

        btnElegirprueba.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnElegirprueba.setText("Elegir");
        btnElegirprueba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnElegirpruebaActionPerformed(evt);
            }
        });
        Pruebas.add(btnElegirprueba, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 30, 120, -1));

        tbpruebas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Categoria", "Determinacion", "Parametro", "Metodo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbpruebas.getTableHeader().setReorderingAllowed(false);
        tbpruebas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbpruebasMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbpruebas);

        Pruebas.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, 720, 280));

        btnAgregarprueba.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAgregarprueba.setText("Agregar ");
        btnAgregarprueba.setEnabled(false);
        btnAgregarprueba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarpruebaActionPerformed(evt);
            }
        });
        Pruebas.add(btnAgregarprueba, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, 190, 40));

        btnquitarprueba.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnquitarprueba.setText("Quitar ");
        btnquitarprueba.setEnabled(false);
        btnquitarprueba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnquitarpruebaActionPerformed(evt);
            }
        });
        Pruebas.add(btnquitarprueba, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 450, 160, 40));

        jLabel63.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel63.setText("Pruebas");
        Pruebas.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, -1, 39));

        Pruebas.add(cbcategoriaprueba, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 190, -1));

        jLabel64.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel64.setText("Categoria :");
        Pruebas.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, -1, -1));

        jLabel65.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel65.setText("Determinacion :");
        Pruebas.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, -1, -1));

        jLabel66.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel66.setText("Parametro :");
        Pruebas.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, -1, -1));

        jLabel68.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel68.setText("Metodo :");
        Pruebas.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, -1, -1));
        Pruebas.add(txtdeterminacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 190, -1));
        Pruebas.add(txtparametro, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 190, -1));
        Pruebas.add(txtmetodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 190, -1));

        jTabbedPane2.addTab("Pruebas", Pruebas);

        Inventario.setBackground(new java.awt.Color(255, 255, 255));
        Inventario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel80.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel80.setText("Inventario");
        Inventario.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, 39));

        tbinventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero Entrada", "Clave", "Nombre", "Fecha", "Fecha Caducidad", "Cantidad", "C. Actual", "Lote", "ID OPP", "Factura"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbinventario.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbinventario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbinventario.setShowHorizontalLines(false);
        tbinventario.setShowVerticalLines(false);
        tbinventario.getTableHeader().setReorderingAllowed(false);
        tbinventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbinventarioMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tbinventario);
        tbinventario.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tbinventario.getColumnModel().getColumnCount() > 0) {
            tbinventario.getColumnModel().getColumn(0).setPreferredWidth(50);
            tbinventario.getColumnModel().getColumn(1).setPreferredWidth(100);
            tbinventario.getColumnModel().getColumn(2).setPreferredWidth(250);
            tbinventario.getColumnModel().getColumn(3).setPreferredWidth(150);
            tbinventario.getColumnModel().getColumn(4).setPreferredWidth(150);
            tbinventario.getColumnModel().getColumn(5).setPreferredWidth(100);
        }

        Inventario.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 56, 650, 370));

        btnCambiarinventario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCambiarinventario.setText("Cambiar");
        btnCambiarinventario.setEnabled(false);
        btnCambiarinventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarinventarioActionPerformed(evt);
            }
        });
        Inventario.add(btnCambiarinventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 150, -1));

        btnGuardarinventario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnGuardarinventario.setText("Guardar");
        btnGuardarinventario.setEnabled(false);
        btnGuardarinventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarinventarioActionPerformed(evt);
            }
        });
        Inventario.add(btnGuardarinventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 450, 160, -1));

        btnCancelarinventario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCancelarinventario.setText("Cancelar");
        btnCancelarinventario.setEnabled(false);
        btnCancelarinventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarinventarioActionPerformed(evt);
            }
        });
        Inventario.add(btnCancelarinventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 450, 160, -1));

        jLabel81.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel81.setText("Buscar  :");
        Inventario.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, 50, -1));

        txtbuscarinventario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtbuscarinventario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarinventarioKeyReleased(evt);
            }
        });
        Inventario.add(txtbuscarinventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 20, 290, -1));

        txtnoentradainventario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtnoentradainventario.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnoentradainventario.setEnabled(false);
        Inventario.add(txtnoentradainventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, 139, -1));

        jLabel82.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel82.setText("Numero Entrada :");
        Inventario.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 60, -1, -1));

        jLabel83.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel83.setText("Clave :");
        Inventario.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 90, -1, -1));

        txtclaveinventario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtclaveinventario.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtclaveinventario.setEnabled(false);
        Inventario.add(txtclaveinventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 90, 139, -1));

        txtnombreinventario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtnombreinventario.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnombreinventario.setEnabled(false);
        Inventario.add(txtnombreinventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 120, 290, -1));

        jLabel84.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel84.setText("Nombre Producto :");
        Inventario.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 120, -1, -1));

        jLabel85.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel85.setText("Fecha :");
        Inventario.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 150, -1, -1));

        txtfechainventario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfechainventario.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtfechainventario.setEnabled(false);
        Inventario.add(txtfechainventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 150, 140, -1));

        txtcantidadinventario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtcantidadinventario.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtcantidadinventario.setEnabled(false);
        Inventario.add(txtcantidadinventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 210, 139, -1));

        jLabel86.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel86.setText("C. Actual :");
        Inventario.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 210, -1, -1));

        txtloteinventario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtloteinventario.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtloteinventario.setEnabled(false);
        Inventario.add(txtloteinventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 270, 139, -1));

        jLabel87.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel87.setText("Lote :");
        Inventario.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 270, -1, -1));

        txtidoppinventario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtidoppinventario.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtidoppinventario.setEnabled(false);
        Inventario.add(txtidoppinventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 300, 139, -1));

        jLabel88.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel88.setText("ID OPP :");
        Inventario.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 300, -1, -1));

        txtfactinventario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfactinventario.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtfactinventario.setEnabled(false);
        Inventario.add(txtfactinventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 330, 139, -1));

        jLabel89.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel89.setText("Factura No. :");
        Inventario.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 330, -1, -1));

        txtcantidadtinventario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtcantidadtinventario.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtcantidadtinventario.setEnabled(false);
        Inventario.add(txtcantidadtinventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 240, 139, -1));

        jLabel90.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel90.setText("Cantidad :");
        Inventario.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 240, -1, -1));

        jLabel91.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel91.setText("Fecha Caducidad :");
        Inventario.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 180, -1, -1));

        txtfechainventario1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfechainventario1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtfechainventario1.setEnabled(false);
        Inventario.add(txtfechainventario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 180, 140, -1));

        jTabbedPane2.addTab("Inventario", Inventario);

        Vendedores.setBackground(new java.awt.Color(255, 255, 255));
        Vendedores.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel18.setText("Vendedores");
        Vendedores.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, -1, 39));

        tbvendedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Telefono", "Correo", "Delegacion", "Colonia", "Calle", "No int", "No ext"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
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
        tbvendedores.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbvendedores.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbvendedores.setShowHorizontalLines(false);
        tbvendedores.setShowVerticalLines(false);
        tbvendedores.getTableHeader().setReorderingAllowed(false);
        tbvendedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbvendedoresMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbvendedores);
        tbvendedores.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tbvendedores.getColumnModel().getColumnCount() > 0) {
            tbvendedores.getColumnModel().getColumn(0).setPreferredWidth(50);
            tbvendedores.getColumnModel().getColumn(1).setPreferredWidth(200);
            tbvendedores.getColumnModel().getColumn(2).setPreferredWidth(150);
            tbvendedores.getColumnModel().getColumn(3).setPreferredWidth(150);
            tbvendedores.getColumnModel().getColumn(4).setPreferredWidth(150);
            tbvendedores.getColumnModel().getColumn(5).setPreferredWidth(100);
            tbvendedores.getColumnModel().getColumn(6).setPreferredWidth(100);
            tbvendedores.getColumnModel().getColumn(7).setPreferredWidth(50);
            tbvendedores.getColumnModel().getColumn(8).setPreferredWidth(50);
        }

        Vendedores.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 48, 650, 400));

        jLabel19.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel19.setText("Buscar  :");
        Vendedores.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 60, -1, -1));

        txtbuscarvendedores.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtbuscarvendedores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarvendedoresKeyReleased(evt);
            }
        });
        Vendedores.add(txtbuscarvendedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 60, 310, -1));

        jLabel20.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel20.setText("ID Vendedor :");
        Vendedores.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 90, -1, -1));

        txtidvendedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtidvendedor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtidvendedor.setEnabled(false);
        Vendedores.add(txtidvendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 90, 153, -1));

        jLabel21.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel21.setText("Telefono :");
        Vendedores.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 150, -1, -1));

        txttelefonovendedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txttelefonovendedor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txttelefonovendedor.setEnabled(false);
        Vendedores.add(txttelefonovendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 150, 220, -1));

        jLabel22.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel22.setText("Correo :");
        Vendedores.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 180, -1, -1));

        cbdelegacion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbdelegacion.setEnabled(false);
        Vendedores.add(cbdelegacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 210, 220, -1));

        jLabel23.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel23.setText("Delegacion :");
        Vendedores.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 210, -1, -1));

        txtnombrevendedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtnombrevendedor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnombrevendedor.setEnabled(false);
        Vendedores.add(txtnombrevendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 120, 290, -1));

        jLabel24.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel24.setText("Nombre :");
        Vendedores.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 120, -1, -1));

        jLabel25.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel25.setText("Colonia:");
        Vendedores.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 240, -1, 22));

        txtcoloniavendedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtcoloniavendedor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtcoloniavendedor.setEnabled(false);
        Vendedores.add(txtcoloniavendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 240, 220, -1));

        txtcorreovendedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtcorreovendedor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtcorreovendedor.setEnabled(false);
        Vendedores.add(txtcorreovendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 180, 220, -1));

        jLabel26.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel26.setText("Calle :");
        Vendedores.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 270, -1, -1));

        txtcallevendedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtcallevendedor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtcallevendedor.setEnabled(false);
        Vendedores.add(txtcallevendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 270, 220, -1));

        txtnointvendedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtnointvendedor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnointvendedor.setEnabled(false);
        Vendedores.add(txtnointvendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 300, 220, -1));

        jLabel28.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel28.setText("No Ext :");
        Vendedores.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 330, -1, -1));

        jLabel29.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel29.setText("No. Int :");
        Vendedores.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 300, -1, -1));

        btnAgregarvendedor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAgregarvendedor.setText("Agregar");
        btnAgregarvendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarvendedorActionPerformed(evt);
            }
        });
        Vendedores.add(btnAgregarvendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 140, 30));

        btnCambiarvendedor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCambiarvendedor.setText("Cambiar");
        btnCambiarvendedor.setEnabled(false);
        btnCambiarvendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarvendedorActionPerformed(evt);
            }
        });
        Vendedores.add(btnCambiarvendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 460, 140, 30));

        btnGuardarvendedor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnGuardarvendedor.setText("Guardar");
        btnGuardarvendedor.setEnabled(false);
        btnGuardarvendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarvendedorActionPerformed(evt);
            }
        });
        Vendedores.add(btnGuardarvendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 460, 140, 30));

        btnCancelarvendedor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCancelarvendedor.setText("Cancelar");
        btnCancelarvendedor.setEnabled(false);
        btnCancelarvendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarvendedorActionPerformed(evt);
            }
        });
        Vendedores.add(btnCancelarvendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 460, 150, 30));

        txtnoextvendedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtnoextvendedor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnoextvendedor.setEnabled(false);
        Vendedores.add(txtnoextvendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 330, 220, -1));

        jTabbedPane2.addTab("Vendedores", Vendedores);

        Proovedores.setBackground(new java.awt.Color(255, 255, 255));
        Proovedores.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel45.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel45.setText("Proveedores");
        Proovedores.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, 39));

        tbproveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Telefono", "Correo", "CP", "Colonia", "Calle", "No int", "No ext", "RFC", "Pago", "Cuenta", "Contacto", "Sist. Calidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbproveedor.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbproveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbproveedor.setShowHorizontalLines(false);
        tbproveedor.setShowVerticalLines(false);
        tbproveedor.getTableHeader().setReorderingAllowed(false);
        tbproveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbproveedorMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbproveedor);
        tbproveedor.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tbproveedor.getColumnModel().getColumnCount() > 0) {
            tbproveedor.getColumnModel().getColumn(0).setPreferredWidth(35);
            tbproveedor.getColumnModel().getColumn(1).setPreferredWidth(200);
            tbproveedor.getColumnModel().getColumn(2).setPreferredWidth(100);
            tbproveedor.getColumnModel().getColumn(3).setPreferredWidth(150);
            tbproveedor.getColumnModel().getColumn(4).setPreferredWidth(100);
            tbproveedor.getColumnModel().getColumn(5).setPreferredWidth(100);
            tbproveedor.getColumnModel().getColumn(6).setPreferredWidth(100);
            tbproveedor.getColumnModel().getColumn(7).setPreferredWidth(50);
            tbproveedor.getColumnModel().getColumn(8).setPreferredWidth(50);
            tbproveedor.getColumnModel().getColumn(9).setPreferredWidth(120);
        }

        Proovedores.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 56, 590, 400));

        jLabel46.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel46.setText("Buscar  :");
        Proovedores.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 20, -1, -1));

        txtbuscarproveedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtbuscarproveedor.setFocusCycleRoot(true);
        txtbuscarproveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarproveedorKeyReleased(evt);
            }
        });
        Proovedores.add(txtbuscarproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 20, 320, -1));

        jLabel47.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel47.setText("ID Proveedor :");
        Proovedores.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 50, -1, -1));

        txtidproveedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtidproveedor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtidproveedor.setEnabled(false);
        txtidproveedor.setNextFocusableComponent(txtnombreproveedor);
        Proovedores.add(txtidproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 50, 118, -1));

        jLabel48.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel48.setText("Telefono :");
        Proovedores.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 110, -1, -1));

        txttelefonoproveedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txttelefonoproveedor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txttelefonoproveedor.setEnabled(false);
        txttelefonoproveedor.setNextFocusableComponent(txtcorreoproveedor);
        Proovedores.add(txttelefonoproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 110, 240, -1));

        jLabel49.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel49.setText("Correo :");
        Proovedores.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 140, -1, -1));

        jLabel50.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel50.setText("Codigo P:");
        Proovedores.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 170, -1, -1));

        txtnombreproveedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtnombreproveedor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnombreproveedor.setEnabled(false);
        txtnombreproveedor.setNextFocusableComponent(txttelefonoproveedor);
        Proovedores.add(txtnombreproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 80, 298, -1));

        jLabel51.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel51.setText("Nombre :");
        Proovedores.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, -1, -1));

        jLabel52.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel52.setText("Colonia:");
        Proovedores.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 200, -1, 22));

        txtcoloniaproveedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtcoloniaproveedor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtcoloniaproveedor.setEnabled(false);
        txtcoloniaproveedor.setNextFocusableComponent(txtcalleproveedor);
        Proovedores.add(txtcoloniaproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 200, 240, -1));

        txtcorreoproveedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtcorreoproveedor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtcorreoproveedor.setEnabled(false);
        Proovedores.add(txtcorreoproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 140, 240, -1));

        jLabel53.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel53.setText("Calle :");
        Proovedores.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 230, -1, -1));

        txtcalleproveedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtcalleproveedor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtcalleproveedor.setEnabled(false);
        txtcalleproveedor.setNextFocusableComponent(txtnointproveedor);
        Proovedores.add(txtcalleproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 230, 240, -1));

        txtnointproveedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtnointproveedor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnointproveedor.setEnabled(false);
        txtnointproveedor.setNextFocusableComponent(txtnoextproveedor);
        Proovedores.add(txtnointproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 260, 240, -1));

        jLabel54.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel54.setText("No Ext :");
        Proovedores.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 290, -1, -1));

        jLabel55.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel55.setText("No. Int :");
        Proovedores.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 260, -1, -1));

        btnAgregarproveedor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAgregarproveedor.setText("Agregar");
        btnAgregarproveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarproveedorActionPerformed(evt);
            }
        });
        Proovedores.add(btnAgregarproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 130, 30));

        btnCambiarproveedor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCambiarproveedor.setText("Cambiar");
        btnCambiarproveedor.setEnabled(false);
        btnCambiarproveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarproveedorActionPerformed(evt);
            }
        });
        Proovedores.add(btnCambiarproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 470, 130, 30));

        btnGuardarproveedor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnGuardarproveedor.setText("Guardar");
        btnGuardarproveedor.setEnabled(false);
        btnGuardarproveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarproveedorActionPerformed(evt);
            }
        });
        Proovedores.add(btnGuardarproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 470, 130, 30));

        btnCancelarproveedor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCancelarproveedor.setText("Cancelar");
        btnCancelarproveedor.setEnabled(false);
        btnCancelarproveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarproveedorActionPerformed(evt);
            }
        });
        Proovedores.add(btnCancelarproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 470, 130, 30));

        txtnoextproveedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtnoextproveedor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnoextproveedor.setEnabled(false);
        txtnoextproveedor.setNextFocusableComponent(txtrfcproveedor);
        Proovedores.add(txtnoextproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 290, 240, -1));

        txtrfcproveedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtrfcproveedor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtrfcproveedor.setEnabled(false);
        txtrfcproveedor.setNextFocusableComponent(cbmodopago1);
        Proovedores.add(txtrfcproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 320, 240, -1));

        jLabel56.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel56.setText("RFC :");
        Proovedores.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 320, -1, -1));

        jLabel57.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel57.setText("Pago: ");
        Proovedores.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 350, -1, -1));

        cbmodopago1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbmodopago1.setEnabled(false);
        cbmodopago1.setNextFocusableComponent(txtcuentaproveedor);
        Proovedores.add(cbmodopago1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 350, 190, -1));

        txtcuentaproveedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtcuentaproveedor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtcuentaproveedor.setEnabled(false);
        txtcuentaproveedor.setNextFocusableComponent(txtcontactoproveedor);
        Proovedores.add(txtcuentaproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 380, 190, -1));

        jLabel58.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel58.setText("4 digitos de cuenta :");
        Proovedores.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 380, -1, -1));

        txtcontactoproveedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtcontactoproveedor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtcontactoproveedor.setEnabled(false);
        txtcontactoproveedor.setNextFocusableComponent(txtsistemacalidad);
        Proovedores.add(txtcontactoproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 410, 240, -1));

        jLabel59.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel59.setText("Contacto :");
        Proovedores.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 410, -1, -1));

        txtsistemacalidad.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtsistemacalidad.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtsistemacalidad.setEnabled(false);
        Proovedores.add(txtsistemacalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 440, 240, 22));

        jLabel70.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel70.setText("Sistema Calidad :");
        Proovedores.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 440, -1, 22));

        txtcpproveedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtcpproveedor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtcpproveedor.setEnabled(false);
        Proovedores.add(txtcpproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 170, 240, -1));

        jTabbedPane2.addTab("Proveedores", Proovedores);

        Clientes.setBackground(new java.awt.Color(255, 255, 255));
        Clientes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel27.setText("Clientes");
        Clientes.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, 39));

        tbclientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Telefono", "Celular", "Correo", "Delegacion", "Colonia", "Calle", "No int", "No ext", "RFC", "Pago", "Cuenta", "Contacto", "Estatus", "Nombre Fact", "Colonia Fact", "Calle Fact", "No Int Fact", "No Ext Fact", "Delegacion Fact"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbclientes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbclientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbclientes.setShowHorizontalLines(false);
        tbclientes.setShowVerticalLines(false);
        tbclientes.getTableHeader().setReorderingAllowed(false);
        tbclientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbclientesMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbclientes);
        tbclientes.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tbclientes.getColumnModel().getColumnCount() > 0) {
            tbclientes.getColumnModel().getColumn(0).setPreferredWidth(35);
            tbclientes.getColumnModel().getColumn(1).setPreferredWidth(200);
            tbclientes.getColumnModel().getColumn(2).setPreferredWidth(150);
            tbclientes.getColumnModel().getColumn(3).setPreferredWidth(150);
            tbclientes.getColumnModel().getColumn(4).setPreferredWidth(200);
        }

        Clientes.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 57, 450, 400));

        jLabel30.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel30.setText("Buscar  :");
        Clientes.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, -1, -1));

        txtbuscarclientes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtbuscarclientes.setFocusCycleRoot(true);
        txtbuscarclientes.setNextFocusableComponent(txtidclientes);
        txtbuscarclientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarclientesKeyReleased(evt);
            }
        });
        Clientes.add(txtbuscarclientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, 280, -1));

        jLabel31.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel31.setText("ID Clientes :");
        Clientes.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, -1, -1));

        txtidclientes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtidclientes.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtidclientes.setEnabled(false);
        txtidclientes.setNextFocusableComponent(txtnombreclientes);
        Clientes.add(txtidclientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, 114, -1));

        jLabel32.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel32.setText("Telefono :");
        Clientes.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 110, -1, -1));

        txttelefonoclientes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txttelefonoclientes.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txttelefonoclientes.setEnabled(false);
        txttelefonoclientes.setNextFocusableComponent(txtcorreoclientes);
        Clientes.add(txttelefonoclientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, 200, -1));

        jLabel33.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel33.setText("Correo :");
        Clientes.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 170, -1, -1));

        cbdelegacion1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbdelegacion1.setEnabled(false);
        cbdelegacion1.setNextFocusableComponent(txtcoloniaclientes);
        Clientes.add(cbdelegacion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 200, 200, -1));

        jLabel34.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel34.setText("Delegacion :");
        Clientes.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 200, -1, -1));

        txtnombreclientes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtnombreclientes.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnombreclientes.setEnabled(false);
        txtnombreclientes.setNextFocusableComponent(txttelefonoclientes);
        Clientes.add(txtnombreclientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 80, 282, -1));

        jLabel35.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel35.setText("Nombre :");
        Clientes.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, -1, -1));

        jLabel36.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel36.setText("Colonia:");
        Clientes.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 230, -1, 22));

        txtcoloniaclientes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtcoloniaclientes.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtcoloniaclientes.setEnabled(false);
        txtcoloniaclientes.setNextFocusableComponent(txtcalleclientes);
        Clientes.add(txtcoloniaclientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 230, 200, -1));

        txtcorreoclientes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtcorreoclientes.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtcorreoclientes.setEnabled(false);
        txtcorreoclientes.setNextFocusableComponent(cbdelegacion1);
        Clientes.add(txtcorreoclientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 170, 200, -1));

        jLabel37.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel37.setText("Calle :");
        Clientes.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 260, -1, -1));

        txtcalleclientes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtcalleclientes.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtcalleclientes.setEnabled(false);
        txtcalleclientes.setNextFocusableComponent(txtnointclientes);
        Clientes.add(txtcalleclientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 260, 200, -1));

        txtnointclientes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtnointclientes.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnointclientes.setEnabled(false);
        txtnointclientes.setNextFocusableComponent(txtnoextclientes);
        Clientes.add(txtnointclientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 290, 200, -1));

        jLabel38.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel38.setText("No Ext :");
        Clientes.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 320, -1, -1));

        jLabel39.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel39.setText("No. Int :");
        Clientes.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 290, -1, -1));

        btnAgregarcliente.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAgregarcliente.setText("Agregar");
        btnAgregarcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarclienteActionPerformed(evt);
            }
        });
        Clientes.add(btnAgregarcliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, -1, -1));

        btnCambiarcliente.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCambiarcliente.setText("Cambiar");
        btnCambiarcliente.setEnabled(false);
        btnCambiarcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarclienteActionPerformed(evt);
            }
        });
        Clientes.add(btnCambiarcliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 480, -1, -1));

        btnGuardarcliente.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnGuardarcliente.setText("Guardar");
        btnGuardarcliente.setEnabled(false);
        btnGuardarcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarclienteActionPerformed(evt);
            }
        });
        Clientes.add(btnGuardarcliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 480, -1, -1));

        btnCancelarcliente.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCancelarcliente.setText("Cancelar");
        btnCancelarcliente.setEnabled(false);
        btnCancelarcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarclienteActionPerformed(evt);
            }
        });
        Clientes.add(btnCancelarcliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 480, -1, -1));

        txtnoextclientes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtnoextclientes.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnoextclientes.setEnabled(false);
        txtnoextclientes.setNextFocusableComponent(cbmodopago);
        Clientes.add(txtnoextclientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 320, 200, -1));

        jLabel41.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel41.setText("Pago: ");
        Clientes.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 350, -1, -1));

        cbmodopago.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbmodopago.setEnabled(false);
        cbmodopago.setNextFocusableComponent(txtcuentaclientes);
        Clientes.add(cbmodopago, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 350, 200, -1));

        txtcuentaclientes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtcuentaclientes.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtcuentaclientes.setEnabled(false);
        txtcuentaclientes.setNextFocusableComponent(txtcontactoclientes);
        Clientes.add(txtcuentaclientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 410, 200, -1));

        jLabel42.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel42.setText("4 cuenta :");
        Clientes.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 410, -1, 18));

        txtcontactoclientes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtcontactoclientes.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtcontactoclientes.setEnabled(false);
        txtcontactoclientes.setNextFocusableComponent(chestatusclientes);
        Clientes.add(txtcontactoclientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 380, 200, -1));

        jLabel43.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel43.setText("Contacto :");
        Clientes.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 380, -1, -1));

        jLabel44.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel44.setText("Cliente aceptado:");
        Clientes.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 440, -1, -1));

        chestatusclientes.setEnabled(false);
        chestatusclientes.setNextFocusableComponent(txtnombreclientes1);
        Clientes.add(chestatusclientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 440, -1, -1));

        jLabel67.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel67.setText("Facturacion");
        Clientes.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 110, -1, -1));

        jLabel69.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel69.setText("Nombre :");
        Clientes.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 140, -1, -1));

        txtnombreclientes1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtnombreclientes1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnombreclientes1.setEnabled(false);
        txtnombreclientes1.setNextFocusableComponent(txtrfcclientes);
        Clientes.add(txtnombreclientes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 140, 240, -1));

        txtrfcclientes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtrfcclientes.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtrfcclientes.setEnabled(false);
        txtrfcclientes.setNextFocusableComponent(txtcoloniaclientes1);
        Clientes.add(txtrfcclientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 170, 190, 20));

        jLabel71.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel71.setText("RFC :");
        Clientes.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 170, -1, -1));

        txtcoloniaclientes1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtcoloniaclientes1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtcoloniaclientes1.setEnabled(false);
        txtcoloniaclientes1.setNextFocusableComponent(txtcalleclientes1);
        Clientes.add(txtcoloniaclientes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 200, 190, -1));

        txtcalleclientes1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtcalleclientes1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtcalleclientes1.setEnabled(false);
        txtcalleclientes1.setNextFocusableComponent(txtnointclientes1);
        Clientes.add(txtcalleclientes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 230, 190, -1));

        txtnointclientes1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtnointclientes1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnointclientes1.setEnabled(false);
        txtnointclientes1.setNextFocusableComponent(txtnoextclientes1);
        Clientes.add(txtnointclientes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 260, 190, -1));

        txtnoextclientes1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtnoextclientes1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtnoextclientes1.setEnabled(false);
        txtnoextclientes1.setNextFocusableComponent(cbdelegacion2);
        Clientes.add(txtnoextclientes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 290, 190, -1));

        jLabel72.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel72.setText("No Ext :");
        Clientes.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 290, -1, -1));

        jLabel73.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel73.setText("No. Int :");
        Clientes.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 260, -1, -1));

        jLabel74.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel74.setText("Colonia:");
        Clientes.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 200, -1, 22));

        jLabel75.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel75.setText("Calle :");
        Clientes.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 230, -1, -1));

        jLabel76.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel76.setText("Delegacion :");
        Clientes.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 320, -1, -1));

        cbdelegacion2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbdelegacion2.setEnabled(false);
        Clientes.add(cbdelegacion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 320, 190, -1));

        jLabel40.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel40.setText("Celular :");
        Clientes.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, -1, -1));

        txtcelularclientes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtcelularclientes.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtcelularclientes.setEnabled(false);
        txtcelularclientes.setNextFocusableComponent(txtcorreoclientes);
        Clientes.add(txtcelularclientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 140, 200, -1));

        cbvendedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbvendedor.setEnabled(false);
        Clientes.add(cbvendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 350, 190, -1));

        jLabel77.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel77.setText("Vendedor :");
        Clientes.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 350, -1, -1));

        jTabbedPane2.addTab("Clientes", Clientes);

        jLabel13.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel13.setText("Datos");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(522, 522, 522))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane2))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1140, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //metodo para pasar de dd/mm/yyyy a yyyy-mm-dd
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
  
    
    //cuando se cierra   
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.mp.setVisible(true);
        this.mp.notificaciones();
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

        
    //Productos
     public void tablaproductos()
    {
        try{
            
            this.tabla_productos=(DefaultTableModel) this.tbproductos.getModel();
            Productos_DB pro = new Productos_DB(this.con);
            List <Producto> productos = pro.select();
            for (int i = 0; i < productos.size(); i++) {
                Object[] obj = new Object[10];
                obj[0]=productos.get(i).getId();
                obj[1]=productos.get(i).getClave();
                obj[2]=productos.get(i).getNombre();
                obj[3]=productos.get(i).getCategoria();
                obj[4]=productos.get(i).getMedida();
                obj[5]=productos.get(i).getPventa();
                obj[6]=productos.get(i).getStockmin();
                obj[7]=productos.get(i).getIva();
                obj[8]=productos.get(i).getProceso();
                obj[9]=productos.get(i).getMeses_caducidad();
                this.tabla_productos.addRow(obj);
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
     
    public void limpiar()
    {
           this.txtbuscar_producto.setText("");
           this.txtidproducto.setText("");
           this.txtclaveproducto.setText("");
           this.txtnombreproducto.setText("");
           this.txtpventaproducto.setText("");
           this.txtsminproducto.setText("");
           this.chivaproducto.setSelected(false);
           this.txtcaducidadproducto.setText("");
    }
    
    private void habilitar()
    {
           this.tbproductos.setEnabled(false);

           this.txtbuscar_producto.setEnabled(false);
           
           this.txtclaveproducto.setEnabled(true);
           this.txtnombreproducto.setEnabled(true);
           this.txtpventaproducto.setEnabled(true);
           this.txtsminproducto.setEnabled(true);
           this.cbcategoriaproducto.setEnabled(true);
           this.cbmedidaproducto.setEnabled(true);
           this.cbprocesoproducto.setEnabled(true);
           
           this.btnAgregar_producto.setEnabled(false);
           this.btnCancelar_producto.setEnabled(true);
           this.btnGuardar_producto.setEnabled(true);
           this.chivaproducto.setEnabled(true);
           
           this.txtcaducidadproducto.setEnabled(true);
    }
    
    public void deshabilitar()
    {
        try
       {
           this.tbproductos.setEnabled(true);
           this.txtbuscar_producto.setEnabled(true);
           
           this.txtclaveproducto.setEnabled(false);
           this.txtnombreproducto.setEnabled(false);
           this.txtpventaproducto.setEnabled(false);
           this.txtsminproducto.setEnabled(false);
           this.cbcategoriaproducto.setEnabled(false);
           this.cbmedidaproducto.setEnabled(false);
           this.cbprocesoproducto.setEnabled(false);
           
           this.btnAgregar_producto.setEnabled(true);
           this.btnCambiar_producto.setEnabled(false);
           this.btnCancelar_producto.setEnabled(false);
           this.btnGuardar_producto.setEnabled(false);
           this.chivaproducto.setEnabled(false);
           
           this.txtcaducidadproducto.setEnabled(false);

           
           limpiar();
       }
       catch(Exception ex)
       {
           System.out.println("Algo esta mal");
       }
    }
    
    public void insertar_producto()
    {
        try{
            if (this.con.isClosed() || this.con.isReadOnly()) {
                this.con.close();
                this.con=Conexion.getConnection();
            }
            Productos_DB pro = new Productos_DB(this.con);
            if(this.seleccionproducto==1)
            {
                Producto producto = new Producto();
                producto.setNombre(this.txtnombreproducto.getText());
                producto.setClave(this.txtclaveproducto.getText());
                producto.setStockmin(Double.parseDouble(this.txtsminproducto.getText()));
                producto.setPventa(Double.parseDouble(this.txtpventaproducto.getText()));
                producto.setMeses_caducidad(Integer.parseInt(this.txtcaducidadproducto.getText()));
                if (this.chivaproducto.isSelected()) {
                    producto.setIva(1);
                }
                else
                {
                    producto.setIva(0);
                }
                producto.setPeso(Double.parseDouble(this.txtpeso_producto.getText()));
                pro.insert(producto, this.cbmedidaproducto.getSelectedIndex()+1, this.cbcategoriaproducto.getSelectedIndex()+1,this.cbprocesoproducto.getSelectedIndex()+1);
                this.tablaproductos();
            }
            if(this.seleccionproducto==2)
            {
                Producto producto = new Producto();              
                producto.setId(Integer.parseInt(this.txtidproducto.getText()));
                producto.setNombre(this.txtnombreproducto.getText());
                producto.setClave(this.txtclaveproducto.getText());
                producto.setStockmin(Double.parseDouble(this.txtsminproducto.getText()));
                producto.setPventa(Double.parseDouble(this.txtpventaproducto.getText()));
                producto.setMeses_caducidad(Integer.parseInt(this.txtcaducidadproducto.getText()));
                if (this.chivaproducto.isSelected()) {
                    producto.setIva(1);
                }
                else
                {
                    producto.setIva(0);
                }
                producto.setPeso(Double.parseDouble(this.txtpeso_producto.getText()));
                pro.update(producto, this.cbmedidaproducto.getSelectedIndex()+1,this.cbcategoriaproducto.getSelectedIndex()+1,this.cbprocesoproducto.getSelectedIndex()+1);                          
                this.tablaproductos();
            }
            this.seleccionproducto=0;
            deshabilitar();
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

    private void btnCancelar_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar_productoActionPerformed
        deshabilitar();
        this.seleccionproducto=0;
    }//GEN-LAST:event_btnCancelar_productoActionPerformed

    private void btnGuardar_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar_productoActionPerformed
        try
        {
            //index para ver que insertamos
            int index = this.cbcategoriaproducto.getSelectedIndex() +1;
           
            switch(index)
            {
                //materia prima
                case 1 :
                    if (this.txtclaveproducto.getText().isEmpty() || this.txtnombreproducto.getText().isEmpty() || this.txtsminproducto.getText().isEmpty() ) {
                        JOptionPane.showMessageDialog(null, "Algun campo esta vacio");
                    }
                    else
                    {
                        if (this.txtsminproducto.getText().matches("^([0-9]+)(\\.[0-9]+)?$")) {
                            insertar_producto();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Formato de stock incorrecto");
                        }
                    }
                    break;

                //Producto Terminado
                case 2:
                if (this.txtclaveproducto.getText().isEmpty() || this.txtnombreproducto.getText().isEmpty() || this.txtpventaproducto.getText().isEmpty()  || this.txtcaducidadproducto.getText().isEmpty() ) {
                    JOptionPane.showMessageDialog(null, "Algun campo esta vacio");
                }
                else
                {
                    if (this.txtpventaproducto.getText().matches("^([0-9]+)(\\.[0-9]+)?$") && this.txtcaducidadproducto.getText().matches("^[0-9]+$")) {
                        insertar_producto();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Formato de Precio de venta incorrecto");
                    }
                }
                break;

                case 3:
                if (this.txtclaveproducto.getText().isEmpty() || this.txtnombreproducto.getText().isEmpty() || this.txtsminproducto.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Algun campo esta vacio");
                }
                else
                {
                    if (this.txtsminproducto.getText().matches("^([0-9]+)(\\.[0-9]+)?$") ) {
                        insertar_producto();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Formato incorrecto");
                    }
                }
                break;
                
                //Galeria
                case 4:
                if (this.txtclaveproducto.getText().isEmpty() || this.txtnombreproducto.getText().isEmpty() || this.txtpventaproducto.getText().isEmpty() || this.txtsminproducto.getText().isEmpty() || this.txtcaducidadproducto.getText().isEmpty() ) {
                    JOptionPane.showMessageDialog(null, "Algun campo esta vacio");
                }
                else
                {
                    if (this.txtpventaproducto.getText().matches("^([0-9]+)(\\.[0-9]+)?$") && this.txtsminproducto.getText().matches("^([0-9]+)(\\.[0-9]+)?$") && this.txtcaducidadproducto.getText().matches("^[0-9]+$")) {
                        insertar_producto();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Formato de Precio de venta incorrecto");
                    }
                }
                break;
            }

            

        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Error :"+ ex);
        }
    }//GEN-LAST:event_btnGuardar_productoActionPerformed

    private void btnCambiar_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiar_productoActionPerformed
        try
        {
            habilitar();
            this.btnCambiar_producto.setEnabled(false);
            this.seleccionproducto=2;
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Error :"+ ex);
        }
    }//GEN-LAST:event_btnCambiar_productoActionPerformed

    private void btnAgregar_productoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregar_productoMouseClicked
        try
        {
            limpiar();
            habilitar();
            this.seleccionproducto=1;
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Error :"+ ex);
        }
    }//GEN-LAST:event_btnAgregar_productoMouseClicked

    private void cbcategoriaproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbcategoriaproductoActionPerformed
        int index = this.cbcategoriaproducto.getSelectedIndex()+1;
        //materia prima
        if (index==1) {
            this.lblproceso.setVisible(false);
            this.lblpventa.setVisible(false);
            this.txtpventaproducto.setVisible(false);
            this.cbprocesoproducto.setVisible(false);
            
            this.chivaproducto.setVisible(false);
            this.lbliva.setVisible(false);

            this.lblsmin.setVisible(true);
            this.txtsminproducto.setVisible(true);
            
            this.lblpeso.setVisible(false);
            this.txtpeso_producto.setVisible(false);

            
            this.txtcaducidadproducto.setVisible(false);
            this.lblcaducidad.setVisible(false);
        }
        //producto terminado
        if (index ==2) {
            this.lblsmin.setVisible(false);
            this.txtsminproducto.setVisible(false);
            
            this.chivaproducto.setVisible(true);
            this.lbliva.setVisible(true);

            this.lblproceso.setVisible(true);
            this.lblpventa.setVisible(true);
            this.txtpventaproducto.setVisible(true);
            this.cbprocesoproducto.setVisible(true);
            
            this.lblpeso.setVisible(false);
            this.txtpeso_producto.setVisible(false);
            
            this.txtcaducidadproducto.setVisible(true);
            this.lblcaducidad.setVisible(true);
        }
        //empaque y envases
        if (index==3) {
            this.lblsmin.setVisible(true);
            this.txtsminproducto.setVisible(true);
            
            this.chivaproducto.setVisible(false);
            this.lbliva.setVisible(false);

            this.lblproceso.setVisible(false);
            this.lblpventa.setVisible(false);
            this.txtpventaproducto.setVisible(false);
            this.cbprocesoproducto.setVisible(false);
            
            this.lblpeso.setVisible(true);
            this.txtpeso_producto.setVisible(true);
            
            this.txtcaducidadproducto.setVisible(false);
            this.lblcaducidad.setVisible(false);
        }
        //Galeria
        if (index==4) {
            this.lblsmin.setVisible(true);
            this.txtsminproducto.setVisible(true);
            
            this.chivaproducto.setVisible(true);
            this.lbliva.setVisible(true);

            this.lblproceso.setVisible(true);
            this.lblpventa.setVisible(true);
            this.txtpventaproducto.setVisible(true);
            this.cbprocesoproducto.setVisible(true);
            
            this.lblpeso.setVisible(false);
            this.txtpeso_producto.setVisible(false);
    
            this.txtcaducidadproducto.setVisible(true);
            this.lblcaducidad.setVisible(true);
        }
    }//GEN-LAST:event_cbcategoriaproductoActionPerformed

    private void txtbuscar_productoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_productoKeyReleased

        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(this.tabla_productos);
        this.tbproductos.setRowSorter(tr);

        tr.setRowFilter(RowFilter.regexFilter(this.txtbuscar_producto.getText().toUpperCase(),1));
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();

        int columnIndexToSort = 0;
        sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));

        tr.setSortKeys(sortKeys);
        tr.sort();
    }//GEN-LAST:event_txtbuscar_productoKeyReleased

    private void tbproductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbproductosMouseClicked
        try
        {
            if (this.seleccionproducto == 0) {

                int columna=this.tbproductos.getSelectedRow();
                this.txtidproducto.setText(this.tbproductos.getValueAt(columna, 0).toString());
                this.txtclaveproducto.setText(this.tbproductos.getValueAt(columna, 1).toString());
                this.txtnombreproducto.setText(this.tbproductos.getValueAt(columna, 2).toString());
                this.cbcategoriaproducto.setSelectedItem(this.tbproductos.getValueAt(columna, 3));
                this.cbmedidaproducto.setSelectedItem(this.tbproductos.getValueAt(columna, 4).toString());
                this.txtpventaproducto.setText(this.tbproductos.getValueAt(columna, 5).toString());
                this.txtsminproducto.setText(this.tbproductos.getValueAt(columna, 6).toString());

                if (this.tbproductos.getValueAt(columna, 7).toString().equals("1")) {
                    this.chivaproducto.setSelected(true);
                }
                else
                {
                    this.chivaproducto.setSelected(false);
                }
                this.cbprocesoproducto.setSelectedItem(this.tbproductos.getValueAt(columna, 8).toString());
                this.txtcaducidadproducto.setText(this.tbproductos.getValueAt(columna, 9).toString());

                this.btnCambiar_producto.setEnabled(true);
            }

        }
        catch(Exception ex)
        {
            
        }

    }//GEN-LAST:event_tbproductosMouseClicked


    
    
     //inventario
    
     public void creaciontablainventario()
    {  
        this.tabla_inventario=(DefaultTableModel) this.tbinventario.getModel();
        
        this.tabla_inventario.setRowCount(0);

        ArrayList <String[]> op = new ArrayList<>();
        
        String query="select inventario.id, productos.clave, productos.nombre, inventario.fechaentrada, inventario.fechacaducidad, inventario.cantidad, inventario.cantidadactual,\n" +
"inventario.lote, inventario.idopp, inventario.facturano from inventario join productos \n" +
"on productos.id=inventario.idproducto where inventario.cantidadactual>0;";
        op=this.dbc.seleccionar(query);
 
        for (int i = 0; i < op.size(); i++) {    
            
            this.tabla_inventario.addRow(op.get(i));
        } 
    }
    
    
    public void habilitarinventario()
    {
           this.tbinventario.setEnabled(false);
            
           this.txtbuscarinventario.setEnabled(false);        

           this.txtfechainventario.setEnabled(true);
           this.txtfechainventario1.setEnabled(true);
           this.txtcantidadinventario.setEnabled(true);
           this.txtloteinventario.setEnabled(true);

           this.txtfactinventario.setEnabled(true);    

           this.btnCancelarinventario.setEnabled(true);
           this.btnGuardarinventario.setEnabled(true);
           this.btnCambiarinventario.setEnabled(false);
    
    }
    
    public void deshabilitarinventario()
    {
            this.tbinventario.setEnabled(true);
            
           this.txtbuscarinventario.setEnabled(true);        

           this.txtfechainventario.setEnabled(false);
           this.txtfechainventario1.setEnabled(false);
           this.txtcantidadinventario.setEnabled(false);
           this.txtloteinventario.setEnabled(false);
           
           this.txtfactinventario.setEnabled(false);  



           this.btnCancelarinventario.setEnabled(false);
           this.btnGuardarinventario.setEnabled(false);
           this.btnCambiarinventario.setEnabled(false);
    
    }
    
    public void limpiarinventario()
    {
           this.txtnoentradainventario.setText(""); 
           this.txtbuscarinventario.setText("");        
           this.txtclaveinventario.setText("");  
           this.txtnombreinventario.setText("");  
           this.txtfechainventario.setText("");  
           this.txtfechainventario1.setText("");  
           this.txtcantidadinventario.setText("");
           this.txtcantidadtinventario.setText("");  
           this.txtloteinventario.setText("");  
           this.txtidoppinventario.setText("");  
           this.txtfactinventario.setText("");   
           
    }
    
    
    private void txtbuscarinventarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarinventarioKeyReleased
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(this.tabla_inventario);
        this.tbinventario.setRowSorter(tr);

        tr.setRowFilter(RowFilter.regexFilter(this.txtbuscarinventario.getText().toUpperCase(),2));
    }//GEN-LAST:event_txtbuscarinventarioKeyReleased

    private void btnCancelarinventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarinventarioActionPerformed
        deshabilitarinventario();
        limpiarinventario();
    }//GEN-LAST:event_btnCancelarinventarioActionPerformed

    private void btnGuardarinventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarinventarioActionPerformed
        try{
            //hacer todo :V
            if (this.txtcantidadinventario.getText().isEmpty() || this.txtloteinventario.getText().isEmpty() || this.txtfactinventario.getText().isEmpty() ||
                this.txtfechainventario.getText().isEmpty() || this.txtfechainventario1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Algun campo esta vacio");
            }
            else
            {
                if (this.txtcantidadinventario.getText().matches("^([0-9]+)(\\.[0-9]+)?$") && this.txtfechainventario.getText().matches("^[0-9]{1,2}\\/[0-9]{1,2}\\/[0-9]{4}$") && this.txtfechainventario1.getText().matches("^[0-9]{1,2}\\/[0-9]{1,2}\\/[0-9]{4}$") ){

                    String query="Update inventario set cantidadactual = ? , lote = ? ,   facturano = ? , fechaentrada = ? , fechacaducidad = ? where id = ? ";
                    PreparedStatement ps= this.dbc.getCnx().prepareStatement(query);

                    ps.setString(1, this.txtcantidadinventario.getText());
                    ps.setString(2, this.txtloteinventario.getText());

                    ps.setString(3, this.txtfactinventario.getText());
                    

                    ps.setString(4, fechadividir(this.txtfechainventario,1));
                    ps.setString(5, fechadividir(this.txtfechainventario1,1));

                    ps.setInt(6, Integer.parseInt(this.txtnoentradainventario.getText()));

                    ps.executeUpdate();
                    ps.close();

                    creaciontablainventario();

                    
                    limpiarinventario();
                    deshabilitarinventario();

                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Algun campo no corresponde con el tipo de dato");
                }
            }
        }
        catch(Exception ex)
        {
             JOptionPane.showMessageDialog(null, "Error de conexion, intente otra vez");
            try {
                this.dbc = new DBcontrolador ();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
            this.con=this.dbc.getCnx();
        }

    }//GEN-LAST:event_btnGuardarinventarioActionPerformed

    private void btnCambiarinventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarinventarioActionPerformed
        habilitarinventario();

    }//GEN-LAST:event_btnCambiarinventarioActionPerformed

    private void tbinventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbinventarioMouseClicked
        try
        {
            this.columnainventario=this.tbinventario.getSelectedRow();

            this.txtnoentradainventario.setText(this.tbinventario.getValueAt(this.columnainventario, 0).toString());
            this.txtclaveinventario.setText(this.tbinventario.getValueAt(this.columnainventario, 1).toString());
            this.txtnombreinventario.setText(this.tbinventario.getValueAt(this.columnainventario, 2).toString());
            this.txtfechainventario.setText(this.tbinventario.getValueAt(this.columnainventario, 3).toString());
            this.txtfechainventario1.setText(this.tbinventario.getValueAt(this.columnainventario, 4).toString());
            this.txtcantidadtinventario.setText(this.tbinventario.getValueAt(this.columnainventario, 5).toString());
            this.txtcantidadinventario.setText(this.tbinventario.getValueAt(this.columnainventario, 6).toString());
            this.txtloteinventario.setText(this.tbinventario.getValueAt(this.columnainventario, 7).toString());
            this.txtidoppinventario.setText(this.tbinventario.getValueAt(this.columnainventario, 8).toString());
            this.txtfactinventario.setText(this.tbinventario.getValueAt(this.columnainventario, 9).toString());
            
            this.txtfechainventario.setText(fechadividir(this.txtfechainventario,0));
            this.txtfechainventario1.setText(fechadividir(this.txtfechainventario1,0));

            this.btnCambiarinventario.setEnabled(true);
        }
        catch(Exception ex)
        {
            
        }
    }//GEN-LAST:event_tbinventarioMouseClicked

    //clientes    
    
     public void habilitarcliente()
    {
       this.btnCancelarcliente.setEnabled(true);
       this.btnGuardarcliente.setEnabled(true);
       this.btnAgregarcliente.setEnabled(false);
       this.btnCambiarcliente.setEnabled(false);
       
       this.txtnombreclientes.setEnabled(true);
       this.txttelefonoclientes.setEnabled(true);
       this.txtcelularclientes.setEnabled(true);
       this.cbdelegacion1.setEnabled(true);
       this.txtcoloniaclientes.setEnabled(true);
       this.txtcalleclientes.setEnabled(true);
       this.txtcorreoclientes.setEnabled(true);
       this.txtnointclientes.setEnabled(true);
       this.txtnoextclientes.setEnabled(true);
       
       this.txtnombreclientes1.setEnabled(true);
       this.txtcoloniaclientes1.setEnabled(true);
       this.txtcalleclientes1.setEnabled(true);
       this.txtnointclientes1.setEnabled(true);
       this.txtnoextclientes1.setEnabled(true);
       this.cbdelegacion2.setEnabled(true);
       
       this.txtrfcclientes.setEnabled(true);
       this.cbmodopago.setEnabled(true);
       this.txtcuentaclientes.setEnabled(true);
       this.txtcontactoclientes.setEnabled(true);
       this.chestatusclientes.setEnabled(true);
       
       this.cbvendedor.setEnabled(true);
       
       
       this.txtbuscarclientes.setEnabled(false);
    }
     
    public void deshabilitarcliente()
    {
       this.btnCancelarcliente.setEnabled(false);
       this.btnGuardarcliente.setEnabled(false);
       this.btnAgregarcliente.setEnabled(true);
       this.btnCambiarcliente.setEnabled(false);
       
       this.txtnombreclientes.setEnabled(false);
       this.txttelefonoclientes.setEnabled(false);
       this.txtcelularclientes.setEnabled(false);
       this.cbdelegacion1.setEnabled(false);
       this.txtcoloniaclientes.setEnabled(false);
       this.txtcalleclientes.setEnabled(false);
       this.txtcorreoclientes.setEnabled(false);
       this.txtnointclientes.setEnabled(false);
       this.txtnoextclientes.setEnabled(false);
       
       this.txtnombreclientes1.setEnabled(false);
       this.txtcoloniaclientes1.setEnabled(false);
       this.txtcalleclientes1.setEnabled(false);
       this.txtnointclientes1.setEnabled(false);
       this.txtnoextclientes1.setEnabled(false);
       this.cbdelegacion2.setEnabled(false);
       
       this.txtrfcclientes.setEnabled(false);
       this.cbmodopago.setEnabled(false);
       this.txtcuentaclientes.setEnabled(false);
       this.txtcontactoclientes.setEnabled(false);
       this.chestatusclientes.setEnabled(false);
       
       this.cbvendedor.setEnabled(false);
       
       this.txtbuscarclientes.setEnabled(true);
    }
    
     public void limpiarcliente()
    {
       
       this.txtnombreclientes.setText("");
       this.txttelefonoclientes.setText("");
       this.txtcelularclientes.setText("");
       this.txtcoloniaclientes.setText("");
       this.txtcalleclientes.setText("");
       this.txtcorreoclientes.setText("");
       this.txtnointclientes.setText("");
       this.txtnoextclientes.setText("");
       
       this.txtnombreclientes1.setText("");
       this.txtcoloniaclientes1.setText("");
       this.txtcalleclientes1.setText("");
       this.txtnointclientes1.setText("");
       this.txtnoextclientes1.setText("");
       
       this.txtrfcclientes.setText("");

       this.txtcuentaclientes.setText("");
       this.txtcontactoclientes.setText("");
       this.chestatusclientes.setSelected(false);
       
       
       this.txtbuscarclientes.setText("");
    }
    
    private void btnCancelarclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarclienteActionPerformed
        deshabilitarcliente();
        limpiarcliente();
    }//GEN-LAST:event_btnCancelarclienteActionPerformed

    private void btnGuardarclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarclienteActionPerformed
        try
        {
            if(this.txtnointclientes.getText().isEmpty())
            {
                this.txtnointclientes.setText(" ");
                
            }
            
            if(this.txtnoextclientes.getText().isEmpty())
            {
                this.txtnoextclientes.setText(" ");
                
            }
            if(this.txtnointclientes1.getText().isEmpty())
            {
                this.txtnointclientes1.setText(" ");
                
            }
            
            if(this.txtnoextclientes1.getText().isEmpty())
            {
                this.txtnoextclientes1.setText(" ");
                
            }
            if (this.txtnombreclientes.getText().isEmpty() || this.txttelefonoclientes.getText().isEmpty() || this.txtcorreoclientes.getText().isEmpty() || this.txtcelularclientes.getText().isEmpty() ||
                this.txtcoloniaclientes.getText().isEmpty() || this.txtcalleclientes.getText().isEmpty() || this.txtnoextclientes.getText().isEmpty() || this.txtnointclientes.getText().isEmpty()
                || this.txtrfcclientes.getText().isEmpty() || this.txtcuentaclientes.getText().isEmpty() || this.txtcontactoclientes.getText().isEmpty() ||
                this.txtnombreclientes1.getText().isEmpty() || this.txtcoloniaclientes1.getText().isEmpty() || this.txtcalleclientes1.getText().isEmpty()
                || this.txtnoextclientes1.getText().isEmpty() || this.txtnointclientes1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Algun campo esta vacio");
            }
            else
            {
                if (this.txtcorreoclientes.getText().matches("^[_a-zA-Z0-9-]+(.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(.[a-zA-Z0-9-]+)*(.[a-zA-Z]{2,4})$")
                    && this.txtcuentaclientes.getText().matches("^[0-9]{4}$") && this.txtrfcclientes.getText().matches("^[A-Z]{3,4}[0-9]{6}[A-Z0-9]{3}$")) {

                    if(this.seleccionclientes==1)
                    {
                        String query="Insert into clientes (nombre,telefono,celular,correo,iddelegacion,colonia,calle,noint,noext,rfc,idmpago,cuenta,contacto,estatus, nombrefact, coloniafact, callefact, nointfact, noextfact, delefact, idvendedor) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        PreparedStatement ps= this.dbc.getCnx().prepareStatement(query);

                        ps.setString(1, this.txtnombreclientes.getText());
                        ps.setString(2, this.txttelefonoclientes.getText());
                        ps.setString(3, this.txtcelularclientes.getText());
                        ps.setString(4, this.txtcorreoclientes.getText());

                        ps.setInt(5, this.cbdelegacion1.getSelectedIndex()+1);

                        ps.setString(6, this.txtcoloniaclientes.getText() );
                        ps.setString(7, this.txtcalleclientes.getText());
                        ps.setString(8, this.txtnointclientes.getText());
                        ps.setString(9, this.txtnoextclientes.getText());

                        ps.setString(10, this.txtrfcclientes.getText());
                        ps.setInt(11, this.cbmodopago.getSelectedIndex()+1);
                        ps.setInt(12, Integer.parseInt(this.txtcuentaclientes.getText()));
                        ps.setString(13, this.txtcontactoclientes.getText());

                        if (this.chestatusclientes.isSelected()) {
                            ps.setInt(14, 2);
                        }
                        else
                        {
                            ps.setInt(14, 1);
                        }

                        ps.setString(15, this.txtnombreclientes1.getText());
                        ps.setString(16, this.txtcoloniaclientes1.getText() );
                        ps.setString(17, this.txtcalleclientes1.getText());
                        ps.setString(18, this.txtnointclientes1.getText());
                        ps.setString(19, this.txtnoextclientes1.getText());
                        ps.setInt(20, this.cbdelegacion2.getSelectedIndex()+1);
                        ps.setInt(21, this.cbvendedor.getSelectedIndex()+1);

                        ps.executeUpdate();
                        ps.close();

                        creaciontablaclientes();
                    }
                    if(this.seleccionclientes==2)
                    {
                        String query="Update clientes set nombre = ? , telefono = ?, celular = ? ,   correo = ? ,  iddelegacion = ? , colonia = ? , calle = ? ,  noint = ? ,  noext= ? , rfc = ? ,idmpago = ?,cuenta = ?,contacto = ?,estatus = ? , "
                        + " nombrefact = ? , coloniafact = ?, callefact = ?, nointfact = ?, noextfact = ?, delefact = ?, idvendedor= ?  where id = ? ";
                        PreparedStatement ps= this.dbc.getCnx().prepareStatement(query);

                        ps.setString(1, this.txtnombreclientes.getText());
                        ps.setString(2, this.txttelefonoclientes.getText());
                        ps.setString(3, this.txtcelularclientes.getText());
                        ps.setString(4, this.txtcorreoclientes.getText());

                        ps.setInt(5, this.cbdelegacion1.getSelectedIndex()+1);

                        ps.setString(6, this.txtcoloniaclientes.getText() );
                        ps.setString(7, this.txtcalleclientes.getText());
                        ps.setString(8, this.txtnointclientes.getText());
                        ps.setString(9, this.txtnoextclientes.getText());

                        ps.setString(10, this.txtrfcclientes.getText());
                        ps.setInt(11, this.cbmodopago.getSelectedIndex()+1);
                        ps.setInt(12, Integer.parseInt(this.txtcuentaclientes.getText()));
                        ps.setString(13, this.txtcontactoclientes.getText());

                        if (this.chestatusclientes.isSelected()) {
                            ps.setInt(14, 2);
                        }
                        else
                        {
                            ps.setInt(14, 1);
                        }

                        ps.setString(15, this.txtnombreclientes1.getText());
                        ps.setString(16, this.txtcoloniaclientes1.getText() );
                        ps.setString(17, this.txtcalleclientes1.getText());
                        ps.setString(18, this.txtnointclientes1.getText());
                        ps.setString(19, this.txtnoextclientes1.getText());
                        ps.setInt(20, this.cbdelegacion2.getSelectedIndex()+1);
                        ps.setInt(21, this.cbvendedor.getSelectedIndex()+1);
                        ps.setInt(22, Integer.parseInt(this.txtidclientes.getText()));
                        ps.executeUpdate();
                        ps.close();

                        creaciontablaclientes();

                    }

                    this.seleccionvendedor=0;
                    deshabilitarcliente();
                    limpiarcliente();

                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Algun campo no corresponde con el tipo de dato");
                }
            }

        }
        catch(Exception ex)
        {
             JOptionPane.showMessageDialog(null, "Error de conexion, intente otra vez");
            try {
                this.dbc = new DBcontrolador ();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
            this.con=this.dbc.getCnx();
        }
    }//GEN-LAST:event_btnGuardarclienteActionPerformed

    private void btnCambiarclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarclienteActionPerformed
        habilitarcliente();
        this.seleccionclientes=2;
    }//GEN-LAST:event_btnCambiarclienteActionPerformed

    private void btnAgregarclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarclienteActionPerformed
        habilitarcliente();
        limpiarcliente();
        this.seleccionclientes=1;
    }//GEN-LAST:event_btnAgregarclienteActionPerformed

    private void txtbuscarclientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarclientesKeyReleased
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(this.tabla4);
        this.tbclientes.setRowSorter(tr);

        tr.setRowFilter(RowFilter.regexFilter(this.txtbuscarclientes.getText().toUpperCase(),1,15));
    }//GEN-LAST:event_txtbuscarclientesKeyReleased

    private void tbclientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbclientesMouseClicked
        try
        {
            this.columnacliente=this.tbclientes.getSelectedRow();

            this.txtidclientes.setText(this.tbclientes.getValueAt(this.columnacliente, 0).toString());
            this.txtnombreclientes.setText(this.tbclientes.getValueAt(this.columnacliente, 1).toString());
            this.txttelefonoclientes.setText(this.tbclientes.getValueAt(this.columnacliente, 2).toString());
            this.txtcelularclientes.setText(this.tbclientes.getValueAt(this.columnacliente, 3).toString());
            this.txtcorreoclientes.setText(this.tbclientes.getValueAt(this.columnacliente, 4).toString());
            this.cbdelegacion1.setSelectedItem(this.tbclientes.getValueAt(this.columnacliente, 5).toString());
            this.txtcoloniaclientes.setText(this.tbclientes.getValueAt(this.columnacliente, 6).toString());
            this.txtcalleclientes.setText(this.tbclientes.getValueAt(this.columnacliente, 7).toString());
            this.txtnointclientes.setText(this.tbclientes.getValueAt(this.columnacliente, 8).toString());
            this.txtnoextclientes.setText(this.tbclientes.getValueAt(this.columnacliente, 9).toString());
            this.txtrfcclientes.setText(this.tbclientes.getValueAt(this.columnacliente, 10).toString());
            this.cbmodopago.setSelectedItem(this.tbclientes.getValueAt(this.columnacliente, 11).toString());
            this.txtcuentaclientes.setText(this.tbclientes.getValueAt(this.columnacliente, 12).toString());
            this.txtcontactoclientes.setText(this.tbclientes.getValueAt(this.columnacliente, 13).toString());

            this.txtnombreclientes1.setText(this.tbclientes.getValueAt(this.columnacliente, 15).toString());
            this.txtcoloniaclientes1.setText(this.tbclientes.getValueAt(this.columnacliente, 16).toString());
            this.txtcalleclientes1.setText(this.tbclientes.getValueAt(this.columnacliente, 17).toString());
            this.txtnointclientes1.setText(this.tbclientes.getValueAt(this.columnacliente, 18).toString());
            this.txtnoextclientes1.setText(this.tbclientes.getValueAt(this.columnacliente, 19).toString());
            this.cbdelegacion2.setSelectedItem(this.tbclientes.getValueAt(this.columnacliente, 20).toString());

            if ( Integer.parseInt(this.tbclientes.getValueAt(this.columnacliente, 14).toString() ) == 1 )
            {
                this.chestatusclientes.setSelected(false);
            }
            else
            {
                this.chestatusclientes.setSelected(true);
            }

            this.btnCambiarcliente.setEnabled(true);
        }
        catch(Exception ex)
        {
            
        }
    }//GEN-LAST:event_tbclientesMouseClicked

    //proveedor    
    public void habilitarproveedor()
    {
       this.btnCancelarproveedor.setEnabled(true);
       this.btnGuardarproveedor.setEnabled(true);
       this.btnAgregarproveedor.setEnabled(false);
       this.btnCambiarproveedor.setEnabled(false);
       
       this.txtnombreproveedor.setEnabled(true);
       this.txttelefonoproveedor.setEnabled(true);
       this.txtcpproveedor.setEnabled(true);
       this.txtcoloniaproveedor.setEnabled(true);
       this.txtcalleproveedor.setEnabled(true);
       this.txtcorreoproveedor.setEnabled(true);
       this.txtnointproveedor.setEnabled(true);
       this.txtnoextproveedor.setEnabled(true);
       this.txtsistemacalidad.setEnabled(true);
       this.txtrfcproveedor.setEnabled(true);
       this.cbmodopago1.setEnabled(true);
       this.txtcuentaproveedor.setEnabled(true);
       this.txtcontactoproveedor.setEnabled(true);
       
       this.txtbuscarproveedor.setEnabled(false);
    }
    
    public void deshabilitarproveedor()
    {
       this.btnCancelarproveedor.setEnabled(false);
       this.btnGuardarproveedor.setEnabled(false);
       this.btnAgregarproveedor.setEnabled(true);
       this.btnCambiarproveedor.setEnabled(false);
       
       this.txtnombreproveedor.setEnabled(false);
       this.txttelefonoproveedor.setEnabled(false);
       this.txtcpproveedor.setEnabled(false);
       this.txtcoloniaproveedor.setEnabled(false);
       this.txtcalleproveedor.setEnabled(false);
       this.txtcorreoproveedor.setEnabled(false);
       this.txtsistemacalidad.setEnabled(false);
       this.txtnointproveedor.setEnabled(false);
       this.txtnoextproveedor.setEnabled(false);
       
       this.txtrfcproveedor.setEnabled(false);
       this.cbmodopago1.setEnabled(false);
       this.txtcuentaproveedor.setEnabled(false);
       this.txtcontactoproveedor.setEnabled(false);
    
       this.txtbuscarproveedor.setEnabled(true);
    }
    
    public void limpiarproveedor()
    {
       this.txtnombreproveedor.setText("");
       this.txttelefonoproveedor.setText("");
       this.txtcoloniaproveedor.setText("");
       this.txtcalleproveedor.setText("");
       this.txtcorreoproveedor.setText("");
       this.txtnointproveedor.setText("");
       this.txtnoextproveedor.setText("");
       this.txtsistemacalidad.setText("");
       this.txtrfcproveedor.setText("");
       this.txtcpproveedor.setText("");

       this.txtcuentaproveedor.setText("");
       this.txtcontactoproveedor.setText("");  
       
       this.txtbuscarproveedor.setText("");
   
    }    
     
    
    private void btnCancelarproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarproveedorActionPerformed
        deshabilitarproveedor();
        limpiarproveedor();
    }//GEN-LAST:event_btnCancelarproveedorActionPerformed

    private void btnGuardarproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarproveedorActionPerformed
        try
        {
            if(this.txtnointproveedor.getText().isEmpty())
            {
                this.txtnointproveedor.setText(" ");
                
            }
            
            if(this.txtnoextproveedor.getText().isEmpty())
            {
                this.txtnoextproveedor.setText(" ");
                
            }

            if (this.txtnombreproveedor.getText().isEmpty() || this.txttelefonoproveedor.getText().isEmpty() || this.txtcorreoproveedor.getText().isEmpty() || this.txtcpproveedor.getText().isEmpty() ||
                this.txtcoloniaproveedor.getText().isEmpty() || this.txtcalleproveedor.getText().isEmpty() || this.txtnoextproveedor.getText().isEmpty() || this.txtnointproveedor.getText().isEmpty()
                || this.txtrfcproveedor.getText().isEmpty() || this.txtcuentaproveedor.getText().isEmpty() || this.txtcontactoproveedor.getText().isEmpty() || this.txtsistemacalidad.getText().isEmpty()   ) {
                JOptionPane.showMessageDialog(null, "Algun campo esta vacio");
            }
            else
            {
                if (this.txtcorreoproveedor.getText().matches("^[_A-Za-z0-9-]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9-]+)*(.[A-Za-z]{2,4})$")
                    && this.txtcuentaproveedor.getText().matches("^[0-9]{4}$") && this.txtrfcproveedor.getText().matches("^[A-Z]{3,4}[0-9]{6}[A-Z0-9]{3}$")|| this.txtcpproveedor.getText().matches("^[0-9]+$") ) {

                    if(this.seleccionproveedor==1) 
                    {
                        String query="Insert into proveedores (nombre,telefono,correo,cp,colonia,calle,noint,noext,rfc,idmpago,cuenta,contacto, sistema_calidad) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        PreparedStatement ps= this.dbc.getCnx().prepareStatement(query);

                        ps.setString(1, this.txtnombreproveedor.getText());
                        ps.setString(2, this.txttelefonoproveedor.getText());
                        ps.setString(3, this.txtcorreoproveedor.getText());

                        ps.setString(4, this.txtcpproveedor.getText());

                        ps.setString(5, this.txtcoloniaproveedor.getText() );
                        ps.setString(6, this.txtcalleproveedor.getText());
                        ps.setString(7, this.txtnointproveedor.getText());
                        ps.setString(8, this.txtnoextproveedor.getText());

                        ps.setString(9, this.txtrfcproveedor.getText());
                        ps.setInt(10, this.cbmodopago1.getSelectedIndex()+1);
                        ps.setInt(11, Integer.parseInt(this.txtcuentaproveedor.getText()));
                        ps.setString(12, this.txtcontactoproveedor.getText());
                        ps.setString(13, this.txtsistemacalidad.getText());

                        ps.executeUpdate();
                        ps.close();

                        creaciontablaproveedor();
                    }
                    if(this.seleccionproveedor==2)
                    {
                        String query="Update proveedores set nombre = ? , telefono = ? ,   correo = ? ,  cp = ? , colonia = ? , calle = ? ,  noint = ? ,  noext= ? , rfc = ? ,idmpago = ?,cuenta = ?,contacto = ?, sistema_calidad = ?  where id = ? ";
                        PreparedStatement ps= this.dbc.getCnx().prepareStatement(query);

                        ps.setString(1, this.txtnombreproveedor.getText());
                        ps.setString(2, this.txttelefonoproveedor.getText());
                        ps.setString(3, this.txtcorreoproveedor.getText());

                        ps.setString(4, this.txtcpproveedor.getText());

                        ps.setString(5, this.txtcoloniaproveedor.getText() );
                        ps.setString(6, this.txtcalleproveedor.getText());
                        ps.setString(7, this.txtnointproveedor.getText());
                        ps.setString(8, this.txtnoextproveedor.getText());

                        ps.setString(9, this.txtrfcproveedor.getText());
                        ps.setInt(10, this.cbmodopago1.getSelectedIndex()+1);
                        ps.setInt(11, Integer.parseInt(this.txtcuentaproveedor.getText()));
                        ps.setString(12, this.txtcontactoproveedor.getText());
                        ps.setString(13, this.txtsistemacalidad.getText());

                        ps.setInt(14, Integer.parseInt(this.txtidproveedor.getText()));
                        ps.executeUpdate();
                        ps.close();

                        creaciontablaproveedor();

                    }

                    this.seleccionproveedor=0;
                    deshabilitarproveedor();
                    limpiarproveedor();

                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Algun campo no corresponde con el tipo de dato");
                }
            }

        }
        catch(Exception ex)
        {
             JOptionPane.showMessageDialog(null, "Error de conexion, intente otra vez");
            try {
                this.dbc = new DBcontrolador ();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
            this.con=this.dbc.getCnx();
        }
    }//GEN-LAST:event_btnGuardarproveedorActionPerformed

    private void btnCambiarproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarproveedorActionPerformed
        habilitarproveedor();
        this.seleccionproveedor=2;
    }//GEN-LAST:event_btnCambiarproveedorActionPerformed

    private void btnAgregarproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarproveedorActionPerformed
        habilitarproveedor();
        limpiarproveedor();
        this.seleccionproveedor=1;
    }//GEN-LAST:event_btnAgregarproveedorActionPerformed

    private void txtbuscarproveedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarproveedorKeyReleased
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(this.tabla5);
        this.tbproveedor.setRowSorter(tr);

        tr.setRowFilter(RowFilter.regexFilter(this.txtbuscarproveedor.getText().toUpperCase(),1));
    }//GEN-LAST:event_txtbuscarproveedorKeyReleased

    private void tbproveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbproveedorMouseClicked
        try
        {
            this.columnaproveedor=this.tbproveedor.getSelectedRow();

            this.txtidproveedor.setText(this.tbproveedor.getValueAt(this.columnaproveedor, 0).toString());
            this.txtnombreproveedor.setText(this.tbproveedor.getValueAt(this.columnaproveedor, 1).toString());
            this.txttelefonoproveedor.setText(this.tbproveedor.getValueAt(this.columnaproveedor, 2).toString());
            this.txtcorreoproveedor.setText(this.tbproveedor.getValueAt(this.columnaproveedor, 3).toString());
            this.txtcpproveedor.setText(this.tbproveedor.getValueAt(this.columnaproveedor, 4).toString());
            this.txtcoloniaproveedor.setText(this.tbproveedor.getValueAt(this.columnaproveedor, 5).toString());
            this.txtcalleproveedor.setText(this.tbproveedor.getValueAt(this.columnaproveedor, 6).toString());
            this.txtnointproveedor.setText(this.tbproveedor.getValueAt(this.columnaproveedor, 7).toString());
            this.txtnoextproveedor.setText(this.tbproveedor.getValueAt(this.columnaproveedor, 8).toString());
            this.txtrfcproveedor.setText(this.tbproveedor.getValueAt(this.columnaproveedor, 9).toString());
            this.cbmodopago1.setSelectedItem(this.tbproveedor.getValueAt(this.columnaproveedor, 10).toString());
            this.txtcuentaproveedor.setText(this.tbproveedor.getValueAt(this.columnaproveedor, 11).toString());
            this.txtcontactoproveedor.setText(this.tbproveedor.getValueAt(this.columnaproveedor, 12).toString());
            this.txtsistemacalidad.setText(this.tbproveedor.getValueAt(this.columnaproveedor, 13).toString());

            this.btnCambiarproveedor.setEnabled(true);
        }
        catch(Exception ex)
        {
            
        }
    }//GEN-LAST:event_tbproveedorMouseClicked

    
    //vendedores   
       
    public void deshabilitarvendedor()
    {
        this.txtbuscarvendedores.setEnabled(true);
        
        this.txtnombrevendedor.setEnabled(false);
        this.txttelefonovendedor.setEnabled(false);
        this.txtcorreovendedor.setEnabled(false);
        this.cbdelegacion.setEnabled(false);
        this.txtcoloniavendedor.setEnabled(false);
        this.txtcallevendedor.setEnabled(false);
        this.txtnoextvendedor.setEnabled(false);
        this.txtnointvendedor.setEnabled(false);     
        this.txtidvendedor.setEnabled(false);
        
        this.btnAgregarvendedor.setEnabled(true);
        this.btnCancelarvendedor.setEnabled(false);
        this.btnGuardarvendedor.setEnabled(false);
        this.btnCambiarvendedor.setEnabled(false);

    }
     
      public void habilitarvendedor()
    {
        this.btnCancelarvendedor.setEnabled(true);
       this.btnGuardarvendedor.setEnabled(true);
       this.btnAgregarvendedor.setEnabled(false);
       this.btnCambiarvendedor.setEnabled(false);
       
       this.txtnombrevendedor.setEnabled(true);
       this.txttelefonovendedor.setEnabled(true);
       this.cbdelegacion.setEnabled(true);
       this.txtcoloniavendedor.setEnabled(true);
       this.txtcallevendedor.setEnabled(true);
       this.txtcorreovendedor.setEnabled(true);
       this.txtnointvendedor.setEnabled(true);
       this.txtnoextvendedor.setEnabled(true);
       this.txtbuscarvendedores.setEnabled(false);
    }
    
    public void limpiarvendedor()
    {
       this.txtidvendedor.setText("");
       this.txtnombrevendedor.setText("");
       this.txttelefonovendedor.setText("");;
       this.txtcoloniavendedor.setText("");
       this.txtcallevendedor.setText("");
       this.txtcorreovendedor.setText("");
       this.txtnointvendedor.setText("");
       this.txtnoextvendedor.setText("");
       
       
    }

    
    private void btnCancelarvendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarvendedorActionPerformed
        deshabilitarvendedor();
        limpiarvendedor();
    }//GEN-LAST:event_btnCancelarvendedorActionPerformed

    private void btnGuardarvendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarvendedorActionPerformed
        try
        {
            if(this.txtnointvendedor.getText().isEmpty())
            {
                this.txtnointvendedor.setText(" ");
                
            }
            
            if(this.txtnoextvendedor.getText().isEmpty())
            {
                this.txtnoextvendedor.setText(" ");
                
            }
            if (this.txtnombrevendedor.getText().isEmpty() || this.txttelefonovendedor.getText().isEmpty() || this.txtcorreovendedor.getText().isEmpty() ||
                this.txtcoloniavendedor.getText().isEmpty() || this.txtcallevendedor.getText().isEmpty() || this.txtnoextvendedor.getText().isEmpty() || this.txtnointvendedor.getText().isEmpty() ) {
                JOptionPane.showMessageDialog(null, "Algun campo esta vacio");
            }
            else
            {
                if (this.txtcorreovendedor.getText().matches("^[_a-zA-Z0-9-]+(.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(.[a-zA-Z0-9-]+)*(.[a-zA-Z]{2,4})$") ) {

                    if(this.seleccionvendedor==1)
                    {
                        String query="Insert into vendedores (nombre,telefono,correo,iddelegacion,colonia,calle,noint,noext) values (?,?,?,?,?,?,?,?)";
                        PreparedStatement ps= this.dbc.getCnx().prepareStatement(query);

                        ps.setString(1, this.txtnombrevendedor.getText());
                        ps.setString(2, this.txttelefonovendedor.getText());
                        ps.setString(3, this.txtcorreovendedor.getText());

                        ps.setInt(4, this.cbdelegacion.getSelectedIndex()+1);

                        ps.setString(5, this.txtcoloniavendedor.getText() );
                        ps.setString(6, this.txtcallevendedor.getText());
                        ps.setString(7, this.txtnointvendedor.getText());
                        ps.setString(8, this.txtnoextvendedor.getText());

                        ps.executeUpdate();
                        ps.close();

                        creaciontablavendedores();
                    }
                    if(this.seleccionvendedor==2)
                    {
                        String query="Update vendedores set nombre = ? , telefono = ? ,   correo = ? ,  iddelegacion = ? , colonia = ? , calle = ? ,  noint = ? ,  noext= ?  where id = ? ";
                        PreparedStatement ps= this.dbc.getCnx().prepareStatement(query);

                        ps.setString(1, this.txtnombrevendedor.getText());
                        ps.setString(2, this.txttelefonovendedor.getText());
                        ps.setString(3, this.txtcorreovendedor.getText());

                        ps.setInt(4, this.cbdelegacion.getSelectedIndex()+1);

                        ps.setString(5, this.txtcoloniavendedor.getText() );
                        ps.setString(6, this.txtcallevendedor.getText());
                        ps.setString(7, this.txtnointvendedor.getText());
                        ps.setString(8, this.txtnoextvendedor.getText());

                        ps.setInt(9, Integer.parseInt(this.txtidvendedor.getText() ));
                        ps.executeUpdate();
                        ps.close();

                        creaciontablavendedores();

                    }

                    this.seleccionvendedor=0;
                    deshabilitarvendedor();
                    combobox();

                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Algun campo tiene informacion incorrecta");
                }
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexion, intente otra vez");
            try {
                this.dbc = new DBcontrolador ();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
            this.con=this.dbc.getCnx();
        }
    }//GEN-LAST:event_btnGuardarvendedorActionPerformed

    private void btnCambiarvendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarvendedorActionPerformed
        this.seleccionvendedor=2;
        habilitarvendedor();
    }//GEN-LAST:event_btnCambiarvendedorActionPerformed

    private void btnAgregarvendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarvendedorActionPerformed

        limpiarvendedor();
        this.seleccionvendedor=1;
        habilitarvendedor();

    }//GEN-LAST:event_btnAgregarvendedorActionPerformed

    private void txtbuscarvendedoresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarvendedoresKeyReleased
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(this.tabla_vendedores);
        this.tbvendedores.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(this.txtbuscarvendedores.getText().toUpperCase(),1,4));
    }//GEN-LAST:event_txtbuscarvendedoresKeyReleased

    private void tbvendedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbvendedoresMouseClicked
        try
        {
            this.columnavendedor=this.tbvendedores.getSelectedRow();

            this.txtidvendedor.setText(this.tbvendedores.getValueAt(this.columnavendedor, 0).toString());
            this.txtnombrevendedor.setText(this.tbvendedores.getValueAt(this.columnavendedor, 1).toString());
            this.txttelefonovendedor.setText(this.tbvendedores.getValueAt(this.columnavendedor, 2).toString());
            this.txtcorreovendedor.setText(this.tbvendedores.getValueAt(this.columnavendedor, 3).toString());
            this.cbdelegacion.setSelectedItem(this.tbvendedores.getValueAt(this.columnavendedor, 4).toString());
            this.txtcoloniavendedor.setText(this.tbvendedores.getValueAt(this.columnavendedor, 5).toString());
            this.txtcallevendedor.setText(this.tbvendedores.getValueAt(this.columnavendedor, 6).toString());
            this.txtnointvendedor.setText(this.tbvendedores.getValueAt(this.columnavendedor, 7).toString());
            this.txtnoextvendedor.setText(this.tbvendedores.getValueAt(this.columnavendedor, 8).toString());

            this.btnCambiarvendedor.setEnabled(true);
        }
        catch(Exception ex)
        {
            
        }
    }//GEN-LAST:event_tbvendedoresMouseClicked

//Ingrediente       
    public void colocarproductoin(Producto pro)
    {
        this.txtclaveingrediente.setText(pro.getClave());
        this.txtidingrediente.setText(pro.getId()+"");
        this.txtnombreingrediente.setText(pro.getNombre());
        this.btnAgregaringre.setEnabled(true);
        this.btnquitaringre.setEnabled(true);
        actualizaringrediente();
    }
    
    public  void insetaringre(Ingrediente ing) throws SQLException
    {
        try
        {
            Ingredientes_DB ingre= new Ingredientes_DB(this.con);
            boolean ingre_exis=true;
            for (int i = 0; i < this.tabla_ingredientes.getRowCount(); i++) {
                if (this.tabla_ingredientes.getValueAt(i, 0).toString().compareTo(ing.getId()+"")==0) {
                    ingre_exis=false;
                }
            }
            
            if (ingre_exis) {

                ingre.insert_ingrediente(ing);
                actualizaringrediente();
            }
            else{
                JOptionPane.showMessageDialog(null, "El ingrediente ya estaba seleccionado");
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexion, intente otra vez");
            try {
                this.con=Conexion.getConnection();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Error :"+ ex); 
        }
    }
    
    public void actualizaringrediente()
    {
        try{
            
        this.tabla_ingredientes=(DefaultTableModel) this.tbingredientes.getModel();
        
        this.tabla_ingredientes.setRowCount(0);
        
        Ingredientes_DB ing = new Ingredientes_DB(this.con);
        
        List <Ingrediente> ingres = ing.select_ing(Integer.parseInt(this.txtidingrediente.getText()));
 
        for (int i = 0; i < ingres.size(); i++) {    
            Object[] obj = new Object[5];
            obj[0]=ingres.get(i).getId();
            obj[1]=ingres.get(i).getClave();
            obj[2]=ingres.get(i).getNombre();
            obj[3]=ingres.get(i).getMedida();
            obj[4]=ingres.get(i).getCantidad();
            this.tabla_ingredientes.addRow(obj);
        } 
        
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexion, intente otra vez");
            try {
                this.con=Conexion.getConnection();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Error :"+ ex);
            try {
                this.con=Conexion.getConnection();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        }
    }

    
    private void btnquitaringreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnquitaringreActionPerformed
        try{
            Ingrediente ing = new Ingrediente();
            ing.setId_profinal(Integer.parseInt(this.txtidingrediente.getText()));
            ing.setId(Integer.parseInt(this.tbingredientes.getValueAt(this.columnaingrediente, 0).toString()));         
            Ingredientes_DB ingre = new Ingredientes_DB(this.con);
            ingre.delete_ingrediente(ing);
            actualizaringrediente();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexion, intente otra vez");
            try {
                this.con=Conexion.getConnection();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Error :"+ ex);
            try {
                this.con=Conexion.getConnection();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        }
    }//GEN-LAST:event_btnquitaringreActionPerformed

    private void btnAgregaringreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregaringreActionPerformed
        try {
            Elegir_Producto ep = new Elegir_Producto(this,2,this.con);
            ep.setVisible(true);
            this.setEnabled(false);

        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAgregaringreActionPerformed

    private void tbingredientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbingredientesMouseClicked
        try
        {
            this.columnaingrediente=this.tbingredientes.getSelectedRow();
            this.btnquitaringre.setEnabled(true);
        }
        catch(Exception ex)
        {

        }
    }//GEN-LAST:event_tbingredientesMouseClicked

    private void btnElegir_ingredienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElegir_ingredienteActionPerformed
        try {
            Elegir_Producto ep = new Elegir_Producto(this,1,this.con);
            ep.setVisible(true);
            this.setEnabled(false);

        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnElegir_ingredienteActionPerformed

    
    
    
    //Prueba
    
    public void insertarpro_prueba(Producto pro)
    {
        this.txtidprueba.setText(pro.getId()+"");
        this.txtclaveprueba.setText(pro.getClave());
        this.txtnombreprueba.setText(pro.getNombre());
        this.btnAgregarprueba.setEnabled(true);
        this.btnquitarprueba.setEnabled(true);
        
        actualizarprueba();
    }
    
    public void actualizarprueba()
    {
        try{
         this.tabla_pruebas=(DefaultTableModel) this.tbpruebas.getModel();
        
        this.tabla_pruebas.setRowCount(0);

        Pruebas_DB pr = new Pruebas_DB(this.con);
        
        List <Prueba> prueba = pr.select_pr(Integer.parseInt(this.txtidprueba.getText()));
 
        for (int i = 0; i < prueba.size(); i++) {    
            Object[] obj = new Object[4];
            obj[0]=prueba.get(i).getCategoria();
            obj[1]=prueba.get(i).getDeterminacion();
            obj[2]=prueba.get(i).getParametro();
            obj[3]=prueba.get(i).getMetodo();
            this.tabla_ingredientes.addRow(obj);
        } 
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexion, intente otra vez");
            try {
                this.con=Conexion.getConnection();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Error :"+ ex);
            try {
                this.con=Conexion.getConnection();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        }
    }
    
    private void btnElegirpruebaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElegirpruebaActionPerformed
         try {
            Elegir_Producto ep = new Elegir_Producto(this,3,this.con);
            ep.setVisible(true);
            this.setEnabled(false);

        } catch (SQLException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnElegirpruebaActionPerformed

    private void tbpruebasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbpruebasMouseClicked
        this.columnaprueba=this.tbpruebas.getSelectedRow();
    }//GEN-LAST:event_tbpruebasMouseClicked

    private void btnAgregarpruebaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarpruebaActionPerformed
        try{
            if (this.txtdeterminacion.getText().isEmpty() || this.txtparametro.getText().isEmpty() || this.txtmetodo.getText().isEmpty() ) {
                JOptionPane.showMessageDialog(null, "Algun campo esta vacio");
            }
            else{
                 if (this.txtparametro.getText().matches("^[A-Za-z\\s]+$") || this.txtparametro.getText().matches("^[0-9]+(.[0-9]+)?-[0-9]+(.[0-9]+)?$") || this.txtparametro.getText().matches("^>=[0-9]+(.[0-9]+)?|<=[0-9]+(.[0-9]+)?$") 
                     || this.txtparametro.getText().matches("^[0-9]+(.[0-9]+)?$")     ) {
                     
                    Pruebas_DB pr = new Pruebas_DB(this.con);
                    
                    Prueba prueba = new Prueba();

                    prueba.setId_producto(Integer.parseInt(this.txtidprueba.getText()));
                    int cate=this.cbcategoriaprueba.getSelectedIndex()+1;
                    prueba.setDeterminacion(this.txtdeterminacion.getText() );
                    prueba.setParametro(this.txtparametro.getText());
                    prueba.setMetodo(this.txtmetodo.getText() );

                    pr.insert(prueba, cate);
                    actualizarprueba();
                 }
                 else{
                     JOptionPane.showMessageDialog(null, "Algun campo incorrecto");
                 }

            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexion, intente otra vez");
            try {
                this.con=Conexion.getConnection();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Error :"+ ex);
            try {
                this.con=Conexion.getConnection();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        }
        
    }//GEN-LAST:event_btnAgregarpruebaActionPerformed

    private void btnquitarpruebaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnquitarpruebaActionPerformed
        try{
            Pruebas_DB pr = new Pruebas_DB(this.con);
            
            Prueba prueba = new Prueba();
            prueba.setId_producto(Integer.parseInt(this.txtidprueba.getText()));
            prueba.setDeterminacion(this.tbpruebas.getValueAt(this.columnaprueba, 1).toString());

            pr.delete(prueba);
            actualizarprueba();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error de conexion, intente otra vez");
            try {
                this.con=Conexion.getConnection();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Error :"+ ex);
            try {
                this.con=Conexion.getConnection();
            } catch (SQLException ex1) {
                Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        }
    }//GEN-LAST:event_btnquitarpruebaActionPerformed

 
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
            java.util.logging.Logger.getLogger(Datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                   // new Datos().setVisible(true);
  
            }
        });
 
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Clientes;
    private javax.swing.JPanel Ingredientes;
    private javax.swing.JPanel Inventario;
    private javax.swing.JPanel Productos;
    private javax.swing.JPanel Proovedores;
    private javax.swing.JPanel Pruebas;
    private javax.swing.JPanel Vendedores;
    private javax.swing.JButton btnAgregar_producto;
    private javax.swing.JButton btnAgregarcliente;
    private javax.swing.JButton btnAgregaringre;
    private javax.swing.JButton btnAgregarproveedor;
    private javax.swing.JButton btnAgregarprueba;
    private javax.swing.JButton btnAgregarvendedor;
    private javax.swing.JButton btnCambiar_producto;
    private javax.swing.JButton btnCambiarcliente;
    private javax.swing.JButton btnCambiarinventario;
    private javax.swing.JButton btnCambiarproveedor;
    private javax.swing.JButton btnCambiarvendedor;
    private javax.swing.JButton btnCancelar_producto;
    private javax.swing.JButton btnCancelarcliente;
    private javax.swing.JButton btnCancelarinventario;
    private javax.swing.JButton btnCancelarproveedor;
    private javax.swing.JButton btnCancelarvendedor;
    private javax.swing.JButton btnElegir_ingrediente;
    private javax.swing.JButton btnElegirprueba;
    private javax.swing.JButton btnGuardar_producto;
    private javax.swing.JButton btnGuardarcliente;
    private javax.swing.JButton btnGuardarinventario;
    private javax.swing.JButton btnGuardarproveedor;
    private javax.swing.JButton btnGuardarvendedor;
    private javax.swing.JButton btnquitaringre;
    private javax.swing.JButton btnquitarprueba;
    private javax.swing.JComboBox<String> cbcategoriaproducto;
    private javax.swing.JComboBox<String> cbcategoriaprueba;
    private javax.swing.JComboBox<String> cbdelegacion;
    private javax.swing.JComboBox<String> cbdelegacion1;
    private javax.swing.JComboBox<String> cbdelegacion2;
    private javax.swing.JComboBox<String> cbmedidaproducto;
    private javax.swing.JComboBox<String> cbmodopago;
    private javax.swing.JComboBox<String> cbmodopago1;
    private javax.swing.JComboBox<String> cbprocesoproducto;
    private javax.swing.JComboBox<String> cbvendedor;
    private javax.swing.JCheckBox chestatusclientes;
    private javax.swing.JCheckBox chivaproducto;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel60;
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
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lblcaducidad;
    private javax.swing.JLabel lbliva;
    private javax.swing.JLabel lblpeso;
    private javax.swing.JLabel lblproceso;
    private javax.swing.JLabel lblpventa;
    private javax.swing.JLabel lblsmin;
    private javax.swing.JTable tbclientes;
    private javax.swing.JTable tbingredientes;
    private javax.swing.JTable tbinventario;
    private javax.swing.JTable tbproductos;
    private javax.swing.JTable tbproveedor;
    private javax.swing.JTable tbpruebas;
    private javax.swing.JTable tbvendedores;
    private javax.swing.JTextField txtbuscar_producto;
    private javax.swing.JTextField txtbuscarclientes;
    private javax.swing.JTextField txtbuscarinventario;
    private javax.swing.JTextField txtbuscarproveedor;
    private javax.swing.JTextField txtbuscarvendedores;
    private javax.swing.JTextField txtcaducidadproducto;
    private javax.swing.JTextField txtcalleclientes;
    private javax.swing.JTextField txtcalleclientes1;
    private javax.swing.JTextField txtcalleproveedor;
    private javax.swing.JTextField txtcallevendedor;
    private javax.swing.JTextField txtcantidadinventario;
    private javax.swing.JTextField txtcantidadtinventario;
    private javax.swing.JTextField txtcelularclientes;
    private javax.swing.JTextField txtclaveingrediente;
    private javax.swing.JTextField txtclaveinventario;
    private javax.swing.JTextField txtclaveproducto;
    private javax.swing.JTextField txtclaveprueba;
    private javax.swing.JTextField txtcoloniaclientes;
    private javax.swing.JTextField txtcoloniaclientes1;
    private javax.swing.JTextField txtcoloniaproveedor;
    private javax.swing.JTextField txtcoloniavendedor;
    private javax.swing.JTextField txtcontactoclientes;
    private javax.swing.JTextField txtcontactoproveedor;
    private javax.swing.JTextField txtcorreoclientes;
    private javax.swing.JTextField txtcorreoproveedor;
    private javax.swing.JTextField txtcorreovendedor;
    private javax.swing.JTextField txtcpproveedor;
    private javax.swing.JTextField txtcuentaclientes;
    private javax.swing.JTextField txtcuentaproveedor;
    private javax.swing.JTextField txtdeterminacion;
    private javax.swing.JTextField txtfactinventario;
    private javax.swing.JTextField txtfechainventario;
    private javax.swing.JTextField txtfechainventario1;
    private javax.swing.JTextField txtidclientes;
    private javax.swing.JTextField txtidingrediente;
    private javax.swing.JTextField txtidoppinventario;
    private javax.swing.JTextField txtidproducto;
    private javax.swing.JTextField txtidproveedor;
    private javax.swing.JTextField txtidprueba;
    private javax.swing.JTextField txtidvendedor;
    private javax.swing.JTextField txtloteinventario;
    private javax.swing.JTextField txtmetodo;
    private javax.swing.JTextField txtnoentradainventario;
    private javax.swing.JTextField txtnoextclientes;
    private javax.swing.JTextField txtnoextclientes1;
    private javax.swing.JTextField txtnoextproveedor;
    private javax.swing.JTextField txtnoextvendedor;
    private javax.swing.JTextField txtnointclientes;
    private javax.swing.JTextField txtnointclientes1;
    private javax.swing.JTextField txtnointproveedor;
    private javax.swing.JTextField txtnointvendedor;
    private javax.swing.JTextField txtnombreclientes;
    private javax.swing.JTextField txtnombreclientes1;
    private javax.swing.JTextField txtnombreingrediente;
    private javax.swing.JTextField txtnombreinventario;
    private javax.swing.JTextField txtnombreproducto;
    private javax.swing.JTextField txtnombreproveedor;
    private javax.swing.JTextField txtnombreprueba;
    private javax.swing.JTextField txtnombrevendedor;
    private javax.swing.JTextField txtparametro;
    private javax.swing.JTextField txtpeso_producto;
    private javax.swing.JTextField txtpventaproducto;
    private javax.swing.JTextField txtrfcclientes;
    private javax.swing.JTextField txtrfcproveedor;
    private javax.swing.JTextField txtsistemacalidad;
    private javax.swing.JTextField txtsminproducto;
    private javax.swing.JTextField txttelefonoclientes;
    private javax.swing.JTextField txttelefonoproveedor;
    private javax.swing.JTextField txttelefonovendedor;
    // End of variables declaration//GEN-END:variables
}

