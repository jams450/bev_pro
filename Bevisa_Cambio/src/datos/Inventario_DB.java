/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

/**
 *
 * @author JAMS
 */
public class Inventario_DB {
    
    private java.sql.Connection userConn;
    
    private final String SELECT ="select inventario.id, productos.clave, productos.nombre, inventario.fechaentrada, inventario.fechacaducidad, inventario.cantidad, inventario.cantidadactual,\n" +
                                "inventario.lote, inventario.idopp, inventario.facturano from inventario join productos \n" +
                                "on productos.id=inventario.idproducto where inventario.cantidadactual>0;";
    
    
}
