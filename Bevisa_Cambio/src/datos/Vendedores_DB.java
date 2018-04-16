
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import negocio.Vendedor;


public class Vendedores_DB {
   
    private java.sql.Connection userConn;
    
    private final String SELECT="select vendedores.id , vendedores.nombre, vendedores.telefono, vendedores.correo,\n" +
        " delegacion.nombre, vendedores.colonia, vendedores.calle,vendedores.noint,vendedores.noext\n" +
        " from vendedores join delegacion on vendedores.iddelegacion = delegacion.id";
    
    private final String INSERT="Insert into vendedores (nombre,telefono,correo,iddelegacion,colonia,calle,noint,noext) values (?,?,?,?,?,?,?,?)";
    
    private final String UPDATE="Update vendedores set nombre = ? , telefono = ? ,   correo = ? ,  iddelegacion = ? , colonia = ? , calle = ? ,  noint = ? ,  noext= ?  where id = ? ";
    
    public List<Vendedor> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Vendedor vende = null;
        List<Vendedor> vendedores = new ArrayList<>();
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                vende = new Vendedor();
                vende.setId(rs.getInt(1));
                vende.setNombre(rs.getString(2));
                vende.setTelefono(rs.getString(3));
                vende.setCorreo(rs.getString(4));
                vende.setDele_muni(rs.getString(5));
                vende.setColonia(rs.getString(6));
                vende.setCalle(rs.getString(7));
                vende.setNoint(rs.getString(8));
                vende.setNoext(rs.getString(9));
                vendedores.add(vende);
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
    
    public int insert(Vendedor vende) throws SQLException 
    {
        Connection conn = null;
        PreparedStatement stmt = null;		
        int rows = 0; 
        try {

            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(this.INSERT);
            int index = 1;//contador de columnas
            
            stmt.setString(index++, vende.getNombre());
            stmt.setString(index++, vende.getTelefono());
            stmt.setString(index++, vende.getCorreo());
            stmt.setString(index++, vende.getDele_muni());
            stmt.setString(index++, vende.getColonia());
            stmt.setString(index++, vende.getCalle());
            stmt.setString(index++, vende.getNoint());
            stmt.setString(index++, vende.getNoext());
            
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

    public Vendedores_DB(Connection userConn) {
        this.userConn = userConn;
    }

    public int update(Vendedor vende) throws SQLException 
    {
        Connection conn = null;
        PreparedStatement stmt = null;		
        int rows = 0; 
        try {

            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(this.UPDATE);
            int index = 1;//contador de columnas
            
            stmt.setString(index++, vende.getNombre());
            stmt.setString(index++, vende.getTelefono());
            stmt.setString(index++, vende.getCorreo());
            stmt.setString(index++, vende.getDele_muni());
            stmt.setString(index++, vende.getColonia());
            stmt.setString(index++, vende.getCalle());
            stmt.setString(index++, vende.getNoint());
            stmt.setString(index++, vende.getNoext());
            stmt.setInt(index++, vende.getId());
            
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
