
package negocio;

public class Proveedores {
    
    private int id;
    private String nombre;
    private String telefono;
    private String correo;
    private String cp;
    private String colonia;
    private String calle;
    private String noint;
    private String noext;
    private String rfc;
    private String idmpago;
    private String cuenta;
    private String contacto;
    private String sistema_calidad;
    
    public Proveedores() {
        this.id = 1;
        this.nombre = "";
        this.telefono = "";
        this.correo = "";
        this.cp = "";
        this.colonia = "";
        this.calle = "";
        this.noint = "";
        this.noext = "";
        this.rfc = "";
        this.idmpago = "";
        this.cuenta = "";
        this.contacto = "";
        this.sistema_calidad = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNoint() {
        return noint;
    }

    public void setNoint(String noint) {
        this.noint = noint;
    }

    public String getNoext() {
        return noext;
    }

    public void setNoext(String noext) {
        this.noext = noext;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getIdmpago() {
        return idmpago;
    }

    public void setIdmpago(String idmpago) {
        this.idmpago = idmpago;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getSistema_calidad() {
        return sistema_calidad;
    }

    public void setSistema_calidad(String sistema_calidad) {
        this.sistema_calidad = sistema_calidad;
    }
    
 }
