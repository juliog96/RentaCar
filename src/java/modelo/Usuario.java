/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author julio
 */
public class Usuario {

    private int id;
    private String dni;
    private String username;
    private String password;
    private String nombre;
    private String apellido;
    private String cp;
    private String telefono;
    private String tipo;

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public Usuario(String dni, String username, String password, String nombre, String apellido, String cp, String telefono, String tipo) {
        this.dni = dni;
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cp = cp;
        this.telefono = telefono;
        this.tipo = tipo;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", dni=" + dni + ", username=" + username + ", password=" + password + ", nombre=" + nombre + ", apellido=" + apellido + ", cp=" + cp + ", telefono=" + telefono + ", tipo=" + tipo + '}';
    }

}
