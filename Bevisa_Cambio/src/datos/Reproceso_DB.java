
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import negocio.*;

public class Reproceso_DB {
    
    private java.sql.Connection userConn;
    
    private final String SELECT_OPF="select ordenes_prod.id, productos.nombre, cantidad,productos.id from ordenes_prod JOIN productos on productos.id=ordenes_prod.idproduc where ordenes_prod.id=? ";
    private final String INSERT_ODF="Insert into ordenes_prod(idopc,idproduc,fecha,cantidad) values (?,?,?,?)";
    private final String SELECT_MAX="select MAX(id) from ordenes_prod";
    private final String SELECT_INVEN="select * from inventario where idproducto = ? and cantidadactual > 0 and fechacaducidad >= ? ";
    private final String INSERT_MPODF="insert into mp_odp (idodp,idinven,cantidad,costo) values (?,?,?,?)";
    private final String UPDATE_INV="update inventario set cantidadactual = ? where id = ? ";
    private final String SELECT_VAL_INV="select sum(inventario.cantidadactual), productos.nombre from inventario join productos on productos.id = inventario.idproducto where fechacaducidad >= ? and idproducto = ?";
 
    private final String INSERT_REPRO="insert into reprocesos (id_lote_ant,id_lote_nuevo,fecha,observaciones) values (?,?,?,?)";
    private final String SELECT_MAX_RE="select MAX(id) from reprocesos";
    
    public Reproceso_DB(Connection userConn) {
        this.userConn = userConn;
    }
    
    public String[] select_odf(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String[] datos=null;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SELECT_OPF);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                datos=new String[4];
                //id
                datos[0]=rs.getString(1);
                //nombre
                datos[1]=rs.getString(2);
                //cantidad
                datos[2]=rs.getString(3);
                //idproduc
                datos[3]=rs.getString(4);
            }
            return datos;
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        
    }
    
    public Producto select_inventario(String date, int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Producto pro = null;
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SELECT_VAL_INV);
            stmt.setString(1, date);
            stmt.setInt(2, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                pro = new Producto();
                pro.setStockmin(rs.getDouble(1));
                pro.setNombre(rs.getString(2));
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
    
    public int insert_opf(Orden_de_Fabricacion odf,Reproceso re) throws SQLException 
    {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;		
        try {

            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(this.INSERT_ODF);
            int index = 1;//contador de columnas
            stmt.setInt(index++,0);
            stmt.setInt(index++, odf.getIdproducto());
            stmt.setString(index++, odf.getFecha());
            stmt.setDouble(index++, odf.getCantidad());
            stmt.executeUpdate();
            
            stmt = conn.prepareStatement(this.SELECT_MAX);
            rs=stmt.executeQuery();
            rs.first();
            int idmax = rs.getInt(1);
            
            for (int i = 0; i < odf.getProduc().size(); i++) {
                
                stmt = conn.prepareStatement(this.SELECT_INVEN);
                stmt.setInt(1,odf.getProduc().get(i).getId());
                stmt.setString(2, odf.getFecha());
                rs=stmt.executeQuery();
                double cantidad2=odf.getProduc().get(i).getStockmin();
                while(rs.next())
                {
                    if (cantidad2!=0) {
                        double aux =rs.getDouble(4) - cantidad2;
                        if (aux >= 0) {
                            stmt = conn.prepareStatement(this.INSERT_MPODF);
                            stmt.setInt(1, idmax);
                            stmt.setInt(2, rs.getInt(1));
                            stmt.setDouble(3, cantidad2);
                            stmt.setDouble(4, rs.getDouble(9)/rs.getDouble(8)*cantidad2);
                            stmt.executeUpdate();
                            
                            stmt = conn.prepareStatement(this.UPDATE_INV);
                            stmt.setDouble(1, aux);
                            stmt.setInt(2, rs.getInt(1));
                            stmt.executeUpdate();

                            cantidad2=0;
                        }
                        else
                        {
                            stmt = conn.prepareStatement(this.INSERT_MPODF);
                            stmt.setInt(1, idmax);
                            stmt.setInt(2, rs.getInt(1));
                            stmt.setDouble(3, rs.getDouble(4));
                            stmt.setDouble(4, rs.getDouble(9)/rs.getDouble(8)*cantidad2);
                            stmt.executeUpdate();
                            
                            stmt = conn.prepareStatement(this.UPDATE_INV);
                            stmt.setDouble(1, 0);
                            stmt.setInt(2, rs.getInt(1));
                            stmt.executeUpdate();
                            
                            cantidad2=cantidad2-rs.getDouble(4);
                        }
                    }
                }
            }
            re.setId_lotenuevo(idmax);
            stmt = conn.prepareStatement(this.INSERT_REPRO);
            stmt.setInt(1, re.getId_loteant());
            stmt.setInt(2, re.getId_lotenuevo());
            stmt.setString(3, re.getId_fecha());
            stmt.setString(4, re.getObservaciones());
            stmt.executeUpdate();
            
            stmt = conn.prepareStatement(this.SELECT_MAX);
            rs=stmt.executeQuery();
            rs.first();
            int idre=rs.getInt(1);
            return idre;
        } finally {
            Conexion.close(stmt);
            //Unicamente cerramos la conexión si fue creada en este metodo
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
    }
}
