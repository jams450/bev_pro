
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import negocio.*;

public class Ventas_DB {
    
     private java.sql.Connection userConn;
     
     private final String INSERT="insert into ventas (fecha,idvendedor,idcliente,importe,iva,total,tipo,op,idestatus) values(?,?,?,?,?,?,?,?,1)";
     
     private final String INSERT_DV="insert into ventas_productos (idventa,idinven,idproducto,pventa,cantidad,importe,descuento,idescuento,iva,total,comision) values (?,?,?,?,?,?,?,?,?,?,?)";
     
     private final String SELECT="select * from ventas_tipo";
     
     private final String SELECT_PROD="select productos.id ,  productos.Clave, productos.Nombre, productos.Pventa, productos.iva from productos join umedida on productos.idmedida= umedida.id  where idcategoria = 2 or idcategoria = 4; ";
     
     private final String SELECT_CLIENTES="select clientes.id, clientes.nombre, clientes.telefono, clientes.correo,vendedores.id,vendedores.nombre from clientes join vendedores on vendedores.id=clientes.idvendedor";
     
     private final String SELECT_VENDEDORES="select vendedores.id, vendedores.nombre, vendedores.telefono, vendedores.correo  from vendedores";
     
     private final String VALIDACION_PRODVTAS="select sum(cantidadactual) from inventario where idproducto = ?";
     
     private final String VALIDACION_LOTES= "select id,cantidadactual from inventario where idproducto = ? and cantidadactual > 0"; 
     
     private final String UPDATE_INV="update inventario set cantidadactual = ? where id = ?";
     
     private final String SELECT_ID="select MAX(id) from ventas";
     
     private final String SELECT_VENTA="select id from ventas where id= ? and idestatus= 1";
     
     private final String SELECT_DETALLE_VENTA="select idinven,cantidad from ventas_productos where idventa= ?";
     
     private final String UPDATE_VENTA="update ventas set idestatus = 2 where id= ?";
     
     private final String UPDATE_INV_CAN="update inventario set cantidadactual = cantidadactual + ? where id= ?";
     
     public Ventas_DB(Connection userConn) {
        this.userConn = userConn;
    }
     
    public int insert(Ventas vta) throws SQLException 
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0; 
        try {

            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(this.INSERT);
            int index = 1;//contador de columnas
            
            stmt.setString(index++, vta.getFecha());
            stmt.setString(index++, vta.getIdvendedor());
            stmt.setString(index++, vta.getIdcliente());
            stmt.setDouble(index++, vta.getImporte());
            stmt.setDouble(index++, vta.getIva());
            stmt.setDouble(index++, vta.getTotal());
            stmt.setString(index++, vta.getTipo());
            stmt.setString(index++, vta.getOp());
            stmt.executeUpdate();
            
            stmt = conn.prepareStatement(this.SELECT_ID);
            rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
            return id;
        } finally {
            Conexion.close(stmt);
            //Unicamente cerramos la conexión si fue creada en este metodo
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        
    } 
    
    public int insert_dv(DetalleVenta det) throws SQLException 
    {
        Connection conn = null;
        PreparedStatement stmt = null;		
        int rows = 0; 
        try {

            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(this.INSERT_DV);
            int index = 1;//contador de columnas
            stmt.setInt(index++, det.getIdventa());
            stmt.setInt(index++, det.getIdinven());
            stmt.setInt(index++, det.getIdproducto());
            stmt.setDouble(index++, det.getPventa());
            stmt.setDouble(index++, det.getCantidad());
            stmt.setDouble(index++, det.getImporte());
            stmt.setDouble(index++, det.getDescuento());
            stmt.setDouble(index++, det.getIdescuento());
            stmt.setDouble(index++, det.getIva());
            stmt.setDouble(index++, det.getTotal());
            stmt.setDouble(index++, det.getComision());
            rows = stmt.executeUpdate();  
            System.out.println("sip");    
        } 
        finally {
        Conexion.close(stmt);
        //Unicamente cerramos la conexión si fue creada en este metodo
        if (this.userConn == null) {
            Conexion.close(conn);
            }
        }
        return rows;
    } 
    
