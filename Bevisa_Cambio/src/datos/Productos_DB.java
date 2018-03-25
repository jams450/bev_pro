
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import negocio.Producto;


public class Productos_DB {

    private java.sql.Connection userConn;
    
    private final String SELECT
            = "select productos.id ,productos.clave, productos.nombre, categoria.nombre, umedida.nombre, productos.pventa, "
            + "productos.smin,  productos.iva, procesos.detalles, productos.mesescaducidad,productos.peso "
            + "from productos join categoria on productos.idcategoria = categoria.id "
            + "join umedida on productos.idmedida= umedida.id join procesos on productos.idproceso = procesos.id;";
    
    private final String INSERT =
            "Insert into productos (clave,nombre,idcategoria,idmedida,pventa, smin,iva,idproceso,peso,mesescaducidad) values (?,?,?,?,?,?,?,?,?,?,?)";
    
    private final String UPDATE=
            "Update productos set  clave = ?,nombre = ?,idcategoria = ?,idmedida = ?,pventa = ?, smin = ?,iva = ?,idproceso = ?,"
            + "peso = ?,mesescaducidad = ? where id = ?";
    
    private final String INSERT_EM =
            "Insert into productos (clave,nombre,idcategoria,idmedida, smin) values (?,?,?,?,?)";
    
    private final String UPDATE_EM=
            "Update productos set  clave = ? ,nombre = ? , idmedida = ? ,smin = ? where id = ?";
    
    private final String INSERT_PT =
            "Insert into productos (clave,nombre,idcategoria,idmedida,pventa,iva,idproceso,mesescaducidad) values (?,?,?,?,?,?,?,?)";
    
    private final String UPDATE_PT=
            "Update productos set nombre = ? , clave = ? ,  idmedida = ? ,pventa = ?, iva=?, mesescaducidad = ? where id = ?"; 

    private final String INSERT_GA =
            "Insert into productos (clave,nombre,idcategoria,idmedida,pventa,mesescaducidad,idproceso, smin,iva) values (?,?,?,?,?,?,?,?,?)";
    
    private final String UPDATE_GA =
            "Update productos set nombre = ? , clave = ? ,   idcategoria = ? ,  idmedida = ?, smin = ? ,pventa = ?, iva=?, mesescaducidad=? where id = ? ";
    
    
    
    public Productos_DB(Connection userConn) {
        this.userConn = userConn;
    }
    
