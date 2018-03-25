
package datos;
import negocio.Usuario;
import java.sql.*;
import java.util.*;

public class Login_DB {
    
    //Variable que almacena una conexion como referencia
    //esta opcion se recibe en el constructor de esta clase
    //y permite reutilizar la misma conexion para ejecutar
    //varios queries de esta clase, opcionalmente se puede
    //utilizar para el uso de una transaccion en SQL
    private java.sql.Connection userConn;
    //Se utiliza un prepareStatement, por lo que podemos
    //utilizar parametros (signos de ?)

    private final String SQL_SELECT
            = "SELECT * FROM usuarios ORDER BY id";

    /*
     * Agregamos el constructor vacio
     */
    public Login_DB() {    
    }
    
    public Login_DB(Connection conn) {
        this.userConn = conn;
    }
    
    /**
     * Metodo que imprime el contenido de la tabla de personas
     *
     * @return Lista de objetos Persona
     * @throws SQLException
     */
    public List<Usuario> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario user = null;
        List<Usuario> users = new ArrayList<>();
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String usuario = rs.getString(2);
                String pass = rs.getString(3);
                String nombre = rs.getString(4);
                int perfil = rs.getInt(5);
                
                user = new Usuario(id,usuario,pass,nombre,perfil);
                users.add(user);
            }
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        return users;
    }
    
}
