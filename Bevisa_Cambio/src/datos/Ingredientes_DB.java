
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import negocio.Ingrediente;
import negocio.Producto;

public class Ingredientes_DB {
    
    private java.sql.Connection userConn;
    
    private final String SELECT_PRO_FI=
            "select productos.id ,  productos.Clave, productos.Nombre, umedida.nombre from productos join umedida on productos.idmedida= umedida.id  where idcategoria = 2 or idcategoria = 4 or idcategoria=1";
    private final String SELECT_PRO_MA=
            "select productos.id ,  productos.Clave, productos.Nombre, umedida.nombre from productos join umedida on productos.idmedida= umedida.id  where idcategoria = 1 or idcategoria = 3; ";
    private final String INSERT_ING=
            "Insert into ingredientes(idproductofinal,idproducto,cantidad) values (?,?,?);";
    
    private final String SELECT_ING=
            "select productos.id ,productos.Clave, productos.Nombre,umedida.Nombre, ingredientes.cantidad from ingredientes join productos on productos.id = ingredientes.idproducto "
            + " join umedida on productos.idmedida= umedida.id where ingredientes.idproductofinal = ? ;";
    
    private final String DELETE_ING=
            "Delete from ingredientes where idproductofinal = ? and idproducto = ?;";
       
    public Ingredientes_DB(Connection userConn) {
        this.userConn = userConn;
    }
    
    public List<Producto> select_pt() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Producto producto = null;
        List<Producto> productos = new ArrayList<>();
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SELECT_PRO_FI);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id= rs.getInt(1);
                String clave = rs.getString(2);
                String nombre = rs.getString(3);
                String medida = rs.getString(4);
                producto = new Producto();
                producto.setId(id);
                producto.setClave(clave);
                producto.setNombre(nombre);
                producto.setMedida(medida);
                productos.add(producto);      
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
    
    public List<Producto> select_ma() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Producto producto = null;
        List<Producto> productos = new ArrayList<>();
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SELECT_PRO_MA);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id= rs.getInt(1);
                String clave = rs.getString(2);
                String nombre = rs.getString(3);
                String medida = rs.getString(4);
                producto = new Producto();
                producto.setId(id);
                producto.setClave(clave);
                producto.setNombre(nombre);
                producto.setMedida(medida);
                productos.add(producto);      
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
    
    public List<Ingrediente> select_ing(int ids) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Ingrediente> ingres = new ArrayList<>();
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SELECT_ING);
            stmt.setInt(1, ids);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id= rs.getInt(1);
                String clave = rs.getString(2);
                String nombre = rs.getString(3);
                String medida = rs.getString(4);
                Double cantidad=rs.getDouble(5);
                Ingrediente ingre = new Ingrediente();
                ingre.setId(id);
                ingre.setClave(clave);
                ingre.setNombre(nombre);
                ingre.setMedida(medida);
                ingre.setCantidad(cantidad);
                ingres.add(ingre);     
                
            }
            return ingres;
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        
    }
    
    public void insert_ingrediente(Ingrediente ingre) throws SQLException 
    {
        Connection conn = null;
        PreparedStatement stmt = null;		
        int rows = 0; 
        try {

            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(this.INSERT_ING);
            int index = 1;//contador de columnas
            
            stmt.setInt(index++, ingre.getId_profinal());
            stmt.setInt(index++, ingre.getId());
            stmt.setDouble(index++, ingre.getCantidad());
            stmt.executeUpdate();
            
        } 
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
        finally {
            Conexion.close(stmt);
            //Unicamente cerramos la conexión si fue creada en este metodo
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        
    }
    
    public int delete_ingrediente(Ingrediente ingre) throws SQLException 
    {
        Connection conn = null;
        PreparedStatement stmt = null;		
        int rows = 0; 
        try {

            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(this.DELETE_ING);
            int index = 1;//contador de columnas
            
            stmt.setInt(index++, ingre.getId_profinal());
            stmt.setInt(index++, ingre.getId());
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
