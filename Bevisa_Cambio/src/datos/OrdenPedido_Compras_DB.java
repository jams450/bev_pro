
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import negocio.*;

public class OrdenPedido_Compras_DB {
    
    private java.sql.Connection userConn;
    
    private final String SELECT_CLIENTES="select clientes.id, clientes.nombre, clientes.telefono, clientes.correo  from clientes";
    
    private final String SELECT_PRODUCTOS="select productos.id , productos.Nombre, productos.Clave, umedida.nombre,productos.pventa,productos.iva,monedas.nombre "
                    + " from productos join umedida on productos.idmedida= umedida.id join monedas on monedas.id=productos.idmoneda where idcategoria = 2 or idcategoria = 4   ";
        
    private final String INSERT_INVENTARIO="Insert into inventario(idproducto,fechaentrada,cantidadactual,lote,idopp,facturano,cantidad,costo,fechacaducidad,idmoneda) values (?,?,?,?,?,?,?,?,?,?)";
    
    private final String SELECT_MAX="select max(id) from inventario";
    
    private final String UPDATE_OPP="update opproveedores set idestatus = 2 where id = ?";
    
    public OrdenPedido_Compras_DB(Connection userConn) {
        this.userConn = userConn;
    }
    
    public List<Clientes> select_clientes() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Clientes cl = null;
        List<Clientes> clientes = new ArrayList<>();
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SELECT_CLIENTES);
            rs = stmt.executeQuery();
            while (rs.next()) {
                cl = new Clientes();

                cl.setId(rs.getInt(1));
                cl.setNombre(rs.getString(2));
                cl.setTelefono(rs.getString(3));
                cl.setCorreo(rs.getString(4));
                
                clientes.add(cl);
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
    
    public List<Producto> select_productos() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Producto pro = null;
        List<Producto> Productos = new ArrayList<>();
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SELECT_PRODUCTOS);
            rs = stmt.executeQuery();
            while (rs.next()) {
                pro = new Producto();

                pro.setId(rs.getInt(1));
                pro.setNombre(rs.getString(2));
                pro.setClave(rs.getString(3));
                pro.setMedida(rs.getString(4));
                pro.setPventa(rs.getDouble(5));
                pro.setIva(rs.getInt(6));
                pro.setMoneda(rs.getString(7));
                
                Productos.add(pro);
            }
            return Productos;
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        
    }
    
    public int insert_inventario(Inventario inven) throws SQLException 
    {
        Connection conn = null;
        PreparedStatement stmt = null;		
        int rows = 0; 
        try {

            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(this.INSERT_INVENTARIO);
            int index = 1;//contador de columnas
            //id->productos
            stmt.setInt(index++, inven.getId());
            stmt.setString(index++, inven.getFecha_entrada());
            stmt.setDouble(index++, inven.getCantidad_actual());
            stmt.setString(index++, inven.getLote());
            stmt.setInt(index++, inven.getIdopp());
            stmt.setString(index++, inven.getFactura()); 
            stmt.setDouble(index++, inven.getCantidad());
            stmt.setDouble(index++, inven.getCosto());
            stmt.setString(index++, inven.getFecha_caducidad()); 
            stmt.setInt(index++, inven.getIdmoneda());
            
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
    
    public int select_max() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int max=0;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SELECT_MAX);
            rs = stmt.executeQuery();
            while (rs.next()) {
                max=rs.getInt(1);
            }
            return max;
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        
    }
 
    public int update(int id) throws SQLException 
    {
        Connection conn = null;
        PreparedStatement stmt = null;		
        int rows = 0; 
        try {

            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(this.UPDATE_OPP);
            int index = 1;//contador de columnas
            
            stmt.setInt(index++, id);
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
     
    

    
    
}
