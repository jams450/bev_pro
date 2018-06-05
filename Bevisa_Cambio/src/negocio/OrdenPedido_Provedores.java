
package negocio;

import java.util.ArrayList;
import java.util.List;

public class OrdenPedido_Provedores {
    private int id;
    private int idproveedor;
    private int idmoneda;
    private double subtotal;
    private double iva;
    private double ptotal;
    private int idestatus;
    private String fecha;
    private int idcpago;
    private String observaciones;
    
    private List<Producto> productos;

    public OrdenPedido_Provedores() {
        this.productos = new ArrayList<>();
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(int idproveedor) {
        this.idproveedor = idproveedor;
    }

    public int getIdmoneda() {
        return idmoneda;
    }

    public void setIdmoneda(int idmoneda) {
        this.idmoneda = idmoneda;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getPtotal() {
        return ptotal;
    }

    public void setPtotal(double ptotal) {
        this.ptotal = ptotal;
    }

    public int getIdestatus() {
        return idestatus;
    }

    public void setIdestatus(int idestatus) {
        this.idestatus = idestatus;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdcpago() {
        return idcpago;
    }

    public void setIdcpago(int idcpago) {
        this.idcpago = idcpago;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    
            
}
