
package negocio;


public class Ingrediente extends Producto{

    private int id_profinal;
    private Double cantidad;
    
    public int getId_profinal() {
        return id_profinal;
    }

    public void setId_profinal(int id_profinal) {
        this.id_profinal = id_profinal;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
