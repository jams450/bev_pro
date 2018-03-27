
package negocio;

public class Inventario {

    private int id;
    private int id_producto;
    private String fecha_entrada;
    private double cantidad_actual; 
    private String lote;
    private int idopp;
    private String factura;
    private double cantidad;
    private double costo;
    private String fecha_caducidad;
    private int idmoneda;
    
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(String fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public double getCantidad_actual() {
        return cantidad_actual;
    }

    public void setCantidad_actual(double cantidad_actual) {
        this.cantidad_actual = cantidad_actual;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public int getIdopp() {
        return idopp;
    }

    public void setIdopp(int idopp) {
        this.idopp = idopp;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getFecha_caducidad() {
        return fecha_caducidad;
    }

    public void setFecha_caducidad(String fecha_caducidad) {
        this.fecha_caducidad = fecha_caducidad;
    }

    public int getIdmoneda() {
        return idmoneda;
    }

    public void setIdmoneda(int idmoneda) {
        this.idmoneda = idmoneda;
    }
            
}
