
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
            + " join vendedores on vendedores.id = clientes.idvendedor order by id";
    
    private final String INSERT="Insert into clientes (nombre,telefono,celular,correo,iddelegacion,colonia,calle,noint,noext,rfc,idmpago,cuenta,contacto,estatus, nombrefact, coloniafact, callefact, nointfact, noextfact, delefact, idvendedor) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        
    private final String UPDATE="Update clientes set nombre = ? , telefono = ?, celular = ? ,   correo = ? ,  iddelegacion = ? , colonia = ? , calle = ? ,  noint = ? ,  noext= ? , rfc = ? ,idmpago = ?,cuenta = ?,contacto = ?,estatus = ? , "
                        + " nombrefact = ? , coloniafact = ?, callefact = ?, nointfact = ?, noextfact = ?, delefact = ?, idvendedor= ?  where id = ? ";
    
    private final String DELE="select * from delegacion;";
    private final String PAGO="select * from modo_pago";
    private final String VENDEDOR="select id,nombre from vendedores";
    
    private final String SELECT_NOMBRE="select * from clientes where nombre=? ";
    
    public Clientes_DB(Connection userConn) {
        this.userConn = userConn;
    }
    
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

                client.setId(rs.getInt(1));
                client.setNombre(rs.getString(2));
                client.setTelefono(rs.getString(3));
                client.setCelular(rs.getString(4));
                client.setCorreo(rs.getString(5));
                client.setDelegacion(rs.getString(6));
                client.setColonia(rs.getString(7));
                client.setCalle(rs.getString(8));
                client.setNo_int(rs.getString(9));
                client.setNo_ext(rs.getString(10));
                client.setRfc(rs.getString(11));
                client.setPago(rs.getString(12));
                client.setCuenta(rs.getString(13));
                client.setContacto(rs.getString(14));
                client.setEstatus(rs.getString(15));
                
                client.setNombref(rs.getString(16));
                client.setDelegacionf(rs.getString(17));
                client.setColoniaf(rs.getString(18));
                client.setCallef(rs.getString(19));
                client.setNo_intf(rs.getString(20));
                client.setNo_extf(rs.getString(21));               
                client.setVendedor(rs.getString(22));
                
                clientes.add(client);
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
    
    public List<String> combo_dele() throws SQLException
    {
         Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> combo = new ArrayList<>();
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(DELE);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString(2);      
                combo.add(nombre);      
            }
            return combo;
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
    }
    
    public List<String> combo_pago() throws SQLException
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> combo = new ArrayList<>();
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(PAGO);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString(2);      
                combo.add(nombre);      
            }
            return combo;
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
    }
    
    public List<String> combo_vendedor() throws SQLException
    {
         Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> combo = new ArrayList<>();
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(VENDEDOR);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString(2);      
                combo.add(nombre);    
            }
            return combo;
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
            stmt.setString(index++, client.getColoniaf());
            stmt.setString(index++, client.getCallef());
            stmt.setString(index++, client.getNo_intf());
            stmt.setString(index++, client.getNo_extf());
            stmt.setString(index++, client.getDelegacionf());
            stmt.setString(index++, client.getVendedor());


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
            stmt.setString(index++, client.getColoniaf());
            stmt.setString(index++, client.getCallef());
            stmt.setString(index++, client.getNo_intf());
            stmt.setString(index++, client.getNo_extf());
            stmt.setString(index++, client.getDelegacionf());
            stmt.setString(index++, client.getVendedor());
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
     
     public boolean existe_cliente(String clave)throws SQLException 
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe=true;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(this.SELECT_NOMBRE);
            stmt.setString(1, clave);
            rs = stmt.executeQuery();
            if (rs.first()) {
                existe=false;
            }
            return existe;
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
    }
   
   
}
