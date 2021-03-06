
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import negocio.Prueba;

public class Pruebas_DB {

    public Pruebas_DB(Connection userConn) {
        this.userConn = userConn;
    }
    
    private java.sql.Connection userConn;
    
    private final String SELECT_PRUEBAS="select categoria_prueba.nombre, pruebas.determinacion, pruebas.parametro, pruebas.metodo from pruebas \n" +
                     " join categoria_prueba on categoria_prueba.id = pruebas.idcategoria where pruebas.idproducto =  ?";
    
    private final String INSERT="Insert into pruebas(idproducto, idcategoria, determinacion,parametro,metodo) values (?,?,?,?,?)";
    
    private final String DELETE="Delete from pruebas where idproducto = ? and determinacion = '?' ";
    
    public List<Prueba> select_pr(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Prueba prueba = new Prueba();
        List<Prueba> pruebas = new ArrayList<>();
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SELECT_PRUEBAS);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                prueba.setCategoria(rs.getString(1));
                prueba.setDeterminacion(rs.getString(2));
                prueba.setParametro(rs.getString(3));
                prueba.setMetodo(rs.getString(4));

                pruebas.add(prueba);      
            }
            return pruebas;
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        
    }
    
    public int insert(Prueba pr,int cate) throws SQLException 
    {
        Connection conn = null;
        PreparedStatement stmt = null;		
        int rows = 0; 
        try {

            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(this.INSERT);
            int index = 1;//contador de columnas
            
            stmt.setInt(index++, pr.getId_producto());
            stmt.setInt(index++, cate);
            stmt.setString(index++, pr.getDeterminacion());
            stmt.setString(index++, pr.getParametro());
            stmt.setString(index++, pr.getMetodo());
            

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
    
    public int delete(Prueba pr) throws SQLException 
    {
        Connection conn = null;
        PreparedStatement stmt = null;		
        int rows = 0; 
        try {

            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(this.DELETE);
            int index = 1;//contador de columnas
            
            stmt.setInt(index++, pr.getId_producto());
            stmt.setString(index++, pr.getDeterminacion());
 
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
    
    
    
    

    
