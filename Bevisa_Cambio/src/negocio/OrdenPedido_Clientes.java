
package negocio;

import java.util.List;

public class OrdenPedido_Clientes {
    private int id;
    private int idcliente;
    private double ptotal;
    private String fecha;

    private List<Producto> productos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public double getPtotal() {
        return ptotal;
    }

    public void setPtotal(Double ptotal) {
        this.ptotal = ptotal;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }


    
            
}
