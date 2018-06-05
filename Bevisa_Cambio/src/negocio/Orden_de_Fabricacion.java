

package negocio;

import java.util.ArrayList;
import java.util.List;

public class Orden_de_Fabricacion {
    private int id;
    private int idproducto;
    private String fecha;
    private double cantidad;
    
    private List<Producto> produc;

    public Orden_de_Fabricacion() {
        this.produc = new ArrayList<>();
    }

    public List<Producto> getProduc() {
        return produc;
    }

    public void setProduc(List<Producto> produc) {
        this.produc = produc;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
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
    
    
}
