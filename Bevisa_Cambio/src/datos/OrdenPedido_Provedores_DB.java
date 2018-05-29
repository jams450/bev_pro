
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import negocio.*;

public class OrdenPedido_Provedores_DB {
    
    private java.sql.Connection userConn;
    
    private final String SELECT_PROVEDOR="select proveedores.id, proveedores.nombre, proveedores.telefono, proveedores.correo  from proveedores";
    private final String SELECT_MAXOPP="Select MAX(id) from opproveedores";
    private final String SELECT_PRODUCTOS="select productos.id , productos.Nombre, productos.Clave, umedida.nombre,productos.pventa,productos.iva,monedas.nombre "
                    + "from productos join umedida on productos.idmedida= umedida.id join monedas on monedas.id=productos.idmoneda  where idcategoria = 1 or idcategoria = 3 ";
    
    private final String SELECT_MONEDAS="select * from monedas;";
    
    private final String INSERT_OPP="Insert into opproveedores(idpro,idmoneda,subtotal,iva,ptotal,idestatus,fecha,idcpago,observaciones) values (?,?,?,?,?,?,?,?,?)";
    private final String INSERT_DET_OPP="Insert into pedidos_opp(idopp,idpro,punitario,ptotal,cantidad) values (?,?,?,?,?)";
    private final String PAGO="SELECT nombre FROM condicion_pago ";
    
    private final String SELECT_DET_OPP="select pedidos_opp.idpro, productos.nombre, productos.clave, pedidos_opp.cantidad, pedidos_opp.punitario from pedidos_opp\n" +
            " join productos on productos.id = pedidos_opp.idpro where pedidos_opp.idopp = ?";
    
    private final String SELECT_OPP="select opproveedores.id ,  proveedores.nombre from opproveedores\n" +
                    " join proveedores on proveedores.id = opproveedores.idpro where idestatus = 1";
    
    private final String SELECT_MONEDA_OPP="select idmoneda from opproveedores where id = ?";
    
    private final String DELETE_OPP="update opproveedores set idestatus = 3 where id =  ?";
    
    private final String SELECT_PROVE_OPP="select proveedores.id  ,  proveedores.nombre , opproveedores.fecha from opproveedores\n" +
            " join proveedores on proveedores.id = opproveedores.idpro where opproveedores.id = ?";
        
    public OrdenPedido_Provedores_DB(Connection userConn) {
        this.userConn = userConn;
    }
    
    public List<Proveedores> select_proveedores() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Proveedores pro = null;
        List<Proveedores> Proveedores = new ArrayList<>();
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SELECT_PROVEDOR);
            rs = stmt.executeQuery();
            while (rs.next()) {
                pro = new Proveedores();

                pro.setId(rs.getInt(1));
                pro.setNombre(rs.getString(2));
                pro.setTelefono(rs.getString(3));
                pro.setCorreo(rs.getString(4));
                
                Proveedores.add(pro);
            }
            return Proveedores;
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        
    }
    
    public int select_max() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int max=0;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SELECT_MAXOPP);
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
    
    public int select_idmoneda_opp(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int max=0;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SELECT_MONEDA_OPP);
            stmt.setString(1, id);
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
    
    public List<Producto> select_detopp(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Producto pro = null;
        List<Producto> Productos = new ArrayList<>();
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SELECT_DET_OPP);
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                pro = new Producto();

                pro.setId(rs.getInt(1));
                pro.setNombre(rs.getString(2));
                pro.setClave(rs.getString(3));
                //PESO -> Cantidad
                pro.setPeso(rs.getDouble(4));
                pro.setPventa(rs.getDouble(5));

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
    
    public List<String> select_monedas() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> monedas = new ArrayList<>();
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SELECT_MONEDAS);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String mon=rs.getString(2);
                
                
                monedas.add(mon);
            }
            return monedas;
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        
    }
    
    public List<String> select_pago() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> pago = new ArrayList<>();
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(PAGO);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String mon=rs.getString(1);
                pago.add(mon);
            }
            return pago;
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        
    }
    
    public List<String[]> select_opp() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String[]> opp = new ArrayList<>();
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SELECT_OPP);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String[] op= new String[2];
                op[0]=rs.getString(1);
                op[1]=rs.getString(2);
                opp.add(op);
            }
            return opp;
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        
    }
    
    public int insert_opp(OrdenPedido_Provedores opp) throws SQLException 
    {
        Connection conn = null;
        PreparedStatement stmt = null;		
        int rows = 0; 
        try {

            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(this.INSERT_OPP);
            int index = 1;//contador de columnas
            
            stmt.setInt(index++, opp.getIdproveedor());
            stmt.setInt(index++, opp.getIdmoneda());
            stmt.setDouble(index++, opp.getSubtotal());
            stmt.setDouble(index++, opp.getIva());
            stmt.setDouble(index++, opp.getPtotal());
            stmt.setInt(index++, opp.getIdestatus());
            stmt.setString(index++, opp.getFecha());
            stmt.setInt(index++, opp.getIdcpago());
            stmt.setString(index++, opp.getObservaciones());
            rows = stmt.executeUpdate();
            
            int idmax = this.select_max();
            
            for (int i = 0; i < opp.getProductos().size(); i++) {
                
                stmt = conn.prepareStatement(this.INSERT_DET_OPP);
                index = 1;//contador de columnas
                
                stmt.setInt(index++, idmax);
                stmt.setInt(index++, opp.getProductos().get(i).getId());
                stmt.setDouble(index++, opp.getProductos().get(i).getPventa());
                stmt.setDouble(index++, opp.getProductos().get(i).getPeso());
                stmt.setDouble(index++, opp.getProductos().get(i).getStockmin());
 
                rows = stmt.executeUpdate();
            }
            
        } finally {
            Conexion.close(stmt);
            //Unicamente cerramos la conexión si fue creada en este metodo
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        return rows;
    }
    public int delete_opp(String id) throws SQLException 
    {
        Connection conn = null;
        PreparedStatement stmt = null;		
        int rows = 0; 
        try {

            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(DELETE_OPP);
            int index = 1;//contador de columnas
            
            stmt.setString(index++, id);
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
    public Proveedores select_provedor(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Proveedores pro = null;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SELECT_PROVE_OPP);
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                pro = new Proveedores();

                pro.setId(rs.getInt(1));
                pro.setNombre(rs.getString(2));
                pro.setCalle(rs.getString(3));
            }
            return pro;
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        
    }
    
    
    
}
