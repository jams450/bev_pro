
package datos;
import java.sql.*;


public class Conexion {
    
    //Valores de conexion a MySql

    private static String JDBC_DRIVER = "com.mysql.jdbc.Driver"; //dirección del driver
    //El puerto es opcional  192.168.1.71
    private static String JDBC_URL = "jdbc:mysql://localhost/insetelc_Bevisa?useSSL=false";
    private static String JDBC_USER = "root";
    private static String JDBC_PASS = "1234";
    
    private static Driver driver = null; //para mantener el driver en esta variable
    //además, evita repetir los mismos pasos para conectar una y otra vez
    
    //Para que no haya problemas al obtener la conexion de
    //manera concurrente, se usa la palabra synchronized
    public static synchronized Connection getConnection() throws SQLException {
        if (driver == null) { //la 1ra vez siempre es igual a null
            try {
                //Se registra el driver
                //cargar el controlador y establecer la conexión
                Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
                driver = (Driver) jdbcDriverClass.newInstance();
                DriverManager.registerDriver(driver);
            } catch (Exception e) {
                System.out.println("Fallo en cargar el driver JDBC");
                e.printStackTrace();
            }
        }
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }

    //Cierre del resultSet
    public static void close(ResultSet rs) { //se guardan resultados de los querys, por ej. si usamos un select, el resultado se guarda aqui
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    //Cierre del PrepareStatement
    public static void close(PreparedStatement stmt) { //carga la instrucción del query hacia la conexión, en relación con los "?" para
        //ingresar datos o variables con ayuda de los querys, por ej. "Update tabla set variable=?, variable=?,..."
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    //Cierre de la conexion
    public static void close(Connection conn) { //para cerrar la conexión con la bd
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
    
    
}
