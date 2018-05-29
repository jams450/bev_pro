
package negocio;

import java.util.ArrayList;

public class Ventas {
    private int id;
    private String fecha;
    private String idvendedor;
    private String idcliente;
    private Double importe;
    private Double iva;
    private Double total;
    private String tipo;
    private String op;
    
    private ArrayList<DetalleVenta> detvta;

    public Ventas() {
        this.detvta = new ArrayList();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getIdvendedor() {
        return idvendedor;
    }

    public void setIdvendedor(String idvendedor) {
        this.idvendedor = idvendedor;
    }

    public String getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(String idcliente) {
        this.idcliente = idcliente;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public ArrayList<DetalleVenta> getDetalle() {
        return detvta;
    }

    public void setDetalle(ArrayList<DetalleVenta> detvta) {
        this.detvta = detvta;
    }
    
}
