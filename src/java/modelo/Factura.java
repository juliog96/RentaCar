/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author julio
 */
public class Factura {

    private int id;
    private String fecha;
    private String entrega;
    private String recogida;
    private int precio;
    private Coche coche;
    private Usuario usuario;
    private Tienda tienda;

    public Factura() {
    }

    public Factura(String fecha, String entrega, String recogida, int precio, Coche coche, Usuario usuario, Tienda tienda) {
        this.fecha = fecha;
        this.entrega = entrega;
        this.recogida = recogida;
        this.precio = precio;
        this.coche = coche;
        this.usuario = usuario;
        this.tienda = tienda;
    }

    public Factura(int id, String fecha, String entrega, String recogida, int precio, Coche coche, Usuario usuario, Tienda tienda) {
        this.id = id;
        this.fecha = fecha;
        this.entrega = entrega;
        this.recogida = recogida;
        this.precio = precio;
        this.coche = coche;
        this.usuario = usuario;
        this.tienda = tienda;
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

    public String getEntrega() {
        return entrega;
    }

    public void setEntrega(String entrega) {
        this.entrega = entrega;
    }

    public String getRecogida() {
        return recogida;
    }

    public void setRecogida(String recogida) {
        this.recogida = recogida;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Coche getCoche() {
        return coche;
    }

    public void setCoche(Coche coche) {
        this.coche = coche;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

}
