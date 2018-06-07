
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import negocio.Proveedores;

public class Proveedores_DB {
    
    private java.sql.Connection userConn;
    
    private final String SELECT="select proveedores.id , proveedores.nombre, proveedores.telefono, proveedores.correo,\n" +
        " proveedores.cp, proveedores.colonia, proveedores.calle, proveedores.noint, proveedores.noext, proveedores.rfc, modo_pago.nombre ,"
        + " proveedores.cuenta, proveedores.contacto, proveedores.sistema_calidad \n" +
        " from proveedores join modo_pago on proveedores.idmpago = modo_pago.id";
    
    private final String INSERT="Insert into proveedores (nombre,telefono,correo,cp,colonia,calle,noint,noext,rfc,idmpago,cuenta,contacto, sistema_calidad) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    private final String UPDATE="Update proveedores set nombre = ? , telefono = ? ,   correo = ? ,  cp = ? , colonia = ? , calle = ? ,  noint = ? ,  noext= ? , rfc = ? ,idmpago = ?,cuenta = ?,contacto = ?, sistema_calidad = ?  where id = ? ";
    
    private final String PAGO = "select * from modo_pago";
    
    private final String SELECT_NOMBRE="select * from proveedores where nombre=? ";
    
    public Proveedores_DB(Connection userConn) {  //constructor
        this.userConn = userConn;
    }

    public List<Proveedores> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null; //AYUDA a cargar query
        ResultSet rs = null;  //donde se guardan los resultados de los query's
        Proveedores prov = null; //objeto de la clase
        List<Proveedores> proveedores = new ArrayList<>();  //instanciar o crear objeto de la clase
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();  
                //si la conexión que me pasaron es dif de nulo, usar esa conexió; si no, crear una nueva
            stmt = conn.prepareStatement(SELECT); //CARGA query
            rs = stmt.executeQuery(); //realiza query y su resultado se guarda en rs
            while (rs.next()) {
                prov = new Proveedores(); //crear nueva instancia de la clase
                prov.setId(rs.getInt(1));
                prov.setNombre(rs.getString(2));
                prov.setTelefono(rs.getString(3));
                prov.setCorreo(rs.getString(4));
                prov.setCp(rs.getString(5));
                prov.setColonia(rs.getString(6));
                prov.setCalle(rs.getString(7));
                prov.setNoint(rs.getString(8));
                prov.setNoext(rs.getString(9));
                prov.setRfc(rs.getString(10));
                prov.setIdmpago(rs.getString(11));
                prov.setCuenta(rs.getString(12));
                prov.setContacto(rs.getString(13));
                prov.setSistema_calidad(rs.getString(14));               
                proveedores.add(prov); //ir añadiendo a la lista el nuevo proveedor
            }
            return proveedores; 
        } finally { //no importa que pase, siempre haga lo siguiente
            Conexion.close(rs);  //cerrar conexión (ResultSet)
            Conexion.close(stmt); //cerrar conexión (PrepareStatement)
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
    
    public int insert(Proveedores prov) throws SQLException 
    {
        Connection conn = null;
        PreparedStatement stmt = null;		
        int rows = 0; 
        try {

            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(this.INSERT);
            int index = 1;//contador de columnas
            
            stmt.setString(index++, prov.getNombre());
            stmt.setString(index++, prov.getTelefono());
            stmt.setString(index++, prov.getCorreo());
            stmt.setString(index++, prov.getCp());
            stmt.setString(index++, prov.getColonia());
            stmt.setString(index++, prov.getCalle());
            stmt.setString(index++, prov.getNoint());
            stmt.setString(index++, prov.getNoext());
            stmt.setString(index++, prov.getRfc());
            stmt.setString(index++, prov.getIdmpago());
            stmt.setString(index++, prov.getCuenta());
            stmt.setString(index++, prov.getContacto());
            stmt.setString(index++, prov.getSistema_calidad());
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

    public int update(Proveedores prov) throws SQLException 
    {
        Connection conn = null;
        PreparedStatement stmt = null;		
        int rows = 0; 
        try {

            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(this.UPDATE);
            int index = 1;//contador de columnas
            
            stmt.setString(index++, prov.getNombre());
            stmt.setString(index++, prov.getTelefono());
            stmt.setString(index++, prov.getCorreo());
            stmt.setString(index++, prov.getCp());
            stmt.setString(index++, prov.getColonia());
            stmt.setString(index++, prov.getCalle());
            stmt.setString(index++, prov.getNoint());
            stmt.setString(index++, prov.getNoext());
            stmt.setString(index++, prov.getRfc());
            stmt.setString(index++, prov.getIdmpago());
            stmt.setString(index++, prov.getCuenta());
            stmt.setString(index++, prov.getContacto());
            stmt.setString(index++, prov.getSistema_calidad());
            stmt.setInt(index++, prov.getId()); //como el where va hasta el final y se basa en el id, por eso también se pone al final
            
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
    
    public boolean existe_proveedor(String clave)throws SQLException 
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