    public List<String> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> venta_tipo = new ArrayList<>();
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString(2);
                
                venta_tipo.add(nombre);      
            }
            return venta_tipo;
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        
    }
    
     public List<Producto> select_prod() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Producto> productos = new ArrayList<>();
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SELECT_PROD);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Producto  pro = new Producto();
                pro.setId(rs.getInt(1));
                pro.setClave(rs.getString(2));
                pro.setNombre(rs.getString(3));
                pro.setPventa(rs.getDouble(4));
                pro.setIva(rs.getInt(5));

                productos.add(pro);
            }
            return productos;
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        
    }
    
     public List<Clientes> select_cliente() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Clientes> clientes = new ArrayList<>();
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SELECT_CLIENTES);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Clientes  clie = new Clientes();
                clie.setId(rs.getInt(1));
                clie.setNombre(rs.getString(2));
                clie.setTelefono(rs.getString(3));
                clie.setCorreo(rs.getString(4));
                //las siguientes líneas son para guardar datos del vendedor pero como no hay tales datos, se ocupan estos dos
                clie.setNombref(rs.getString(5));  
                clie.setVendedor(rs.getString(6));
               

                clientes.add(clie);
            }
            return clientes;
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        
    }
     
     public List<Vendedor> select_vendedor() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vendedor> vendedores = new ArrayList<>();
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SELECT_VENDEDORES);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Vendedor ven = new Vendedor();
                ven.setId(rs.getInt(1));
                ven.setNombre(rs.getString(2));
                ven.setTelefono(rs.getString(3));
                ven.setCorreo(rs.getString(4));

                vendedores.add(ven);
            }
            return vendedores;
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        
    }
     
    public double validacion_prodvtas(String producto) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
       double sumatoria=0;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(VALIDACION_PRODVTAS);
            stmt.setString(1,producto);
            rs = stmt.executeQuery();
            while (rs.next()) {
                sumatoria= rs.getDouble(1);
            }
            return sumatoria;
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        
    }
    
    public List<String[]> validacion_lotes(String producto) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String[]> lotes = new ArrayList<>();
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(VALIDACION_LOTES);
            stmt.setString(1,producto);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String[] lote= new String[2];
                lote[0]=rs.getString(1);
                lote[1]=rs.getString(2);
                lotes.add(lote);
            }
            return lotes;
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        
    }
    
    public int update_inv(Inventario inven) throws SQLException 
    {
        Connection conn = null;
        PreparedStatement stmt = null;		
        int rows = 0; 
        try {

            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(this.UPDATE_INV);
            int index = 1;//contador de columnas
            
            stmt.setDouble(index++, inven.getCantidad_actual());            
            stmt.setInt(index++, inven.getId());
            
            rows = stmt.executeUpdate();
        } finally {
            Conexion.close(stmt);
            //Unicamente cerramos la conexión si fue creada en este metodo
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        return rows;
    }

    public boolean cancelacion(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean si=false;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(this.SELECT_VENTA);
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            if (rs.first()) {
                si=true;
                stmt = conn.prepareStatement(this.UPDATE_VENTA);
                stmt.setString(1, id);
                stmt.executeUpdate();
                
                stmt = conn.prepareStatement(this.SELECT_DETALLE_VENTA);
                stmt.setString(1, id);
                rs = stmt.executeQuery();
                while(rs.next())
                {
                    stmt = conn.prepareStatement(this.UPDATE_INV_CAN);
                    stmt.setInt(1, rs.getInt(2));
                    stmt.setInt(2, rs.getInt(1));
                    stmt.executeUpdate();
                }
            }
            return si;
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        
    }

    
    
}
