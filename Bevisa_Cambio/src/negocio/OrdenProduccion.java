
package negocio;

import java.util.List;

public class OrdenProduccion {
    private int id;
    private int idopc;
    private int idprofuc;
    private String fecha;
    private double cantidad;
    private String estatus;
    private String observaciones;
    
    private List<Inventario> productos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdopc() {
        return idopc;
    }

    public void setIdopc(int idopc) {
        this.idopc = idopc;
    }

    public int getIdprofuc() {
        return idprofuc;
    }

    public void setIdprofuc(int idprofuc) {
        this.idprofuc = idprofuc;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<Inventario> getProductos() {
        return productos;
    }

    public void setProductos(List<Inventario> productos) {
        this.productos = productos;
    }

    

    
            
}
