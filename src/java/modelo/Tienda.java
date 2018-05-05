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
public class Tienda {

    private int id;
    private String nombre;
    private int empleados;
    private Localizacion localizacion;

    public Tienda() {
    }

    public Tienda(int id, String nombre, int empleados, Localizacion localizacion) {
        this.id = id;
        this.nombre = nombre;
        this.empleados = empleados;
        this.localizacion = localizacion;
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

    public int getEmpleados() {
        return empleados;
    }

    public void setEmpleados(int empleados) {
        this.empleados = empleados;
    }

    public Localizacion getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(Localizacion localizacion) {
        this.localizacion = localizacion;
    }

    @Override
    public String toString() {
        return "Tienda{" + "id=" + id + ", nombre=" + nombre + ", empleados=" + empleados + ", localizacion=" + localizacion + '}';
    }

}
