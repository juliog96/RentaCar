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
public class RankingNumFacturas {

    private int id;
    private String tienda;
    private int facturas;

    public RankingNumFacturas() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    public int getFacturas() {
        return facturas;
    }

    public void setFacturas(int facturas) {
        this.facturas = facturas;
    }

}
