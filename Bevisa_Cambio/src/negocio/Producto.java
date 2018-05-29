
package negocio;

/**
 *
 * @author JAMS
 */
public class Producto {

    

    private int id;
    private String clave;
    private String nombre;
    private String categoria;
    private String medida;
    private double pventa;
    private double stockmin;
    private int iva;
    private String proceso;
    private int meses_caducidad;
    private double peso;
    private String moneda;

    public Producto() {
        this.nombre = "";
        this.clave = "";
        this.categoria = "1";
        this.medida = "1";
        this.pventa = 0.0;
        this.stockmin = 0;
        this.iva = 0;
        this.proceso = "1";
        this.meses_caducidad = 1;
        this.peso=0.0;
        this.moneda="1";
    }
    
    public Producto(int id, String clave,String nombre, String categoria, String medida, double pventa, double stockmin, int iva, String proceso, int meses_caducidad, double peso, String moneda) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
        this.categoria = categoria;
        this.medida = medida;
        this.pventa = pventa;
        this.stockmin = stockmin;
        this.iva = iva;
        this.proceso = proceso;
        this.meses_caducidad = meses_caducidad;
        this.peso=peso;
        this.moneda=moneda;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public void setMeses_caducidad(int meses_caducidad) {
        this.meses_caducidad = meses_caducidad;
    }

    public String getProceso() {
        return proceso;
    }

    public int getMeses_caducidad() {
        return meses_caducidad;
    }
    
   
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setPventa(double pventa) {
        this.pventa = pventa;
    }

    public void setStockmin(double stockmin) {
        this.stockmin = stockmin;
    }



    public void setIva(int iva) {
        this.iva = iva;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getMedida() {
        return medida;
    }

    public String getClave() {
        return clave;
    }


    public double getPventa() {
        return pventa;
    }

    public double getStockmin() {
        return stockmin;
    }


    public int getIva() {
        return iva;
    }
    
    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    
    
}
