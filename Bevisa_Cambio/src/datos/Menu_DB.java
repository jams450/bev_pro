
package datos;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;


public class Menu_DB {

    private java.sql.Connection userConn;
    
    private final String SELECT_STOCK
            = "select sum(inventario.cantidadactual),productos.nombre,productos.smin \n" +
            "from productos left join inventario on productos.id=inventario.idproducto \n" +
            "where idcategoria = 1 or idcategoria= 4 or idcategoria = 3 \n" +
            "group by productos.id";
    private final String SELECT_CADUCIDAD
            = "select inventario.id,productos.nombre, inventario.fechacaducidad from inventario \n" +
              " join productos on productos.id=inventario.idproducto where inventario.cantidadactual >0 and productos.idcategoria != 3";
     
    
    public Menu_DB(Connection userConn) {
        this.userConn = userConn;
    }
    
    public  List<String[]> select_st() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;;
        List<String[]> stock = new ArrayList<>();
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(this.SELECT_STOCK);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String[] s = new String[3];
                s[0]= rs.getString(1);
                s[1]= rs.getString(2);
                s[2]= rs.getString(3);
                stock.add(s);
            }
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        return stock;
    }
    
    
     public List<String[]> select_ca() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;;
        List<String[]> ca = new ArrayList<>();
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(this.SELECT_CADUCIDAD);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String[] s = new String[3];
                s[0]= rs.getString(1);
                s[1]= rs.getString(2);
                s[2]= rs.getString(3);
                ca.add(s);
            }
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        return ca;
    }
     
    public String notificaciones() throws SQLException{
            List <String[]> op = new ArrayList<>();
            op=this.select_st();
            String alerta="";
            for (int i = 0; i < op.size(); i++) {

                if (op.get(i)[0] == null) {
                    if (Double.parseDouble(op.get(i)[2]) != 0) {
                        alerta+="No hay suficente stock del producto "+op.get(i)[1]+ " falta "+ op.get(i)[2] +"\n";
                    } 
                }
                else
                {
                    
                    if (Double.parseDouble(op.get(i)[2]) > Double.parseDouble(op.get(i)[0])) { 
                    alerta+="No hay suficente stock del producto "+op.get(i)[1]+ " falta "+ (Double.parseDouble(op.get(i)[2]) - Double.parseDouble(op.get(i)[0])) +"\n";
                    }
                }
            }
            

            op.clear();
            op = this.select_ca();
            LocalDate ahora = LocalDate.now().plus(Period.ofMonths(2));
            LocalDate ahora2 = LocalDate.now();
            for (int i = 0; i < op.size(); i++) {
                LocalDate caducidad = LocalDate.parse(op.get(i)[2]);
                if (caducidad.isBefore(ahora2)) {
                    alerta+="El producto "+op.get(i)[1]+" con el no. entrada "+op.get(i)[0] +" ya caduco \n";
                }
                else{
                    if (caducidad.isBefore(ahora)) {      
                        alerta+="El producto "+op.get(i)[1]+" con el no. entrada "+op.get(i)[0] +" esta proximo a caducar\n";
                    }
                }
            }
            
            return alerta;
        
        
    }
    
}
