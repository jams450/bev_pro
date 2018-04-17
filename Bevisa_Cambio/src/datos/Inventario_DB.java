/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import negocio.Inventario;
import negocio.Producto;

/**
 *
 * @author JAMS
 */
public class Inventario_DB {
    
    private java.sql.Connection userConn;
    
    private final String SELECT ="select inventario.id, productos.clave, productos.nombre, inventario.fechaentrada, inventario.fechacaducidad, inventario.cantidad, inventario.cantidadactual,\n" +
                                "inventario.lote, inventario.idopp, inventario.facturano from inventario join productos \n" +
                                "on productos.id=inventario.idproducto where inventario.cantidadactual>0;";
    
    private final String UPDATE="Update inventario set cantidadactual = ? , lote = ? ,   facturano = ? , fechaentrada = ? , fechacaducidad = ? where id = ? ";
  
    
    public Inventario_DB(Connection userConn) {
        this.userConn = userConn;
    }
    
    public List<Inventario> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Inventario inven = null;
        List<Inventario> inventario = new ArrayList<>();
        try {
            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                inven = new Inventario();
                Producto pro = new Producto();
                inven.setId(rs.getInt(1));
                pro.setClave(rs.getString(2));
                pro.setNombre(rs.getString(3));
                inven.setProduc(pro);
                inven.setFecha_entrada(rs.getString(4));
                inven.setFecha_caducidad(rs.getString(5));
                inven.setCantidad(rs.getDouble(6));
                inven.setCantidad_actual(rs.getDouble(7));
                inven.setLote(rs.getString(8));
                inven.setIdopp(rs.getInt(9));
                inven.setFactura(rs.getString(10));
                
                inventario.add(inven);
                    
            }
            return inventario;
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        
    }
   
    public int update(Inventario inven) throws SQLException 
    {
        Connection conn = null;
        PreparedStatement stmt = null;		
        int rows = 0; 
        try {

            conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
            stmt = conn.prepareStatement(this.UPDATE);
            int index = 1;//contador de columnas
            
            stmt.setDouble(index++, inven.getCantidad_actual());
            stmt.setString(index++, inven.getLote());
            stmt.setString(index++, inven.getFactura());
            stmt.setString(index++, inven.getFecha_entrada());
            stmt.setString(index++, inven.getFecha_caducidad());
            stmt.setInt(index++, inven.getId());
            
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

    


