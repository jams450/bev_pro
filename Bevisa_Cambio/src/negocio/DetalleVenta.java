
package negocio;

public class DetalleVenta {
    private int idventa;
    private int idinven;
    private int idproducto;
    private Double pventa;
    private Double cantidad;
    private Double importe;
    private Double descuento;
    private Double idescuento;
    private Double iva;
    private Double total;
    private Double comision;

    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public int getIdinven() {
        return idinven;
    }

    public void setIdinven(int idinven) {
        this.idinven = idinven;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public Double getPventa() {
        return pventa;
    }

    public void setPventa(Double pventa) {
        this.pventa = pventa;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public Double getIdescuento() {
        return idescuento;
    }

    public void setIdescuento(Double idescuento) {
        this.idescuento = idescuento;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getComision() {
        return comision;
    }

    public void setComision(Double comision) {
        this.comision = comision;
    }
    
    
}