    public List<Producto> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Producto producto = null;
        List<Producto> productos = new ArrayList<>();
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id= rs.getInt(1);
                String clave = rs.getString(2);
                String nombre = rs.getString(3);
                String categoria = rs.getString(4);
                String medida = rs.getString(5);
                double pventa =rs.getDouble(6);
                double stockmin =rs.getDouble(7);
                int iva= rs.getInt(8);
                String proceso = rs.getString(9);
                int meses_caducidad=rs.getInt(10);
                int peso=rs.getInt(11);
                producto = new Producto(id,clave,nombre,categoria,medida,pventa,stockmin,iva,proceso,meses_caducidad,peso);
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
    
    
    public int insert(Producto pro,int medida,int cate,int proceso) throws SQLException 
    {
        Connection conn = null;
        PreparedStatement stmt = null;		
        int rows = 0; 
        try {

            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(this.INSERT);
            int index = 1;//contador de columnas
            
            stmt.setString(index++, pro.getClave());
            stmt.setString(index++, pro.getNombre());
            stmt.setInt(index++, cate);
            stmt.setInt(index++, medida);
            stmt.setDouble(index++, pro.getPventa());
            stmt.setDouble(index++, pro.getStockmin());
            stmt.setInt(index++, pro.getIva());
            stmt.setInt(index++, proceso);
            stmt.setDouble(index++, pro.getPeso());
            stmt.setDouble(index++, pro.getStockmin());
            stmt.setInt(index++, pro.getMeses_caducidad());
            
            

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
    
    public int update(Producto pro,int medida,int cate,int proceso) throws SQLException 
    {
        Connection conn = null;
        PreparedStatement stmt = null;		
        int rows = 0; 
        try {

            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(this.UPDATE);
            int index = 1;//contador de columnas
            
            stmt.setString(index++, pro.getClave());
            stmt.setString(index++, pro.getNombre());
            stmt.setInt(index++, cate);
            stmt.setInt(index++, medida);
            stmt.setDouble(index++, pro.getPventa());
            stmt.setDouble(index++, pro.getStockmin());
            stmt.setInt(index++, pro.getIva());
            stmt.setInt(index++, proceso);
            stmt.setDouble(index++, pro.getPeso());
            stmt.setDouble(index++, pro.getStockmin());
            stmt.setInt(index++, pro.getMeses_caducidad());
            stmt.setInt(index++, pro.getId());

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

    public int insert_empaque(Producto pro,int medida,int cate) throws SQLException 
    {
        Connection conn = null;
        PreparedStatement stmt = null;		
        int rows = 0; 
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(this.INSERT_EM);
            int index = 1;//contador de columnas
            
            stmt.setString(index++, pro.getClave());
            stmt.setString(index++, pro.getNombre());
            stmt.setInt(index++, cate);
            stmt.setInt(index++, medida);
            stmt.setDouble(index++, pro.getStockmin());

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
    
    public int update_empaque(Producto pro,int medida) throws SQLException 
    {
        Connection conn = null;
        PreparedStatement stmt = null;		
        int rows = 0; 
        try {

            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(this.UPDATE_EM);
            int index = 1;//contador de columnas
            
            stmt.setString(index++, pro.getClave());
            stmt.setString(index++, pro.getNombre());
            stmt.setInt(index++, medida);
            stmt.setDouble(index++, pro.getStockmin());

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
    
    public int insert_pt(Producto pro,int medida,int cate, int proceso) throws SQLException 
    {
        Connection conn = null;
        PreparedStatement stmt = null;		
        int rows = 0; 
        try {          
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(this.INSERT_PT);
            int index = 1;//contador de columnas
            
            stmt.setString(index++, pro.getClave());
            stmt.setString(index++, pro.getNombre());
            stmt.setInt(index++, cate);
            stmt.setInt(index++, medida);
            stmt.setDouble(index++,pro.getPventa());
            stmt.setInt(index++,pro.getIva());
            stmt.setInt(index++,proceso);
            stmt.setDouble(index++,pro.getMeses_caducidad());

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
    
    public int update_pt(Producto pro,int medida,int cate, int proceso) throws SQLException 
    {
        Connection conn = null;
        PreparedStatement stmt = null;		
        int rows = 0; 
        try {          
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(this.UPDATE_PT);
            int index = 1;//contador de columnas
            
            stmt.setString(index++, pro.getClave());
            stmt.setString(index++, pro.getNombre());
            stmt.setInt(index++, medida);
            stmt.setDouble(index++,pro.getPventa());
            stmt.setInt(index++,pro.getIva());
            stmt.setInt(index++,proceso);
            stmt.setDouble(index++,pro.getMeses_caducidad());
            stmt.setInt(index++,pro.getId());
            
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

    public int insert_galeria(Producto pro,int medida,int cate, int proceso) throws SQLException 
    {
        Connection conn = null;
        PreparedStatement stmt = null;		
        int rows = 0; 
        try {          
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(this.INSERT_GA);
            int index = 1;//contador de columnas
            
            stmt.setString(index++, pro.getClave());
            stmt.setString(index++, pro.getNombre());
            stmt.setInt(index++, cate);
            stmt.setInt(index++, medida);
            stmt.setDouble(index++,pro.getPventa());
            stmt.setDouble(index++,pro.getMeses_caducidad());
            stmt.setInt(index++,proceso);
            stmt.setDouble(index++,pro.getMeses_caducidad());
            stmt.setInt(index++,pro.getIva());
            
            

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
    
    public int update_galeria(Producto pro,int medida,int cate, int proceso) throws SQLException 
    {
        Connection conn = null;
        PreparedStatement stmt = null;		
        int rows = 0; 
        try {          
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(this.UPDATE_GA);
            int index = 1;//contador de columnas
            
            stmt.setString(index++, pro.getClave());
            stmt.setString(index++, pro.getNombre());
            stmt.setInt(index++, cate);
            stmt.setInt(index++, medida);
            stmt.setDouble(index++,pro.getPventa());
            stmt.setDouble(index++,pro.getMeses_caducidad());
            stmt.setInt(index++,proceso);
            stmt.setDouble(index++,pro.getMeses_caducidad());
            stmt.setInt(index++,pro.getIva());
            stmt.setInt(index++, pro.getId());
            

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
