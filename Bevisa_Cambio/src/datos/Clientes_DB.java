
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import negocio.Clientes;

public class Clientes_DB {
   
    private java.sql.Connection userConn;
    
    private final String SELECT="select clientes.id , clientes.nombre, clientes.telefono, clientes.celular, clientes.correo,\n" +
        " delegacion.nombre, clientes.colonia, clientes.calle, clientes.noint, clientes.noext, clientes.rfc, modo_pago.nombre ,"
        + " clientes.cuenta, clientes.contacto, clientes.estatus, clientes.nombrefact,clientes.coloniafact, clientes.callefact, clientes.nointfact, clientes.noextfact, clientes.delefact ,vendedores.nombre\n" +
        " from clientes join delegacion on clientes.iddelegacion = delegacion.id join modo_pago on clientes.idmpago = modo_pago.id"
            + "join vendedores on vendedores.id = clientes.idvendedor";
    
    private final String INSERT="Insert into clientes (nombre,telefono,celular,correo,iddelegacion,colonia,calle,noint,noext,rfc,idmpago,cuenta,contacto,estatus, nombrefact, coloniafact, callefact, nointfact, noextfact, delefact, idvendedor) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        
    private final String UPDATE="Update clientes set nombre = ? , telefono = ?, celular = ? ,   correo = ? ,  iddelegacion = ? , colonia = ? , calle = ? ,  noint = ? ,  noext= ? , rfc = ? ,idmpago = ?,cuenta = ?,contacto = ?,estatus = ? , "
                        + " nombrefact = ? , coloniafact = ?, callefact = ?, nointfact = ?, noextfact = ?, delefact = ?, idvendedor= ?  where id = ? ";
    
    
    public List<Clientes> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Clientes client = null;
        List<Clientes> clientes = new ArrayList<>();
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                client = new Clientes();
                int i=1;
                client.setId(rs.getInt(i++));
                client.setNombre(rs.getString(i++));
                client.setTelefono(rs.getString(i++));
                client.setCelular(rs.getString(i++));
                client.setCorreo(rs.getString(i++));
                client.setDelegacion(rs.getString(i++));
                client.setColonia(rs.getString(i++));
                client.setCalle(rs.getString(i++));
                client.setNo_int(rs.getString(i++));
                client.setNo_ext(rs.getString(i++));
                client.setRfc(rs.getString(i++));
                client.setPago(rs.getString(i++));
                client.setCuenta(rs.getString(i++));
                client.setContacto(rs.getString(i++));
                client.setEstatus(rs.getString(i++));
                
                client.setNombref(rs.getString(i++));
                client.setDelegacionf(rs.getString(i++));
                client.setColoniaf(rs.getString(i++));
                client.setCallef(rs.getString(i++));
                client.setNo_intf(rs.getString(i++));
                client.setNo_extf(rs.getString(i++));
                
                
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
    
    public int insert(Clientes client) throws SQLException 
    {
        Connection conn = null;
        PreparedStatement stmt = null;		
        int rows = 0; 
        try {

            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(this.INSERT);
            int index = 1;//contador de columnas
            
            stmt.setString(index++, client.getNombre());
            stmt.setString(index++, client.getTelefono());
            stmt.setString(index++, client.getCelular());
            stmt.setString(index++, client.getCorreo());
            stmt.setString(index++, client.getDelegacion());
            stmt.setString(index++, client.getColonia());
            stmt.setString(index++, client.getCalle());
            stmt.setString(index++, client.getNo_int());
            stmt.setString(index++, client.getNo_ext());
            stmt.setString(index++, client.getRfc());
            stmt.setString(index++, client.getPago());
            stmt.setString(index++, client.getCuenta());
            stmt.setString(index++, client.getContacto());
            stmt.setString(index++, client.getEstatus());
            
            stmt.setString(index++, client.getNombref());
            stmt.setString(index++, client.getDelegacionf());
            stmt.setString(index++, client.getColoniaf());
            stmt.setString(index++, client.getCallef());
            stmt.setString(index++, client.getNo_intf());
            stmt.setString(index++, client.getNo_extf());

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

    public int update(Clientes client) throws SQLException 
    {
        Connection conn = null;
        PreparedStatement stmt = null;		
        int rows = 0; 
        try {

            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(this.UPDATE);
            int index = 1;//contador de columnas
            
            stmt.setString(index++, client.getNombre());
            stmt.setString(index++, client.getTelefono());
            stmt.setString(index++, client.getCelular());
            stmt.setString(index++, client.getCorreo());
            stmt.setString(index++, client.getDelegacion());
            stmt.setString(index++, client.getColonia());
            stmt.setString(index++, client.getCalle());
            stmt.setString(index++, client.getNo_int());
            stmt.setString(index++, client.getNo_ext());
            stmt.setString(index++, client.getRfc());
            stmt.setString(index++, client.getPago());
            stmt.setString(index++, client.getCuenta());
            stmt.setString(index++, client.getContacto());
            stmt.setString(index++, client.getEstatus());
            
            stmt.setString(index++, client.getNombref());
            stmt.setString(index++, client.getDelegacionf());
            stmt.setString(index++, client.getColoniaf());
            stmt.setString(index++, client.getCallef());
            stmt.setString(index++, client.getNo_intf());
            stmt.setString(index++, client.getNo_extf());
            stmt.setInt(index++, client.getId());
            
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
