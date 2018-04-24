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
    private Date fecha;
    private Date entrega;
    private Date recogida;
    private Coche coche;
    private Usuario usuario;
    private Tienda tienda;

    public Factura(Date fecha, Date entrega, Date recogida, Coche coche, Usuario usuario, Tienda tienda) {
        this.fecha = fecha;
        this.entrega = entrega;
        this.recogida = recogida;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getEntrega() {
        return entrega;
    }

    public void setEntrega(Date entrega) {
        this.entrega = entrega;
    }

    public Date getRecogida() {
        return recogida;
    }

    public void setRecogida(Date recogida) {
        this.recogida = recogida;
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

    @Override
    public String toString() {
        return "Factura{" + "id=" + id + ", fecha=" + fecha + ", entrega=" + entrega + ", recogida=" + recogida + ", coche=" + coche + ", usuario=" + usuario + ", tienda=" + tienda + '}';
    }

}
