/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author JAMS
 */
public class Usuario {

    private int id;
    private String user;
    private String pass;
    private String nombre;
    private int perfil;

    public Usuario(int id, String user, String pass, String nombre, int perfil) {
        this.id = id;
        this.user = user;
        this.pass = pass;
        this.nombre = nombre;
        this.perfil = perfil;
    }
    
    public Usuario() {
        this.id = id;
        this.user = user;
        this.pass = pass;
        this.nombre = nombre;
        this.perfil = perfil;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPerfil() {
        return perfil;
    }

    public void setPerfil(int perfil) {
        this.perfil = perfil;
    }
    
    
    
    
}
